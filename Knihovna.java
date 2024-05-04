package projekt_1;
import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import Kniha.Knihy;
import Kniha.Roman;
import Kniha.Ucebnice;

public class Knihovna {
    private ArrayList<Knihy> knihy = new ArrayList<>();

    public boolean pridatKnihu(Knihy kniha) {
        // Check if a book with the same title already exists
        for (Knihy existingBook : knihy) {
            if (existingBook.getTitul().equalsIgnoreCase(kniha.getTitul())) {
                System.out.println("\nKniha s titulem '" + kniha.getTitul() + "' jiz existuje. Nelze pridat duplikat.- \n");
                return false; // Return false to indicate that the book was not added
            }
        }
        // If the loop completes without finding a duplicate, add the book
        knihy.add(kniha);
        // Return true to indicate that the book was successfully added
        return true;
    }
    


    public void upravitKnihu(String title, Knihy novaKniha) {
        for (int i = 0; i < knihy.size(); i++) {
            if (knihy.get(i).getTitul().equalsIgnoreCase(title)) {
                knihy.set(i, novaKniha);
                return;
            }
        }
        System.out.println("Kniha nenalezena.");
    }

    public void odstranitKnihu(String titul) {
        for (int i = 0; i < knihy.size(); i++) {
            if (knihy.get(i).getTitul().equalsIgnoreCase(titul)) {
                knihy.remove(i);
                System.out.println("Kniha uspesne odstranena.");
                return;
            }
        }
        System.out.println("Kniha nenalezena.");
    }

    public void markasVypujcena(String titul) {
        for (Knihy kniha : knihy) {
            if (kniha.getTitul().equalsIgnoreCase(titul)) {
                kniha.setVypujcena(true);
                System.out.println("Kniha oznacena jako vypujcena.");
                return;
            }
        }
        System.out.println("Kniha nenalezena.");
    }

    public void markasDostupna(String title) {
        for (Knihy kniha : knihy) {
            if (kniha.getTitul().equalsIgnoreCase(title)) {
                kniha.setVypujcena(false);
                System.out.println("Kniha oznacena jako dostupna.");
                return;
            }
        }
        System.out.println("Kniha nenalezena.");
    }

    public void vypisKnih() {
        // Sort books by publish date
        Collections.sort(knihy, Comparator.comparing(Knihy::getdatumVydani));

        // Print book information
        System.out.println("Vsechny knihy v chronologickem poradi:");
        for (Knihy kniha : knihy) {
            System.out.println("Titul: " + kniha.getTitul());
            System.out.println("Autor/Autori: " + String.join(", ", kniha.getAutor()));
            System.out.println("Zanr: " + kniha.getZanr());
            if (kniha instanceof Roman) {
                System.out.println("Typ: " + ((Roman) kniha).getTyp());
            } else if (kniha instanceof Ucebnice) {
                System.out.println("Typ: " + ((Ucebnice) kniha).getType());
            }
            System.out.println("Datum Vydani: " + kniha.getdatumVydani());
            System.out.println("Je vypujcena: " + (kniha.isVypujcena() ? "Yes" : "No"));
            System.out.println();
        }
    }

    public void vypisDostupnychknih() {
        System.out.println("Dostupne knihy:");
        for (Knihy kniha : knihy) {
            if (!kniha.isVypujcena()) {
                System.out.println(kniha);
            }
        }
    }

    public void vypisPujcenychknih() {
        System.out.println("Vypujcene knihy:");
        for (Knihy kniha : knihy) {
            if (kniha.isVypujcena()) {
                System.out.println(kniha);
            }
        }
    }

    public Knihy[] getKnihy() {
        return knihy.toArray(new Knihy[0]);
    }
    
    public static void ulozKnihu(String souborNazev, String titul, String[] autori, int datumVydani, String genre, boolean isVypujcena) {
        try (FileWriter writer = new FileWriter(souborNazev)) {
        	String formattedString = String.format("%s,%s,%d,%s,%b", titul, String.join(",", autori), datumVydani, genre, isVypujcena);
        	writer.write(formattedString);
        } catch (IOException e) {
            System.out.println("Chyba při ukládání do souboru: " + e.getMessage());
        }
    }
    
    public static String nactiInformaceZeSouboru(String souborNazev) throws IOException {
       StringBuilder informace = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(souborNazev))) {
            String line;
            while ((line = reader.readLine()) != null) {
                informace.append(line).append("\n");
            }
        }
        return informace.toString();
    }

    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}

import java.util.Scanner;

import Kniha.Knihy;
import Kniha.Roman;
import Kniha.Ucebnice;

public class main {
    public static void main(String[] args) {
        Knihovna knihovna = new Knihovna();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Vyberte si z nabidky pomoci cisel moznosti");
            System.out.println("1. Pridat knihu");
            System.out.println("2. Upravit knihu");
            System.out.println("3. Odstranit knihu");
            System.out.println("4. Vyhledat podle nazvu");
            System.out.println("5. Status vypujceni knihy");
            System.out.println("6. Databaze vsech knih");
            System.out.println("7. Databaze knih dle autoru");
            System.out.println("8. Databaze knih dle zanru");
            System.out.println("9. Database vypujcenych knih");
            System.out.println("10. Zavrit aplikaci");

            int cases = scanner.nextInt();
            scanner.nextLine(); 

            switch (cases) {
                case 1:
                    System.out.println("Vlozte informace o knihach:");
                    System.out.print("Titul: ");
                    String titul = scanner.nextLine();
                    System.out.print("Autor: ");
                    String autor = scanner.nextLine();
                    String[] autori = autor.split(",");
                    
                    int datumVydani;
                    while (true) {
                        System.out.print("Rok datumu vydani:");
                        if (scanner.hasNextInt()) {
                            datumVydani = scanner.nextInt();
                            break;
                        } else {
                            System.out.println("Spatny vstup. Zadejte prosim platny rok.");
                            scanner.nextLine(); // Consume invalid input
                        }
                    }
                    scanner.nextLine(); // Consume newline character
                    
                    System.out.print("Zarn (roman/ucebnice): ");
                    String genre = scanner.nextLine().toLowerCase();
                    while (!genre.equals("roman") && !genre.equals("ucebnice")) {
                        System.out.println("Spatny zanr. Zadejte prosim 'roman' nebo 'ucebnice':");
                        genre = scanner.nextLine().toLowerCase();
                    }            
                    if (genre.equals("roman")) {
                        boolean jeDostupna = true;
                        Roman novyRoman = new Roman(titul, autori, datumVydani, jeDostupna);
                        if (knihovna.pridatKnihu(novyRoman)) {
                            System.out.println("Kniha uspesne pridana!");
                        }
                    } else {
                        boolean jeDostupna = true;
                        Ucebnice novaUcebnice = new Ucebnice(titul, autori, datumVydani, jeDostupna);
                        if (knihovna.pridatKnihu(novaUcebnice)) {
                            System.out.println("Kniha uspesne pridana!");
                        }
                    }
                    break;

                case 2:
                    System.out.println("Vlozte nazev knihy, kterou chcete upravit:");
                    String editTitle = scanner.nextLine();
                
                    Knihy upravaKnihy = null;
                    for (Knihy book : knihovna.getKnihy()) {
                        if (book.getTitul().equalsIgnoreCase(editTitle)) {
                            upravaKnihy = book;
                            break;
                        }
                    }
                
                    if (upravaKnihy == null) {
                        System.out.println("Error: Nazev knihy '" + editTitle + "' neexistuje.");
                        break;
                    }
                
                    System.out.println("Zadejte nove udaje:");
                
                    System.out.print("Novy titul: ");
                    String novyTitul = scanner.nextLine();
        
                    System.out.print("Novy autor: ");
                    String novyAutor = scanner.nextLine();
                    String[] novyAutori = novyAutor.split(",");
                
                    // Edit publish date
                    System.out.print("Novy rok vydani: ");
                    String noveDatum = scanner.nextLine();
                
                    // Edit borrow status
                    System.out.print("Je kniha dostupna? (Zadejte 'A' pro ANO, 'N' pro NE): ");
                    String jeVypujcenaVstup = scanner.nextLine();
                    boolean jeVypujcena = jeVypujcenaVstup.equalsIgnoreCase("A");
                    upravaKnihy.setVypujcena(jeVypujcena);
                
                    // Set the new details
                    upravaKnihy.setTitul(novyTitul);
                    upravaKnihy.setAutor(novyAutori);
                    upravaKnihy.setdatumVydani(noveDatum);
                
                    System.out.println("Kniha uspesne upravena!");
                    break;
                

                case 3:
                    // Delete a book
                    System.out.println("Zadejte nazev knihy pro odstraneni");
                    String odstranitTitul = scanner.nextLine();
                    boolean odstraneniNalezeno = false;
                    for (Knihy book : knihovna.getKnihy()) {
                        if (book.getTitul().equalsIgnoreCase(odstranitTitul)) {
                            odstraneniNalezeno = true;
                            knihovna.odstranitKnihu(odstranitTitul);
                            break;
                        }
                    }
                    if (!odstraneniNalezeno) {
                        System.out.println("Kniha nenalezena.");
                    }
                    break;

                case 4:
                    // Search book by name
                    System.out.println("Zadejte nazev knihy pro zobrazeni informaci:");
                    String vyhledatTitul = scanner.nextLine();
                    boolean foundVyhledani = false;
                    for (Knihy book : knihovna.getKnihy()) {
                        if (book.getTitul().equalsIgnoreCase(vyhledatTitul)) {
                            foundVyhledani = true;
                            System.out.println("\nKniha nalezena.");
                            System.out.println("Titul: " + book.getTitul());
                            System.out.println("Autori: " + String.join(", ", book.getAutor()));
                            System.out.println("Rok vydani: " + book.getdatumVydani());
                            System.out.println("Zanr: " + book.getZanr());
                            System.out.println("Je kniha vypujcena?: " + (book.isVypujcena() ? "NE" : "ANO") + "\n");
                            break;
                        }
                    }
                    if (!foundVyhledani) {
                        System.out.println("Kniha nenalezena.");
                    }
                    break;
                
                case 5:
                    System.out.println("Zadejte nazev knihy pro upravu dostupnosti");
                    String oznacitTitul = scanner.nextLine();
                    boolean foundOznaceni = false;
                    for (Knihy book : knihovna.getKnihy()) {
                        if (book.getTitul().equalsIgnoreCase(oznacitTitul)) {
                            foundOznaceni = true;
                            System.out.println("Oznacit knihu jako vracenou? ('A' pro vraceni, 'N' pro vypujceni):");
                            String markInput = scanner.nextLine();
                            boolean jeVypujcenad = markInput.equalsIgnoreCase("Y");
                            if (jeVypujcenad) {
                                knihovna.markasVypujcena(oznacitTitul);
                            } else {
                                knihovna.markasDostupna(oznacitTitul);
                            }
                            break;
                        }
                    }
                    if (!foundOznaceni) {
                        System.out.println("Kniha nenalezena.");
                    }
                    break;

                case 6:
                    Knihy[] vsechnyKnihy = knihovna.getKnihy();
                    if (vsechnyKnihy.length == 0) {
                        System.out.println("Databaze je prazdna.");
                    } else {
                        System.out.println("Databaze vsech knih:");
                        for (Knihy kniha : vsechnyKnihy) {
                            System.out.println(kniha.getTitul());
                        }
                    }
                    break;
                
                
                case 7:
                    System.out.println("Vlozte jmeno autora:");
                    String jmenoAutora = scanner.nextLine();
                    boolean foundAutor = false;
                    System.out.println("Knihy od " + jmenoAutora + ":");
                    for (Knihy kniha : knihovna.getKnihy()) {
                        for (String Autor : kniha.getAutor()) {
                            if (Autor.equalsIgnoreCase(jmenoAutora)) {
                                foundAutor = true;
                                System.out.println(kniha.getTitul());
                                break; // Break the inner loop once a match is found for efficiency
                            }
                        }
                    }
                    if (!foundAutor) {
                        System.out.println("Nenalezeny knihy od " + jmenoAutora);
                    }
                    break;
                
                case 8:
                    System.out.println("Vlozte zanr (roman/ucebnice):");
                    String nazevZanru = scanner.nextLine().toLowerCase();
                    while (!nazevZanru.equals("roman") && !nazevZanru.equals("ucebnice")) {
                        System.out.println("Spatny zanr. Zvolte prosim 'roman' nebo 'ucebnice':");
                        nazevZanru = scanner.nextLine().toLowerCase();
                    }
                    
                    boolean foundZanr = false;
                    System.out.println("Knihy v zanru" + nazevZanru);
                    for (Knihy kniha : knihovna.getKnihy()) {
                        if (kniha.getZanr().equalsIgnoreCase(nazevZanru)) {
                            foundZanr = true;
                            System.out.println(kniha.getTitul());
                        }
                    }
                    if (!foundZanr) {
                        System.out.println("Nebyly nalezeny knihy v zanru " + nazevZanru);
                    }
                    break;
                
                case 9:
                    boolean foundVypujcene = false;
                    System.out.println("Vypujcene knihy:");
                    for (Knihy kniha : knihovna.getKnihy()) {
                        if (kniha.isVypujcena()) {
                            foundVypujcene = true;
                            System.out.println("Titul: " + kniha.getTitul() + ", Zanr: " + kniha.getZanr());
                        }
                    }
                    if (!foundVypujcene) {
                        System.out.println("Zadne vypujcene knihy.");
                    }
                    break;
                

                case 10:
                    System.out.println("Opousteni programu");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Neplatna volba, zvolte prosim z nabizenych.");
            }
        }
    }
}

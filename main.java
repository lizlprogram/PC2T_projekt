import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import Kniha.Knihy;
import Kniha.Roman;
import Kniha.Ucebnice;


public class Main {

	public static void main(String[] args) {
        Knihovna knihovna = new Knihovna();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            try {
            System.out.println("Vyberte si z nabidky pomoci cisel moznosti");
            System.out.println("1. Pridat knihu");
            System.out.println("2. Upravit knihu");
            System.out.println("3. Odstranit knihu");
            System.out.println("4. Vyhledat podle nazvu");
            System.out.println("5. Status vypujceni knihy");
            System.out.println("6. Databaze vsech knih");
            System.out.println("7. Databaze knih dle autoru");
            System.out.println("8. Databaze knih dle zanru");
            System.out.println("9. Databaze vypujcenych knih");
            System.out.println("10. Ulozeni informace o vybrane knizce do souboru .txt");
            System.out.println("11. Nacteni vsech informacii o dane knize ze souboru .txt");
            System.out.println("12. Zavrit aplikaci");

            int vyber = scanner.nextInt();
            scanner.nextLine(); 

            switch (vyber) {
                case 1:
                    System.out.println("\nVlozte informace o knihach:");
                    System.out.print("Titul: ");
                    String titul = scanner.nextLine();
                    System.out.print("Autor: ");
                    String autor = scanner.nextLine();
                    String[] autori = autor.split(",");
                    
                    int datumVydani;
                    while (true) {
                        System.out.print("Rok vydani:");
                        if (scanner.hasNextInt()) {
                            datumVydani = scanner.nextInt();
                            break;
                        } else {
                            System.out.println("Spatny vstup. Zadejte prosim platny rok.");
                            scanner.nextLine(); 
                        }
                    }
                    scanner.nextLine(); 
                    
                    System.out.print("Zanr (roman/ucebnice): ");
                    String genre = scanner.nextLine().toLowerCase();
                    while (!genre.equals("roman") && !genre.equals("ucebnice")) {
                        System.out.println("Spatny zanr. Zadejte prosim 'roman' nebo 'ucebnice':");
                        genre = scanner.nextLine().toLowerCase();
                    }            
                    if (genre.equals("roman")) {
                        boolean jeDostupna = true;
                        Roman novyRoman = new Roman(titul, autori, datumVydani, jeDostupna);
                        if (knihovna.pridatKnihu(novyRoman)) {
                            System.out.println("\nKniha uspesne pridana!\n");
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
                    for (Knihy knizky : knihovna.getKnihy()) {
                        if (knizky.getTitul().equalsIgnoreCase(editTitle)) {
                            upravaKnihy = knizky;
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
                        upravaKnihy.setTitul(novyTitul);
        
                    System.out.print("Novy autor: ");
                    String novyAutor = scanner.nextLine();
                    String[] novyAutori = novyAutor.split(",");
                
              
                  while (true) {
                        System.out.print("Novy rok vydani: ");
                        String noveDatumStr = scanner.nextLine();
                  
                      
                        if (noveDatumStr.matches("\\d+")) {
                        int noveDatum = Integer.parseInt(noveDatumStr);
                        upravaKnihy.setdatumVydani(noveDatum);
                        break; 
                        } else {
                        System.out.println("Spatny vstup. Zadejte prosim platny rok.");
                        }
                         }
                    
                    System.out.print("Je kniha dostupna? (Zadejte 'A' pro ANO, 'N' pro NE): ");
                        String jeVypujcenaVstup = scanner.nextLine();
                        while (!jeVypujcenaVstup.equalsIgnoreCase("A") && !jeVypujcenaVstup.equalsIgnoreCase("N")) {
                              System.out.print("Spatny vstup. Zadejte prosim 'A' pro ANO nebo 'N' pro NE: ");
                              jeVypujcenaVstup = scanner.nextLine();
                        }
                        boolean jeVypujcena = jeVypujcenaVstup.equalsIgnoreCase("A");
                        upravaKnihy.setVypujcena(jeVypujcena);
                
                   
                    upravaKnihy.setTitul(novyTitul);
                    upravaKnihy.setAutor(novyAutori);
                
                    System.out.println(" \nKniha uspesne upravena! \n");
                    break;
                

                case 3:
                    System.out.println("\nZadejte nazev knihy pro odstraneni");
                    String odstranitTitul = scanner.nextLine();
                    boolean odstraneniNalezeno = false;
                    for (Knihy knizky : knihovna.getKnihy()) {
                        if (knizky.getTitul().equalsIgnoreCase(odstranitTitul)) {
                            odstraneniNalezeno = true;
                            knihovna.odstranitKnihu(odstranitTitul);
                            break;
                        }
                    }
                    if (!odstraneniNalezeno) {
                        System.out.println("\nKniha nenalezena.\n");
                    }
                    break;

                case 4:
                    System.out.println("\nZadejte nazev knihy pro zobrazeni informaci:");
                    String vyhledatTitul = scanner.nextLine();
                    boolean foundVyhledani = false;
                    for (Knihy knizky : knihovna.getKnihy()) {
                        if (knizky.getTitul().equalsIgnoreCase(vyhledatTitul)) {
                            foundVyhledani = true;
                            System.out.println("\nKniha nalezena.");
                            System.out.println("Titul: " + knizky.getTitul());
                            System.out.println("Autori: " + String.join(", ", knizky.getAutor()));
                            System.out.println("Rok vydani: " + knizky.getdatumVydani());
                            System.out.println("Zanr: " + knizky.getZanr());
                            System.out.println("Je kniha vypujcena?: " + (knizky.isVypujcena() ? "NE" : "ANO") + "\n");
                            break;
                        }
                    }
                    if (!foundVyhledani) {
                        System.out.println("\nKniha nenalezena.");
                    }
                    break;
                
                case 5:
                    System.out.println("\nZadejte nazev knihy pro upravu dostupnosti");
                    String oznacitTitul = scanner.nextLine();
                    boolean foundOznaceni = false;
                    for (Knihy knizky : knihovna.getKnihy()) {
                        if (knizky.getTitul().equalsIgnoreCase(oznacitTitul)) {
                            foundOznaceni = true;
                            System.out.println("\nOznacit knihu jako vracenou? ('A' pro vraceni, 'N' pro vypujceni):");
                            String markInput = scanner.nextLine();
                            boolean jeVypujcena2 = markInput.equalsIgnoreCase("A");
                            if (jeVypujcena2) {
                                knihovna.markasVypujcena(oznacitTitul);
                            } else {
                                knihovna.markasDostupna(oznacitTitul);
                            }
                            break;
                        }
                    }
                    if (!foundOznaceni) {
                        System.out.println("\nKniha nenalezena.");
                    }
                    break;

                case 6:
                  Knihy[] allKnizky = knihovna.getKnihy();
                  if (allKnizky.length == 0) {
                        System.out.println("\nDatabaze je prazdna.");
                  } else {
                        System.out.println("\nVypis vsech knih:");
                        for (Knihy knizky : allKnizky) {
                              System.out.println("Titul: " + knizky.getTitul());
                              System.out.println("Autor/Autori: " + String.join(", ", knizky.getAutor()));
                              System.out.println("Zanr: " + knizky.getZanr());
                              if (knizky instanceof Roman) {
                              System.out.println("Typ: " + ((Roman) knizky).getTyp());
                              }
                              System.out.println("Datum Vydani: " + knizky.getdatumVydani());
                              System.out.println("Je vypujcena: " + (knizky.isVypujcena() ? "NE" : "ANO"));
                              System.out.println();
                        }
                  }
                  break;
                
                
                case 7:
                        System.out.println("\nVlozte jmeno autora:");
                        String autorJmeno = scanner.nextLine();
                        boolean foundAutor = false;
                        System.out.println("Knihy od " + autorJmeno + ":");
                  
                        List<Knihy> chronologickyKnihy = new ArrayList<>(Arrays.asList(knihovna.getKnihy()));
                        chronologickyKnihy.sort(Comparator.comparingInt(Knihy::getdatumVydani));

                        for (Knihy knizky : chronologickyKnihy) {
                              for (String author : knizky.getAutor()) {
                                    if (author.equalsIgnoreCase(autorJmeno)) {
                                    foundAutor = true;
                                    System.out.println(knizky.getTitul() + ", rok vydani: " + knizky.getdatumVydani());
                                    break; 
                                    }
                              }
                        }
                        if (!foundAutor) {
                              System.out.println("\nNenalezeny knihy od " + autorJmeno);
                        }
                        break;
            
                
                case 8:
                  System.out.println("\nVlozte zanr (roman/ucebnice):");
                  String nazevZanru = scanner.nextLine().toLowerCase();
                  while (!nazevZanru.equals("roman") && !nazevZanru.equals("ucebnice")) {
                        System.out.println("\nSpatny zanr. Zvolte prosim 'roman' nebo 'ucebnice':");
                        nazevZanru = scanner.nextLine().toLowerCase();
                  }
                  
                  boolean foundKnizky = false;
                  System.out.println("\nKnihy v zanru " + nazevZanru + ":");
                  for (Knihy kniha : knihovna.getKnihy()) {
                        if (kniha.getZanr().equalsIgnoreCase(nazevZanru)) {
                              foundKnizky = true;
                              System.out.println("Titul: " + kniha.getTitul());
                              System.out.println("Datum vydani: " + kniha.getdatumVydani());
                              if (kniha instanceof Roman) {
                              System.out.println("Typ: " + ((Roman) kniha).getTyp());
                              } else if (kniha instanceof Ucebnice) {
                              System.out.println("Typ: " + ((Ucebnice) kniha).getType());
                              }
                              System.out.println();
                        }
                  }
                  if (!foundKnizky) {
                        System.out.println("\nNebyly nalezeny knihy v zanru " + nazevZanru);
                  }
                  break;
                
                  case 9:
                        boolean dostupneKnihy = false;
                        System.out.println("Vypujcene knihy:");
                        for (Knihy knizky : knihovna.getKnihy()) {
                              if (!knizky.isVypujcena()) {
                                    dostupneKnihy = true;
                                    System.out.println("Titul: " + knizky.getTitul() + ", Zanr: " + knizky.getZanr());
                              }
                        }
                        if (!dostupneKnihy) {
                              System.out.println("\nZadne knihy nejsou vypujceny.\n");
                        }
                        break;
                        
                case 11:
                	System.out.println("Zadejte název souboru .txt, ze kterého chcete načíst informace o knize:");
                    String soubor = scanner.nextLine();
                    try {
                        String informace = Knihovna.nactiInformaceZeSouboru(soubor);
                        System.out.println("Informace o knize načteny ze souboru:");
                        System.out.println(informace);
                    } catch (IOException e) {
                        System.out.println("Chyba při čtení souboru: " + e.getMessage());
                    }
                    break;
                    
                case 12:
                    System.out.println("Opousteni programu");
                    System.exit(0);
                    break;
                    default:
                    System.out.println("Neplatna volba. Zvolte prosim moznost ze seznamu.");
            }
        } catch (InputMismatchException e) {
            System.out.println("Spatny vstup. Zadejte prosim cislo moznosti.");
            scanner.nextLine(); 
        }
    }
}
}
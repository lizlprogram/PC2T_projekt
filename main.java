import java.io.IOException;
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
            System.out.println("10. Ulozeni informace o vybrane knizce do souboru .txt");
            System.out.println("11. Nacteni vsech informacii o dane knize ze souboru .txt");
            System.out.println("12. Zavrit aplikaci");

            int cases = scanner.nextInt();
            scanner.nextLine(); 

            switch (cases) {
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
                
                    System.out.println(" \nKniha uspesne upravena!");
                    break;
                

                case 3:
                    
                    System.out.println("\nZadejte nazev knihy pro odstraneni");
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
                        System.out.println("\nKniha nenalezena.");
                    }
                    break;

                case 4:
                   
                    System.out.println("\nZadejte nazev knihy pro zobrazeni informaci:");
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
                        System.out.println("\nKniha nenalezena.");
                    }
                    break;
                
                case 5:
                    System.out.println("\nZadejte nazev knihy pro upravu dostupnosti");
                    String oznacitTitul = scanner.nextLine();
                    boolean foundOznaceni = false;
                    for (Knihy book : knihovna.getKnihy()) {
                        if (book.getTitul().equalsIgnoreCase(oznacitTitul)) {
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
                Knihy[] allBooks = knihovna.getKnihy();
                if (allBooks.length == 0) {
                    System.out.println("\nDatabaze je prazdna.");
                } else {
                    System.out.println("\nVypis vsech knih:");
                    for (Knihy book : allBooks) {
                        System.out.println("Titul: " + book.getTitul());
                        System.out.println("Autor/Autori: " + String.join(", ", book.getAutor()));
                        System.out.println("Zanr: " + book.getZanr());
                        if (book instanceof Roman) {
                            System.out.println("Typ: " + ((Roman) book).getTyp());
                        }
                        System.out.println("Datum Vydani: " + book.getdatumVydani());
                        System.out.println("Je vypujcena: " + (book.isVypujcena() ? "NE" : "ANO"));
                        System.out.println();
                    }
                }
                break;
                
                
                case 7:
                System.out.println("\nVlozte jmeno autora:");
                String jmenoAutora = scanner.nextLine();
                boolean foundAutor = false;
                System.out.println("Knihy od " + jmenoAutora + ":");
                for (Knihy kniha : knihovna.getKnihy()) {
                    for (String Autor : kniha.getAutor()) {
                        if (Autor.equalsIgnoreCase(jmenoAutora)) {
                            foundAutor = true;
                            System.out.println("Titul: " + kniha.getTitul());
                            System.out.println("Datum vydani: " + kniha.getdatumVydani() + "\n");
                            break; 
                        }
                    }
                }
                if (!foundAutor) {
                    System.out.println("\nNenalezeny knihy od " + jmenoAutora);
                }
                break;
            
                
                case 8:
                  System.out.println("\nVlozte zanr (roman/ucebnice):");
                  String nazevZanru = scanner.nextLine().toLowerCase();
                  while (!nazevZanru.equals("roman") && !nazevZanru.equals("ucebnice")) {
                        System.out.println("\nSpatny zanr. Zvolte prosim 'roman' nebo 'ucebnice':");
                        nazevZanru = scanner.nextLine().toLowerCase();
                  }
                  
                  boolean foundBooks = false;
                  System.out.println("\nKnihy v zanru " + nazevZanru + ":");
                  for (Knihy kniha : knihovna.getKnihy()) {
                        if (kniha.getZanr().equalsIgnoreCase(nazevZanru)) {
                              foundBooks = true;
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
                  if (!foundBooks) {
                        System.out.println("\nNebyly nalezeny knihy v zanru " + nazevZanru);
                  }
                  break;
                
                case 9:
                  boolean foundVypujcene = true;
                  System.out.println("Vypujcene knihy:");
                  for (Knihy kniha : knihovna.getKnihy()) {
                        if (kniha.isVypujcena()) {
                              foundVypujcene = false;
                              System.out.println("Titul: " + kniha.getTitul());
                              System.out.println("Datum vydani: " + kniha.getdatumVydani());
                              System.out.println("Zanr: " + kniha.getZanr() + "\n");
                              System.out.println();
                        }
                  }
                  if (!foundVypujcene) {
                        System.out.println("Zadne vypujcene knihy.");
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
                    System.out.println("Neplatna volba, zvolte prosim z nabizenych.");
            }
        }
    }

}


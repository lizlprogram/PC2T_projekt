package projekt;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


public class Databaza {




	

		 private static List<Kniha> knihy;
		 
		 public void Knihovna() {
		        knihy = new ArrayList<>();
		    }

		 public void addKniha(Kniha kniha) {
		        knihy.add(kniha);
		    }

		 public void editKniha(String nazev, String[] autori, int rok, boolean dostupnost) {
		        for (Kniha kniha : knihy) {
		            if (kniha.getNazev().equalsIgnoreCase(nazev)) {
		                kniha.setAutori(autori);
		                kniha.setRok(rok);
		                kniha.setDostupnost(dostupnost);
		                break;
		            }
		        }
		    }
		 
		 public void deleteKniha(String nazev) {
		        knihy.removeIf(kniha -> kniha.getNazev().equalsIgnoreCase(nazev));
		    }
		 
		 public void dostupnostKniha(String nazev, boolean dostupnost) {
		        for (Kniha kniha : knihy) {
		            if (kniha.getNazev().equalsIgnoreCase(nazev)) {
		                kniha.setDostupnost(dostupnost);
		                break;
		            }
		        }
		    }
		 
		 public static void zobrazKnihy() {
		        Collections.sort(knihy, Comparator.comparing(Kniha::getNazev));
		        for (Kniha kniha : knihy) {
		            System.out.println("nazev: " + kniha.getNazev());
		            System.out.println("autoris: " + Arrays.toString(kniha.getAutori()));
		            System.out.println("rok: " + kniha.getRok());
		            System.out.println("dostupnost: " + (kniha.isDostupnost() ? "dostupnost" : "Not dostupnost"));
		            System.out.println();
		        }
		    }
		 
		 public static void hledejKnihy(String nazev) {
		        for (Kniha kniha : knihy) {
		            if (kniha.getNazev().equalsIgnoreCase(nazev)) {
		                System.out.println("nazev: " + kniha.getNazev());
		                System.out.println("autoris: " + Arrays.toString(kniha.getAutori()));
		                System.out.println("rok: " + kniha.getRok());
		                System.out.println("dostupnost: " + (kniha.isDostupnost() ? "dostupnost" : "Not dostupnost"));
		                System.out.println();
		                return;
		            }
		        }
		        System.out.println("kniha not found.");
		    }
		 
		 public static void zobrazKnihyPodleAutora(String autori) {
		        List<Kniha> autoriKnihy = new ArrayList<>();
		        for (Kniha kniha : knihy) {
		            for (String auth : kniha.getAutori()) {
		                if (auth.equalsIgnoreCase(autori)) {
		                    autoriKnihy.add(kniha);
		                    break;
		                }
		            }
		        }
		        Collections.sort(autoriKnihy, Comparator.comparing(Kniha::getRok));
		        for (Kniha kniha : autoriKnihy) {
		            System.out.println("nazev: " + kniha.getNazev());
		            System.out.println("rok: " + kniha.getRok());
		            System.out.println("dostupnost: " + (kniha.isDostupnost() ? "dostupnost" : "Not dostupnost"));
		            System.out.println();
		        }
		    }

		    public static void zobrazKnihyPodleZanru(String zanr) {
		        for (Kniha kniha : knihy) {
		            if (kniha instanceof Roman) {
		                Roman Roman = (Roman) kniha;
		                if (Roman.getZanr().equalsIgnoreCase(zanr)) {
		                    System.out.println("nazev: " + Roman.getNazev());
		                    System.out.println("autoris: " + Arrays.toString(Roman.getAutori()));
		                    System.out.println("rok: " + Roman.getRok());
		                    System.out.println("dostupnost: " + (Roman.isDostupnost() ? "dostupnost" : "Not dostupnost"));
		                    System.out.println();
		                }
		            }
		        }
		    }

		    public static void zobrazPujceneKnihy() {
		        for (Kniha kniha : knihy) {
		            if (!kniha.isDostupnost()) {
		                System.out.println("nazev: " + kniha.getNazev());
		                if (kniha instanceof Roman) {
		                    System.out.println("Type: Roman");
		                } else if (kniha instanceof Ucebnica) {
		                    System.out.println("Type: Ucebnica");
		                }
		                System.out.println();
		            }
		        }
		    }

		    public static void ulozKnihu(String nazev, String filename) {
		        for (Kniha kniha : knihy) {
		            if (kniha.getNazev().equalsIgnoreCase(nazev)) {
		                try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
		                    oos.writeObject(kniha);
		                    System.out.println("kniha saved to file successfully.");
		                } catch (IOException e) {
		                    System.out.println("Error saving kniha to file: " + e.getMessage());
		                }
		                return;
		            }
		        }
		        System.out.println("kniha not found.");
		    }

		    public static void nacitajKnihu(String filename) {
		        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
		            Kniha kniha = (Kniha) ois.readObject();
		            System.out.println("nazev: " + kniha.getNazev());
		            System.out.println("autoris: " + Arrays.toString(kniha.getAutori()));
		            System.out.println("rok: " + kniha.getRok());
		            System.out.println("dostupnost: " + (kniha.isDostupnost() ? "dostupnost" : "Not dostupnost"));
		            System.out.println();
		        } catch (IOException | ClassNotFoundException e) {
		            System.out.println("Error loading kniha from file: " + e.getMessage());
		        }
		    }
		}
	
	
	
	
	
	
	


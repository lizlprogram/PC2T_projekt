package Kniha;

import java.util.Scanner;
import java.util.InputMismatchException;

public class Ucebnice extends Knihy {
    private String type;

    public Ucebnice(String titul, String[] autor, int datumVydani, boolean vypujcena) {
        super(titul, autor, datumVydani, "Ucebnice", vypujcena); 
        setType();
        setDostupnost(); 
    }

    private void setType() {
        Scanner scanner = new Scanner(System.in);
        boolean validtype = false;
        
        while (!validtype) {
            try {
                System.out.println("Vyberte doporuceny rocnik:");
                System.out.println("1. 1.rocnik");
                System.out.println("2. 2.rocnik");

                int typeUcebnice = scanner.nextInt();
                scanner.nextLine(); 

                switch (typeUcebnice) {
                    case 1:
                        this.type = "1. rocnik";
                        validtype = true;
                        break;
                    case 2:
                        this.type = "2. rocnik";
                        validtype = true;
                        break;
                }  
            }    
            catch (InputMismatchException e) {
                System.out.println("Spatny typ Ucebnice. Zvolte prosim nabizeny typ.");
                scanner.nextLine(); 
            } 
        }
        


        }

    private void setDostupnost() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Je ucebnice vypujcena? ('A' pro ANO, 'N' pro NE): ");
        String isAvailableInput = scanner.nextLine();
        boolean isAvailable = isAvailableInput.equalsIgnoreCase("A");
        setVypujcena(!isAvailable);
    }

    public String getType() {
        return type;
    }
}

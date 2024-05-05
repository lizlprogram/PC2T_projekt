package Kniha;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Roman extends Knihy {
    private String type;

    public Roman(String titul, String[] autor, int datumVydani, boolean vypujcena) {
        super(titul, autor, datumVydani, "roman", vypujcena); 
        setTyp();
        setDostupnost(); // This method should be called from the constructor
    }

    public void setTyp() {
        Scanner scanner = new Scanner(System.in);
        boolean validType = false;

        while (!validType) {
            try {
                System.out.println("Vyberte typ romanu:");
                System.out.println("1. Mysteriozni");
                System.out.println("2. Romanticky");
                System.out.println("3. Scifi");
                System.out.println("4. Historicky");
                System.out.println("5. Fantasy");

                int typRomanu = scanner.nextInt();
                scanner.nextLine(); 

                switch (typRomanu) {
                    case 1:
                        this.type = "Mysteriozni";
                        validType = true;
                        break;
                    case 2:
                        this.type = "Romanticky";
                        validType = true;
                        break;
                    case 3:
                        this.type = "Scifi";
                        validType = true;
                        break;
                    case 4:
                        this.type = "Historicky";
                        validType = true;
                        break;
                    case 5:
                        this.type = "Fantasy";
                        validType = true;
                        break;
                }
            } 
            catch (InputMismatchException e) {
                System.out.println("Spatny typ romanu. Zvolte prosim nabizeny typ.");
                scanner.nextLine(); 
            }
        }
    }

    private void setDostupnost() {
      Scanner scanner = new Scanner(System.in);
      boolean validInput = false;
  
      while (!validInput) {
          System.out.print("Je roman vypujceny? ('A' pro ANO, 'N' pro NE): ");
          String isAvailableInput = scanner.nextLine();
          
          if (isAvailableInput.equalsIgnoreCase("A") || isAvailableInput.equalsIgnoreCase("N")) {
              boolean isAvailable = isAvailableInput.equalsIgnoreCase("A");
              setVypujcena(!isAvailable);
              validInput = true;
          } else {
              System.out.println("Spatny vstup. Zadejte prosim 'A' pro ANO nebo 'N' pro NE.");
          }
      }
  }

    // Getter for type
    public String getTyp() {
        return type;
    }
}

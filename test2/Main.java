package projekt;

import java.util.Scanner;

public class Main extends Databaza{
    public static void main(String[] args) {
       // Databaza databaza = new Databaza();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Select option:");
            System.out.println("1. Add Book");
            System.out.println("2. Edit Book");
            System.out.println("3. Delete Book");
            System.out.println("4. Mark Book Status");
            System.out.println("5. Display Books");
            System.out.println("6. Search Book");
            System.out.println("7. Display Books by Author");
            System.out.println("8. Display Books by Genre");
            System.out.println("9. Display Borrowed Books");
            System.out.println("10. Save Book to File");
            System.out.println("11. Load Book from File");
            System.out.println("0. Exit");

            int option = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (option) {
                case 1:
                    // Add Book
                    // Implementation depends on how you want to take input from the user
                    break;
                case 2:
                    // Edit Book
                    // Implementation depends on how you want to take input from the user
                    break;
                case 3:
                    // Delete Book
                    // Implementation depends on how you want to take input from the user
                    break;
                case 4:
                    // Mark Book Status
                    // Implementation depends on how you want to take input from the user
                    break;
                case 5:
                    // Display Books
                    Databaza.zobrazKnihy();
                    break;
                case 6:
                    // Search Book
                    System.out.println("Enter book title to search:");
                    String nazev = scanner.nextLine();
                    Databaza.hledejKnihy(nazev);
                    break;
                case 7:
                    // Display Books by Author
                    System.out.println("Enter author name to search:");
                    String autori = scanner.nextLine();
                    Databaza.zobrazKnihyPodleAutora(autori);
                    break;
                case 8:
                    // Display Books by Genre
                    System.out.println("Enter genre to search:");
                    String zanr = scanner.nextLine();
                    Databaza.zobrazKnihyPodleZanru(zanr);
                    break;
                case 9:
                    // Display Borrowed Books
                    Databaza.zobrazPujceneKnihy();
                    break;
                case 10:
                    // Save Book to File
                    System.out.println("Enter book title to save:");
                    String saveTitle = scanner.nextLine();
                    System.out.println("Enter file name to save:");
                    String saveFileName = scanner.nextLine();
                    Databaza.ulozKnihu(saveTitle, saveFileName);
                    break;
                case 11:
                    // Load Book from File
                    System.out.println("Enter file name to load:");
                    String loadFileName = scanner.nextLine();
                    Databaza.nacitajKnihu(loadFileName);
                    break;
                case 0:
                    // Exit
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid option.");
            }
        }
    }
}
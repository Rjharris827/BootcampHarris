package org.example;
import java.util.Scanner;
public class Library {
        private static Book[] inventory = new Book[20];

        public static void main(String[] args) {
            populateInventory();
            Scanner scanner = new Scanner(System.in);
            boolean running = true;

            while (running) {
                System.out.println("=== Bookstore Home ===");
                System.out.println("1. Show Available Books");
                System.out.println("2. Show Checked Out Books");
                System.out.println("3. Exit");
                System.out.print("Choose an option: ");
                String choice = scanner.nextLine();

                switch (choice) {
                    case "1":
                        showAvailableBooks(scanner);
                        break;
                    case "2":
                        showCheckedOutBooks(scanner);
                        break;
                    case "3":
                        running = false;
                        break;
                    default:
                        System.out.println("Invalid choice.");
                }
            }
            scanner.close();
        }

        private static void populateInventory() {
            for (int i = 0; i < inventory.length; i++) {
                inventory[i] = new Book(i + 1, "94950-3999" + (i + 1), "Spirit " + (i + 1));
            }
        }

        private static void showAvailableBooks(Scanner scanner) {
            System.out.println("--- Available Books ---");
            for (Book book : inventory) {
                if (!book.isCheckedOut()) {
                    System.out.printf("ID: %d | ISBN: %s | Title: %s%n", book.getId(), book.getIsbn(), book.getTitle());
                }
            }

            System.out.print("Enter the ID of the book to check out (or X to go back): ");
            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("X")) return;

            try {
                int bookId = Integer.parseInt(input);
                Book book = findBookById(bookId);
                if (book != null && !book.isCheckedOut()) {
                    System.out.print("Enter your name to check out the book: ");
                    String name = scanner.nextLine();
                    book.checkOut(name);
                    System.out.println("Book checked out successfully.");
                } else {
                    System.out.println("Book not available.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid ID format.");
            }
        }

        private static void showCheckedOutBooks(Scanner scanner) {
            System.out.println("--- Checked Out Books ---");
            for (Book book : inventory) {
                if (book.isCheckedOut()) {
                    System.out.printf("ID: %d | ISBN: %s | Title: %s | Checked out to: %s%n",
                            book.getId(), book.getIsbn(), book.getTitle(), book.getCheckedOutTo());
                }
            }

            System.out.print("Enter C to check in a book or X to go back: ");
            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("X")) return;

            if (input.equalsIgnoreCase("C")) {
                System.out.print("Enter the ID of the book to check in: ");
                try {
                    int bookId = Integer.parseInt(scanner.nextLine());
                    Book book = findBookById(bookId);
                    if (book != null && book.isCheckedOut()) {
                        book.checkIn();
                        System.out.println("Book checked in successfully.");
                    } else {
                        System.out.println("Book not currently checked out.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid ID format.");
                }
            }
        }

        private static Book findBookById(int id) {
            for (Book book : inventory) {
                if (book.getId() == id) return book;
            }
            return null;
        }
    }



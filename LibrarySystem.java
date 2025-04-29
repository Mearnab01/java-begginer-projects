import java.util.*;
import java.util.regex.*;

class Book {
    String id, title, author;
    boolean isAvailable;

    Book(String id, String title, String author) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.isAvailable = true;
    }

    public String toString() {
        return id + " | " + title + " by " + author + " | " + (isAvailable ? "Available" : "Issued");
    }
}

public class LibrarySystem {
    static Scanner sc = new Scanner(System.in);
    static List<Book> books = new ArrayList<>();

    public static void main(String[] args) {
        int choice;
        do {
            System.out.println("\n--- Library Book Management ---");
            System.out.println("1. Add Book");
            System.out.println("2. Display All Books");
            System.out.println("3. Search by Title");
            System.out.println("4. Search by Book ID");
            System.out.println("5. Issue Book");
            System.out.println("6. Return Book");
            System.out.println("7. Delete Book");
            System.out.println("8. Exit");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();
            sc.nextLine(); // flush

            switch (choice) {
                case 1 -> addBook();
                case 2 -> displayBooks();
                case 3 -> searchBookByTitle();
                case 4 -> searchBookById();
                case 5 -> issueBook();
                case 6 -> returnBook();
                case 7 -> deleteBook();
                case 8 -> System.out.println("Exiting...");
                default -> System.out.println("Invalid choice!");
            }
        } while (choice != 8);
    }

    static void addBook() {
        System.out.print("Enter Book ID: ");
        String id = sc.nextLine();
        while (!isValidBookId(id)) {
            System.out.println("❌ Invalid Book ID. It should be alphanumeric and not empty.");
            System.out.print("Enter Book ID: ");
            id = sc.nextLine();
        }

        System.out.print("Enter Title: ");
        String title = sc.nextLine();
        while (title.trim().isEmpty()) {
            System.out.println("❌ Title cannot be empty.");
            System.out.print("Enter Title: ");
            title = sc.nextLine();
        }

        System.out.print("Enter Author: ");
        String author = sc.nextLine();
        while (author.trim().isEmpty()) {
            System.out.println("❌ Author cannot be empty.");
            System.out.print("Enter Author: ");
            author = sc.nextLine();
        }

        books.add(new Book(id, title, author));
        System.out.println("✅ Book added.");
    }

    static void displayBooks() {
        System.out.println("\n--- Book List ---");
        if (books.isEmpty()) {
            System.out.println("No books available.");
            return;
        }
        for (Book b : books)
            System.out.println(b);
    }

    static void searchBookByTitle() {
        System.out.print("Enter title keyword: ");
        String keyword = sc.nextLine().toLowerCase();
        boolean found = false;
        for (Book b : books) {
            if (b.title.toLowerCase().contains(keyword)) {
                System.out.println(b);
                found = true;
            }
        }
        if (!found)
            System.out.println("No match found.");
    }

    static void searchBookById() {
        System.out.print("Enter Book ID: ");
        String id = sc.nextLine();
        boolean found = false;
        for (Book b : books) {
            if (b.id.equals(id)) {
                System.out.println(b);
                found = true;
                break;
            }
        }
        if (!found)
            System.out.println("❌ No book found with the given ID.");
    }

    static void issueBook() {
        System.out.print("Enter Book ID to issue: ");
        String id = sc.nextLine();
        for (Book b : books) {
            if (b.id.equals(id)) {
                if (!b.isAvailable) {
                    System.out.println("❌ Book is already issued.");
                } else {
                    b.isAvailable = false;
                    System.out.println("✅ Book issued.");
                }
                return;
            }
        }
        System.out.println("❌ Book ID not found.");
    }

    static void returnBook() {
        System.out.print("Enter Book ID to return: ");
        String id = sc.nextLine();
        for (Book b : books) {
            if (b.id.equals(id)) {
                if (b.isAvailable) {
                    System.out.println("❌ Book was not issued.");
                } else {
                    b.isAvailable = true;
                    System.out.println("✅ Book returned.");
                }
                return;
            }
        }
        System.out.println("❌ Book ID not found.");
    }

    static void deleteBook() {
        System.out.print("Enter Book ID to delete: ");
        String id = sc.nextLine();
        Iterator<Book> iterator = books.iterator();
        boolean found = false;
        while (iterator.hasNext()) {
            Book b = iterator.next();
            if (b.id.equals(id)) {
                iterator.remove();
                found = true;
                System.out.println("✅ Book deleted.");
                break;
            }
        }
        if (!found) {
            System.out.println("❌ No book found with the given ID.");
        }
    }

    static boolean isValidBookId(String id) {
        Pattern pattern = Pattern.compile("^[a-zA-Z0-9]+$"); // Alphanumeric validation
        Matcher matcher = pattern.matcher(id);
        return matcher.matches() && !id.trim().isEmpty();
    }
}

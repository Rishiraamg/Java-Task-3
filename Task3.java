import java.util.*;
//LIBRARY MANAGEMENT SYSTEM CREATED BY RISHIRAAM USING OOP
//BY USING TWO SEPARATE CLASSES FOR STORING USERS AND BOOKS
class Book {
    int id;
    String title;
    boolean isIssued;

    public Book(int id, String title) {
        this.id = id;
        this.title = title;
        this.isIssued = false;
    }

    public void issue() {
        isIssued = true;
    }

    public void returnBook() {
        isIssued = false;
    }

    public String show() {
        return "[" + id + "] " + title + " - " + (isIssued ? "Issued" : "Available");
    }
}

class User {
    int userId;
    String name;
    ArrayList<Book> borrowedBooks;

    public User(int userId, String name) {
        this.userId = userId;
        this.name = name;
        this.borrowedBooks = new ArrayList<>();
    }

    public void borrowBook(Book book) {
        borrowedBooks.add(book);
    }

    public boolean returnBook(Book book) {
        return borrowedBooks.remove(book);
    }

    public boolean hasBook(Book book) {
        return borrowedBooks.contains(book);
    }

    public void printBorrowedBooks() {
        if (borrowedBooks.isEmpty()) {
            System.out.println("No books borrowed.");
        } else {
            for (Book b : borrowedBooks) {
                System.out.println(b.show());
            }
        }
    }

    public String show() {
        return "[" + userId + "] " + name;
    }
}


public class Task3 {
    static ArrayList<Book> books = new ArrayList<>();
    static ArrayList<User> users = new ArrayList<>();
    static Scanner scanner = new Scanner(System.in);

    static void viewBooks() {
        System.out.println("\n--- Book List ---");
        for (Book book : books) {
            System.out.println(book.show());
        }
    }

    static void viewUsers() {
        System.out.println("\n--- User List ---");
        for (User user : users) {
            System.out.println(user.show());
        }
    }

    static void issueBook() {
        System.out.print("Enter User ID: ");
        int userId = scanner.nextInt();
        User user = findUserById(userId);
        if (user == null) {
            System.out.println("User not found!");
            return;
        }

        System.out.print("Enter Book ID to issue: ");
        int bookId = scanner.nextInt();
        Book book = findBookById(bookId);
        if (book == null) {
            System.out.println("Book not found!");
            return;
        }

        if (book.isIssued) {
            System.out.println("Book is already issued!");
            return;
        }

        book.issue();
        user.borrowBook(book);
        System.out.println("Book issued to " + user.name);
    }

    static void returnBook() {
        System.out.print("Enter User ID: ");
        int userId = scanner.nextInt();
        User user = findUserById(userId);
        if (user == null) {
            System.out.println("User not found!");
            return;
        }

        System.out.print("Enter Book ID to return: ");
        int bookId = scanner.nextInt();
        Book book = findBookById(bookId);
        if (book == null) {
            System.out.println("Book not found!");
            return;
        }

        if (!user.hasBook(book)) {
            System.out.println("User doesn't have this book.");
            return;
        }

        book.returnBook();
        user.returnBook(book);
        System.out.println("Book returned successfully.");
    }

    static void viewUserBorrowedBooks() {
        System.out.print("Enter User ID: ");
        int userId = scanner.nextInt();
        User user = findUserById(userId);
        if (user == null) {
            System.out.println("User not found!");
            return;
        }

        System.out.println("Books borrowed by " + user.name + ":");
        user.printBorrowedBooks();
    }

    static Book findBookById(int id) {
        for (Book b : books) {
            if (b.id == id)
                return b;
        }
        return null;
    }

    static User findUserById(int id) {
        for (User u : users) {
            if (u.userId == id)
                return u;
        }
        return null;
    }

    public static void main(String[] args) {

        books.add(new Book(1, "The Alchemist"));
        books.add(new Book(2, "1984"));
        books.add(new Book(3, "To Kill a Mockingbird"));
        books.add(new Book(4, "The Great Gatsby"));
        books.add(new Book(5, "Pride and Prejudice"));

        users.add(new User(101, "Alice"));
        // users.add(new User(102, "Bob"));
        // users.add(new User(103, "Charlie"));
        // users.add(new User(104, "David"));

        System.out.println("Welcome to Library Management System using JAVA");

        while (true) {
            
            System.out.println("1. View Books");
            System.out.println("2. View Users");
            System.out.println("3. Issue Book");
            System.out.println("4. Return Book");
            System.out.println("5. View User's Borrowed Books");
            System.out.println("6. Exit");
            System.out.print("Enter your option (1-6): ");
            int option = scanner.nextInt();

            if (option == 6) {
                System.out.println("Exiting Student Record Management System\n\n");
                break;
            }
            if(option > 6 || option < 1 ){
                System.out.println("Invalid Option. Select again\n\n");
                continue;
            }

            switch (option) {
                case 1 -> viewBooks();
                case 2 -> viewUsers();
                case 3 -> issueBook();
                case 4 -> returnBook();
                case 5 -> viewUserBorrowedBooks();
            }
            System.out.println();
        }
    }
}

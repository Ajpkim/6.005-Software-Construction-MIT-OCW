package library;

import java.util.*;

public class Library {
    
    String libraryAddress;
    ArrayList<Book> books;
   
    public Library(String address) {
        libraryAddress = address;
        books = new ArrayList<Book>();
    }
    
    public static void printOpeningHours() {
        System.out.println("Libraries are open daily from 9am to 5pm.");
    }
    
    public void printAddress() {
        System.out.println(libraryAddress);
    }

    public void addBook(Book book) {
        books.add(book);
    }
    
    public void borrowBook(String bookTitle) {
        boolean inCatalog = false;
        for (int i = 0; i < books.size(); i++) {
            if (books.get(i).title.equals(bookTitle)) {
                inCatalog = true;
                if (books.get(i).isBorrowed()) {
                    System.out.println("Sorry, this book is already borrowed.");
                    break;
                } else {
                    books.get(i).borrowed();
                    System.out.println("You successfully borrowed " + books.get(i).title);
                    break;
                } 
            }
        }
        if (! inCatalog) {
            System.out.println("Sorry, this book is not in our catalog.");
        }
    }
        
    public void returnBook(String bookTitle) {
        boolean inCatalog = false;
        for (int i = 0; i < books.size(); i++) {
            if (books.get(i).title.equals(bookTitle)) {
                inCatalog = true;
                if (books.get(i).isBorrowed()) {
                    books.get(i).returned();
                    System.out.println("You have successfully returned " + books.get(i).title);
                    break;
                } else {
                    System.out.println("You have not currently checkout out this book.");
                    break;
                } 
            }
        }
        if (! inCatalog) {
            System.out.println("Sorry, this book is not in our catalog.");
        }
    }
    
    public void printAvailableBooks() {
        if (books.size() == 0) {
            System.out.println("There are no books in the catalog.");
        } else {
                for (int i = 0; i < books.size(); i++) {
                    if (! books.get(i).isBorrowed()) {
                        System.out.println(books.get(i).title);
                        }
                    }
        }
//        Original implementation: 
//        List<String> availableBooks = new ArrayList<String>();
//        for (int i = 0; i < books.size(); i++) {
//            if (! books.get(i).isBorrowed()) {
//                 availableBooks.add(books.get(i).title);
//            }
//        }
//        System.out.println(availableBooks);
    }
    
    public static void main(String[] args) {
        // Create two libraries        
        Library firstLibrary = new Library("10 Main St.");
        Library secondLibrary = new Library("228 Liberty St.");

//         Add four books to the first library
        firstLibrary.addBook(new Book("The Da Vinci Code"));
        firstLibrary.addBook(new Book("Le Petit Prince"));
        firstLibrary.addBook(new Book("A Tale of Two Cities"));
        firstLibrary.addBook(new Book("The Lord of the Rings"));

        // Print opening hours and the addresses
        System.out.println("Library hours:");
        printOpeningHours();
        System.out.println();

        System.out.println("Library addresses:");
        firstLibrary.printAddress();
        secondLibrary.printAddress();
        System.out.println();

//        // Try to borrow The Lords of the Rings from both libraries
        System.out.println("Borrowing The Lord of the Rings:");
        firstLibrary.borrowBook("The Lord of the Rings");
        firstLibrary.borrowBook("The Lord of the Rings");
        secondLibrary.borrowBook("The Lord of the Rings");
        System.out.println();

//        // Print the titles of all available books from both libraries
        System.out.println("Books available in the first library:");
        firstLibrary.printAvailableBooks();
        System.out.println();
        System.out.println("Books available in the second library:");
        secondLibrary.printAvailableBooks();
        System.out.println();

//        // Return The Lords of the Rings to the first library
        System.out.println("Returning The Lord of the Rings:");
        firstLibrary.returnBook("The Lord of the Rings");
        System.out.println();

//        // Print the titles of available from the first library
        System.out.println("Books available in the first library:");
        firstLibrary.printAvailableBooks();
    }
} 
import java.util.ArrayList;

class Book {
    private String title;
    private String author;
    private String isbn;
    private boolean isBorrowed;
    private ArrayList<String> transactionHistory = new ArrayList<>();

    public Book(String title, String author, String isbn) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.isBorrowed = false;
        transactionHistory.add("Book added to library.");
    }

    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public String getIsbn() { return isbn; }
    public boolean isBorrowed() { return isBorrowed; }

    public void borrow(String user) {
        if (!isBorrowed) {
            isBorrowed = true;
            transactionHistory.add(user + " borrowed the book.");
        }
    }

    public void returnBook(String user) {
        if (isBorrowed) {
            isBorrowed = false;
            transactionHistory.add(user + " returned the book.");
        }
    }

    public ArrayList<String> getTransactions() {
        return transactionHistory;
    }

    @Override
    public String toString() {
        return title + " by " + author + " [ISBN: " + isbn + "] - " + (isBorrowed ? "Borrowed" : "Available");
    }
}

public class Library {
    private ArrayList<Book> books = new ArrayList<>();

    public void addBook(String title, String author, String isbn) {
        books.add(new Book(title, author, isbn));
    }

    public boolean removeBook(String isbn) {
        return books.removeIf(b -> b.getIsbn().equals(isbn));
    }

    public Book searchByISBN(String isbn) {
        for (Book b : books) {
            if (b.getIsbn().equals(isbn)) return b;
        }
        return null;
    }

    public ArrayList<Book> searchByTitle(String keyword) {
        ArrayList<Book> result = new ArrayList<>();
        for (Book b : books) {
            if (b.getTitle().toLowerCase().contains(keyword.toLowerCase())) {
                result.add(b);
            }
        }
        return result;
    }

    public ArrayList<Book> getAllBooks() {
        return books;
    }
}

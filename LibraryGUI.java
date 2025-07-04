import javax.swing.*;
import java.awt.*;

public class LibraryGUI extends JFrame {
    private Library library = new Library();

    public LibraryGUI() {
        setTitle("Library Management System");
        setSize(400, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridLayout(7, 1));

        JButton addBtn = new JButton("Add Book");
        JButton removeBtn = new JButton("Remove Book");
        JButton searchBtn = new JButton("Search Book by Title");
        JButton borrowBtn = new JButton("Borrow Book");
        JButton returnBtn = new JButton("Return Book");
        JButton viewAllBtn = new JButton("View All Books");
        JButton txnBtn = new JButton("View Transaction History");

        add(addBtn);
        add(removeBtn);
        add(searchBtn);
        add(borrowBtn);
        add(returnBtn);
        add(viewAllBtn);
        add(txnBtn);

        addBtn.addActionListener(e -> {
            String title = JOptionPane.showInputDialog("Book Title:");
            String author = JOptionPane.showInputDialog("Author:");
            String isbn = JOptionPane.showInputDialog("ISBN:");
            if (title != null && author != null && isbn != null) {
                library.addBook(title, author, isbn);
                JOptionPane.showMessageDialog(this, "Book added.");
            }
        });

        removeBtn.addActionListener(e -> {
            String isbn = JOptionPane.showInputDialog("Enter ISBN to remove:");
            boolean removed = library.removeBook(isbn);
            JOptionPane.showMessageDialog(this, removed ? "Removed." : "Not found.");
        });

        searchBtn.addActionListener(e -> {
            String keyword = JOptionPane.showInputDialog("Enter title keyword:");
            var list = library.searchByTitle(keyword);
            StringBuilder sb = new StringBuilder();
            for (var b : list) sb.append(b).append("\n");
            showInTextArea(sb.toString(), "Search Results");
        });

        borrowBtn.addActionListener(e -> {
            String isbn = JOptionPane.showInputDialog("Enter ISBN to borrow:");
            String user = JOptionPane.showInputDialog("Enter your name:");
            Book b = library.searchByISBN(isbn);
            if (b != null && !b.isBorrowed()) {
                b.borrow(user);
                JOptionPane.showMessageDialog(this, "Borrowed successfully.");
            } else {
                JOptionPane.showMessageDialog(this, "Book not found or already borrowed.");
            }
        });

        returnBtn.addActionListener(e -> {
            String isbn = JOptionPane.showInputDialog("Enter ISBN to return:");
            String user = JOptionPane.showInputDialog("Enter your name:");
            Book b = library.searchByISBN(isbn);
            if (b != null && b.isBorrowed()) {
                b.returnBook(user);
                JOptionPane.showMessageDialog(this, "Returned successfully.");
            } else {
                JOptionPane.showMessageDialog(this, "Book not found or not borrowed.");
            }
        });

        viewAllBtn.addActionListener(e -> {
            StringBuilder sb = new StringBuilder();
            for (var b : library.getAllBooks()) sb.append(b).append("\n");
            showInTextArea(sb.toString(), "All Books");
        });

        txnBtn.addActionListener(e -> {
            String isbn = JOptionPane.showInputDialog("Enter ISBN to view history:");
            Book b = library.searchByISBN(isbn);
            if (b != null) {
                StringBuilder sb = new StringBuilder();
                for (String t : b.getTransactions()) sb.append(t).append("\n");
                showInTextArea(sb.toString(), "Transaction History");
            } else {
                JOptionPane.showMessageDialog(this, "Book not found.");
            }
        });

        setVisible(true);
    }

    private void showInTextArea(String text, String title) {
        JTextArea area = new JTextArea(text);
        area.setEditable(false);
        JScrollPane pane = new JScrollPane(area);
        JOptionPane.showMessageDialog(this, pane, title, JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args) {
        new LibraryGUI();
    }
}

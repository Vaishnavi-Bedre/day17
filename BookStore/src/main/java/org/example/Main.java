package org.example;

import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

import java.util.Date;

public class Main {
    private static final String DATABASE_NAME = "bookstore";

    public static void main(String[] args) {
        // Connect to MongoDB
        MongoDatabase database = MongoClients.create().getDatabase(DATABASE_NAME);

        // Initialize bookstore manager
        BookstoreManager bookstoreManager = new BookstoreManager(database);

        // Example data for Author, Inventory, and SalesTransactions
        Author author1 = new Author();
        author1.setName("John Doe");
        author1.setBiographies("John Doe is a prolific author.");

        Inventory book1 = new Inventory();
        book1.setISBN("978-3-16-148410-0");
        book1.setTitle("Book Title 1");
        book1.setAuthor("John Doe");
        book1.setGenre("Fiction");
        book1.setPrice(19.99);
        book1.setStock_quantity(100);

        SalesTransactions sale1 = new SalesTransactions();
        sale1.setTransactionId("TXN123456");
        sale1.setISBN("978-3-16-148410-0");
        sale1.setQuantity_sold(2);
        sale1.setTotal_price(39.98);
        sale1.setDate_of_transaction(new Date());

        // CRUD operations for Author
        String authorId = bookstoreManager.createAuthor(author1);
        bookstoreManager.listAllAuthors();
        bookstoreManager.updateAuthor(authorId, "Updated biography");
        bookstoreManager.listAllAuthors();
        bookstoreManager.deleteAuthor(authorId);
        bookstoreManager.listAllAuthors();

        // CRUD operations for Inventory
        bookstoreManager.createBook(book1);
        bookstoreManager.listAllBooks();
        bookstoreManager.updateStockQuantity(book1.getISBN(), 120);
        bookstoreManager.listAllBooks();
        bookstoreManager.deleteBook(book1.getISBN());
        bookstoreManager.listAllBooks();

        // CRUD operations for SalesTransactions
        String saleId = bookstoreManager.createSale(sale1);
        bookstoreManager.listAllSales();
        if (saleId != null) {
            bookstoreManager.updateQuantitySold(saleId, 3);
            bookstoreManager.listAllSales();
            bookstoreManager.deleteSale(saleId);
            bookstoreManager.listAllSales();
        } else {
            System.out.println("Failed to create sale.");
        }
    }
}

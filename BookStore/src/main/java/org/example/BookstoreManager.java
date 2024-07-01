package org.example;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.types.ObjectId;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.set;

public class BookstoreManager {
    private final MongoCollection<Document> authorsCollection;
    private final MongoCollection<Document> inventoryCollection;
    private final MongoCollection<Document> salesTransactionsCollection;

    public BookstoreManager(MongoDatabase database) {
        this.authorsCollection = database.getCollection("Author");
        this.inventoryCollection = database.getCollection("Inventory");
        this.salesTransactionsCollection = database.getCollection("SalesTransactions");
    }

    // Authors CRUD operations
    public String createAuthor(Author author) {
        Document authorDoc = new Document("name", author.getName())
                .append("biographies", author.getBiographies());
        authorsCollection.insertOne(authorDoc);
        return authorDoc.getObjectId("_id").toString();
    }

    public void updateAuthor(String authorId, String biography) {
        try {
            ObjectId objectId = new ObjectId(authorId);
            authorsCollection.updateOne(eq("_id", objectId), set("biographies", biography));
        } catch (IllegalArgumentException e) {
            System.err.println("Invalid authorId format: " + authorId);
            e.printStackTrace();
            // Handle the exception as per your application's error handling strategy
        }
    }

    public void deleteAuthor(String authorId) {
        try {
            ObjectId objectId = new ObjectId(authorId);
            authorsCollection.deleteOne(eq("_id", objectId));
        } catch (IllegalArgumentException e) {
            System.err.println("Invalid authorId format: " + authorId);
            e.printStackTrace();
            // Handle the exception as per your application's error handling strategy
        }
    }

    public void listAllAuthors() {
        System.out.println("\nAll Authors:");
        for (Document doc : authorsCollection.find()) {
            System.out.println(doc.toJson());
        }
    }

    // Inventory CRUD operations
    public void createBook(Inventory book) {
        Document bookDoc = new Document("ISBN", book.getISBN())
                .append("title", book.getTitle())
                .append("author", book.getAuthor())
                .append("genre", book.getGenre())
                .append("price", book.getPrice())
                .append("stock_quantity", book.getStock_quantity());
        inventoryCollection.insertOne(bookDoc);
    }

    public void updateStockQuantity(String ISBN, int newStockQuantity) {
        inventoryCollection.updateOne(eq("ISBN", ISBN), set("stock_quantity", newStockQuantity));
    }

    public void deleteBook(String ISBN) {
        inventoryCollection.deleteOne(eq("ISBN", ISBN));
    }

    public void listAllBooks() {
        System.out.println("\nAll Books:");
        for (Document doc : inventoryCollection.find()) {
            System.out.println(doc.toJson());
        }
    }

    // Sales Transactions CRUD operations
    public String createSale(SalesTransactions sale) {
        Document saleDoc = new Document("transactionId", sale.getTransactionId())
                .append("ISBN", sale.getISBN())
                .append("quantity_sold", sale.getQuantity_sold())
                .append("total_price", sale.getTotal_price())
                .append("date_of_transaction", sale.getDate_of_transaction());
        salesTransactionsCollection.insertOne(saleDoc);
        return null;
    }

    public void updateQuantitySold(String saleId, int newQuantitySold) {
        if (saleId != null) {
            try {
                ObjectId objectId = new ObjectId(saleId);
                salesTransactionsCollection.updateOne(eq("_id", objectId), set("quantity_sold", newQuantitySold));
            } catch (IllegalArgumentException e) {
                System.err.println("Invalid saleId format: " + saleId);
                e.printStackTrace();
                // Handle the exception as per your application's error handling strategy
            }
        } else {
            System.err.println("saleId cannot be null.");
        }
    }

    public void deleteSale(String saleId) {
        if (saleId != null) {
            try {
                ObjectId objectId = new ObjectId(saleId);
                salesTransactionsCollection.deleteOne(eq("_id", objectId));
            } catch (IllegalArgumentException e) {
                System.err.println("Invalid saleId format: " + saleId);
                e.printStackTrace();
                // Handle the exception as per your application's error handling strategy
            }
        } else {
            System.err.println("saleId cannot be null.");
        }
    }

    public void listAllSales() {
        System.out.println("\nAll Sales Transactions:");
        for (Document doc : salesTransactionsCollection.find()) {
            System.out.println(doc.toJson());
        }
    }
}

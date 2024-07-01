package org.example;

import java.security.PrivateKey;
import java.util.Date;

public class SalesTransactions {
    private String id;
    private String transactionId;
    private String ISBN;
    private int quantity_sold;
    private double total_price;
    private Date date_of_transaction;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public int getQuantity_sold() {
        return quantity_sold;
    }

    public void setQuantity_sold(int quantity_sold) {
        this.quantity_sold = quantity_sold;
    }

    public double getTotal_price() {
        return total_price;
    }

    public void setTotal_price(double total_price) {
        this.total_price = total_price;
    }

    public Date getDate_of_transaction() {
        return date_of_transaction;
    }

    public void setDate_of_transaction(Date date_of_transaction) {
        this.date_of_transaction = date_of_transaction;
    }
}

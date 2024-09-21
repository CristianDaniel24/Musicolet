package classes;

import java.time.LocalDateTime;
import java.util.HashMap;

public class ShoppingCart {
    private int id;
    private HashMap<Product, Integer> products;
    private Bill bill;
    private LocalDateTime dateCreation;
    private LocalDateTime dateModification;
    private LocalDateTime dateElimination;

    public ShoppingCart() {
        products = new HashMap<>();
    }

    public ShoppingCart(int id, Bill bill, LocalDateTime dateCreation, LocalDateTime dateModification, LocalDateTime dateElimination) {
        this.id = id;
        this.bill = bill;
        this.dateCreation = dateCreation;
        this.dateModification = dateModification;
        this.dateElimination = dateElimination;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public HashMap<Product, Integer> getProducts() {
        return products;
    }

    public void setProducts(HashMap<Product, Integer> products) {
        this.products = products;
    }

    public Bill getBill() {
        return bill;
    }

    public void setBill(Bill bill) {
        this.bill = bill;
    }

    public LocalDateTime getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(LocalDateTime dateCreation) {
        this.dateCreation = dateCreation;
    }

    public LocalDateTime getDateModification() {
        return dateModification;
    }

    public void setDateModification(LocalDateTime dateModification) {
        this.dateModification = dateModification;
    }

    public LocalDateTime getDateElimination() {
        return dateElimination;
    }

    public void setDateElimination(LocalDateTime dateElimination) {
        this.dateElimination = dateElimination;
    }
}

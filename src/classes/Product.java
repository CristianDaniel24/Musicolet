package classes;

import java.time.LocalDateTime;

public class Product {
    private String name;
    private Double price;
    private Integer stock;
    private Integer id;
    private LocalDateTime dateCreation;
    private LocalDateTime dateModification;
    private LocalDateTime dateElimination;

    public Product(String name, Double price, Integer stock, Integer id, LocalDateTime dateCreation, LocalDateTime dateModification, LocalDateTime dateElimination) {
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.id = id;
        this.dateCreation = dateCreation;
        this.dateModification = dateModification;
        this.dateElimination = dateElimination;
    }

    public Product() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public void detailsProduct() {
        System.out.printf("%-15s %,-15.2f %-5s %-10d%n", name, price, id, stock);
    }

    @Override
    public String toString() {
        return name + "," + price + "," + stock + "," + id + "," +
                dateCreation + "," + dateModification + "," + dateElimination;
    }
}

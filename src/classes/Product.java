package classes;

import java.time.LocalDateTime;

public class Product {
    private String name;
    private Double price;
    private Integer stack;
    private Integer id;
    private LocalDateTime dateCreation;
    private LocalDateTime dateModification;
    private LocalDateTime dateElimination;

    public Product(String name, Double price, Integer stack) {
        this.name = name;
        this.price = price;
        this.stack = stack;
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

    public Integer getStack() {
        return stack;
    }

    public void setStack(Integer stack) {
        this.stack = stack;
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

    @Override
    public String toString() {
        return name + "," + price + "," + stack + "," + id + "," +
                dateCreation + "," + dateModification + "," + dateElimination;
    }
}

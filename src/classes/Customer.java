package classes;

import java.time.LocalDateTime;

public class Customer extends Person {
    private Integer id;
    private String address;
    private LocalDateTime dateCreation;
    private LocalDateTime dateModification;
    private LocalDateTime dateElimination;

    public Customer(Integer id, String address) {
        this.id = id;
        this.address = address;
    }

    public Customer() {
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public void buyProduct() {
    }

    public void listProduct() {
    }

    public String toString() {
        return id + "," + address + "," + dateCreation + "," +
                dateModification + "," + dateElimination;
    }

}

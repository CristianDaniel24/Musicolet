package classes;

import java.time.LocalDateTime;

public class Bill {
    private int id;
    private Double total;
    private LocalDateTime dateCreation;
    private LocalDateTime datePayment;

    public Bill(int id, Double total, LocalDateTime dateCreation, LocalDateTime datePayment) {
        this.id = id;
        this.total = total;
        this.dateCreation = dateCreation;
        this.datePayment = datePayment;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public LocalDateTime getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(LocalDateTime dateCreation) {
        this.dateCreation = dateCreation;
    }

    public LocalDateTime getDatePayment() {
        return datePayment;
    }

    public void setDatePayment(LocalDateTime datePayment) {
        this.datePayment = datePayment;
    }
}

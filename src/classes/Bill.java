package classes;

import java.time.LocalDateTime;

public class Bill {
    private static int count = 0;
    private int id;
    private Double total;
    private LocalDateTime dateCreation;
    private LocalDateTime datePayment;

    public Bill() {
        this.id = ++count;
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

    public void detailsBill() {
        LocalDateTime dateCreation = LocalDateTime.now();
        LocalDateTime datePayment = LocalDateTime.now();
        System.out.printf("%-25s %-25s %-25s %-25s\n", "", "Total:", "Date Creation:", "Date Payment:");
        System.out.printf("%-25d %-25.2f %-25s %-25s\n", id, total, dateCreation, datePayment);
        System.out.println("----------------------------------------------------------------------------------------------");
    }
}

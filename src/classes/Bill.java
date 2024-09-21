package classes;

import constants.ServiceConstants;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

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

    public void detailsProducts(HashMap<Product, Integer> productsShoppingCart) {
        if (productsShoppingCart.isEmpty()) {
            System.out.println("\nThe shoppingCart is empty");
        } else {
            Double total = 0.0;
            System.out.println("\n-----------------------------------------------------------------------");
            System.out.printf("%-10s %-20s %-10s %-10s %-10s\n", "ID", "Name", "Price", "Amount", "Total Product");
            for (Map.Entry<Product, Integer> entry : productsShoppingCart.entrySet()) {
                Product product = entry.getKey();
                Integer quantity = entry.getValue();
                Double priceProductTotal = product.getPrice() * quantity;
                total += priceProductTotal;
                System.out.printf("%-10s %-20s %-10s %-10s %-10s\n", product.getId(), product.getName(), product.getPrice(), quantity, priceProductTotal);
            }
            ServiceConstants.BILL.setTotal(total);
        }
        System.out.println("-----------------------------------------------------------------------");
    }

    public void detailsBill() {
        LocalDateTime dateCreation = LocalDateTime.now();
        LocalDateTime datePayment = LocalDateTime.now();
        System.out.printf("%-35s %-35s\n", "Id Bill", "Total:");
        System.out.printf("%-35d %-35.2f\n", id, total);
        System.out.printf("%-35s %-35s\n", "Date Creation:", "Date Payment:");
        System.out.printf("%-35s %-35s\n", dateCreation, datePayment);
        System.out.println("-----------------------------------------------------------------------");
    }
}

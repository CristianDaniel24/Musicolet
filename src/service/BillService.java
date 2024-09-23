package service;

import classes.Bill;
import classes.Product;
import classes.ShoppingCart;

import java.util.HashMap;
import java.util.Map;

public class BillService {

    public void printBIll(ShoppingCart shoppingCart, HashMap<Product, Integer> productsShoppingCart, Bill bill) {
        System.out.println("\n------------------------------------BILL----------------------------------------");
        System.out.printf("%-2s %-2s\n", "ID:", shoppingCart.getId());
        System.out.printf("%-31s %-35s %-35s\n", "Name:", "SubTotal:", "Stock:");
        for (Map.Entry<Product, Integer> entry : productsShoppingCart.entrySet()) {
            Product product = entry.getKey();
            Integer quantity = entry.getValue();
            Double priceProduct = product.getPrice() * quantity;
            System.out.printf("%-31s %-35.2f %-35s\n", product.getName(), priceProduct, quantity);
        }
        System.out.printf("\n%-31s %-35s %-35s\n", "Date Creation:", "Date Payment:", "Total:");
        System.out.printf("%-31s %-35s %-35s\n", bill.getDateCreation(), bill.getDatePayment(), bill.getTotal());
        System.out.println("--------------------------------------------------------------------------------");
    }
}

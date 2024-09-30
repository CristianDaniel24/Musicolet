package service;

import classes.Bill;
import classes.Product;
import classes.ShoppingCart;

import java.util.HashMap;
import java.util.Map;

public class BillService {

    public void printBIll(ShoppingCart shoppingCart, HashMap<Product, Integer> productsShoppingCart, Bill bill) {
        System.out.println("\n------------------------------------BILL-------------------------------------------------------");
        System.out.printf("%-2s %-2s\n", "ID:", shoppingCart.getId());
        System.out.printf("%-31s %-35s %-15s %-1s\n", "Name:", "UnitValue", "Amount:", "SubTotal:");
        for (Map.Entry<Product, Integer> entry : productsShoppingCart.entrySet()) {
            Product product = entry.getKey();
            Integer quantity = entry.getValue();
            Double priceProduct = product.getPrice() * quantity;
            System.out.printf("%-31s $%,-34.2f %-15s $%,-1.2f\n", product.getName(), product.getPrice(), quantity, priceProduct);
        }

        System.out.printf("\n%-31s %-51s %-1s\n", "Date Creation:", "Date Payment:", "Total:");
        System.out.printf("%tB/%td/%tY %tI:%tM:%tS %-4Tp %tB/%td/%tY %tI:%tM:%tS %-24Tp $%,-1.2f\n", bill.getDateCreation(), bill.getDateCreation(), bill.getDateCreation(), bill.getDateCreation(), bill.getDateCreation(), bill.getDateCreation(), bill.getDateCreation(), bill.getDatePayment(), bill.getDatePayment(), bill.getDatePayment(), bill.getDatePayment(), bill.getDatePayment(), bill.getDatePayment(), bill.getDatePayment(), bill.getTotal());
        System.out.println("-----------------------------------------------------------------------------------------------");
    }
}

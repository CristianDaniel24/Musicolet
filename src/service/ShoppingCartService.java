package service;

import classes.Bill;
import classes.Product;
import classes.ShoppingCart;
import constants.ReaderConstants;
import constants.ServiceConstants;
import exceptions.MusicoletException;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class ShoppingCartService {

    public ShoppingCartService() {
    }

    public void addProduct(LinkedList<Product> productList, HashMap<Product, Integer> productsShoppingCart) throws MusicoletException {
        try {
            System.out.println("Enter the id for add ShoppingCart:");
            int idInput = Integer.parseInt(ReaderConstants.reader.readLine());

            boolean found = false;
            for (Product products : productList) {
                if (idInput == products.getId()) {
                    int count = productsShoppingCart.getOrDefault(products, 0);
                    productsShoppingCart.put(products, ++count);
                    System.out.println("The product added successfully");
                    found = true;
                    break;
                }
            }
            if (!found) {
                throw new MusicoletException("The id is invalid");
            }
        } catch (NumberFormatException e) {
            System.out.println("Enter the number, please");
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (MusicoletException e) {
            System.out.println(e.getMessage());
        }

    }

    public void removeProduct(HashMap<Product, Integer> productsShoppingCart) throws MusicoletException, IOException {
        try {
            if (productsShoppingCart.isEmpty()) {
                System.out.println("\nThe shoppingCart is empty");
            } else {
                System.out.println("\nEnter the id of deleted");
                int idInput = Integer.parseInt(ReaderConstants.reader.readLine());

                for (Product product : productsShoppingCart.keySet()) {
                    if (product.getId() == idInput) {
                        productsShoppingCart.remove(product);
                        System.out.println("\nThe product has been successfully removed");
                        break;
                    } else {
                        throw new MusicoletException("The id is invalid");
                    }
                }
            }
        } catch (NumberFormatException e) {
            System.out.println("\nEnter the number, please");
        } catch (MusicoletException e) {
            System.out.println(e.getMessage());
        }
    }

    public void listProduct(HashMap<Product, Integer> productsShoppingCart) {
        if (productsShoppingCart.isEmpty()) {
            System.out.println("\nThe shoppingCart is empty");
        } else {
            System.out.println("\n-----------------------------------------------------");
            System.out.printf("%-10s %-20s %-10s %-10s\n", "ID", "Name", "Price", "Amount");
            for (Map.Entry<Product, Integer> entry : productsShoppingCart.entrySet()) {
                Product product = entry.getKey();
                Integer quantity = entry.getValue();
                //TODO: Imprimir subtotal
                System.out.printf("%-10s %-20s %-10s %-10s\n", product.getId(), product.getName(), product.getPrice(), quantity);
            }
        }
        System.out.println("-----------------------------------------------------");
    }

    public void finishAndPay(ShoppingCart shoppingCart) {
        Double total = sumProducts(shoppingCart.getProducts());

        LocalDateTime dateCreation = LocalDateTime.now();
        LocalDateTime datePayment = LocalDateTime.now();
        Bill bill = new Bill(shoppingCart.getId(), total, dateCreation, datePayment);

        //SE ENVIA LA FACTURA
        shoppingCart.setBill(bill);
        ServiceConstants.BILL_SERVICE.printBIll(shoppingCart, shoppingCart.getProducts(), bill);
    }

    public double sumProducts(HashMap<Product, Integer> productsShoppingCart) {
        double total = 0.0;
        for (Map.Entry<Product, Integer> entry : productsShoppingCart.entrySet()) {
            Product product = entry.getKey();
            Integer quantity = entry.getValue();
            double priceTotalProduct = product.getPrice() * quantity;
            total += priceTotalProduct;
        }
        return total;
    }
}

package service;

import classes.Product;
import constants.FileRoutes;
import constants.ReaderConstants;
import constants.ServiceConstants;
import exceptions.MusicoletException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
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
                    int count = productsShoppingCart.getOrDefault(products, 1);
                    productsShoppingCart.put(products, count);
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

    public int getLastIdProducts() throws IOException {
        int lastId = 0;
        BufferedReader reader = new BufferedReader(new FileReader(FileRoutes.RUTE_PRODUCTS));
        String line;
        while ((line = reader.readLine()) != null) {
            String[] data = line.split(",");
            lastId = Integer.parseInt(data[3]);
        }
        return lastId;
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
                System.out.printf("%-10s %-20s %-10s %-10s\n", product.getId(), product.getName(), product.getPrice(), quantity);
            }
        }
        System.out.println("-----------------------------------------------------");
    }

    //TODO: Arreglar metodo
    public void finishAndPay(HashMap<Product, Integer> productsShoppingCart) {
        ServiceConstants.BILL.detailsProducts(productsShoppingCart);
        ServiceConstants.BILL.detailsBill();
    }
}

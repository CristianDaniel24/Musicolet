package service;

import classes.Product;
import classes.ShoppingCart;
import constants.FileRoutes;
import exceptions.MusicoletException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;

public class ShoppingCartService {

    public ShoppingCartService() {
    }

    public static void addProduct(BufferedReader reader, Product[] products) throws MusicoletException {

        int count = 0;

        try (BufferedReader readerProducts = new BufferedReader(new FileReader(FileRoutes.RUTE_PRODUCTS))) {
            ShoppingCart shoppingCart = new ShoppingCart();
            System.out.println("Enter the id for add ShoppingCart:");
            int idInput = Integer.parseInt(reader.readLine());

            String line;
            while ((line = readerProducts.readLine()) != null) {
                System.out.println(line);
                String[] data = line.split(",");
                if (data.length >= 7) {

                    String name = data[0];
                    Double price = Double.parseDouble(data[1]);
                    int stock = Integer.parseInt(data[2]);
                    int idFile = Integer.parseInt(data[3]);
                    LocalDateTime dateCreation = LocalDateTime.parse(data[4]);

                    LocalDateTime dateEdition = null;
                    if (!data[5].equals("null")) {
                        dateEdition = LocalDateTime.parse(data[5]);
                    }

                    LocalDateTime dateEliminate = null;
                    if (!data[6].equals("null")) {
                        dateEliminate = LocalDateTime.parse(data[6]);
                    }

                    System.out.println("ID Input: " + idInput);
                    System.out.println("ID File: " + idFile);
                    if (idInput == idFile) {
                        products[count] = new Product(name, price, stock, idFile, dateCreation, dateEdition, dateEliminate);
                        count++;
                        //AGREGAR??
                        //shoppingCart.addProduct(products);
                        shoppingCart.detailsShoppingCart();
                        break;
                    } else {
                        throw new MusicoletException("The id is invalid");
                    }
                }
            }
        } catch (NumberFormatException e) {
            System.out.println("Enter the number, please");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.printf("%-15s %-15s %-5s %-10s%n", "Name", "Price", "ID", "Stock");
        System.out.println("---------------------------------------------");
        for (int i = 0; i < count; i++) {
            products[i].detailsProduct();
        }
    }

    public static int getLastIdProducts() throws IOException {
        int lastId = 0;
        BufferedReader reader = new BufferedReader(new FileReader(FileRoutes.RUTE_PRODUCTS));
        String line;
        while ((line = reader.readLine()) != null) {
            String[] data = line.split(",");
            lastId = Integer.parseInt(data[3]);
        }
        return lastId;
    }

    public static void removeProduct(BufferedReader reader, Product[] products) throws MusicoletException, IOException {
        ShoppingCart shoppingCart = new ShoppingCart();
        try (BufferedReader readerProducts = new BufferedReader(new FileReader(FileRoutes.RUTE_PRODUCTS))) {
            System.out.println("\nEnter the id of deleted");
            int idInput = Integer.parseInt(reader.readLine());

            boolean productFound = false;
            // Iterar sobre el array de productos
            for (Product product : products) {
                if (product != null && product.getId() == idInput) {
                    // Verificar si el producto está en el HashMap (carrito)
                    if (products.containsKey(product)) {
                        // Eliminar el producto del carrito
                        products.remove(product);
                        System.out.println("The product has been successfully removed from the cart: " + product.getName());
                    } else {
                        System.out.println("The product is not in the cart");
                    }
                    productFound = true;
                    break;
                }
            }

            if (!productFound) {
                System.out.println("A product was not found or an invalid id");
            } else {
                throw new MusicoletException("The id is invalid");
            }
        } catch (NumberFormatException e) {
            System.out.println("\nEnter the number, please");
        }
    }

    public static void finishAndPay() {
    }

    public static void listProduct() {
    }
}

package service;

import classes.Bill;
import classes.Product;
import classes.ShoppingCart;
import constants.FileRoutes;
import constants.ReaderConstants;
import constants.ServiceConstants;
import exceptions.MusicoletException;

import java.io.BufferedWriter;
import java.io.FileWriter;
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
            //PREGUNTA LA ID DEL PRODUCTO AL USUARIO
            System.out.println("Enter the id for add ShoppingCart:");
            int idInput = Integer.parseInt(ReaderConstants.reader.readLine());

            boolean found = false;
            for (Product products : productList) {
                //VALIDA SI EXISTE LA ID DEL PRODUCTO PREGUNTADO
                //SI EXISTEN PRODUCTOS CON ESA ID ENTONCES SI HAY EN LA BODEGA
                if (idInput == products.getId()) {
                    //SE CONSIGUE LA CANTIDAD DE CADA PRODUCTO
                    //TODO: ESTO DE ABAJO SOLUCIONA EL PROBLEMA DEL PRODUCTO NULL A LA HORA DE CONSEGUIR LA CANTIDAD
                    int quantityProducts = productsShoppingCart.getOrDefault(products, 0);

                    //SE PREGUNTA LA CANTIDAD DE PRODUCTOS A EL USUARIO
                    System.out.println("Enter the quantity of products you want buy");
                    int stockInput = Integer.parseInt(ReaderConstants.reader.readLine());
                    //SE REALIZA LA SUMA DE LOS PRODUCTOS QUE PIDIO EL USUARIO CON LOS DEL SHOPPINGCART
                    stockInput += quantityProducts;

                    //AHORA SE VERIFICA QUE HAYAN LOS SUFICIENTES PRODUCTOS EN LA BODEGA
                    if (products.getStock() >= stockInput) {
                        found = true;
                        //SE AGREGA LA CANTIDAD DE PRODUCTOS AL SHOPPINGCART
                        productsShoppingCart.put(products, stockInput);
                        System.out.println("\nThe products were added to the ShoppingCart successfully =)");
                        return;
                    } else {
                        System.out.println("\nSorry, we don't have that quantity of products requested =(");
                        return;
                    }
                }
            }
            if (!found) {
                throw new MusicoletException("\nThe id is invalid");
            }

        } catch (NumberFormatException e) {
            System.out.println("Enter the number, please");
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (MusicoletException e) {
            System.out.println(e.getMessage());
        }

    }

    public void removeProduct(HashMap<Product, Integer> productsShoppingCart) throws IOException {
        try {
            if (productsShoppingCart.isEmpty()) {
                System.out.println("\nThe shoppingCart is empty =(");
            } else {
                System.out.println("\nEnter the id of deleted");
                int idInput = Integer.parseInt(ReaderConstants.reader.readLine());

                //SE UTILIZO EL USO DE UN INTERRUPTOR PARA QUE MUESTRE EL MENSAJE DE ID INVALIDA CORRECTAMENTE
                boolean deletedProduct = false;
                for (Product product : productsShoppingCart.keySet()) {
                    if (product.getId() == idInput) {
                        productsShoppingCart.remove(product);
                        deletedProduct = true;
                        break;
                    }
                }
                if (deletedProduct) {
                    System.out.println("\nThe product has been successfully removed =)");
                } else {
                    System.out.println("\nThe product you want to delete does not exist or is invalid =(");
                }
            }
        } catch (NumberFormatException e) {
            System.out.println("\nEnter the number, please");
        }
    }

    public void listProduct(HashMap<Product, Integer> productsShoppingCart) {
        if (productsShoppingCart.isEmpty()) {
            System.out.println("\nThe shoppingCart is empty =(");
        } else {
            System.out.println("\n--------------------------------------------------------------");
            System.out.printf("%-10s %-20s %-10s %-10s %-10s\n", "ID", "Name", "Price", "Subtotal", "Amount");
            for (Map.Entry<Product, Integer> entry : productsShoppingCart.entrySet()) {
                Product product = entry.getKey();
                Integer quantity = entry.getValue();
                Double subtotal = product.getPrice() * quantity;
                System.out.printf("%-10s %-20s $%,-8.2f  $%,-10.2f %-10s\n", product.getId(), product.getName(), product.getPrice(), subtotal, quantity);
            }
            System.out.println("--------------------------------------------------------------");
        }
    }

    public void finishAndPay(ShoppingCart shoppingCart, LinkedList<Product> productList) throws IOException {
        Double total = sumProducts(shoppingCart.getProducts());

        LocalDateTime dateCreation = LocalDateTime.now();
        LocalDateTime datePayment = LocalDateTime.now();

        Bill bill = new Bill(shoppingCart.getId(), total, dateCreation, datePayment);

        shoppingCart.setBill(bill);
        ServiceConstants.BILL_SERVICE.printBIll(shoppingCart, shoppingCart.getProducts(), bill);
        //DESPUES DE LA FACTURA SE HACE LA ACTUALIZACION DEL ARCHIVO TXT CON EL NUEVO STOCK

        int amountFile;
        BufferedWriter writerClear = new BufferedWriter(new FileWriter(FileRoutes.RUTE_PRODUCTS, false));

        BufferedWriter writer = new BufferedWriter(new FileWriter(FileRoutes.RUTE_PRODUCTS, true));
        for (Product productLinket : productList) {
            //SE RESTA LA CANTIDAD DE PRODUCTOS DEL SHOPINGCART CON LA CANTIDAD DEL LINKEDLIST
            for (Map.Entry<Product, Integer> entry : shoppingCart.getProducts().entrySet()) {
                Product stockShopingCart = entry.getKey();
                //TODO:NO ESTA RESTANDO LOS PRODUCTOS BIEN
                amountFile = productLinket.getStock() - stockShopingCart.getStock();
                //ENVIA EL NUEVO STOCK
                productLinket.setStock(amountFile);
            }
            //POR ULTIMO SE ESCRIBE LA INFORMACION ACTUALIZADA EN EL ARCHIVO
            writer.write(productLinket + System.lineSeparator());
        }
        writer.close();
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

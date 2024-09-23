package service;

import classes.Customer;
import classes.Person;
import classes.Product;
import classes.ShoppingCart;
import constants.FileRoutes;
import constants.ServiceConstants;
import exceptions.MusicoletException;

import java.io.*;
import java.time.LocalDateTime;
import java.util.LinkedList;

public class CustomerService {

    public static void listProduct() {
    }

    public void createCustomer(BufferedReader reader) throws IOException {

        ServiceConstants.PERSON_SERVICE.createPerson();

        int idCustomer = ServiceConstants.PERSON_SERVICE.getLastIdPerson();

        System.out.println("\nEnter your address:");
        String address = reader.readLine();
        Customer customer = new Customer(idCustomer, address);
        LocalDateTime dateCreationCustomer = LocalDateTime.now();
        customer.setDateCreation(dateCreationCustomer);

        BufferedWriter writerCustomer = new BufferedWriter(new FileWriter(FileRoutes.RUTE_CUSTOMER, true));
        writerCustomer.write(customer + System.lineSeparator());
        writerCustomer.close();
    }

    public boolean searchIdCustomer(Person person) throws IOException {
        BufferedReader readerFileCustomer = new BufferedReader(new FileReader(FileRoutes.RUTE_CUSTOMER));
        String lineCustomer;
        boolean yes = false;
        while ((lineCustomer = readerFileCustomer.readLine()) != null) {
            String[] dataCustomer = lineCustomer.split(",");
            int idCustomer = Integer.parseInt(dataCustomer[0]);
            int idPerson = person.getId();

            if (idPerson == idCustomer) {
                yes = true;
                break;
            }
        }
        return yes;
    }

    public void menuCustomer(Person person) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        boolean exit = false;
        while (!exit) {
            try {
                System.out.printf("\nWELCOME %s to the menu CUSTOMER!!\n", person.getName());
                System.out.println("1. Buy product");
                System.out.println("2. List of products");
                System.out.println("3. Exit");
                System.out.println("Enter the option:");
                int option = Integer.parseInt(reader.readLine());
                switch (option) {
                    case 1:
                        buyProduct(reader);
                        break;
                    case 2:
                        listProduct();
                        break;
                    case 3:
                        System.out.println("Bye..");
                        exit = true;
                        break;
                    default:
                        System.out.println("The option is invalid");
                }
            } catch (NumberFormatException e) {
                System.out.println("\nEnter the number, please");
            }
        }
    }

    public void buyProduct(BufferedReader reader) throws IOException {
        LinkedList<Product> productList = ServiceConstants.PRODUCT_SERVICES.getProducts();
        ServiceConstants.PRODUCT_SERVICES.printTable(productList);
        ShoppingCart shoppingCart = new ShoppingCart();
        boolean exit = false;
        while (!exit) {
            try {
                System.out.println("\n1. Add product");
                System.out.println("2. Remove product");
                System.out.println("3. Finish and pay");
                System.out.println("4. List products");
                System.out.println("5. Exit");
                System.out.println("Enter the option:");
                int option = Integer.parseInt(reader.readLine());
                switch (option) {
                    case 1:
                        ServiceConstants.SHOPPING_CART_SERVICE.addProduct(productList, shoppingCart.getProducts());
                        break;
                    case 2:
                        ServiceConstants.SHOPPING_CART_SERVICE.removeProduct(shoppingCart.getProducts());
                        break;
                    case 3:
                        ServiceConstants.SHOPPING_CART_SERVICE.finishAndPay(shoppingCart);
                        break;
                    case 4:
                        ServiceConstants.SHOPPING_CART_SERVICE.listProduct(shoppingCart.getProducts());
                        break;
                    case 5:
                        exit = true;
                        break;
                    default:
                        System.out.println("\nThe option is invalid");
                }
            } catch (NumberFormatException e) {
                System.out.println("\nEnter the number, please");
            } catch (MusicoletException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }
}

package service;

import classes.Customer;
import classes.Person;
import classes.Product;
import constants.FileRoutes;
import constants.ServiceConstants;
import exceptions.MusicoletException;

import java.io.*;
import java.time.LocalDateTime;

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
        BufferedReader readerProducts = new BufferedReader(new FileReader(FileRoutes.RUTE_PRODUCTS));
        Product[] products = new Product[100];

        System.out.println("---------------------------------------------");
        System.out.printf("%-15s %-15s %-5s %-10s\n", "Name", "Price", "Id", "stock");
        String line;
        while ((line = readerProducts.readLine()) != null) {
            String[] data = line.split(",");
            if (data.length >= 4) { // Se asegura de que haya al menos 4 elementos
                String name = data[0];
                Double price = Double.parseDouble(data[1]);
                String stock = data[2];
                String id = data[3];

                System.out.printf("%-15s %,-15.2f %-5s %-10s%n", name, price, id, stock);
            } else {
                System.out.println("Linea mal formateada: " + line);
            }
        }
        System.out.println("---------------------------------------------");

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
                        ShoppingCartService.addProduct(reader, products);
                        break;
                    case 2:
                        ShoppingCartService.removeProduct(reader, products);
                        break;
                    case 3:
                        ShoppingCartService.finishAndPay();
                        break;
                    case 4:
                        ShoppingCartService.listProduct();
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

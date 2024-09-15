package service;

import classes.Customer;
import classes.Person;
import constants.FileRoutes;
import constants.ServiceConstants;

import java.io.*;
import java.time.LocalDateTime;

public class CustomerService {

    public void menuCustomer(Person person) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        Customer customer = new Customer();
        boolean exit = false;
        while (!exit) {
            try {
                System.out.println("\nWELCOME " + person.getName() + " to the menu CUSTOMER!!");
                System.out.println("1. Buy product");
                System.out.println("2. List of products");
                System.out.println("3. Exit");
                System.out.println("Enter the option:");
                int option = Integer.parseInt(reader.readLine());
                switch (option) {
                    case 1:
                        customer.buyProduct();
                        break;
                    case 2:
                        customer.listProduct();
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
}

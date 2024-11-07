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

    /**
     * Este metodo crea cuetas de clientes solicitantoles la informacion necesaria
     *
     * @param reader Se usa para leer las entradas por pantalla del usuario
     * @throws IOException Se usa para capturar errores de lectura
     */
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

    /**
     * Este metodo busca la id del usuario y la compara con las id almacenadas en los archivos txt
     *
     * @param person Se recibe la clase Person con sus atributos y metodos
     * @return yes Este retorno devuelve si se pudo encontrar a la persona o no
     * @throws IOException Se usa para capturar errores de lectura
     */
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

    /**
     * Este metodo imprime el menu del Usuario por pantalla
     *
     * @param person Se recibe la clase Person con sus atributos y metodos
     * @throws NumberFormatException Se usa por si el usuario no ingresa un digito cuando es solicitado
     * @throws IOException           Se usa para capturar errores de lectura
     */
    public void menuCustomer(Person person) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        boolean exit = false;
        while (!exit) {
            try {
                System.out.printf("\nWELCOME %s to the menu CUSTOMER!!\n", person.getName());
                System.out.println("1. Buy product");
                System.out.println("2. Exit");
                System.out.println("Enter the option:");
                int option = Integer.parseInt(reader.readLine());
                switch (option) {
                    case 1:
                        buyProduct(reader);
                        break;
                    case 2:
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

    /**
     * Este metodo imprime por pantalla  el menu al usuario para que ingrese una opcion
     *
     * @param reader Se usa para leer entradas del usuario por pantalla
     * @throws NumberFormatException Ocurre cuando el usuario no ingresa un numero cuando es solicitado
     */
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
                        ServiceConstants.SHOPPING_CART_SERVICE.finishAndPay(shoppingCart, productList);
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

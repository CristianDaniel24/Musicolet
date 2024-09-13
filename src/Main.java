import classes.Customer;
import classes.Employee;
import classes.Person;
import classes.Validaciones;
import constants.FileRoutes;
import exceptions.ExceptionValidateAccount;
import service.ProductService;

import java.io.*;
import java.time.LocalDateTime;

public class Main {

    public static final ProductService PRODUCT_SERVICES = new ProductService();

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        menuLogin(reader);
    }

    public static void menuLogin(BufferedReader reader) throws IOException {
        boolean exit = false;
        while (!exit) {
            try {
                System.out.println("\nWELCOME TO MUSICOLET!!");
                System.out.println("\nLOGIN");
                System.out.println("1. Create account");
                System.out.println("2. Login");
                System.out.println("3. Exit");
                System.out.println("Enter the option:");
                int option = Integer.parseInt(reader.readLine());
                switch (option) {
                    case 1:
                        createAccount(reader);
                        System.out.println("....");
                        break;
                    case 2:
                        login(reader);
                        break;
                    case 3:
                        System.out.println("Exit..");
                        exit = true;
                        break;
                    default:
                        System.out.println("\nThe option is invalid");
                }
            } catch (NumberFormatException e) {
                System.out.println("\nEnter the number, please");
            }
        }
    }

    public static void createAccount(BufferedReader reader) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FileRoutes.RUTE_PERSON, true))) {
            int lastId = PRODUCT_SERVICES.getLastId();

            //SOLICITAR LOS DATOS DE LA PERSONA
            Person person = new Person();
            Validaciones validaciones = new Validaciones();

            System.out.println("\nEnter your name:");
            String nameInput = reader.readLine();
            validaciones.validateName(nameInput);
            person.setName(nameInput);

            System.out.println("Enter your phone:");
            String phoneInput = reader.readLine();
            validaciones.validatePhone(phoneInput);
            person.setPhone(phoneInput);

            System.out.println("Enter your document:");
            String documentInput = reader.readLine();
            validaciones.validateDocument(documentInput);
            person.setDocument(documentInput);

            System.out.println("Enter your mail:");
            String mailInput = reader.readLine();
            validaciones.validateMail(mailInput);
            person.setMail(mailInput);

            System.out.println("Enter your password:");
            String passwordInput = reader.readLine();
            validaciones.validatePassword(passwordInput);
            person.setPassword(passwordInput);

            person.setId(lastId + 1);

            writer.write(person.toString() + System.lineSeparator());
            writer.close();
            try {
                boolean exit = false;
                while (!exit) {
                    System.out.println("\nSelect your role");
                    System.out.println("1. Employee");
                    System.out.println("2. Customer");
                    System.out.println("3. Exit");
                    System.out.println("Enter the option:");
                    int option = Integer.parseInt(reader.readLine());
                    switch (option) {
                        case 1:
                            //SOLICITAR LOS DATOS DEL EMPLEADO
                            System.out.println("\nEnter your id:");
                            int idEmployee = Integer.parseInt(reader.readLine());
                            String rol = "Employee";
                            System.out.println("Enter your salary:");
                            Double salary = Double.parseDouble(reader.readLine());
                            Employee employee = new Employee(idEmployee, rol, salary);
                            LocalDateTime dateCreationEmployee = LocalDateTime.now();
                            employee.setDateCreation(dateCreationEmployee);

                            BufferedWriter writerEmployee = new BufferedWriter(new FileWriter(FileRoutes.RUTE_EMPLOYEE, true));
                            writerEmployee.write(employee.toString() + System.lineSeparator());
                            writerEmployee.close();
                            break;
                        case 2:
                            //SOLICITAR LOS DATOS DEL USUARIO
                            System.out.println("\nEnter your id:");
                            int idCustomer = Integer.parseInt(reader.readLine());
                            System.out.println("Enter your address:");
                            String address = reader.readLine();
                            Customer customer = new Customer(idCustomer, address);
                            LocalDateTime dateCreationCustomer = LocalDateTime.now();
                            customer.setDateCreation(dateCreationCustomer);

                            BufferedWriter writerCustomer = new BufferedWriter(new FileWriter(FileRoutes.RUTE_CUSTOMER, true));
                            writerCustomer.write(customer.toString() + System.lineSeparator());
                            writerCustomer.close();
                            break;
                        case 3:
                            exit = true;
                        default:
                            System.out.println("\nThe option is invalid");
                    }
                }

            } catch (NumberFormatException e) {
                System.out.println("\nEnter the number, please");
            }
            writer.write(person + System.lineSeparator());
            writer.close();
            PRODUCT_SERVICES.details();
        } catch (IOException e) {
            System.out.println("\nError reading file");
        } catch (NumberFormatException e) {
            System.out.println("\nEnter the number, please");
        } catch (ExceptionValidateAccount e) {
            System.out.println("\nError " + e.getMessage());
        }
    }

    public static void login(BufferedReader reader) throws IOException {
        BufferedReader readerFile = new BufferedReader(new FileReader(FileRoutes.RUTE_PERSON));

        System.out.println("Enter your mail:");
        String mailInput = reader.readLine();

        System.out.println("Enter your password:");
        String passwordInput = reader.readLine();
        boolean login = false;
        String line;
        Person person = new Person();
        while ((line = readerFile.readLine()) != null) {
            String[] data = line.split(",");
            String mail = data[3];
            String password = data[4];

            if (mail.equals(mailInput) && password.equals(passwordInput)) {
                login = true;
                //RELLENAR CON LOS SETTERS
                //????
                break;
            }
        }
        if (login) {
            //BUSQUEDA DE EL ID DE EMPLOYEE
            BufferedReader readerFileEmployee = new BufferedReader(new FileReader(FileRoutes.RUTE_EMPLOYEE));
            String lineEmployee;
            while ((lineEmployee = readerFileEmployee.readLine()) != null) {
                String[] dataEmployee = lineEmployee.split(",");
                int idEmployee = Integer.parseInt(dataEmployee[0]);
                int idPerson = person.getId();

                if (idPerson == idEmployee) {
                    readerFileEmployee.close();
                    menuEmployee(reader);
                    break;
                }
            }

            //BUSQUEDA DE EL ID DE CUSTOMER
            BufferedReader readerFileCustomer = new BufferedReader(new FileReader(FileRoutes.RUTE_CUSTOMER));
            String lineCustomer;
            while ((lineCustomer = readerFileCustomer.readLine()) != null) {
                String[] dataCustomer = lineCustomer.split(",");
                int idCustomer = Integer.parseInt(dataCustomer[0]);
                int idPerson = person.getId();

                if (idPerson == idCustomer) {
                    readerFileCustomer.close();
                    menuCustomer(reader);
                    break;
                }
            }
        } else {
            System.out.println("\nThe mail or password are invalids");
        }
        readerFile.close();
    }


    public static void menuEmployee(BufferedReader reader) throws IOException {
        BufferedReader readerProducts = new BufferedReader(new FileReader(FileRoutes.RUTE_PRODUCTS));
        BufferedWriter writer = new BufferedWriter(new FileWriter(FileRoutes.RUTE_PRODUCTS, true));
        boolean exit = false;
        while (!exit) {
            try {
                System.out.println("\nWELCOME TO THE EMPLOYEE MENU!!");
                System.out.println("1. Add product");
                System.out.println("2. Edit product");
                System.out.println("3. Delete product");
                System.out.println("4. List of products");
                System.out.println("5. Exit");
                System.out.println("Enter the option:");
                int option = Integer.parseInt(reader.readLine());

                switch (option) {
                    case 1:
                        PRODUCT_SERVICES.addProduct(reader);
                        break;
                    case 2:
                        PRODUCT_SERVICES.editProduct(reader, readerProducts, writer);
                        readerProducts.close();
                        writer.close();
                        break;
                    case 3:
                        PRODUCT_SERVICES.deletedProduct(reader, readerProducts);
                        readerProducts.close();
                        break;
                    case 4:
                        PRODUCT_SERVICES.listProductDeleted();
                        break;
                    case 5:
                        System.out.println("Bye..");
                        exit = true;
                        break;
                    default:
                        System.out.println("\nThe option is invalid");
                }
            } catch (NumberFormatException e) {
                System.out.println("\nEnter the number, please");
            }
        }
    }

    public static void menuCustomer(BufferedReader reader) throws IOException {
        Customer customer = new Customer();
        boolean exit = false;
        while (!exit) {
            try {
                System.out.println("\nWELCOME TO THE CUSTOMER'S MENU!!");
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
}

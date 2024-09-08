import classes.Person;
import classes.Validaciones;
import exceptions.ExceptionValidateAccount;
import service.ProductService;

import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String rute = "db/Person.txt";

        String ruteProducts = "db/Products.txt";

        BufferedReader readerFile = new BufferedReader(new FileReader(rute));

        BufferedReader readerProducts = new BufferedReader(new FileReader(ruteProducts));

        menulogin(reader, rute, readerFile, readerProducts, ruteProducts);
    }

    public static void menulogin(BufferedReader reader, String rute, BufferedReader readerFile, BufferedReader readerProducts, String ruteProducts) throws IOException {
        ProductService productService = new ProductService();
        boolean exit = false;
        while (!exit) {
            try {
                System.out.println("\nLOGIN");
                System.out.println("1. Create account");
                System.out.println("2. Login");
                System.out.println("3. Exit");
                System.out.println("Enter the option:");
                int option = Integer.parseInt(reader.readLine());
                switch (option) {
                    case 1:
                        try (BufferedWriter writer = new BufferedWriter(new FileWriter(rute, true))) {
                            int lastId = productService.getLastId(rute);

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

                            writer.write(person + System.lineSeparator());
                            writer.close();
                            productService.details(rute);
                        } catch (IOException e) {
                            System.out.println("\nError reading file");
                        } catch (NumberFormatException e) {
                            System.out.println("\nEnter the number, please");
                        } catch (ExceptionValidateAccount e) {
                            System.out.println("\nError " + e.getMessage());
                        }
                        break;
                    case 2:
                        System.out.println("Enter your mail:");
                        String mailInput = reader.readLine();

                        System.out.println("Enter your password:");
                        String passwordInput = reader.readLine();
                        boolean login = false;
                        String line;
                        while ((line = readerFile.readLine()) != null) {
                            String[] data = line.split(",");
                            String mail = data[3];
                            String password = data[4];

                            if (mail.equals(mailInput) && password.equals(passwordInput)) {
                                login = true;
                                break;
                            }
                        }
                        if (login) {
                            menuMajor(reader, ruteProducts, readerProducts);
                        } else {
                            System.out.println("\nThe mail or password are invalids");
                        }
                        break;
                    case 3:
                        System.out.println("Exit..");
                        exit = true;
                        break;
                    default:
                        System.out.println("\nThe option is invalid");
                }
            } catch (NumberFormatException e) {
                System.out.println("\nEnter the number, plase");
            }
        }
    }

    public static void menuMajor(BufferedReader reader, String ruteProducts, BufferedReader readerProducts) throws IOException {
        boolean exit = false;
        while (!exit) {
            try {
                System.out.println("\nWELCOME!!");
                System.out.println("\nMUSICOLET MENU");
                System.out.println("1. Add product");
                System.out.println("2. Edit product");
                System.out.println("3. Delete product");
                System.out.println("4. List of products");
                System.out.println("5. Exit");
                System.out.println("Enter the option:");
                int option = Integer.parseInt(reader.readLine());

                ProductService productService = new ProductService();
                switch (option) {
                    case 1:
                        productService.addProduct(reader, ruteProducts);
                        break;
                    case 2:
                        productService.editProduct(reader, readerProducts, ruteProducts);
                        break;
                    case 3:
                        productService.deletedProduct(reader, readerProducts, ruteProducts);
                        break;
                    case 4:
                        productService.listProductDeleted(ruteProducts);
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
}
import classes.Person;
import constants.FileRoutes;
import constants.ServiceConstants;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        menuLogin();
    }

    public static void menuLogin() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

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

    public static void createAccount(BufferedReader reader) throws IOException {
        try {
            boolean exit = false;
            while (!exit) {
                System.out.println("\nSelect your role");
                System.out.println("1. Employee");
                System.out.println("2. Customer");
                System.out.println("3. Exit");
                System.out.println("Enter the option:");
                int option = Integer.parseInt(reader.readLine());

                if (option == 1) {
                    ServiceConstants.EMPLOYEE_SERVICE.createEmployee(reader);
                } else if (option == 2) {
                    ServiceConstants.CUSTOMER_SERVICE.createCustomer(reader);
                } else if (option == 3) {
                    exit = true;
                } else {
                    System.out.println("\nThe option is invalid");
                }
            }
        } catch (NumberFormatException e) {
            System.out.println("\nEnter the number, please");
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
            String name = data[0];
            String phone = data[1];
            String document = data[2];
            String mail = data[3];
            String password = data[4];
            int id = Integer.parseInt(data[5]);

            if (mail.equals(mailInput) && password.equals(passwordInput)) {
                login = true;
                person.setName(name);
                person.setPhone(phone);
                person.setDocument(document);
                person.setMail(mail);
                person.setPassword(password);
                person.setId(id);
                break;
            }
        }
        if (login) {
            ServiceConstants.EMPLOYEE_SERVICE.searchIdEmployee();

            ServiceConstants.CUSTOMER_SERVICE.searchIdCustomer();
        } else {
            System.out.println("\nThe mail or password are invalids");
        }
        readerFile.close();
    }
}

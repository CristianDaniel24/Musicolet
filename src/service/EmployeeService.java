package service;

import classes.Employee;
import constants.FileRoutes;
import constants.ServiceConstants;

import java.io.*;
import java.time.LocalDateTime;

public class EmployeeService {

    public void menuEmployee() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
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
                        ServiceConstants.PRODUCT_SERVICES.addProduct(reader);
                        break;
                    case 2:
                        ServiceConstants.PRODUCT_SERVICES.editProduct(reader, readerProducts, writer);
                        readerProducts.close();
                        writer.close();
                        break;
                    case 3:
                        ServiceConstants.PRODUCT_SERVICES.deletedProduct(reader, readerProducts);
                        readerProducts.close();
                        break;
                    case 4:
                        ServiceConstants.PRODUCT_SERVICES.listProductDeleted();
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

    public void createEmployee(BufferedReader reader) throws IOException {

        ServiceConstants.PERSON_SERVICE.createPerson();

        int idEmployee = ServiceConstants.PERSON_SERVICE.getLastIdPerson();

        String rol = "Employee";
        System.out.println("\nEnter your salary:");
        Double salary = Double.parseDouble(reader.readLine());
        Employee employee = new Employee(idEmployee, rol, salary);
        LocalDateTime dateCreationEmployee = LocalDateTime.now();
        employee.setDateCreation(dateCreationEmployee);

        BufferedWriter writerEmployee = new BufferedWriter(new FileWriter(FileRoutes.RUTE_EMPLOYEE, true));
        writerEmployee.write(employee + System.lineSeparator());
        writerEmployee.close();
    }

    public void searchIdEmployee() throws IOException {
        BufferedReader readerFileEmployee = new BufferedReader(new FileReader(FileRoutes.RUTE_EMPLOYEE));
        String lineEmployee;
        while ((lineEmployee = readerFileEmployee.readLine()) != null) {
            String[] dataEmployee = lineEmployee.split(",");
            int idEmployee = Integer.parseInt(dataEmployee[0]);
            int idPerson = ServiceConstants.PERSON_SERVICE.createPerson().getId();

            if (idPerson == idEmployee) {
                readerFileEmployee.close();
                //SE LLAMA EL MENU??
                menuEmployee();
            }
        }
    }
}

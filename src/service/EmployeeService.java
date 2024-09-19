package service;

import classes.Employee;
import classes.Person;
import constants.FileRoutes;
import constants.ServiceConstants;

import java.io.*;
import java.time.LocalDateTime;

public class EmployeeService {

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

    public boolean searchIdEmployee(Person person) throws IOException {
        BufferedReader readerFileEmployee = new BufferedReader(new FileReader(FileRoutes.RUTE_EMPLOYEE));
        String lineEmployee;
        boolean yes = false;
        while ((lineEmployee = readerFileEmployee.readLine()) != null) {
            String[] dataEmployee = lineEmployee.split(",");
            int idEmployee = Integer.parseInt(dataEmployee[0]);
            int idPerson = person.getId();

            if (idPerson == idEmployee) {
                yes = true;
                break;
            }
        }
        return yes;
    }

    public void menuEmployee(Person person) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        boolean exit = false;
        while (!exit) {
            try {
                System.out.printf("\nWELCOME %s to the menu EMPLOYEE!!\n", person.getName());
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
                        ServiceConstants.PRODUCT_SERVICES.editProduct(reader);
                        break;
                    case 3:
                        ServiceConstants.PRODUCT_SERVICES.deletedProduct();
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


}

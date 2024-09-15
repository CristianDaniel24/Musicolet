package constants;

import classes.Validaciones;
import service.CustomerService;
import service.EmployeeService;
import service.PersonService;
import service.ProductService;

public class ServiceConstants {
    public static final ProductService PRODUCT_SERVICES = new ProductService();
    public static final PersonService PERSON_SERVICE = new PersonService();
    public static final EmployeeService EMPLOYEE_SERVICE = new EmployeeService();
    public static final CustomerService CUSTOMER_SERVICE = new CustomerService();
    public static final Validaciones VALIDACIONES = new Validaciones();
}

package constants;

import classes.Bill;
import classes.Validaciones;
import service.*;

public class ServiceConstants {
    public static final ProductService PRODUCT_SERVICES = new ProductService();
    public static final PersonService PERSON_SERVICE = new PersonService();
    public static final EmployeeService EMPLOYEE_SERVICE = new EmployeeService();
    public static final CustomerService CUSTOMER_SERVICE = new CustomerService();
    public static final Validaciones VALIDACIONES = new Validaciones();
    public static final ShoppingCartService SHOPPING_CART_SERVICE = new ShoppingCartService();
    public static final Bill BILL = new Bill();
}

package service;

import classes.Bill;
import classes.Product;
import classes.ShoppingCart;
import constants.ReaderConstants;
import constants.ServiceConstants;
import exceptions.MusicoletException;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class ShoppingCartService {

    public ShoppingCartService() {
    }

    /**
     * Este metodo permite al usuario agregar productos al carrito de compras
     *
     * @param productList          Se recibe la lista con los productos
     * @param productsShoppingCart Se recibe el carrito de compras
     * @throws MusicoletException    Ocurre cuando hay un error de la aplicacion
     * @throws NumberFormatException Ocurre cuando el usuario no ingresa un digito cuando es solicitado
     * @throws IOException           Se usa para capturar errores de lectura
     */
    public void addProduct(LinkedList<Product> productList, HashMap<Product, Integer> productsShoppingCart) throws MusicoletException {
        try {
            //PREGUNTA LA ID DEL PRODUCTO AL USUARIO
            System.out.println("Enter the id for add ShoppingCart:");
            int idInput = Integer.parseInt(ReaderConstants.reader.readLine());

            boolean found = false;
            for (Product products : productList) {
                //VALIDA SI EXISTE LA ID DEL PRODUCTO PREGUNTADO
                //SI EXISTEN PRODUCTOS CON ESA ID ENTONCES SI HAY EN LA BODEGA
                if (idInput == products.getId()) {
                    found = true;
                    //SE CONSIGUE LA CANTIDAD DE CADA PRODUCTO
                    //TODO: ESTO DE ABAJO SOLUCIONA EL PROBLEMA DEL PRODUCTO NULL A LA HORA DE CONSEGUIR LA CANTIDAD
                    int quantityProducts = productsShoppingCart.getOrDefault(products, 0);

                    //SE PREGUNTA LA CANTIDAD DE PRODUCTOS A EL USUARIO
                    System.out.println("Enter the quantity of products you want buy");
                    int stockInput = Integer.parseInt(ReaderConstants.reader.readLine());
                    //SE REALIZA LA SUMA DE LOS PRODUCTOS QUE PIDIO EL USUARIO CON LOS DEL SHOPPINGCART
                    stockInput += quantityProducts;

                    //AHORA SE VERIFICA QUE HAYAN LOS SUFICIENTES PRODUCTOS EN LA BODEGA
                    if (products.getStock() >= stockInput) {
                        //SE AGREGA LA CANTIDAD DE PRODUCTOS AL SHOPPINGCART
                        productsShoppingCart.put(products, stockInput);
                        System.out.println("\nThe products were added to the ShoppingCart successfully =)");
                        break;
                    } else {
                        System.out.println("\nSorry, we don't have that quantity of products requested =(");
                        break;
                    }
                }
            }
            if (!found) {
                throw new MusicoletException("\nThe id is invalid");
            }
        } catch (NumberFormatException e) {
            System.out.println("Enter the number, please");
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (MusicoletException e) {
            System.out.println(e.getMessage());
        }

    }

    /**
     * Este metodo permite al usuario eliminar productos del carrito de compras
     *
     * @param productsShoppingCart Se recibe el carrito de compras
     * @throws IOException           Se usa para capturar errores de lectura
     * @throws NumberFormatException Ocurre cuando el usuario no ingresa un digito cuando es solicitado
     */
    public void removeProduct(HashMap<Product, Integer> productsShoppingCart) throws IOException {
        try {
            if (productsShoppingCart.isEmpty()) {
                System.out.println("\nThe shoppingCart is empty =(");
            } else {
                System.out.println("\nEnter the id of deleted");
                int idInput = Integer.parseInt(ReaderConstants.reader.readLine());

                //SE UTILIZO EL USO DE UN INTERRUPTOR PARA QUE MUESTRE EL MENSAJE DE ID INVALIDA CORRECTAMENTE
                boolean deletedProduct = false;
                for (Product product : productsShoppingCart.keySet()) {
                    if (product.getId() == idInput) {
                        productsShoppingCart.remove(product);
                        deletedProduct = true;
                        break;
                    }
                }
                if (deletedProduct) {
                    System.out.println("\nThe product has been successfully removed =)");
                } else {
                    System.out.println("\nThe product you want to delete does not exist or is invalid =(");
                }
            }
        } catch (NumberFormatException e) {
            System.out.println("\nEnter the number, please");
        }
    }

    /**
     * Este metodo imprime por pantalla una lista de los productos del carrito de compras
     *
     * @param productsShoppingCart Se recibe el carrito de compras
     */
    public void listProduct(HashMap<Product, Integer> productsShoppingCart) {
        if (productsShoppingCart.isEmpty()) {
            System.out.println("\nThe shoppingCart is empty =(");
        } else {
            System.out.println("\n--------------------------------------------------------------");
            System.out.printf("%-10s %-20s %-10s %-10s %-10s\n", "ID", "Name", "Price", "Subtotal", "Amount");
            for (Map.Entry<Product, Integer> entry : productsShoppingCart.entrySet()) {
                Product product = entry.getKey();
                Integer quantity = entry.getValue();
                Double subtotal = product.getPrice() * quantity;
                System.out.printf("%-10s %-20s $%,-8.2f  $%,-10.2f %-10s\n", product.getId(), product.getName(), product.getPrice(), subtotal, quantity);
            }
            System.out.println("--------------------------------------------------------------");
        }
    }

    /**
     * Este metodo permite al usuario finalizar la compra
     *
     * @param shoppingCart Se recibe el carrito de compras
     * @param productList  Se recibe la lista de los productos de la tienda
     * @throws IOException Se usa para capturar errores de lectura
     */
    public void finishAndPay(ShoppingCart shoppingCart, LinkedList<Product> productList) throws IOException {
        if (!shoppingCart.getProducts().isEmpty()) {
            Double total = sumProducts(shoppingCart.getProducts());

            LocalDateTime dateCreation = LocalDateTime.now();
            LocalDateTime datePayment = LocalDateTime.now();

            Bill bill = new Bill(shoppingCart.getId(), total, dateCreation, datePayment);

            shoppingCart.setBill(bill);
            ServiceConstants.BILL_SERVICE.printBIll(shoppingCart, shoppingCart.getProducts(), bill);
            //DESPUES DE LA FACTURA SE HACE LA ACTUALIZACION DEL ARCHIVO TXT CON EL NUEVO STOCK
            int resultRest;
            //SE ITERAN LOS PRODUCTOS DEL CARRITO
            for (Map.Entry<Product, Integer> entry : shoppingCart.getProducts().entrySet()) {
                Product productShoppingCart = entry.getKey();
                int quantity = entry.getValue();
                for (Product productStore : productList) {
                    //SE BUSCA EL ID DE LOS PRODUCTOS EN LA BODEGA
                    if (productShoppingCart.getId().equals(productStore.getId())) {
                        //ACTUALIZAR EL STOCK DE LOS PRODUCTOS DE LA BODEGA
                        resultRest = productStore.getStock() - quantity;
                        //SE LE ENVIA EL RESULTADO DE LA RESTA A LA LISTA
                        productStore.setStock(resultRest);
                    }
                }
            }
            //ESCRIBE EN EL ARCHIVO TXT LA NUEVA CANTIDAD ACTUALIZADA
            ServiceConstants.PRODUCT_SERVICES.writeFinishAndPay(productList);
            //SE GUARDA LA FACTURA CREADA EN UN ARCHIVO TXT
            ServiceConstants.BILL_SERVICE.saveBills(shoppingCart, bill);
            //POR ULTIMO SE LIMPIA EL CARRITO DE COMPRAS
            shoppingCart.getProducts().clear();
        } else {
            System.out.println("\nThe ShoppingCart is empty =(");
        }
    }

    /**
     * Este metodo suma el precio de los productos del carrito de compras y retorna el total
     *
     * @param productsShoppingCart Se recibe el carrito de compras
     * @return Retorna la suma de los precios de los productos del carrito de compras
     */
    public double sumProducts(HashMap<Product, Integer> productsShoppingCart) {
        double total = 0.0;
        for (Map.Entry<Product, Integer> entry : productsShoppingCart.entrySet()) {
            Product product = entry.getKey();
            Integer quantity = entry.getValue();
            double priceTotalProduct = product.getPrice() * quantity;
            total += priceTotalProduct;
        }
        return total;
    }
}

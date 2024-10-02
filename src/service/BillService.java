package service;

import classes.Bill;
import classes.Product;
import classes.ShoppingCart;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class BillService {

    public void printBIll(ShoppingCart shoppingCart, HashMap<Product, Integer> productsShoppingCart, Bill bill) {
        System.out.println("\n------------------------------------BILL-------------------------------------------------------");
        System.out.printf("%-2s %-2s\n", "ID:", shoppingCart.getId());
        System.out.printf("%-31s %-35s %-15s %-1s\n", "Name:", "UnitValue:", "Amount:", "SubTotal:");
        for (Map.Entry<Product, Integer> entry : productsShoppingCart.entrySet()) {
            Product product = entry.getKey();
            Integer quantity = entry.getValue();
            Double priceProduct = product.getPrice() * quantity;
            System.out.printf("%-31s $%,-34.2f %-15s $%,-1.2f\n", product.getName(), product.getPrice(), quantity, priceProduct);
        }

        System.out.printf("\n%-31s %-51s %-1s\n", "Date Creation:", "Date Payment:", "Total:");
        System.out.printf("%tB/%td/%tY %tI:%tM:%tS %-4Tp %tB/%td/%tY %tI:%tM:%tS %-24Tp $%,-1.2f\n", bill.getDateCreation(), bill.getDateCreation(), bill.getDateCreation(), bill.getDateCreation(), bill.getDateCreation(), bill.getDateCreation(), bill.getDateCreation(), bill.getDatePayment(), bill.getDatePayment(), bill.getDatePayment(), bill.getDatePayment(), bill.getDatePayment(), bill.getDatePayment(), bill.getDatePayment(), bill.getTotal());
        System.out.println("-----------------------------------------------------------------------------------------------");
    }

    public void saveBills(ShoppingCart shoppingCart, Bill bill) {
        //FORMATO PARA LAS FACTURAS
        String line1 = "\n------------------------------------BILL-------------------------------------------------------";
        String line2 = String.format("ID: %-2d\n", shoppingCart.getId());
        String line3 = "";
        for (Map.Entry<Product, Integer> entry : shoppingCart.getProducts().entrySet()) {
            Product product = entry.getKey();
            Integer quantity = entry.getValue();
            Double priceProduct = product.getPrice() * quantity;
            line3 = String.format("Name: %-31s UnitValue: %-35f Amount: %-15d SubTotal: %-1f\n", product.getName(), product.getPrice(), quantity, priceProduct);
        }
        String line4 = String.format("Date Creation: %tB/%td/%tY %tI:%tM:%tS %-4Tp Date Payment: %tB/%td/%tY %tI:%tM:%tS %-24Tp Total: $%,-1.2f\n", bill.getDateCreation(), bill.getDateCreation(), bill.getDateCreation(), bill.getDateCreation(), bill.getDateCreation(), bill.getDateCreation(), bill.getDateCreation(), bill.getDatePayment(), bill.getDatePayment(), bill.getDatePayment(), bill.getDatePayment(), bill.getDatePayment(), bill.getDatePayment(), bill.getDatePayment(), bill.getTotal());
        String line5 = "-----------------------------------------------------------------------------------------------";

        //CREACION DE LOS ARCHIVOS TXT
        String directory = "db/Bills";
        int count = 1;

        boolean fileCreated = false;
        //SE ITERA EL BLOQUE DE CODIGO HASTA QUE EL ARCHIVO TXT NO EXISTA
        while (!fileCreated) {
            String nameFile = "bill" + count + ".txt";
            File file = new File(directory, nameFile);

            //SE VERIFICA SI EL ARCHIVO YA EXISTE
            if (!file.exists()) {
                try {
                    //SI EL ARCHIVO NO EXISTE ENTONCES SE CREA
                    fileCreated = file.createNewFile();
                    if (fileCreated) {
                        //SE LLENA EL TXT CON EL FORMATO CREADO ANTERIORMENTE
                        FileWriter fileWriter = new FileWriter(file);
                        fileWriter.write(line1);
                        fileWriter.write(line2);
                        fileWriter.write(line3);
                        fileWriter.write(line4);
                        fileWriter.write(line5);
                        fileWriter.close();
                    }
                } catch (IOException e) {
                    System.out.println("\nAn error occurred while creating or writing to the file: " + e.getMessage());
                }
            } else {
                //SI YA EXISTE EL ARCHIVO ENTONCES SE LE AGREGA 1 AL CONTADOR
                count++;
            }
        }
    }
}

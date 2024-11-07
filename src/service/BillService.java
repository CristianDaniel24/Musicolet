package service;

import classes.Bill;
import classes.Product;
import classes.ShoppingCart;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BillService {

    /**
     * Este metodo imprime la factura del usuario
     *
     * @param shoppingCart         Se recibe la clase ShoppingCart con todos sus atributos y metodos
     * @param productsShoppingCart Se recibe el carrito de compras del usuario
     * @param bill                 Se recibe la clase Bill con informacion faltante de las facturas
     */
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
        System.out.printf("%tm/%td/%tY %tI:%tM:%tS %-11Tp %tm/%td/%tY %tI:%tM:%tS %-31Tp $%,-1.2f\n", bill.getDateCreation(), bill.getDateCreation(), bill.getDateCreation(), bill.getDateCreation(), bill.getDateCreation(), bill.getDateCreation(), bill.getDateCreation(), bill.getDatePayment(), bill.getDatePayment(), bill.getDatePayment(), bill.getDatePayment(), bill.getDatePayment(), bill.getDatePayment(), bill.getDatePayment(), bill.getTotal());
        System.out.println("-----------------------------------------------------------------------------------------------");
    }

    /**
     * Este metodo guarda las facturas realizadas en un archivo txt
     *
<<<<<<< HEAD
     * @param shoppingCart Se recibe el carrito de compras
=======
     * @param shoppingCart Se recibe la clase ShoppingCart con todos sus atributos y metodos
>>>>>>> 39b0fe96bbdd02b43fef02d3b4ef250880125db4
     * @param bill         Se recibe la clase Bill con informacion faltante de las facturas
     * @throws IOException Ocurre cuando se encuentra un error a la hora de crear un archivo nuevo
     */
    public void saveBills(ShoppingCart shoppingCart, Bill bill) {
        LinkedList<String> lines = new LinkedList<>();
        //FORMATO PARA LAS FACTURAS
        lines.add("------------------------------------BILL-------------------------------------------------------");
        lines.add(String.format("ID: %-2d", shoppingCart.getId()));
        lines.add(String.format("%-31s %-35s %-15s %-1s", "Name:", "UnitValue:", "Amount:", "SubTotal:"));
        for (Map.Entry<Product, Integer> entry : shoppingCart.getProducts().entrySet()) {
            Product product = entry.getKey();
            Integer quantity = entry.getValue();
            Double priceProduct = product.getPrice() * quantity;
            lines.add(String.format("%-31s $%,-34.2f %-15s $%,-1.2f", product.getName(), product.getPrice(), quantity, priceProduct));
        }
        lines.add(String.format("%-31s %-51s %-1s", "Date Creation:", "Date Payment:", "Total:"));
        lines.add(String.format("%tm/%td/%tY %tI:%tM:%tS %-11Tp %tm/%td/%tY %tI:%tM:%tS %-31Tp $%,-1.2f", bill.getDateCreation(), bill.getDateCreation(), bill.getDateCreation(), bill.getDateCreation(), bill.getDateCreation(), bill.getDateCreation(), bill.getDateCreation(), bill.getDatePayment(), bill.getDatePayment(), bill.getDatePayment(), bill.getDatePayment(), bill.getDatePayment(), bill.getDatePayment(), bill.getDatePayment(), bill.getTotal()));
        lines.add("-----------------------------------------------------------------------------------------------");

        String directory = "db/Bills";
        File folder = new File(directory);

        //SE LLAMA EL METODO QUE RETORNA EL NUEVO NOMBRE DEL ARCHIVO
        String newFileName = newNameFile();
        File newFile = new File(folder, newFileName);

        try {
            if (newFile.createNewFile()) {
                //SE ESCRIBE EL TXT CON EL FORMATO CREADO ANTERIORMENTE
                FileWriter fileWriter = new FileWriter(newFile);
                for (String linesInput : lines) {
                    fileWriter.write(linesInput + System.lineSeparator());
                }
                fileWriter.close();
            } else {
                System.out.println("Error creating file =(");
            }
        } catch (IOException e) {
            System.out.println("\nAn error occurred while creating or writing to the file: " + e.getMessage());
        }
    }

    /**
     * Este metodo crea un nuevo nombre para los archivos txt de las facturas
     *
     * @return Este metodo retorna el nuevo nombre de los archivos txt para las facturas
     * @throws IOException Ocurre cuando se encuentra un error a la hora de crear un archivo nuevo
     */
    public String newNameFile() {
        //CREACION DE LOS ARCHIVOS TXT
        String directory = "db/Bills";
        //SE CREA UN OBJETO DE TIPO FILE CON LA RUTA DE LA CARPETA
        File folder = new File(directory);

        int numberMax = 0;
        File[] files = folder.listFiles((_, name) -> name.startsWith("bill_") && name.endsWith(".txt"));

        if (files != null) {
            Pattern regexName = Pattern.compile("bill_(\\d+)\\.txt");

            //SE RECORREN TODOS LOS ARCHIVOS QUE CUMPLEN CON EL FILTRO
            for (File file : files) {
                //SE OBTIENE EL NOMBRE DEL ARCHIVO
                String fileName = file.getName();
                //SE APLICA EL PATRON DEL REGEX PARA PODER BUSCAR
                Matcher matcher = regexName.matcher(fileName);
                if (matcher.matches()) {
                    //SE CONVIERTE EL NUMERO DEL GRUPO DEL REGEX A ENTERO
                    int fileNumber = Integer.parseInt(matcher.group(1));
                    if (fileNumber > numberMax) {
                        /*
                        SI EL NUMERO ENCONTRADO EN EL NOMBRE ES MAYOR QUE EL DE NUMBERMAX
                        ENTONCES SE ACTUALIZA LA VARIABLE CON ESE NUMERO PARA LUEGO  SUMARLE 1
                         */
                        numberMax = fileNumber;
                    }
                }
            }
        }
        //SE HACE LA SUMA LA NUMERO MAS ALTO ENCONTRADO
        numberMax += 1;
        String newFileName = "bill_" + numberMax + ".txt";
        return newFileName;
    }
}

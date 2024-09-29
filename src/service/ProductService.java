package service;

import classes.Product;
import constants.FileRoutes;

import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ProductService {

    public ProductService() {
    }

    private static Product getProduct(String[] data, String name, Double price) {
        Integer stock = Integer.parseInt(data[2]);
        Integer id = Integer.parseInt(data[3]);
        LocalDateTime dateCreation = LocalDateTime.parse(data[4]);

        LocalDateTime dateEdition = null;
        if (!data[5].equals("null")) {
            dateEdition = LocalDateTime.parse(data[5]);
        }

        LocalDateTime dateEliminate = null;
        if (!data[6].equals("null")) {
            dateEliminate = LocalDateTime.parse(data[6]);
        }
        return new Product(name, price, stock, id, dateCreation, dateEdition, dateEliminate);
    }

    public void addProduct(BufferedReader reader) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(FileRoutes.RUTE_PRODUCTS, true));
        int lastId = getLastIdProducts();

        Product product = new Product();
        System.out.println("\nEnter the name:");
        String name = reader.readLine();
        product.setName(name);
        System.out.println("Enter the price:");
        Double price = Double.parseDouble(reader.readLine());
        product.setPrice(price);
        System.out.println("Enter the stock:");
        Integer stock = Integer.parseInt(reader.readLine());
        product.setStock(stock);
        product.setId(lastId + 1);

        LocalDateTime dateTimeCurrent = LocalDateTime.now();
        product.setDateCreation(dateTimeCurrent);

        writer.write(product + System.lineSeparator());
        writer.close();
        detailsProduct();
    }

    public void detailsProduct() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(FileRoutes.RUTE_PRODUCTS));
        String line;
        System.out.println("The file has:");
        while ((line = reader.readLine()) != null) {
            System.out.println(line);
        }
    }

    public int getLastIdProducts() throws IOException {
        int lastId = 0;
        BufferedReader reader = new BufferedReader(new FileReader(FileRoutes.RUTE_PRODUCTS));
        String line;
        while ((line = reader.readLine()) != null) {
            String[] data = line.split(",");
            lastId = Integer.parseInt(data[3]);
        }
        return lastId;
    }

    public void editProduct(BufferedReader reader) throws IOException {
        List<Product> listProducts = new ArrayList<>();

        try (BufferedReader readerProducts = new BufferedReader(new FileReader(FileRoutes.RUTE_PRODUCTS))) {
            String line;
            while ((line = readerProducts.readLine()) != null) {
                String[] data = line.split(",");
                String name = data[0];
                Double price = Double.parseDouble(data[1]);
                int stock = Integer.parseInt(data[2]);
                int id = Integer.parseInt(data[3]);
                LocalDateTime dateCreation = LocalDateTime.parse(data[4]);

                LocalDateTime dateEdition = null;
                if (!data[5].equals("null")) {
                    dateEdition = LocalDateTime.parse(data[5]);
                }

                LocalDateTime dateEliminate = null;
                if (!data[6].equals("null")) {
                    dateEliminate = LocalDateTime.parse(data[6]);
                }

                listProducts.add(new Product(name, price, stock, id, dateCreation, dateEdition, dateEliminate));
            }
        } catch (IOException e) {
            System.out.println("Error when reading the file");
        }

        listProduct();
        System.out.println("\nEnter the Id of Product to edit:");
        int idInput = Integer.parseInt(reader.readLine());

        Product productFound = null;
        for (Product product1 : listProducts) {
            if (product1.getId() == idInput) {
                productFound = product1;
                break;
            }
        }

        if (productFound != null) {
            System.out.println("Enter the new name:");
            String newName = reader.readLine();
            productFound.setName(newName);
            System.out.println("Enter the new price:");
            Double newPrice = Double.parseDouble(reader.readLine());
            productFound.setPrice(newPrice);
            System.out.println("Enter the new stock:");
            Integer newStock = Integer.parseInt(reader.readLine());
            productFound.setStock(newStock);
            LocalDateTime dateModification = LocalDateTime.now();
            productFound.setDateModification(dateModification);

            BufferedWriter writer = new BufferedWriter(new FileWriter(FileRoutes.RUTE_PRODUCTS, false));
            for (Product product : listProducts) {
                writer.write(product.toString());
                writer.newLine();
            }
            System.out.println("\nSuccessfully edited product");
            detailsProduct();
            writer.close();
        } else {
            System.out.println("The product with id: " + idInput + " was not found");
        }
    }

    public void deletedProduct() throws IOException {

    }

    public void listProduct() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(FileRoutes.RUTE_PRODUCTS));
        String line;
        System.out.println("\nThe products has:");
        while ((line = reader.readLine()) != null) {
            String[] data = line.split(",");
            System.out.println("\nTipe: " + data[0] + "\n" + "Price: " + data[1] + "\n" + "Id: " + data[3]);
        }
    }

    public void listProductDeleted() throws IOException {

    }

    public int getLastId() throws IOException {
        int lastId = 0;
        BufferedReader reader = new BufferedReader(new FileReader(FileRoutes.RUTE_PRODUCTS));
        String line;
        while ((line = reader.readLine()) != null) {
            String[] data = line.split(",");
            lastId = Integer.parseInt(data[5]);
        }
        return lastId;
    }

    public void details() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(FileRoutes.RUTE_PRODUCTS));
        String line;
        System.out.println("The file has:");
        while ((line = reader.readLine()) != null) {
            System.out.println(line);
        }
    }

    public LinkedList<Product> getProducts() throws IOException {
        BufferedReader readerProducts = new BufferedReader(new FileReader(FileRoutes.RUTE_PRODUCTS));
        LinkedList<Product> productList = new LinkedList<>();
        String line;
        while ((line = readerProducts.readLine()) != null) {
            String[] data = line.split(",");
            String name = data[0];
            Double price = Double.parseDouble(data[1]);
            Product product = getProduct(data, name, price);
            productList.add(product);
        }
        readerProducts.close();
        return productList;
    }

    public void printTable(LinkedList<Product> productList) {
        System.out.println("---------------------------------------------");
        System.out.printf("%-15s %-15s %-5s %-10s\n", "Name", "Price", "Id", "stock");
        for (Product product : productList) {
            System.out.printf("%-15s %,-15.2f %-5s %-10s%n", product.getName(), product.getPrice(), product.getId(), product.getStock());
        }
        System.out.println("---------------------------------------------");
    }

    //METODO PARA ACTUALIZAR EL TXT CON EL NUEVO STOCK
}
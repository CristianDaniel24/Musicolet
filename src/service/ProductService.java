package service;

import classes.Product;
import constants.FileRoutes;

import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ProductService {
    public ProductService() {
    }

    public void addProduct(BufferedReader reader) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FileRoutes.RUTE_PRODUCTS, true))) {
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
    }

    public void detailsProduct() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(FileRoutes.RUTE_PRODUCTS));
        String line;
        System.out.println("The file has:");
        while ((line = reader.readLine()) != null) {
            System.out.println(line);
        }
        reader.close();
    }

    public int getLastIdProducts() throws IOException {
        int lastId = 0;
        BufferedReader reader = new BufferedReader(new FileReader(FileRoutes.RUTE_PRODUCTS));
        String line;
        while ((line = reader.readLine()) != null) {
            String[] data = line.split(",");
            lastId = Integer.parseInt(data[3]);
        }
        reader.close();
        return lastId;
    }


    public void editProduct(BufferedReader reader, BufferedReader readerProducts, BufferedWriter writer) throws IOException {

        List<Product> listProducts = new ArrayList<>();

        try {
            String line;
            while ((line = readerProducts.readLine()) != null) {
                String[] data = line.split(",");
                String name = data[0];
                Double price = Double.parseDouble(data[1]);
                int stock = Integer.parseInt(data[2]);
                int id = Integer.parseInt(data[3]);
                LocalDateTime dateCreation = LocalDateTime.parse(data[4]);
                LocalDateTime dateEliminate = LocalDateTime.parse(data[6]);
                listProducts.add(new Product(name, price, stock, id, dateCreation, dateEliminate));
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

            BufferedWriter writer1 = new BufferedWriter(new FileWriter(FileRoutes.RUTE_PRODUCTS, false));
            for (Product product : listProducts) {
                writer1.write(product.toString());
                writer1.newLine();
            }
            writer1.close();
            System.out.println("\nSuccessfully edited product");
            detailsProduct();
        } else {
            System.out.println("The product with id: " + idInput + " was not found");
        }
    }


    public void deletedProduct(BufferedReader reader, BufferedReader readerProducts) throws IOException {

    }

    public void listProduct() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(FileRoutes.RUTE_PRODUCTS));
        String line;
        System.out.println("\nThe products has:");
        while ((line = reader.readLine()) != null) {
            String[] data = line.split(",");
            System.out.println("\nTipe: " + data[0] + "\n" + "Price: " + data[1] + "\n" + "Id: " + data[3]);
        }
        reader.close();
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
        reader.close();
        return lastId;
    }

    public void details() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(FileRoutes.RUTE_PRODUCTS));
        String line;
        System.out.println("The file has:");
        while ((line = reader.readLine()) != null) {
            System.out.println(line);
        }
        reader.close();
    }
}
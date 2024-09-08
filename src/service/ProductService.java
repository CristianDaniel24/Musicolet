package service;

import classes.Product;

import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ProductService {
    public ProductService() {
    }

    public int getLastId(String rute) throws IOException {
        int lastId = 0;
        BufferedReader reader = new BufferedReader(new FileReader(rute));
        String line;
        while ((line = reader.readLine()) != null) {
            String[] data = line.split(",");
            lastId = Integer.parseInt(data[5]);
        }
        reader.close();
        return lastId;
    }

    public int getLastIdProducts(String ruteProducts) throws IOException {
        int lastId = 0;
        BufferedReader reader = new BufferedReader(new FileReader(ruteProducts));
        String line;
        while ((line = reader.readLine()) != null) {
            String[] data = line.split(",");
            lastId = Integer.parseInt(data[3]);
        }
        reader.close();
        return lastId;
    }


    public void details(String rute) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(rute));
        String line;
        System.out.println("The file has:");
        while ((line = reader.readLine()) != null) {
            System.out.println(line);
        }
        reader.close();
    }

    public void detailsProduct(String ruteProducts) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(ruteProducts));
        String line;
        System.out.println("The file has:");
        while ((line = reader.readLine()) != null) {
            System.out.println(line);
        }
        reader.close();
    }

    public void addProduct(BufferedReader reader, String ruteProducts) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ruteProducts, true))) {
            int lastId = getLastIdProducts(ruteProducts);
            Product product = new Product();
            System.out.println("Enter the name:");
            String name = reader.readLine();
            product.setName(name);
            System.out.println("Enter the price:");
            Double price = Double.parseDouble(reader.readLine());
            product.setPrice(price);
            System.out.println("Enter the stack:");
            Integer stack = Integer.parseInt(reader.readLine());
            product.setStack(stack);
            product.setId(lastId + 1);

            LocalDateTime dateTimecurrent = LocalDateTime.now();
            product.setDateCreation(dateTimecurrent);

            writer.write(product + System.lineSeparator());
            writer.close();
            detailsProduct(ruteProducts);
        } catch (IOException e) {
            System.out.println("The file doesn't exist");
        }
    }


    public void editProduct(BufferedReader reader, BufferedReader readerProducts, String ruteProducts) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(ruteProducts, true));
        String line;
        String line2;
        int idTxt = 0;
        listProduct(ruteProducts);

        System.out.println("\nEnter the id of product to edit:");
        int idEdit = Integer.parseInt(reader.readLine());
        List<String> listProducts = new ArrayList<>();
        while ((line = readerProducts.readLine()) != null) {
            listProducts.add(line);
        }

        while ((line2 = readerProducts.readLine()) != null) {
            String[] data = line2.split(",");
            String name = data[0];
            Double price = Double.parseDouble(data[1]);
            Integer stack = Integer.parseInt(data[2]);
            idTxt = Integer.parseInt(data[3]);

            if (idEdit == idTxt) {
                for (String list : listProducts) {
                    Product product = new Product(name, price, stack);
                    int lastId = getLastIdProducts(ruteProducts);
                    System.out.println("\nEnter the new name:");
                    String newName = reader.readLine();
                    product.setName(newName);
                    System.out.println("Enter the new price:");
                    Double newPrice = Double.parseDouble(reader.readLine());
                    product.setPrice(newPrice);
                    System.out.println("Enter the new stack:");
                    Integer newStack = Integer.parseInt(reader.readLine());
                    product.setStack(newStack);
                    product.setId(lastId + 1);

                    LocalDateTime dateTimeModification = LocalDateTime.now();
                    product.setDateModification(dateTimeModification);

                    list = product.toString();
                    try (FileWriter clear = new FileWriter(ruteProducts, false)) {
                        System.out.println("\nThe file has been cleaned");
                    } catch (IOException e) {
                        System.out.println("Error cleaning file");
                    }

                    writer.write(list);
                    writer.close();
                }

                detailsProduct(ruteProducts);
            } else {
                System.out.println("\nThe product is invalid");
            }
        }
    }

    public void deletedProduct(BufferedReader reader, BufferedReader readerProducts, String ruteProducts) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ruteProducts, true))) {
            Product product = new Product();
            String line;
            int idTxt = 0;
            listProduct(ruteProducts);

            System.out.println("\nEnter the id of product to deleted:");
            int idDeleted = Integer.parseInt(reader.readLine());

            while ((line = readerProducts.readLine()) != null) {
                String[] data = line.split(",");
                String name = data[0];
                Double price = Double.parseDouble(data[1]);
                Integer stack = Integer.parseInt(data[2]);
                idTxt = Integer.parseInt(data[3]);

                if (idTxt == idDeleted) {
                    LocalDateTime dateTimecurrent = LocalDateTime.now();
                    product.setDateElimination(dateTimecurrent);

                    writer.write(product + System.lineSeparator());
                    writer.close();

                    detailsProduct(ruteProducts);
                } else {
                    System.out.println("\nThe product is invalid");
                }
            }
        }
    }

    public void listProductDeleted(String ruteProducts) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(ruteProducts));
        String line;
        System.out.println("\nThe products has:");
        while ((line = reader.readLine()) != null) {
            String[] data = line.split(",");
            if (data[6] == null) {
                System.out.println(line);
            }
            reader.close();
        }
    }

    public void listProduct(String ruteProducts) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(ruteProducts));
        String line;
        System.out.println("\nThe products has:");
        while ((line = reader.readLine()) != null) {
            String[] data = line.split(",");
            System.out.println("\nTipe: " + data[0] + "\n" + "Price: " + data[1] + "\n" + "Id: " + data[3]);
        }
        reader.close();
    }
}
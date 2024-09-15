package service;

import classes.Person;
import constants.FileRoutes;
import constants.ServiceConstants;
import exceptions.ExceptionValidateAccount;

import java.io.*;

public class PersonService {

    public Person createPerson() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Person person = new Person();
        boolean inputValid = false;
        while (!inputValid) {

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(FileRoutes.RUTE_PERSON, true))) {

                int lastId = ServiceConstants.PERSON_SERVICE.getLastIdPerson();

                System.out.println("\nEnter your name:");
                String nameInput = reader.readLine();
                ServiceConstants.VALIDACIONES.validateName(nameInput);
                person.setName(nameInput);

                System.out.println("Enter your phone:");
                String phoneInput = reader.readLine();
                ServiceConstants.VALIDACIONES.validatePhone(phoneInput);
                person.setPhone(phoneInput);

                System.out.println("Enter your document:");
                String documentInput = reader.readLine();
                ServiceConstants.VALIDACIONES.validateDocument(documentInput);
                person.setDocument(documentInput);

                System.out.println("Enter your mail:");
                String mailInput = reader.readLine();
                ServiceConstants.VALIDACIONES.validateMail(mailInput);
                person.setMail(mailInput);

                System.out.println("Enter your password:");
                String passwordInput = reader.readLine();
                ServiceConstants.VALIDACIONES.validatePassword(passwordInput);
                person.setPassword(passwordInput);

                person.setId(lastId + 1);

                writer.write(person + System.lineSeparator());

                inputValid = true;
            } catch (ExceptionValidateAccount e) {
                System.out.println("\nError " + e.getMessage());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return person;
    }

    public int getLastIdPerson() throws IOException {
        int lastId = 0;
        BufferedReader reader = new BufferedReader(new FileReader(FileRoutes.RUTE_PERSON));
        String line;
        while ((line = reader.readLine()) != null) {
            String[] data = line.split(",");
            lastId = Integer.parseInt(data[5]);
        }
        reader.close();
        return lastId;
    }
}
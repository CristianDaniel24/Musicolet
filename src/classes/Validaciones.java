package classes;

import exceptions.ExceptionValidateAccount;

import java.io.BufferedReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validaciones {
    private BufferedReader readerFile;

    public Validaciones() {
    }

    public void validateName(String nameInput) throws ExceptionValidateAccount {
        String regex = "^[A-Za-z]{3,}$";

        if (!Pattern.matches(regex, nameInput)) {
            throw new ExceptionValidateAccount("The name must have a minimum (3 letters)");
        }
    }

    public void validatePhone(String phoneInput) throws ExceptionValidateAccount {
        String regex = "^\\d{10}$\n";
        Pattern pattern = Pattern.compile(regex);

        Matcher matcher = pattern.matcher(phoneInput);
        if (!matcher.matches()) {
            throw new ExceptionValidateAccount("The phone number must have (10 digits)");
        }
    }

    public void validateDocument(String documentInput) throws ExceptionValidateAccount {
        String regex = "^\\d{8,11}$\n";
        Pattern pattern = Pattern.compile(regex);

        Matcher matcher = pattern.matcher(documentInput);
        if (!matcher.matches()) {
            throw new ExceptionValidateAccount("The Document can have between (8 to 11 numbers)");
        }
    }

    public void validateMail(String mailInput) throws ExceptionValidateAccount {
        String regex = "^[a-zA-Z]{1}[a-zA-Z0-9.]{3,}@[a-zA-Z]+\\.(com|co\\.uk|outlook\\.com)$";

        if (!Pattern.matches(regex, mailInput)) {
            throw new ExceptionValidateAccount("The Mail must contain at least (letters, @, gmail(etc..).com(etc..))");
        }
    }

    public void validatePassword(String passwordInput) throws ExceptionValidateAccount {
        String regex = "^(?=(?:.*[A-Za-z]){4})(?=.*\\d)[A-Za-z\\d]{4,}$\n";

        if (!Pattern.matches(regex, passwordInput)) {
            throw new ExceptionValidateAccount("The password must have a minimum of (4 letters and 1 number)");
        }
    }
}

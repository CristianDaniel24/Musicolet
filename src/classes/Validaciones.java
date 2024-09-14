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
        String regex = "^3\\d{9}$";
        Pattern pattern = Pattern.compile(regex);

        Matcher matcher = pattern.matcher(phoneInput);
        if (!matcher.matches()) {
            throw new ExceptionValidateAccount("The number phone must start with a (3) and must be (10 digits)");
        }
    }

    public void validateDocument(String documentInput) throws ExceptionValidateAccount {
        String regex = "^1\\d{8,11}$";
        Pattern pattern = Pattern.compile(regex);

        Matcher matcher = pattern.matcher(documentInput);
        if (!matcher.matches()) {
            throw new ExceptionValidateAccount("The Document must begin with a (1) and can have between (8 to 11 numbers)");
        }
    }

    public void validateMail(String mailInput) throws ExceptionValidateAccount {
        String regex = "^[a-zA-Z]{1}[a-zA-Z0-9.]{3,}@[a-zA-Z]+\\.(com|co\\.uk|outlook\\.com)$\n";

        if (!Pattern.matches(regex, mailInput)) {
            throw new ExceptionValidateAccount("The Mail must contain at least (letters, @, gmail(etc..).com(etc..))");
        }
    }

    public void validatePassword(String passwordInput) throws ExceptionValidateAccount {
        String regex = "^[a-zA-Z0-9\\s.,@!*_?¿\"'¨+/#-]{4,}$";

        if (!Pattern.matches(regex, passwordInput)) {
            throw new ExceptionValidateAccount("The password must have a minimum of (4 letters and 1 number)");
        }
    }
}

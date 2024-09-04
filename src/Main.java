import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args)throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        //TODO: probar ruta - String rute = "/db/Person.txt";
        boolean exit = false;
        while (!exit){
            try {
                System.out.println("\nMUSICOLET MENU");
                System.out.println("1. Add product");
                System.out.println("2. Search product");
                System.out.println("3. Delete product");
                System.out.println("4. List of products");
                System.out.println("5. Exit");
                System.out.println("Enter the option:");
                int option = Integer.parseInt(reader.readLine());

                switch (option){
                    case 1:
                        break;
                    case 2:
                        break;
                    case 3:
                        break;
                    case 4:
                        break;
                    case 5:
                        System.out.println("Bye..");
                        exit = true;
                        break;
                    default:
                        System.out.println("\nThe option is invalid");
                }
            }catch (NumberFormatException e){
                System.out.println("\nEnter the number, please");
            }
        }
    }
}

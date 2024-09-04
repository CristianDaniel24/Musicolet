package classes;

public class Person {
    private String name;
    private String mail;
    private Integer phone;
    private Integer document;
    private Integer id;
    private Integer password;

    public Person(String name, String mail, Integer phone, Integer document, Integer id, Integer password){
        this.name = name;
        this.mail = mail;
        this.phone = phone;
        this.document = document;
        this.id = id;
        this.password = password;
    }
}

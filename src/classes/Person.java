package classes;

public class Person {
    private String name;
    private String phone;
    private String document;
    private String mail;
    private String password;
    private Integer id;

    public Person(String name, String phone, String document, String mail, String password, Integer id) {
        this.name = name;
        this.mail = mail;
        this.phone = phone;
        this.document = document;
        this.password = password;
        this.id = id;
    }

    public Person() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return name + "," + phone + "," + document + "," + mail + "," +
                password + "," + id;
    }
}

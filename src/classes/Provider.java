package classes;

public class Provider {
    private String nameCompany;
    private String direction;

    public Provider(String nameCompany, String direction) {
        this.nameCompany = nameCompany;
        this.direction = direction;
    }

    public String getNameCompany() {
        return nameCompany;
    }

    public void setNameCompany(String nameCompany) {
        this.nameCompany = nameCompany;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public void dispenseProducts() {
    }

}

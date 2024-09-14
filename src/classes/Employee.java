package classes;

import java.time.LocalDateTime;

public class Employee {
    private Integer id;
    private String rol;
    private Double salary;
    private LocalDateTime dateCreation;
    private LocalDateTime dateModification;
    private LocalDateTime dateElimination;


    public Employee(Integer id, String rol, Double salary) {
        this.id = id;
        this.rol = rol;
        this.salary = salary;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public LocalDateTime getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(LocalDateTime dateCreation) {
        this.dateCreation = dateCreation;
    }

    public LocalDateTime getDateModification() {
        return dateModification;
    }

    public void setDateModification(LocalDateTime dateModification) {
        this.dateModification = dateModification;
    }

    public LocalDateTime getDateElimination() {
        return dateElimination;
    }

    public void setDateElimination(LocalDateTime dateElimination) {
        this.dateElimination = dateElimination;
    }

    @Override
    public String toString() {
        return id + "," + rol + "," + salary + "," + dateCreation + "," +
                dateModification + "," + dateElimination;
    }
}

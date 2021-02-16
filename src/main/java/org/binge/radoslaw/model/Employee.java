package org.binge.radoslaw.model;

import javax.persistence.*;

@Entity
@Table(name = "Employees2", schema = "TEST")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "employees2_generator")
    @SequenceGenerator(name = "employees2_generator", sequenceName = "EMPLOYEES2_SEQ", allocationSize = 1)
    @Column(name = "ID", updatable = false, nullable = false)
    private long id;

    @Column(name = "FIRST_NAME", nullable = false)
    private String firstName;

    @Column(name = "LAST_NAME", nullable = false)
    private String lastName;

    @Column(name = "LOGIN", nullable = false, unique = true)
    private String login;

    @Column(name = "CURRENT_ON")
    private String currentOn;

    public Employee() {
    }

    public Employee(int id, String firstName, String lastName, String login, String currentOn) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.login = login;
        this.currentOn = currentOn;
    }

    public Employee(String firstName, String lastName, String login) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.login = login;
    }

    @Override
    public String toString() {
        return "Employees{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", login='" + login + '\'' +
                ", currentOn='" + currentOn + '\'' +
                '}';
    }

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getCurrentOn() {
        return currentOn;
    }

    public void setCurrentOn(String currentOn) {
        this.currentOn = currentOn;
    }
}

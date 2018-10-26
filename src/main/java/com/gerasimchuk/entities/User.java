package com.gerasimchuk.entities;

import com.gerasimchuk.utils.JSONconvertable;

import javax.persistence.*;

@Entity(name = "Users")
@Table(name = "users", schema = "mywwldatabase", catalog = "")
public class User implements JSONconvertable {
    private int id;
    private String name;
    private String middleName;
    private String lastName;
    private String personalNumber;
    private String password;
    private Driver driver;
    private Manager manager;
    private Admin admin;

    public User() {
    }

    public User(String name,
                String middleName,
                String lastName,
                String personalNumber,
                String password,
                Driver driver,
                Manager manager,
                Admin admin) {
        this.name = name;
        this.middleName = middleName;
        this.lastName = lastName;
        this.personalNumber = personalNumber;
        this.password = password;
        this.driver = driver;
        this.manager = manager;
        this.admin = admin;
    }

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "name", nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "middlename", nullable = false)
    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    @Column(name = "lastname", nullable = false)
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Column(name = "personal_number", nullable = false, unique = true)
    public String getPersonalNumber() {
        return personalNumber;
    }

    public void setPersonalNumber(String personalNumber) {
        this.personalNumber = personalNumber;
    }

    @Column(name = "password", nullable = false)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @OneToOne
    @JoinColumn(name = "driver_id")
    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }


    @OneToOne
    @JoinColumn(name = "manager_id")
    public Manager getManager() {
        return manager;
    }

    public void setManager(Manager manager) {
        this.manager = manager;
    }


    @OneToOne
    @JoinColumn(name = "admin_id")
    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }


    @Override
    public String convertToJSONString() {
        StringBuilder result = new StringBuilder("{");
        result.append("\"id\":\"").append(id).append("\",");
        result.append("\"name\":\"").append(name).append("\",");
        result.append("\"middleName\":\"").append(middleName).append("\",");
        result.append("\"lastName\":\"").append(lastName).append("\",");
        result.append("\"personalNumber\":\"").append(personalNumber).append("\"}");
        return result.toString();
    }
}

package com.gerasimchuk.entities;

import javax.persistence.*;

@Entity(name = "Users")
@Table(name = "users", schema = "mywwldatabase", catalog = "")
public class User {
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
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;

        User user = (User) o;

        if (id != user.id) return false;
        if (!name.equals(user.name)) return false;
        if (!middleName.equals(user.middleName)) return false;
        if (!lastName.equals(user.lastName)) return false;
        if (!personalNumber.equals(user.personalNumber)) return false;
        if (!password.equals(user.password)) return false;
        if (driver != null ? !driver.equals(user.driver) : user.driver != null) return false;
        if (manager != null ? !manager.equals(user.manager) : user.manager != null) return false;
        return admin != null ? admin.equals(user.admin) : user.admin == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + name.hashCode();
        result = 31 * result + middleName.hashCode();
        result = 31 * result + lastName.hashCode();
        result = 31 * result + personalNumber.hashCode();
        result = 31 * result + password.hashCode();
        result = 31 * result + (driver != null ? driver.hashCode() : 0);
        result = 31 * result + (manager != null ? manager.hashCode() : 0);
        result = 31 * result + (admin != null ? admin.hashCode() : 0);
        return result;
    }
}

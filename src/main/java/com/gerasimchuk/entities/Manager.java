package com.gerasimchuk.entities;


import javax.persistence.*;

@Entity(name = "Managers")
@Table(name = "managers",schema = "mywwldatabase", catalog = "")
public class Manager {


    private int id;
    private User user;

    public Manager() {
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

    @OneToOne(mappedBy = "manager")
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Manager)) return false;

        Manager manager = (Manager) o;

        return id == manager.id;
    }

    @Override
    public int hashCode() {
        return id;
    }
}

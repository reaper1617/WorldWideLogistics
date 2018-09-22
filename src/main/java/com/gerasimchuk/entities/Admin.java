package com.gerasimchuk.entities;


import javax.persistence.*;

@Entity(name = "Admins")
@Table(name = "admins", schema = "mywwldatabase", catalog = "")
public class Admin {

    private int id;
    private User user;

    public Admin() {
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

    @OneToOne(mappedBy = "admin")
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


}

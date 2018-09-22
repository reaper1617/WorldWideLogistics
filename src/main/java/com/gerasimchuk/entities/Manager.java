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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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


}

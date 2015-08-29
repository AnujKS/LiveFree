package com.app.livefree.livefree.model;

/**
 * Created by anujkumars on 8/29/2015.
 */
public class User {
    int id;
    String phone;
    String name;
    String registrationKey;

    public String getRegistrationKey() {
        return registrationKey;
    }

    public void setRegistrationKey(String registrationKey) {
        this.registrationKey = registrationKey;
    }


    public String getName() {
        return name;
    }

    public void setName(String name,String phone) {
        this.name = name;
        this.phone=phone;
    }

    public User(String phone) {
        this.phone = phone;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}

package com.company;

public class Employee {
    private int Id;
    private String name;
    private String surname;
    private String email;

    public Employee(int id, String name, String surname, String email) {
        Id = id;
        this.name = name;
        this.surname = surname;
        this.email = email;
    }

    public Employee(String name, String surname, String email) {
        this.name = name;
        this.surname = surname;
        this.email = email;
    }

    //region Getters and Setters
    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    //endregion

    @Override
    public String toString() {
        return name+" "+surname+" "+email;
   }
}

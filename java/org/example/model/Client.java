package org.example.model;

public class Client {
    private int id;
    private String name;
    private String email;
    private int age;

    public Client()
    {

    }

    public Client(String name, String email, int age) {
        super();
        this.name = name;
        this.email = email;
        this.age = age;
    }

    public Client(int id, String name, String email, int age) {
        super();
        this.id = id;
        this.name = name;
        this.email = email;
        this.age = age;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String a) {
        this.name = a;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String a) {
        this.email = a;
    }

    public int getAge() {
        return this.age;
    }

    public void setAge(int age) {
        this.age = age;
    }
    public String toString(){
        return id + ", " + "'" + this.name + "'" + ", "+ "'" + this.email + "'" + ","  + this.age;
    }

}
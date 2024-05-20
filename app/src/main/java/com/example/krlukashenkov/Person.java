package com.example.krlukashenkov;
import java.io.Serializable;
public class Person implements Serializable{
    private int id; //идентификатор
    private int age; //возраст
    private String name;//имя
    private String phone;//номер телефона
    private String login;//логин
    private String password; //пароль

    public Person(int id, int age, String name, String phone, String login, String password) {
        this.id = id;
        this.age = age;
        this.name = name;
        this.phone = phone;
        this.login = login;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

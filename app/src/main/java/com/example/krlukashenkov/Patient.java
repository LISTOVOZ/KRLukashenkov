package com.example.krlukashenkov;
import java.io.Serializable;
public class Patient implements Serializable{
    private int id; //идентификатор
    private int age; //возраст
    private String name;//имя
    private String phone;//номер телефона
    private String gender;//пол
    private String plague; //диагноз

    public Patient(int id, int age, String name, String phone, String gender, String plague) {
        this.id = id;
        this.age = age;
        this.name = name;
        this.phone = phone;
        this.gender = gender;
        this.plague = plague;
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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPlague() {
        return plague;
    }

    public void setPlague(String plague) {
        this.plague = plague;
    }
}

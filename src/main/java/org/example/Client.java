package org.example;

import java.time.LocalDate;

public class Client {

    static int nextId = 1001;
    private int id;
    private String lastName;
    private String firstName;
    private String middleName;
    private String phoneNumber;
    private Gender.gender sex;
    //private String gender;
    private LocalDate birthday;

    public Client(String lastName, String firstName, String middleName, String phoneNumber, String sex, LocalDate birthday) {
        this.id = nextId++;
        this.lastName = lastName;
        this.firstName = firstName;
        this.middleName = middleName;
        this.phoneNumber = phoneNumber;
        this.sex = Gender.gender.valueOf(sex);
        setBirthday(birthday);
    }
    public Client(String lastName, String firstName, String phoneNumber, String sex, LocalDate birthday) {
        this.id = nextId++;
        this.lastName = lastName;
        this.firstName = firstName;
        this.middleName = "";
        this.phoneNumber = phoneNumber;
        this.sex = Gender.gender.valueOf(sex);
        setBirthday(birthday);
    }

    public int getId() {
        return id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Gender.gender getSex() {
        return sex;
    }

    public void setGenderOfUser(String sex) {
        this.sex = Gender.gender.valueOf(sex);
    }


    public LocalDate getBirthday() {
        return birthday;
    }

    @Override
    public String toString() {
        return "Client " +
               "id:" + id +
               ", " + lastName +
               " " + firstName +
               " " + middleName +
               ", phoneNumber: " + phoneNumber + '\'' +
               ", gender " + sex +
               ", birthday: " + birthday;
    }

    public void setBirthday(LocalDate birthday) {
        if (birthday == null || birthday.isAfter((LocalDate.now().minusYears(18)))) {
            System.out.println("This client is too young");
        } else {
            this.birthday = birthday;
        }
    }
}
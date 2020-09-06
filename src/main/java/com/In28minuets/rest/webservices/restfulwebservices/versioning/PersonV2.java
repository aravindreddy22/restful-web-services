package com.In28minuets.rest.webservices.restfulwebservices.versioning;

public class PersonV2 {

    private String firstName;
    private String lastName;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public PersonV2() {
    }

    public PersonV2(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }
}

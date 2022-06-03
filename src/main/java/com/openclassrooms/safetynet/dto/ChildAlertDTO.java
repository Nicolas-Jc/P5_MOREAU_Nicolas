package com.openclassrooms.safetynet.dto;

import com.openclassrooms.safetynet.model.Person;

import java.util.List;

public class ChildAlertDTO {

    String firstName;
    String lastName;
    int age;
    List<Person> householdMembers;

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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public List<Person> getHouseholdMembers() {
        return householdMembers;
    }

    public void setHouseholdMembers(List<Person> householdMembers) {
        this.householdMembers = householdMembers;
    }
}

package com.openclassrooms.safetynet.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;

@Getter
@Setter
public class Person {

    //@JsonProperty("firstname")
    private String firstName;
    private String lastName;
    private String address;
    private String city;
    private String zip;
    private String phone;
    private String email;

    @Override
    public String toString() {
        StringBuilder pe = new StringBuilder();
        pe.append("***** Person Details *****\n");
        pe.append("firstName=" + getFirstName() + "\n");
        pe.append("lastName=" + getLastName() + "\n");
        pe.append("address=" + getAddress() + "\n");
        pe.append("city=" + getCity() + "\n");
        //pe.append("zip="+ Arrays.toString(getPhoneNumbers())+"\n");
        pe.append("zip=" + getCity() + "\n");
        //pe.append("Cities="+Arrays.toString(getCities().toArray())+"\n");
        pe.append("phone=" + getPhone() + "\n");
        pe.append("email=" + getEmail() + "\n");
        pe.append("*****************************" + "\n");

        return pe.toString();
    }

}

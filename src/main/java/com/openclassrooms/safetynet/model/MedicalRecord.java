package com.openclassrooms.safetynet.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;
import java.util.List;

@Getter
@Setter
public class MedicalRecord {
    private String firstName;
    private String lastName;
    private String birthdate;
    private List<String> medications;
    private List<String> allergies;

    @Override
    public String toString() {
        StringBuilder mr = new StringBuilder();
        mr.append("***** MedicalRecord Details *****\n");
        mr.append("firstName=" + getFirstName() + "\n");
        mr.append("lastName=" + getLastName() + "\n");
        mr.append("birthdate=" + getBirthdate() + "\n");
        mr.append("medications=" + Arrays.toString(getMedications().toArray()) + "\n");
        mr.append("allergies=" + Arrays.toString(getAllergies().toArray()) + "\n");
        mr.append("*****************************" + "\n");

        return mr.toString();
    }

}

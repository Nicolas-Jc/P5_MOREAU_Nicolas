package com.openclassrooms.safetynet.dto;

import java.util.List;

public class FireStationPerimeterDTO {
    List<PersonFireStationDTO> fireStationPersons;
    int adultCount;
    int childCount;

    public List<PersonFireStationDTO> getFireStationPersons() {
        return fireStationPersons;
    }

    public void setFireStationPersons(List<PersonFireStationDTO> fireStationPersons) {
        this.fireStationPersons = fireStationPersons;
    }

    public int getAdultCount() {
        return adultCount;
    }

    public void setAdultCount(int adultCount) {
        this.adultCount = adultCount;
    }

    public int getChildCount() {
        return childCount;
    }

    public void setChildCount(int childCount) {
        this.childCount = childCount;
    }
}

package com.openclassrooms.safetynet.dto;

import java.util.List;

public class FireStationDTO {
    List<InfoPersonDTO> fireStationPersons;
    int adultCount;
    int childCount;

    public List<InfoPersonDTO> getFireStationPersons() {
        return fireStationPersons;
    }

    public void setFireStationPersons(List<InfoPersonDTO> fireStationPersons) {
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

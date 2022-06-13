package com.openclassrooms.safetynet.dto;

import java.util.List;

public class FloodDTO {
    String address;
    List<FloodPersonDTO> FloodListPersons;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<FloodPersonDTO> getFloodListPersons() {
        return FloodListPersons;
    }

    public void setFloodListPersons(List<FloodPersonDTO> floodListPersons) {
        FloodListPersons = floodListPersons;
    }
}

package com.openclassrooms.safetynet.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FireStation {

    private String address;
    private String station;

    @Override
    public String toString() {
        StringBuilder fs = new StringBuilder();
        fs.append("***** FireStation Details *****\n");
        fs.append("address=" + getAddress() + "\n");
        fs.append("station=" + getStation() + "\n");
        fs.append("*****************************" + "\n");

        return fs.toString();
    }

}

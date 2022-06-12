package com.openclassrooms.safetynet.dao;

import com.openclassrooms.safetynet.data.Data;
import com.openclassrooms.safetynet.model.MedicalRecord;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class MedicalRecordsDAOImpl implements MedicalRecordsDAO {
    //private List<MedicalRecord> medicalRecords = new ArrayList<>();
    //List<MedicalRecord> medicalRecords = new ArrayList<>();


    @Override
    public List<MedicalRecord> getAllMedicalRecords() {
        //return medicalRecords;
        return Data.getMedicalRecords();
    }

    @Override
    public void setAllMedicalRecords(List<MedicalRecord> listMedicalRecord) {
        Data.setMedicalRecords(listMedicalRecord);

    }

    @Override
    public MedicalRecord getMedicalRecords(String firstName, String lastName) {
        for (MedicalRecord m : Data.getMedicalRecords()) {
            if (m.getFirstName().contentEquals(firstName) && m.getLastName().contentEquals(lastName)) {
                return m;
            }
        }
        return null;
    }

    @Override
    public Boolean deleteMedicalRecords(String firstName, String lastName) {
        // if more than one medical record
        List<MedicalRecord> medicalRecordsFind = new ArrayList<>();

        for (MedicalRecord m : Data.getMedicalRecords()) {
            if (m.getFirstName().contentEquals(firstName) && m.getLastName().contentEquals(lastName)) {
                medicalRecordsFind.add(m);
            }
        }

        if (medicalRecordsFind.size() > 0) {
            Data.getMedicalRecords().removeAll(medicalRecordsFind);
            return true;
        }

        return false;
    }


}

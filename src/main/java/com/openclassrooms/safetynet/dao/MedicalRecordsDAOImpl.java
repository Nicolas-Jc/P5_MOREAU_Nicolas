package com.openclassrooms.safetynet.dao;

import com.openclassrooms.safetynet.data.Data;
import com.openclassrooms.safetynet.model.MedicalRecord;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MedicalRecordsDAOImpl implements MedicalRecordsDAO {
    //private List<MedicalRecord> medicalRecords = new ArrayList<>();

    @Override
    public List<MedicalRecord> getAllMedicalRecords() {
        //return medicalRecords;
        return Data.getMedicalRecords();
    }

    @Override
    public void setAllMedicalRecords(List<MedicalRecord> listMedicalRecord) {
        Data.setMedicalRecords(listMedicalRecord);
        
    }

}

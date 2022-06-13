package com.openclassrooms.safetynet.service;


import com.openclassrooms.safetynet.dao.MedicalRecordsDAO;
import com.openclassrooms.safetynet.model.MedicalRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicalRecordService {
    @Autowired
    private MedicalRecordsDAO medicalRecordsDAO;


    public List<MedicalRecord> findAll() {
        return medicalRecordsDAO.getAllMedicalRecords();
    }

    public MedicalRecord findMedicalRecord(String firstName, String lastName) {
        return medicalRecordsDAO.getMedicalRecords(firstName, lastName);
    }

    public Boolean delete(String firstName, String lastName) {
        return medicalRecordsDAO.deleteMedicalRecords(firstName, lastName);
    }

    public MedicalRecord modify(MedicalRecord medicalRecord) {
        return medicalRecordsDAO.modifyMedicalRecords(medicalRecord);
    }

    public MedicalRecord add(MedicalRecord medicalRecord) {
        return medicalRecordsDAO.addMedicalRecords(medicalRecord);

    }

}

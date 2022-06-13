package com.openclassrooms.safetynet.service;

import com.openclassrooms.safetynet.model.MedicalRecord;

import java.util.List;

public interface MedicalRecordService {
    List<MedicalRecord> findAll();

    MedicalRecord findMedicalRecord(String firstName, String lastName);

    Boolean delete(String firstName, String lastName);

    MedicalRecord modify(MedicalRecord medicalRecord);

    MedicalRecord add(MedicalRecord medicalRecord);
}

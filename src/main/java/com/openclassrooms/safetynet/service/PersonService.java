package com.openclassrooms.safetynet.service;

import com.openclassrooms.safetynet.dao.PersonDAO;
import com.openclassrooms.safetynet.dto.CommunityEmailDTO;
import com.openclassrooms.safetynet.dto.PhoneAlertDTO;
import com.openclassrooms.safetynet.model.Person;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PersonService {
    @Autowired
    private PersonDAO personDAO;

    //@Autowired
    //private MedicalRecordService medicalRecordService;

    //@Autowired
    //private FireStationService fireStationService;


    //@Override
    // A Supprimer fin de projet
    public List<Person> findAll() {
        return personDAO.getAllPersons();
    }


    // *************** A REVOIR **************************
    // ***************************************************
    //@Override
    public List<CommunityEmailDTO> getCommunityEmail(String city) {
        List<CommunityEmailDTO> listEmailInfo = new ArrayList<>();
        List<Person> persons = findAll();

        System.out.println("Entree PersonService getCommunityEmail");

        for (Person p : persons) {
            if (p.getCity().contentEquals(city)) {
                CommunityEmailDTO communityEmailDTO = new CommunityEmailDTO();
                communityEmailDTO.setEmail(p.getEmail());
                listEmailInfo.add(communityEmailDTO);
            }
        }
        return listEmailInfo;
    }


}

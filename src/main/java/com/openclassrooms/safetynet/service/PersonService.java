package com.openclassrooms.safetynet.service;

import com.openclassrooms.safetynet.dto.*;
import com.openclassrooms.safetynet.model.Person;

import java.util.List;

public interface PersonService {
    List<Person> findAll();

    List<Person> findPerson(String firstName, String lastName);

    List<CommunityEmailDTO> getCommunityEmailByCity(String city);

    List<PhoneAlertDTO> getPhoneAlertByFirestation(String station);

    List<ChildAlertDTO> getChildAlertByAddress(String address);

    List<FireDTO> getPersonByAddress(String address);

    List<InfoPersonDetailedDTO> getPersonByFirstLastName(String firstName, String lastName);

    Boolean deletePerson(String firstName, String lastName);

    Person addPerson(Person person);

    Person modifyPerson(Person person);
}

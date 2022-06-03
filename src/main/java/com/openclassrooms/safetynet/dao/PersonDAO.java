package com.openclassrooms.safetynet.dao;

import com.openclassrooms.safetynet.model.Person;
import org.springframework.stereotype.Component;

import java.util.List;

public interface PersonDAO {

    public List<Person> getAllPersons();

    public void setAllPersons(List<Person> listPerson);

    public List<Person> findPersonByAdress(String address);

}

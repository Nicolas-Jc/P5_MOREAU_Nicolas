package com.openclassrooms.safetynet.dao;

import com.openclassrooms.safetynet.model.Person;

import java.util.List;

public interface PersonDAO {

    public List<Person> getAllPersons();

    public void setAllPersons(List<Person> listPerson);

    public List<Person> findPersonByAdress(String address);

    Boolean deletePerson(String firstName, String lastName);

    Person addPerson(Person person);
}

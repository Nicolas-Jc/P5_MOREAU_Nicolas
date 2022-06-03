package com.openclassrooms.safetynet.dao;

import com.openclassrooms.safetynet.data.Data;
import com.openclassrooms.safetynet.model.Person;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class PersonDAOImpl implements PersonDAO {

    private List<Person> persons = new ArrayList<>();

    @Override
    public List<Person> getAllPersons() {
        return Data.getPersons();
    }

    @Override
    public void setAllPersons(List<Person> listPerson) {
        Data.setPersons(listPerson);

    }


}

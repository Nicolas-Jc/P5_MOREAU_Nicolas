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

    @Override
    public List<Person> findPersonByAdress(String address) {
        // Création Liste des personnes résidant à l'adresse en paramètre
        List<Person> listPersonByAddress = new ArrayList<>();
        // Boucle sur la liste des presonnes du fichier en entrée
        for (Person p : Data.getPersons()) {
            if (p.getAddress().equals(address)) {
                listPersonByAddress.add(p);
            }
        }
        return listPersonByAddress;

    }


}

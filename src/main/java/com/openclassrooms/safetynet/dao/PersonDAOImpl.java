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

    @Override
    public Boolean deletePerson(String firstName, String lastName) {
        for (Person p : Data.getPersons()) {
            if (p.getFirstName().contentEquals(firstName) && p.getLastName().contentEquals(lastName)) {
                Data.getPersons().remove(p);
                return true;
            }
        }
        return false;
    }

    @Override
    public Person addPerson(Person person) {
        Data.getPersons().add(person);
        return person;

    }

    @Override
    public List<Person> findPersonByFirstNameAndLastName(String firstName, String lastName) {
        List<Person> listPerson = new ArrayList<>();

        for (Person p : Data.getPersons()) {
            if ((p.getFirstName().contentEquals(firstName)) && (p.getLastName().contentEquals(lastName)))
                listPerson.add(p);
        }
        return listPerson;
    }

    @Override
    public Person modifyPerson(Person person) {
        int index = 0;

        for (Person p : Data.getPersons()) {
            if (p.getFirstName().contentEquals(person.getFirstName())
                    && p.getLastName().contentEquals(person.getLastName())) {
                Data.getPersons().set(index, person);
                return person;
            }
            index++;
        }

        return null;
    }

}

package com.openclassrooms.safetynet.DAO;

import com.openclassrooms.safetynet.dao.PersonDAO;
import com.openclassrooms.safetynet.data.Data;
import com.openclassrooms.safetynet.model.Person;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class PersonDAOTest {

    @Autowired
    PersonDAO personDAO;

    /*@Test
    public void getAllPersonsTest() {

        //GIVEN
        List<Person> listPerson = personDAO.getAllPersons();
        // WHEN
        // THEN
        assertThat(listPerson.size()).isEqualTo(7);
    }*/

    @Test
    public void findPersonByAddressTest() {

        // GIVEN // WHEN
        List<Person> listPerson = personDAO.findPersonByAdress("99 Street Test FindPersonByAdress");
        // THEN
        assertThat(listPerson.get(0).getFirstName()).isEqualTo("FirstNameTestFindByAdress");
        assertThat(listPerson.get(0).getLastName()).isEqualTo("LastNameTestFindByAdress");
    }

    @Test
    public void findPersonByNotExistingAddressTest() {

        // GIVEN // WHEN
        List<Person> listPerson = personDAO.findPersonByAdress("99 Street Not Existing ");
        // THEN
        assertThat(listPerson).isEmpty();
    }

    @Test
    public void findPersonByFirstNameAndLastNameTest() {

        // GIVEN // WHEN
        List<Person> listPerson = personDAO.findPersonByFirstNameAndLastName("FirstNameTestFindByLnameFname"
                , "LastNameTestFindByLnameFname");
        // THEN
        assertThat(listPerson.get(0).getAddress()).isEqualTo("99 Street Test FindPersonByFirstNameLastName");
    }

    @Test
    public void addPersonTest() {

        // GIVEN
        Person personTest = new Person("FirstnameTestAdd"
                , "LastnameTestAdd"
                , "adressTestAdd"
                , "CityTestAdd"
                , "zipTestAdd"
                , "phoneTestAdd"
                , "email.TestAdd");
        // WHEN
        Person PersonAdded = personDAO.addPerson(personTest);
        // THEN
        assertThat(PersonAdded.getFirstName()).isEqualTo(personTest.getFirstName());
        assertThat(PersonAdded.getLastName()).isEqualTo(personTest.getLastName());
        assertThat(PersonAdded.getAddress()).isEqualTo(personTest.getAddress());
        assertThat(PersonAdded.getCity()).isEqualTo(personTest.getCity());
        assertThat(PersonAdded.getZip()).isEqualTo(personTest.getZip());
        assertThat(PersonAdded.getPhone()).isEqualTo(personTest.getPhone());
        assertThat(PersonAdded.getEmail()).isEqualTo(personTest.getEmail());
    }

    @Test
    public void modifyPersonTest() {

        // GIVEN
        Person personToModify = new Person("FirstnametestModify"
                , "LastnametestModify"
                , "adress"
                , "City"
                , "zip"
                , "phone",
                "email@test.fr");
        // WHEN
        Person personModified = personDAO.modifyPerson(personToModify);

        // THEN
        assertThat(personModified.getAddress()).isEqualTo(personToModify.getAddress());
        assertThat(personModified.getCity()).isEqualTo(personToModify.getCity());
        assertThat(personModified.getZip()).isEqualTo(personToModify.getZip());
        assertThat(personModified.getPhone()).isEqualTo(personToModify.getPhone());
        assertThat(personModified.getEmail()).isEqualTo(personToModify.getEmail());
    }


    @Test
    public void deleteExistingPersonTest() {

        // GIVEN // WHEN
        boolean returnDelete = personDAO.deletePerson("FirstNameTestDel"
                , "LastNameTestDel");
        // THEN
        assertThat(returnDelete).isTrue();

    }

    @Test
    public void deleteNotExistingPersonTest() {

        // GIVEN // WHEN
        boolean returnDelete = personDAO.deletePerson("FirstNameNotExist"
                , "LastNameNotExist");
        // THEN
        assertThat(returnDelete).isFalse();

    }

}

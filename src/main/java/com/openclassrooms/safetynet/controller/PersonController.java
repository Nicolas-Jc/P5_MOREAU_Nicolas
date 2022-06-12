package com.openclassrooms.safetynet.controller;

import com.openclassrooms.safetynet.dto.ChildAlertDTO;
import com.openclassrooms.safetynet.dto.CommunityEmailDTO;
import com.openclassrooms.safetynet.dto.PhoneAlertDTO;
import com.openclassrooms.safetynet.model.Person;
import com.openclassrooms.safetynet.service.PersonService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/person")
public class PersonController {

    private static final Logger logger = LogManager.getLogger("PersonController");

    @Autowired
    PersonService personService;

    @DeleteMapping
    public String deletePerson(@RequestParam("firstname") String firstName, @RequestParam("lastname") String lastName) {

        //
        //if (firstName.isEmpty() || lastName.isEmpty())
        //   throw new PersonBadParameter("Firstname and lastname are required");*/

        if (personService.delete(firstName, lastName)) {
            logger.info("Delete person OK");
            return "Deleted person : " + firstName + " " + lastName;
        } /*else
            throw new PersonNotFound(firstName + " " + lastName);*/
        return null;
    }

    @PostMapping
    public Person addPerson(@RequestBody Person person) {

        Person personAdded = personService.add(person);

        if (personAdded == null) {
            logger.error("addPerson : KO");
            //throw new PersonAddedException("Add " + person.toString() + " : ERROR");
        }
        logger.info("Add " + personAdded.toString());
        return personAdded;
    }

}

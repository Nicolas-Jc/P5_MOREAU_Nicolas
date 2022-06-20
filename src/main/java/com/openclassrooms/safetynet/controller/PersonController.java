package com.openclassrooms.safetynet.controller;


import com.openclassrooms.safetynet.model.Person;
import com.openclassrooms.safetynet.service.PersonServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/person")
public class PersonController {

    private static final Logger logger = LogManager.getLogger("PersonController");

    @Autowired
    PersonServiceImpl personService;

    @DeleteMapping
    public String deletePerson(@RequestParam("firstname") String firstName, @RequestParam("lastname") String lastName) {

        if (personService.deletePerson(firstName, lastName)) {
            logger.info("Delete person OK");
            return "Deleted person : " + firstName + " " + lastName;
        }
        return null;
    }

    @PostMapping
    public Person addPerson(@RequestBody Person person) {

        Person personAdded = personService.addPerson(person);

        if (personAdded == null) {
            logger.error("addPerson : KO");
        }
        logger.info("addPerson " + personAdded.toString());
        return personAdded;
    }

    @PutMapping
    public Person modifyPerson(@RequestBody Person person) {

        Person personModified = personService.modifyPerson(person);

        if (personModified == null) {
            logger.error("modifyPerson : Not Found");
        }
        logger.info("modifyPerson : OK");
        return personModified;
    }
}

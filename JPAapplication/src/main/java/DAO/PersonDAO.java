package DAO;

import entities.Person;
import jakarta.persistence.EntityManager;

/**
 * Data Access Object (DAO) for managing Person entities.
 */
public class PersonDAO {
    private final EntityManager entityManager;

    /**
     * Constructs a PersonDAO with the provided EntityManager.
     *
     * @param entityManager The EntityManager to be used for database operations.
     */
    public PersonDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    /**
     * Persists a new Person entity to the database.
     *
     * @param person The Person entity to be created.
     */
    public void createPerson(Person person) {
        entityManager.persist(person);
    }

    /**
     * Retrieves a Person entity from the database based on its ID.
     *
     * @param id The ID of the Person entity to retrieve.
     * @return The Person entity with the specified ID, or null if not found.
     */
    public Person findPerson(Integer id) {
        return entityManager.find(Person.class, id);
    }

    /**
     * Updates an existing Person entity in the database.
     *
     * @param person The Person entity to be updated.
     */
    public void updatePerson(Person person) {
        entityManager.merge(person);
    }

    /**
     * Deletes a Person entity from the database based on its ID.
     *
     * @param id The ID of the Person entity to delete.
     */
    public void deletePerson(Integer id) {
        Person person = entityManager.find(Person.class, id);
        if (person != null) {
            entityManager.remove(person);
        }
    }
}


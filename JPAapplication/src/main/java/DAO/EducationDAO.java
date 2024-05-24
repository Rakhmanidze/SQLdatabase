package DAO;

import entities.Education;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Id;

/**
 * Data Access Object (DAO) for managing Education entities.
 */
public class EducationDAO {
    private final EntityManager entityManager;

    /**
     * Constructs an EducationDAO with the provided EntityManager.
     *
     * @param entityManager The EntityManager to be used for database operations.
     */
    public EducationDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    /**
     * Persists a new Education entity to the database.
     *
     * @param education The Education entity to be created.
     */
    public void createEducation(Education education) {
        entityManager.persist(education);
    }

    /**
     * Retrieves an Education entity from the database based on its ID.
     *
     * @param id The ID of the Education entity to retrieve.
     * @return The Education entity with the specified ID, or null if not found.
     */
    public Education findEducation(Integer id) {
        return entityManager.find(Education.class, id);
    }

    /**
     * Updates an existing Education entity in the database.
     *
     * @param education The Education entity to be updated.
     */
    public void updateEducation(Education education) {
        entityManager.merge(education);
    }

    /**
     * Deletes an Education entity from the database based on its ID.
     *
     * @param id The ID of the Education entity to delete.
     */
    public void deleteEducation(Integer id) {
        Education education = entityManager.find(Education.class, id);
        if (education != null) {
            entityManager.remove(education);
        }
    }
}


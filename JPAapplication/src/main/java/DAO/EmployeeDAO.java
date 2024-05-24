package DAO;

import entities.Employee;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Id;

/**
 * Data Access Object (DAO) for managing Employee entities.
 */
public class EmployeeDAO {
    private final EntityManager entityManager;

    /**
     * Constructs an EmployeeDAO with the provided EntityManager.
     *
     * @param entityManager The EntityManager to be used for database operations.
     */
    public EmployeeDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    /**
     * Persists a new Employee entity to the database.
     *
     * @param employee The Employee entity to be created.
     */
    public void createEmployee(Employee employee) {
        entityManager.persist(employee);
    }

    /**
     * Retrieves an Employee entity from the database based on its ID.
     *
     * @param id The ID of the Employee entity to retrieve.
     * @return The Employee entity with the specified ID, or null if not found.
     */
    public Employee findEmployee(Integer id) {
        return entityManager.find(Employee.class, id);
    }

    /**
     * Updates an existing Employee entity in the database.
     *
     * @param employee The Employee entity to be updated.
     */
    public void updateEmployee(Employee employee) {
        entityManager.merge(employee);
    }

    /**
     * Deletes an Employee entity from the database based on its ID.
     *
     * @param id The ID of the Employee entity to delete.
     */
    public void deleteEmployee(Integer id) {
        Employee employee = entityManager.find(Employee.class, id);
        if (employee != null) {
            entityManager.remove(employee);
        }
    }
}

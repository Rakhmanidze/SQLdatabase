package DAO;

import entities.Customer;
import entities.Person;
import jakarta.persistence.EntityManager;

/**
 * Data Access Object (DAO) for managing Customer entities.
 */
public class CustomerDAO {
    private final EntityManager entityManager;

    /**
     * Constructs a CustomerDAO with the provided EntityManager.
     *
     * @param entityManager The EntityManager to be used for database operations.
     */
    public CustomerDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    /**
     * Persists a new Customer entity to the database.
     *
     * @param customer The Customer entity to be created.
     */
    public void createCustomer(Customer customer) {
        entityManager.persist(customer);
    }

    /**
     * Retrieves a Customer entity from the database based on its ID.
     *
     * @param id The ID of the Customer entity to retrieve.
     * @return The Customer entity with the specified ID, or null if not found.
     */
    public Customer findCustomer(Integer id) {
        return entityManager.find(Customer.class, id);
    }

    /**
     * Updates an existing Customer entity in the database.
     *
     * @param customer The Customer entity to be updated.
     */
    public void updateCustomer(Customer customer) {
        entityManager.merge(customer);
    }

    /**
     * Deletes a Customer entity from the database based on its ID.
     *
     * @param id The ID of the Customer entity to delete.
     */
    public void deleteCustomer(Integer id) {
        Customer customer = entityManager.find(Customer.class, id);
        if (customer != null) {
            entityManager.remove(customer);
        }
    }
}


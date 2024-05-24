package DAO;

import entities.Account;
import jakarta.persistence.EntityManager;

/**
 * Data Access Object (DAO) for managing Account entities.
 */
public class AccountDAO {
    private final EntityManager entityManager;

    /**
     * Constructs an AccountDAO with the provided EntityManager.
     *
     * @param entityManager The EntityManager to be used for database operations.
     */
    public AccountDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    /**
     * Persists a new Account entity to the database.
     *
     * @param account The Account entity to be created.
     */
    public void createAccount(Account account) {
        entityManager.persist(account);
    }

    /**
     * Retrieves an Account entity from the database based on its ID.
     *
     * @param id The ID of the Account entity to retrieve.
     * @return The Account entity with the specified ID, or null if not found.
     */
    public Account findAccount(Integer id) {
        return entityManager.find(Account.class, id);
    }

    /**
     * Updates an existing Account entity in the database.
     *
     * @param account The Account entity to be updated.
     */
    public void updateAccount(Account account) {
        entityManager.merge(account);
    }

    /**
     * Deletes an Account entity from the database based on its ID.
     *
     * @param id The ID of the Account entity to delete.
     */
    public void deleteAccount(Integer id) {
        Account account = entityManager.find(Account.class, id);
        if (account != null) {
            entityManager.remove(account);
        }
    }
}

package DAO;

import entities.Bank;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;

import java.util.List;

/**
 * Data Access Object (DAO) for managing Bank entities.
 */
public class BankDAO {
    private final EntityManager entityManager;

    /**
     * Constructs a BankDAO with the provided EntityManager.
     *
     * @param entityManager The EntityManager to be used for database operations.
     */
    public BankDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    /**
     * Persists a new Bank entity to the database.
     *
     * @param bank The Bank entity to be created.
     */
    public void createBank(Bank bank) {
        entityManager.persist(bank);
    }

    /**
     * Retrieves a Bank entity from the database based on its ID.
     *
     * @param id The ID of the Bank entity to retrieve.
     * @return The Bank entity with the specified ID, or null if not found.
     */
    public Bank findBank(Integer id) {
        return entityManager.find(Bank.class, id);
    }

    /**
     * Retrieves a Bank entity from the database based on its IBAN.
     *
     * @param iban The IBAN of the Bank entity to retrieve.
     * @return The Bank entity with the specified IBAN, or null if not found.
     */
    public Bank findBankByIban(String iban) {
        TypedQuery<Bank> query = entityManager.createQuery("SELECT b FROM Bank b WHERE b.iban = :iban", Bank.class);
        query.setParameter("iban", iban);
        try {
            return query.getSingleResult();
        } catch (NoResultException e) {
            // Log or handle the case where no bank with the given IBAN is found
            System.err.println("No bank found with IBAN: " + iban);
            return null;
        }
    }

    /**
     * Updates an existing Bank entity in the database.
     *
     * @param bank The Bank entity to be updated.
     */
    public void updateBank(Bank bank) {
        entityManager.merge(bank);
    }

    /**
     * Deletes a Bank entity from the database based on its ID.
     *
     * @param id The ID of the Bank entity to delete.
     */
    public void deleteBank(Integer id) {
        Bank bank = entityManager.find(Bank.class, id);
        if (bank != null) {
            entityManager.remove(bank);
        }
    }
}


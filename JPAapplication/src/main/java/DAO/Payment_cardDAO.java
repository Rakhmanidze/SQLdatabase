package DAO;

import entities.Payment_card;
import jakarta.persistence.EntityManager;

/**
 * Data Access Object (DAO) for managing Payment_card entities.
 */
public class Payment_cardDAO {
    private final EntityManager entityManager;

    /**
     * Constructs a Payment_cardDAO with the provided EntityManager.
     *
     * @param entityManager The EntityManager to be used for database operations.
     */
    public Payment_cardDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    /**
     * Persists a new Payment_card entity to the database.
     *
     * @param paymentCard The Payment_card entity to be created.
     */
    public void createPaymentCard(Payment_card paymentCard) {
        entityManager.persist(paymentCard);
    }

    /**
     * Retrieves a Payment_card entity from the database based on its ID.
     *
     * @param id The ID of the Payment_card entity to retrieve.
     * @return The Payment_card entity with the specified ID, or null if not found.
     */
    public Payment_card findPaymentCard(Integer id) {
        return entityManager.find(Payment_card.class, id);
    }

    /**
     * Updates an existing Payment_card entity in the database.
     *
     * @param paymentCard The Payment_card entity to be updated.
     */
    public void updatePaymentCard(Payment_card paymentCard) {
        entityManager.merge(paymentCard);
    }

    /**
     * Deletes a Payment_card entity from the database based on its ID.
     *
     * @param id The ID of the Payment_card entity to delete.
     */
    public void deletePaymentCard(Integer id) {
        Payment_card paymentCard = entityManager.find(Payment_card.class, id);
        if (paymentCard != null) {
            entityManager.remove(paymentCard);
        }
    }
}


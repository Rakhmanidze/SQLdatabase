import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

/**
 * Main class for executing banking operations and testing functionalities.
 */
public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Bank");
        EntityManager em = emf.createEntityManager();

        BankService bankService = new BankService(em);
        bankService.makeMoneyTransfer(em,200,7, 1); // try from account with invalid balance (11)
//        bankService.deleteViewCreateItViewAndCheckIt(em);
//        bankService.createNewBank(em, "New Bank 130", "New Office 130", "NEWIBAN130");
//        bankService.deleteInsertedBank(em, "NEWIBAN130");
//        bankService.createTriggerCheckItDropIt(em);
//        bankService.displayDataFromManyToManyTable(em);

//        bankService.displayDataFromDescendantTable(em);

        em.close();
        emf.close();
    }
}

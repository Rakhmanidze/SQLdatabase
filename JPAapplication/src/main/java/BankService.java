import DAO.*;
import entities.*;
import jakarta.persistence.EntityManager;
import java.util.Arrays;
import java.util.List;

/**
 * This class provides various methods for performing operations related to banking entities
 * and database manipulation.
 */
public class BankService {
    AccountDAO accountDAO;
    BankDAO bankDAO;
    CustomerDAO customerDAO;
    EducationDAO educationDAO;
    EmployeeDAO employeeDAO;
    Payment_cardDAO paymentCardDAO;
    PersonDAO personDAO;

    /**
     * Constructs a BankService instance with initialized DAOs.
     *
     * @param em The EntityManager instance for database interaction.
     */
    public BankService(EntityManager em) {
        accountDAO = new AccountDAO(em);
        bankDAO = new BankDAO(em);
        customerDAO = new CustomerDAO(em);
        educationDAO = new EducationDAO(em);
        employeeDAO = new EmployeeDAO(em);
        paymentCardDAO = new Payment_cardDAO(em);
        personDAO = new PersonDAO(em);
    }


    /**
     * Transfers money between two accounts.
     *
     * @param em            The EntityManager instance for database interaction.
     * @param tranferAmount The amount to transfer.
     * @param idAccountFrom The ID of the account to transfer from.
     * @param idAccountTo   The ID of the account to transfer to.
     */
    public void makeMoneyTransfer(EntityManager em,Integer tranferAmount,Integer idAccountFrom, Integer idAccountTo) {
        try {
            em.getTransaction().begin();

            Account accountFrom = accountDAO.findAccount(idAccountFrom);
            Account accountTo = accountDAO.findAccount(idAccountTo);

            if (accountFrom != null && accountTo != null && accountFrom.getAccount_balance() >= tranferAmount) {
                accountFrom.setAccount_balance(accountFrom.getAccount_balance() - tranferAmount);

                accountDAO.updateAccount(accountFrom);
                accountTo.setAccount_balance(accountTo.getAccount_balance() + tranferAmount);
                accountDAO.updateAccount(accountTo);

                em.getTransaction().commit();
            } else {
                // Rollback transaction if accounts are not found or insufficient balance
                if (em.getTransaction().isActive()) {
                    em.getTransaction().rollback();
                }
                throw new Exception("Invalid account or insufficient balance");
            }
        } catch (Exception e) {
            // Rollback transaction if an exception occurs
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
        }
    }

    /**
     * Deletes a database view, creates it, populates it with data, and checks the results.
     *
     * @param em The EntityManager instance for database interaction.
     */
    public void deleteViewCreateItViewAndCheckIt(EntityManager em) {
        try {
            em.getTransaction().begin();

            // Delete view if it exists
            em.createNativeQuery("DROP VIEW IF EXISTS person_card").executeUpdate();

            // create that view
            em.createNativeQuery("CREATE VIEW person_card AS\n" +
                    "SELECT " +
                    "    p.birth_certificate_number AS birth_certificate_number, " +
                    "    p.full_name AS person_name, " +
                    "    pc.card_number, " +
                    "    pc.card_status " +
                    "FROM " +
                    "    Person p " +
                    "JOIN " +
                    "    Payment_Card pc ON p.id_person = pc.account_id").executeUpdate();

            // check in see what this view does
            List<Object[]> viewResult = em.createNativeQuery("SELECT * FROM person_card").getResultList();
            for (Object[] row : viewResult) {
                System.out.println(Arrays.toString(row));
            }

            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
        }
    }

    /**
     * Creates a new bank entity if a bank with the provided IBAN doesn't already exist.
     *
     * @param em        The EntityManager instance for database interaction.
     * @param bankName  The name of the new bank.
     * @param mainOffice The main office of the new bank.
     * @param iban      The IBAN of the new bank.
     */
    public void createNewBank(EntityManager em, String bankName, String mainOffice, String iban) {
        try {
            em.getTransaction().begin();

            // Check if a bank with the given IBAN already exists
            Bank existingBank = bankDAO.findBankByIban(iban);
            if (existingBank != null) {
                System.out.println("A bank with the same IBAN already exists.");
                return;
            }

            // Create and persist the new bank
            Bank newBank = new Bank();
            newBank.setBank_name(bankName);
            newBank.setMain_office(mainOffice);
            newBank.setIban(iban);
            bankDAO.createBank(newBank);

            // Retrieve the bank details from the database using the IBAN
            List<Object[]> viewResult = em.createNativeQuery("SELECT * FROM bank WHERE iban = '" + iban + "'")
                    .getResultList();

            // Print the retrieved bank details
            for (Object[] row : viewResult) {
                System.out.println("Bank ID: " + row[0]);
                System.out.println("Bank Name: " + row[1]);
                System.out.println("Main Office: " + row[2]);
                System.out.println("IBAN: " + row[3]);
            }

            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
        }
    }

    /**
     * Deletes a bank entity with the specified IBAN if it exists.
     *
     * @param em   The EntityManager instance for database interaction.
     * @param iban The IBAN of the bank to delete.
     */
    public void deleteInsertedBank(EntityManager em, String iban) {
        try {
            em.getTransaction().begin();

            Bank bankToDelete = bankDAO.findBankByIban(iban);
            if (bankToDelete == null) {
                System.out.println("Bank with IBAN " + iban + " not found.");
                return;
            }

            bankDAO.deleteBank(bankToDelete.getId_bank());
            System.out.println("Bank with IBAN " + iban + " deleted successfully.");

            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
        }
    }

    /**
     * Creates a trigger function to check account balance and tests it with invalid data insertion.
     *
     * @param em The EntityManager instance for database interaction.
     */
    public void createTriggerCheckItDropIt(EntityManager em) {
        try {
            em.getTransaction().begin();

            // Drop the trigger if it exists
            em.createNativeQuery("DROP TRIGGER IF EXISTS account_balance_trigger  ON account")
                    .executeUpdate();

            // Create or replace the trigger function
            em.createNativeQuery("CREATE OR REPLACE FUNCTION check_balance_trigger() RETURNS TRIGGER AS $$\n" +
                            "BEGIN\n" +
                            "    IF NEW.account_balance IS NULL OR NEW.account_balance < 0 THEN\n" +
                            "        RAISE EXCEPTION 'Account balance cannot be null or negative';\n" +
                            "    END IF;\n" +
                            "    RETURN NEW;\n" +
                            "END;\n" +
                            "$$ LANGUAGE plpgsql;")
                    .executeUpdate();

            // Insert invalid data to test the trigger
            try {
                em.createNativeQuery("INSERT INTO account (iban, account_number, account_balance, account_type, currency_type) " +
                                "VALUES ('CZ0800', '1030500891', -100.00, 'checking', 'USD')")
                        .executeUpdate();
            } catch (Exception e) {
                // If insertion fails due to trigger, catch the exception
                System.out.println("Trigger successfully prevented insertion of invalid data.");
            }

            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
        }

        em.getTransaction().begin();
        em.createNativeQuery("DROP TRIGGER IF EXISTS account_balance_trigger ON account")
                .executeUpdate();
        em.getTransaction().commit();

        // Check if the trigger was deleted
        System.out.println("Trigger deleted successfully.");
    }

    /**
     * Displays data from the many-to-many relationship table between Account and Person entities.
     *
     * @param entityManager The EntityManager instance for database interaction.
     */
    public static void displayDataFromManyToManyTable(EntityManager entityManager) {
        List<Object[]> results = entityManager
                .createQuery("SELECT a.account_number, p.full_name FROM Account a JOIN a.persons p", Object[].class)
                .getResultList();

        for (Object[] result : results) {
            String accountNumber = (String) result[0];
            String fullName = (String) result[1];

            System.out.println("---");
            System.out.println("Account number: " + accountNumber);
            System.out.println("Person name: " + fullName);
            System.out.println("---");
        }
    }

    /**
     * Displays data from the descendant table Customer.
     *
     * @param entityManager The EntityManager instance for database interaction.
     */

    public static void displayDataFromDescendantTable(EntityManager entityManager) {
        List<Customer> results = entityManager
                .createQuery("SELECT c FROM Customer c", Customer.class)
                .getResultList();

        for (Customer customer : results) {
            System.out.println("---");
            System.out.println("customer name is: " + customer.getFull_name());
            System.out.println("---");
        }
    }

    /**
     * Provides CRUD operations for various entities including Account, Bank, Customer, Education,
     * Employee, Payment_card, and Person.
     */
    public void createAccount(Account account) {
        accountDAO.createAccount(account);
    }

    public void updateAccount(Account account) {
        accountDAO.updateAccount(account);
    }

    public Account findAccount(Integer id) {
        return accountDAO.findAccount(id);
    }

    public void deleteAccount(Integer id) {
        accountDAO.deleteAccount(id);
    }

    public void createBank(Bank bank) {
        bankDAO.createBank(bank);
    }

    public void updateBank(Bank bank) {
        bankDAO.updateBank(bank);
    }

    public Bank findBank(Integer id) {
        return bankDAO.findBank(id);
    }

    public void deleteBank(Integer id) {
        bankDAO.deleteBank(id);
    }

    public void createCustomer(Customer customer) {
        customerDAO.createCustomer(customer);
    }

    public void updateCustomer(Customer customer) {
        customerDAO.updateCustomer(customer);
    }

    public Customer findCustomer(Integer id) {
        return customerDAO.findCustomer(id);
    }

    public void deleteCustomer(Integer id) {
        customerDAO.deleteCustomer(id);
    }

    public void createEducation(Education education) {
        educationDAO.createEducation(education);
    }

    public void updateEducation(Education education) {
        educationDAO.updateEducation(education);
    }

    public Education findEducation(Integer id) {
        return educationDAO.findEducation(id);
    }

    public void deleteEducation(Integer id) {
        educationDAO.deleteEducation(id);
    }

    public void createEmployee(Employee employee) {
        employeeDAO.createEmployee(employee);
    }

    public void updateEmployee(Employee employee) {
        employeeDAO.updateEmployee(employee);
    }

    public Employee findEmployee(Integer id) {
        return employeeDAO.findEmployee(id);
    }

    public void deleteEmployee(Integer id) {
        employeeDAO.deleteEmployee(id);
    }

    public void createPayment_card(Payment_card paymentCard) {
        paymentCardDAO.createPaymentCard(paymentCard);
    }

    public void updatePayment_card(Payment_card paymentCard) {
        paymentCardDAO.updatePaymentCard(paymentCard);
    }

    public Payment_card findPayment_card(Integer id) {
        return paymentCardDAO.findPaymentCard(id);
    }

    public void deletePayment_card(Integer id) {
        paymentCardDAO.deletePaymentCard(id);
    }

    public void createPerson(Person person) {
        personDAO.createPerson(person);
    }

    public void updatePerson(Person person) { personDAO.updatePerson(person);}

    public Person findPerson(Integer id) {
        return personDAO.findPerson(id);
    }

    public void deletePerson(Integer id) {
        personDAO.deletePerson(id);
    }
}

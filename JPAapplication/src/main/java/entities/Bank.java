package entities;

import jakarta.persistence.*;
import java.util.Collection;

/**
 * This class represents a Bank entity in the database.
 * Each bank has a unique identifier, name, main office location, and IBAN.
 * It also holds a collection of accounts associated with this bank.
 */
@Entity
@Table(name = "bank")
public class Bank {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_bank", nullable = false)
    private Integer id_bank;

    @Column(name = "bank_name", nullable = false, unique = true, length = 30)
    private String bank_name;

    @Column(name = "main_office", nullable = false, length = 30)
    private String main_office;

    @Column(name = "iban", nullable = false, unique = true, length = 34)
    private String iban;

    @OneToMany(mappedBy = "bank")
    private Collection<Account> accounts;

    public Bank() {
    }

    /**
     * Constructs a Bank object with the provided parameters.
     *
     * @param id_bank     The unique identifier for the bank.
     * @param bank_name   The unique name of the bank.
     * @param main_office The location of the main office of the bank.
     * @param iban        The unique IBAN associated with the bank.
     * @param accounts    The collection of Account objects associated with the bank.
     */
    public Bank(Integer id_bank, String bank_name, String main_office, String iban, Collection<Account> accounts) {
        this.id_bank = id_bank;
        this.bank_name = bank_name;
        this.main_office = main_office;
        this.iban = iban;
        this.accounts = accounts;
    }


    //    Getters and Setters
    public Integer getId_bank() {
        return id_bank;
    }

    public String getBank_name() {
        return bank_name;
    }

    public String getMain_office() {
        return main_office;
    }

    public String getIban() {
        return iban;
    }

    public Collection<Account> getAccounts() {
        return accounts;
    }

    public void setId_bank(Integer id_bank) {
        this.id_bank = id_bank;
    }

    public void setBank_name(String bank_name) {
        this.bank_name = bank_name;
    }

    public void setMain_office(String main_office) {
        this.main_office = main_office;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public void setAccounts(Collection<Account> accounts) {
        this.accounts = accounts;
    }
}

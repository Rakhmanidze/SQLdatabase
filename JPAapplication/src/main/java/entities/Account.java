package entities;

import jakarta.persistence.*;
import java.util.Collection;

/**
 * This class represents an Account entity in the database.
 * Each account has a unique identifier, account number, balance, type, and currency.
 * It also holds a reference to the bank it belongs to, any associated payment card,
 * and the collection of persons associated with this account.
 */
@Entity
@Table(name = "account")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_account", nullable = false)
    private Integer id_account;

    @Column(name = "account_number", nullable = false, unique = true, length = 10)
    private String account_number;

    @Column(name = "account_balance", nullable = false)
    private double account_balance;

    @Column(name = "account_type", nullable = false, length = 50)
    private String account_type;

    @Column(name = "currency_type", nullable = false, length = 3)
    private String currency_type;

//    @Column(name = "iban", nullable = false, unique = true, length = 34)
//    private String iban;

    @ManyToOne
    @JoinColumn(name = "iban", referencedColumnName = "iban")
    private Bank bank;

    @OneToOne(mappedBy = "account")
    private Payment_card paymentCard;

    @ManyToMany(mappedBy = "accounts")
    private Collection<Person> persons;

    public Account() {
    }

    /**
     * Constructs an Account object with the provided parameters.
     *
     * @param id_account      The unique identifier for the account.
     * @param account_number  The unique account number.
     * @param account_balance The balance of the account.
     * @param account_type    The type of the account (e.g., savings, current).
     * @param currency_type   The currency type of the account (e.g., USD, EUR).
     * @param iban            The IBAN associated with the account.
     * @param bank            The Bank object associated with the account.
     * @param paymentCard     The Payment_card object associated with the account.
     * @param persons         The collection of Person objects associated with the account.
     */
    public Account(Integer id_account, String account_number, double account_balance, String account_type, String currency_type, String iban, Bank bank, Payment_card paymentCard, Collection<Person> persons) {
        this.id_account = id_account;
        this.account_number = account_number;
        this.account_balance = account_balance;
        this.account_type = account_type;
        this.currency_type = currency_type;
//        this.iban = iban;
        this.bank = bank;
        this.paymentCard = paymentCard;
        this.persons = persons;
    }

//    Getters and Setters

    public Integer getId_account() {
        return id_account;
    }

    public String getAccount_number() {
        return account_number;
    }

    public double getAccount_balance() {
        return account_balance;
    }

    public String getAccount_type() {
        return account_type;
    }

    public String getCurrency_type() {
        return currency_type;
    }

//    public String getIban() {
//        return iban;
//    }

    public Bank getBank() {
        return bank;
    }

    public Payment_card getPaymentCard() {
        return paymentCard;
    }

    public Collection<Person> getPersons() {
        return persons;
    }

    public void setId_account(Integer id_account) {
        this.id_account = id_account;
    }

    public void setAccount_number(String account_number) {
        this.account_number = account_number;
    }

    public void setAccount_balance(double account_balance) {
        this.account_balance = account_balance;
    }

    public void setAccount_type(String account_type) {
        this.account_type = account_type;
    }

    public void setCurrency_type(String currency_type) {
        this.currency_type = currency_type;
    }

//    public void setIban(String iban) {
//        this.iban = iban;
//    }

    public void setBank(Bank bank) {
        this.bank = bank;
    }

    public void setPaymentCard(Payment_card paymentCard) {
        this.paymentCard = paymentCard;
    }

    public void setPersons(Collection<Person> persons) {
        this.persons = persons;
    }
}

package entities;

import jakarta.persistence.*;

import java.util.Collection;
import java.util.Date;

/**
 * This class represents a Person entity in the database.
 * Each person has a unique identifier, birth certificate number, birth date, full name, email, phone number,
 * country, city, postcode, street, and may be associated with multiple accounts.
 */
@Entity
@Table(name = "person")
@Inheritance(strategy = InheritanceType.JOINED)
//@DiscriminatorColumn(name = "person_type", discriminatorType = DiscriminatorType.STRING)
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_person", nullable = false)
    private Integer id_person;

    @Column(name = "birth_certificate_number", nullable = false, unique = true, length = 10)
    private String birth_certificate_number;

    @Column(name = "birth_date", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date birth_date;

    @Column(name = "full_name", nullable = false, length = 50)
    private String full_name;

    @Column(name = "email", nullable = false, length = 50)
    private String email;

    @Column(name = "phone_number", nullable = false, length = 13)
    private String phone_number;

    @Column(name = "country", nullable = false, length = 50)
    private String country;

    @Column(name = "city", nullable = false, length = 50)
    private String city;

    @Column(name = "postcode", nullable = false, length = 7)
    private String postcode;

    @Column(name = "street", nullable = false, length = 50)
    private String street;

    @ManyToMany
    @JoinTable(
            name = "person_account",
            joinColumns = @JoinColumn(name = "person_id"),
            inverseJoinColumns = @JoinColumn(name = "account_id")
    )
    private Collection<Account> accounts;

    public Person() {}

    /**
     * Constructs a Person object with the provided parameters.
     *
     * @param id_person               The unique identifier for the person.
     * @param birth_certificate_number The unique birth certificate number of the person.
     * @param birth_date              The birthdate of the person.
     * @param full_name               The full name of the person.
     * @param email                   The email address of the person.
     * @param phone_number            The phone number of the person.
     * @param country                 The country of residence of the person.
     * @param city                    The city of residence of the person.
     * @param postcode                The postcode of residence of the person.
     * @param street                  The street address of residence of the person.
     * @param accounts                The collection of accounts associated with the person.
     */
    public Person(Integer id_person, String birth_certificate_number, Date birth_date, String full_name, String email, String phone_number, String country, String city, String postcode, String street, Collection<Account> accounts) {
        this.id_person = id_person;
        this.birth_certificate_number = birth_certificate_number;
        this.birth_date = birth_date;
        this.full_name = full_name;
        this.email = email;
        this.phone_number = phone_number;
        this.country = country;
        this.city = city;
        this.postcode = postcode;
        this.street = street;
        this.accounts = accounts;
    }

    //    Getters and Setters
    public Integer getId_person() {
        return id_person;
    }

    public void setId_person(Integer id_person) {
        this.id_person = id_person;
    }

    public String getBirth_certificate_number() {
        return birth_certificate_number;
    }

    public void setBirth_certificate_number(String birth_certificate_number) {
        this.birth_certificate_number = birth_certificate_number;
    }

    public Date getBirth_date() {
        return birth_date;
    }

    public void setBirth_date(Date birth_date) {
        this.birth_date = birth_date;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public Collection<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(Collection<Account> accounts) {
        this.accounts = accounts;
    }
}

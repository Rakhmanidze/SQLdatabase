package entities;

import jakarta.persistence.*;
import java.util.Date;

/**
 * This class represents a Payment Card entity in the database.
 * Each payment card has a unique identifier, card number, status, expiration date, CVC, and is associated with an account.
 */
@Entity
@Table(name = "payment_card")
public class Payment_card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_card", nullable = false)
    private Integer id_card;

    @Column(name = "card_number", nullable = false, unique = true, length = 20)
    private String card_number;

    @Column(name = "card_status", nullable = false, length = 7)
    private String card_status;

    @Column(name = "expiration_date", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date expiration_date;

    @Column(name = "CVC", nullable = false)
    private short CVC;

    @OneToOne
    @JoinColumn(name = "account_id", referencedColumnName = "id_account", unique = true)
    private Account account;

    public Payment_card() {}

    /**
     * Constructs a Payment_card object with the provided parameters.
     *
     * @param id_card         The unique identifier for the payment card.
     * @param card_number     The unique card number.
     * @param card_status     The status of the card.
     * @param expiration_date The expiration date of the card.
     * @param CVC             The Card Verification Code (CVC).
     * @param account         The Account object associated with the card.
     */
    public Payment_card(Integer id_card, String card_number, String card_status, Date expiration_date, short CVC, Account account) {
        this.id_card = id_card;
        this.card_number = card_number;
        this.card_status = card_status;
        this.expiration_date = expiration_date;
        this.CVC = CVC;
        this.account = account;
    }

    //    Getters and Setters
    public Integer getId_card() {
        return id_card;
    }

    public String getCard_number() {
        return card_number;
    }

    public String getCard_status() {
        return card_status;
    }

    public Date getExpiration_date() {
        return expiration_date;
    }

    public short getCVC() {
        return CVC;
    }

    public Account getAccount() {
        return account;
    }

    public void setId_card(Integer id_card) {
        this.id_card = id_card;
    }

    public void setCard_number(String card_number) {
        this.card_number = card_number;
    }

    public void setCard_status(String card_status) {
        this.card_status = card_status;
    }

    public void setExpiration_date(Date expiration_date) {
        this.expiration_date = expiration_date;
    }

    public void setCVC(short CVC) {
        this.CVC = CVC;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}

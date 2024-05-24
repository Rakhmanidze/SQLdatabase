package entities;

import jakarta.persistence.*;
import java.util.Date;

/**
 * This class represents a Customer entity in the database, extending the Person class.
 * Each customer has a status, registration date, and is associated with an employee.
 */
@Entity
@Table(name = "customer")
//@DiscriminatorValue("Customer")
public class Customer extends Person {
    @Column(name = "customer_status", nullable = false, length = 10)
    private String customer_status;

    @Column(name = "registration_date", nullable = false)
    private Date registration_date;

    @ManyToOne
    @JoinColumn(name = "id_person", nullable = false)
    private Employee employee;

    public Customer() {}

    /**
     * Constructs a Customer object with the provided parameters.
     *
     * @param customer_status   The status of the customer.
     * @param registration_date The registration date of the customer.
     * @param employee          The Employee object associated with the customer.
     */
    public Customer(String customer_status, Date registration_date, Employee employee) {
        this.customer_status = customer_status;
        this.registration_date = registration_date;
        this.employee = employee;
    }

    //    Getters and Setters
    public String getCustomer_status() {
        return customer_status;
    }

    public Date getRegistration_date() {
        return registration_date;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setCustomer_status(String customer_status) {
        this.customer_status = customer_status;
    }

    public void setRegistration_date(Date registration_date) {
        this.registration_date = registration_date;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}

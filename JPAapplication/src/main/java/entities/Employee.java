package entities;

import jakarta.persistence.*;
import java.util.Collection;

/**
 * This class represents an Employee entity in the database, extending the Person class.
 * Each employee may have a superior, a collection of customers, and a collection of educations.
 */
@Entity
@Table(name = "employee")
//@DiscriminatorValue("Employee")
public class Employee extends Person {
    @ManyToOne
    @JoinColumn(name = "id_superior")
    private Employee superior;

    @OneToMany(mappedBy = "employee")
    private Collection<Customer> customers;

    @OneToMany(mappedBy = "employee")
    private Collection<Education> educations;

    public Employee() {}

    /**
     * Constructs an Employee object with the provided parameters.
     *
     * @param superior   The superior employee of this employee.
     * @param customers  The collection of customers associated with this employee.
     * @param educations The collection of educations associated with this employee.
     */
    public Employee(Employee superior, Collection<Customer> customers, Collection<Education> educations) {
        this.superior = superior;
        this.customers = customers;
        this.educations = educations;
    }

    //    Getters and Setters
    public Employee getSuperior() {
        return superior;
    }

    public void setSuperior(Employee superior) {
        this.superior = superior;
    }

    public Collection<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(Collection<Customer> customers) {
        this.customers = customers;
    }

    public Collection<Education> getEducations() {
        return educations;
    }

    public void setEducations(Collection<Education> educations) {
        this.educations = educations;
    }
}

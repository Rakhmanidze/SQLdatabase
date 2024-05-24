package entities;

import jakarta.persistence.*;

/**
 * This class represents an Education entity in the database.
 * Each education record has a unique identifier, institution name, and is associated with an employee.
 */
@Entity
@Table(name = "education")
public class Education {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_education")
    private Integer id_education;

    @Column(name = "institution_name", nullable = false, length = 50)
    private String institution_name;

    @ManyToOne
    @JoinColumn(name = "id_person", nullable = false)
    private Employee employee;

    public Education() {
    }

    /**
     * Constructs an Education object with the provided parameters.
     *
     * @param id_education     The unique identifier for the education record.
     * @param institution_name The name of the institution.
     * @param employee         The Employee object associated with the education record.
     */
    public Education(Integer id_education, String institution_name, Employee employee) {
        this.id_education = id_education;
        this.institution_name = institution_name;
        this.employee = employee;
    }

    //    Getters and Setters
    public Integer getId_education() {
        return id_education;
    }

    public String getInstitution_name() {
        return institution_name;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}

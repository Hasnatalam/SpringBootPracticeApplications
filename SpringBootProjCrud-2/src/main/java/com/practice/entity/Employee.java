/*package com.hasnat.entity;

import java.time.LocalDate;

import org.antlr.v4.runtime.misc.NotNull;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Data
@Table(name = "employeeManagement")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "name")
    private String name;

    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "city")
    private String city;

    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "designation")
    private String designation;

    @NotNull
    @Column(name = "salary")
    private Double salary;

    @NotNull
    @Column(name = "joining_date")
    private LocalDate joiningDate;
}
*/

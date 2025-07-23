package com.hrmanagement.hrmanagement.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({"manager"})
@Entity
@Table(name="employees")
public class Employee {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable=false)
    private String name;

    private String email;
    private String department;
    private Double baseSalary;

    private LocalDate joinDate;

    // Self-reference: manager of this employee
    @ManyToOne
    @JoinColumn(name="manager_id")
    private Employee manager;

    // Reverse link handled in User
    // getters/setters...
    // (Generate or write manually)
    // ...
    public Integer getId() {return id;}
    public void setId(Integer id){this.id=id;}
    public String getName(){return name;}
    public void setName(String name){this.name=name;}
    public String getEmail(){return email;}
    public void setEmail(String email){this.email=email;}
    public String getDepartment(){return department;}
    public void setDepartment(String d){this.department=d;}
    public Double getBaseSalary(){return baseSalary;}
    public void setBaseSalary(Double s){this.baseSalary=s;}
    public LocalDate getJoinDate(){return joinDate;}
    public void setJoinDate(LocalDate jd){this.joinDate=jd;}
    public Employee getManager(){return manager;}
    public void setManager(Employee m){this.manager=m;}
}
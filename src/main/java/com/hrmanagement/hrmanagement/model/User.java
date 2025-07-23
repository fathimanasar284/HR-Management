package com.hrmanagement.hrmanagement.model;

import jakarta.persistence.*;

@Entity
@Table(name = "users", uniqueConstraints = @UniqueConstraint(columnNames = "username"))
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable=false, unique=true)
    private String username;

    @Column(nullable=false)
    private String password;   // store hashed

    @Enumerated(EnumType.STRING)
    @Column(nullable=false)
    private Role role;

    // Link to employee record (nullable for admin accounts)
    @OneToOne
    @JoinColumn(name="employee_id")
    private Employee employee;

    // getters/setters...
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public Role getRole() { return role; }
    public void setRole(Role role) { this.role = role; }

    public Employee getEmployee() { return employee; }
    public void setEmployee(Employee employee) { this.employee = employee; }
}

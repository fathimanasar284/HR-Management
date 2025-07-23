package com.hrmanagement.hrmanagement.dao;
import org.springframework.data.jpa.repository.JpaRepository;
import com.hrmanagement.hrmanagement.model.*;
import java.util.List;
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    List<Employee> findByManager_Id(Integer managerId);
}

package com.hrmanagement.hrmanagement.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.hrmanagement.hrmanagement.dao.EmployeeRepository;
import com.hrmanagement.hrmanagement.model.Employee;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {
    private final EmployeeRepository repo;
    public EmployeeService(EmployeeRepository repo){this.repo=repo;}

    public List<Employee> all(){ return repo.findAll(); }
    public Optional<Employee> get(Integer id){ return repo.findById(id); }

    @Transactional
    public Employee save(Employee e){ return repo.save(e); }

    @Transactional
    public boolean delete(Integer id){
        if(repo.existsById(id)){ repo.deleteById(id); return true; }
        return false;
    }
}

package com.hrmanagement.hrmanagement.controller;

import org.springframework.web.bind.annotation.*;
import com.hrmanagement.hrmanagement.service.EmployeeService;
import com.hrmanagement.hrmanagement.dao.EmployeeRepository;
import com.hrmanagement.hrmanagement.model.Employee;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {
    private final EmployeeService empService;
    private final EmployeeRepository empRepo;
    public EmployeeController(EmployeeService s, EmployeeRepository r){this.empService=s; this.empRepo=r;}

    @GetMapping
    public List<Employee> all(){ return empService.all(); }

    @GetMapping("/{id}")
    public Employee get(@PathVariable Integer id){ return empService.get(id).orElse(null); }

    @PostMapping
    public Employee create(@RequestBody Employee e){
        // if manager id present, fetch & set. Accept raw JSON; front-end must pass correct fields.
        if(e.getManager()!=null && e.getManager().getId()!=null){
            empRepo.findById(e.getManager().getId()).ifPresent(e::setManager);
        }
        return empService.save(e);
    }

    @PutMapping("/{id}")
    public Employee update(@PathVariable Integer id, @RequestBody Employee e){
        e.setId(id);
        if(e.getManager()!=null && e.getManager().getId()!=null){
            empRepo.findById(e.getManager().getId()).ifPresent(e::setManager);
        }
        return empService.save(e);
    }

    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable Integer id){ return empService.delete(id); }
}

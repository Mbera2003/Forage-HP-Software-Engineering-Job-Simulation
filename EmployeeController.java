package com.example.demo;

import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
    private List<Employee> employees = new ArrayList<>();

    // GET method to retrieve all employees
    @GetMapping
    public List<Employee> getAllEmployees() {
        return employees;
    }

    // POST method to add a new employee
    @PostMapping
    public void addEmployee(@RequestBody Employee employee) {
        employees.add(employee);
    }
}

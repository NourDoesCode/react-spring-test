package com.nour.ems_app.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nour.ems_app.dto.EmployeeDto;
import com.nour.ems_app.service.EmployeeService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/api/employees")
public class EmployeeController {
    // inject service interface
    private EmployeeService employeeService;

    // Build add Employee REST API
    @PostMapping
    public ResponseEntity<EmployeeDto> createEmployee(@RequestBody EmployeeDto employeeDto) {
        EmployeeDto savedEmployee = employeeService.createEmployee(employeeDto);
        return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);
    }

    // Build GET single employee based on url
    @GetMapping(path = "{id}")
    public ResponseEntity<EmployeeDto> getEmployeeById(@PathVariable("id") Long id) {
        EmployeeDto employeeById = employeeService.getEmployeeById(id);

        return ResponseEntity.ok(employeeById);
    }

    // Build Get All Employees REST API
    @GetMapping(path = "all")
    public ResponseEntity<List<EmployeeDto>> getAllEmployees() {
        List<EmployeeDto> employeesList = employeeService.getAllEmployees();
        return ResponseEntity.ok(employeesList);
    }

    // Build Update Employee REST API
    @PutMapping(path = "{id}")
    public ResponseEntity<EmployeeDto> updateEmployee(@PathVariable("id") Long id,
            @RequestBody EmployeeDto updatedEmployeeDetails) {
        EmployeeDto updatedEmployee = employeeService.updateEmployee(id, updatedEmployeeDetails);
        return ResponseEntity.ok(updatedEmployee);

    }
}

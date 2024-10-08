package com.nour.ems_app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nour.ems_app.models.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}

package com.hasnat.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.hasnat.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {}

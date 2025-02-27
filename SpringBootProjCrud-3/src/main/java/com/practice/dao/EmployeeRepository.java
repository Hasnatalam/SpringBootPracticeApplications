 package com.practice.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.practice.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

}

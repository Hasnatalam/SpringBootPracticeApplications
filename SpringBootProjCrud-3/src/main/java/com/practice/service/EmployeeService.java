package com.practice.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.practice.dao.EmployeeRepository;
import com.practice.entity.Employee;

@Service
public class EmployeeService {
	@Autowired
	private EmployeeRepository repo;
	
	public String healthCheckup() {
		return "Hello";
	}
	public Employee addEmployee(Employee e) {
		return repo.save(e);
	}
	public List<Employee> getAllEmployee(){
		return repo.findAll();
	}
	public Optional<Employee> getEmployee(Integer id){
		return repo.findById(id);
	}
	
	public Employee updateEmployee(Employee e) {
		Optional<Employee> byId = repo.findById(e.getId());
		Employee employee = byId.get();
		employee.setName(e.getName());
		employee.setDesg(e.getDesg());
		return repo.save(employee);
	}
	public void deleteEmployee(Integer id) {
		repo.deleteById(id);
	}
}

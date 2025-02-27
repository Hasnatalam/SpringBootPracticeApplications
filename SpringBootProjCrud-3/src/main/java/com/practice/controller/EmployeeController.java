package com.practice.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.practice.entity.Employee;
import com.practice.service.EmployeeService;

@RestController
@RequestMapping("/employee/api")
public class EmployeeController {
	@Autowired
	private EmployeeService service;
	
	@PostMapping("/healthCheck")
	public String healthCheckup() {
		return service.healthCheckup();
	}
	@PostMapping("/add")
	public Employee addEmployee(@RequestBody Employee e) {
		return service.addEmployee(e);
	}
	
	@PostMapping("/getAll")
	public List<Employee> getAllEmployee(){
		return service.getAllEmployee();
	}
	@GetMapping("/get")
	public Optional<Employee> getEmployee(@RequestParam Integer id){
		return service.getEmployee(id);
	}
	@PostMapping("/update")
	public Employee uodate(@RequestBody Employee e) {
		return service.updateEmployee(e);
	}
	
	@GetMapping("/delete")
	public void deleteEmployee(@RequestParam Integer id) {
		service.deleteEmployee(id);
	}
}

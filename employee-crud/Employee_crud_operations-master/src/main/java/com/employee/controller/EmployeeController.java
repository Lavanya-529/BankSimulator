package com.employee.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.employee.entity.Employee;
import com.employee.service.EmployeeService;

@RestController
@RequestMapping("/emp")
@CrossOrigin(origins = "http://localhost:5174")
public class EmployeeController {
	
	@Autowired
	private EmployeeService employeeService;
	
	@PostMapping("/add")
	public Employee addEmployee(@RequestBody Employee employee) {
		Employee emp = employeeService.saveEmployee(employee);
		return emp;
	}
	 
	@GetMapping("/all")
	public Iterable<Employee> getAllEmp(){
		return employeeService.getAllEmployees();
	}
	
	@GetMapping("/getEmp/{id}")
	public Optional<Employee> getEmployeeById(@PathVariable Long id){
		return employeeService.getEmpById(id);
	}
	
	@DeleteMapping("/delete/{id}")
	public void deleteEmployeeById(@PathVariable Long id) {
		employeeService.deleteById(id);
	}
	
	
	@PutMapping("/update/{id}")
	public Employee updateingEmployee(@PathVariable Long id, @RequestBody Employee emp) {
		return employeeService.updateEmployee(id, emp);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
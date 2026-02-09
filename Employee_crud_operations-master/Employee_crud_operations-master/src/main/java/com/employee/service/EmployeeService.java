package com.employee.service;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.employee.entity.Employee;
import com.employee.repository.EmployeeRepo;

@Service
public class EmployeeService {
	
	//field Level Injection
	@Autowired
	private EmployeeRepo employeeRepo;
	
	public Employee saveEmployee(Employee emp) {
		Employee employee = employeeRepo.save(emp);
		return employee;
	}
	
	public Iterable<Employee> getAllEmployees() {
		Iterable<Employee> allEmp = employeeRepo.findAll();
		return allEmp;
	}
	
	public Optional<Employee> getEmpById(Long id) {
		Optional<Employee> emp = employeeRepo.findById(id);
		return emp;
	}
	
	public void deleteById(Long id) {
		employeeRepo.deleteById(id);
	}
	
	/*public Employee updateEmployee(Long id,Employee emp) { 
		Optional<Employee> optionalEmployee = employeeRepo.findById(id);
		
		if(optionalEmployee.isPresent()) {
			
			Employee employee = optionalEmployee.get();
			System.out.println(emp.getName());
			System.out.println(emp.getDepartment());
			employee.setName(emp.getName());
			employee.setDepartment(emp.getDepartment());
			
			return employeeRepo.save(employee);
		}
		
		return null;
	}*/
	
	public Employee updateEmployee(Long id , Employee updatedEmployee) {
		Employee employee = employeeRepo.findById(id).orElse(null);
		if(employee != null) {
			employee.setName(updatedEmployee.getName());
			employee.setDepartment(updatedEmployee.getDepartment());
			return employeeRepo.save(employee);
		}
		
		return null;
	}
	
	 
	
}
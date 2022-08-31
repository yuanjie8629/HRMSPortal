package com.dao;

import java.util.List;

import com.bean.Employee;

public interface EmployeeDao {
	void add(Employee employee);
	void update(Employee employee);
	void delete(int id);
	List<Employee> list();
	Employee getEmployeeById(int id);
	List<Employee> getAllDeveloper();
}
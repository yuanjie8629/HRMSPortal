package com.bean;

import java.util.List;

import javax.persistence.*;

@Entity
@DiscriminatorValue("employee")
public class Employee extends User{
	private String dept, role;
	
	@OneToMany(mappedBy="employee")
	private List<Project> empProject;
	
	@OneToMany(mappedBy="manager")
	private List<Project> managerProject;

	public String getDept() {
		return dept;
	}

	public void setDept(String dept) {
		this.dept = dept;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	
	public List<Project> getProject() {
		if (this.role.equals("Project Manager"))
			return managerProject;
		else 
			return empProject;
	}

	public void setProject(List<Project> project) {
		if (this.role.equals("Project Manager"))
			this.managerProject = project;
		else
			this.empProject = project;
	}

	public Employee() {
		super();
	}
	
	public Employee(String dept, String role) {
		super();
		this.dept = dept;
		this.role = role;
	}
	
	public Employee(int id, String username, String password, String name, String email, String contactNo, String dept, String role) {
		super(id, username, password, name, email, contactNo);
		this.dept = dept;
		this.role = role;
	}

	@Override
	public String toString() {
		return super.toString();
	}
}

package com.bean;

import java.time.LocalDate;
import javax.persistence.*;

import org.hibernate.annotations.Type;

@Entity
@Table(name="project")
public class Project {
	@Id
	@GeneratedValue
	private int id;
	private String client;
	private String technology;
	
	@Column(name="start_date")
	private LocalDate startDate;
	
	@ManyToOne
	@JoinColumn(name="employee_id")
	private Employee employee;
	
	@ManyToOne
	@JoinColumn(name="manager_id")
	private Employee manager;
	
	private char accept;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getClient() {
		return client;
	}

	public void setClient(String client) {
		this.client = client;
	}

	public String getTechnology() {
		return technology;
	}

	public void setTechnology(String technology) {
		this.technology = technology;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Employee getManager() {
		return manager;
	}

	public void setManager(Employee manager) {
		this.manager = manager;
	}

	public char isAccept() {
		return accept;
	}

	public void setAccept(char accept) {
		this.accept = accept;
	}
	
	public Project() {
		
	}

	public Project(int id, String client, String technology, LocalDate startDate, Employee employee, Employee manager,
			char accept) {
		super();
		this.id = id;
		this.client = client;
		this.technology = technology;
		this.startDate = startDate;
		this.employee = employee;
		this.manager = manager;
		this.accept = accept;
	}

	@Override
	public String toString() {
		return "Project [id=" + id + ", client=" + client + ", technology=" + technology + ", startDate=" + startDate
				+ ", employee=" + employee.getName() + ", manager=" + manager.getName() + ", accept=" + accept + "]";
	}
}

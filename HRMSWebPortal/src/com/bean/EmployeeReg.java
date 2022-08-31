package com.bean;

import java.time.LocalDateTime;

import javax.persistence.*;

@Entity
@Table(name="employee_reg")
public class EmployeeReg {
	@Id
	@GeneratedValue
	private int id;
	private String username, password, fname, lname, email, gender, city;
	
	@Column(name="reg_date",columnDefinition="timestamp")
	private LocalDateTime regDate;
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getFname() {
		return fname;
	}
	
	public void setFname(String fname) {
		this.fname = fname;
	}
	
	public String getLname() {
		return lname;
	}
	
	public void setLname(String lname) {
		this.lname = lname;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getGender() {
		return gender;
	}
	
	public void setGender(String gender) {
		this.gender = gender;
	}
	
	public String getCity() {
		return city;
	}
	
	public void setCity(String city) {
		this.city = city;
	}
	
	public LocalDateTime getRegDate() {
		return regDate;
	}

	public void setRegDate(LocalDateTime regDate) {
		this.regDate = regDate;
	}

	public EmployeeReg() {
		
	}

	public EmployeeReg(int id, String username, String password, String fname, String lname, String email,
			String gender, String city, LocalDateTime regDate) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.fname = fname;
		this.lname = lname;
		this.email = email;
		this.gender = gender;
		this.city = city;
		this.regDate = regDate;
	}

	@Override
	public String toString() {
		return "EmployeeReg [id=" + id + ", username=" + username + ", password=" + password + ", fname=" + fname
				+ ", lname=" + lname + ", email=" + email + ", gender=" + gender + ", city=" + city + ", regDate="
				+ regDate + "]";
	}
}

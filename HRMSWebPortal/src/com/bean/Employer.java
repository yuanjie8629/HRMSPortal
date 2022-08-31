package com.bean;

import javax.persistence.*;

@Entity  
@DiscriminatorValue("employer")
public class Employer extends User {
	public Employer () {
		super();
	}
	
	public Employer(int id, String username, String password, String name, String email, String contactNo) {
		super(id, username, password, name, email, contactNo);
	}

	@Override
	public String toString() {
		return super.toString();
	}
}

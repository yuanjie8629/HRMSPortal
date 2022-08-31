package com.db;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.bean.Employee;
import com.bean.Employer;

public class InsertEmployer {

	public static void main(String[] args) {
		Session session = null;
		Transaction t = null;
		try {
			SessionFactory factory = DBConnection.getSessionFactory();
			session = factory.openSession();  
			t = session.beginTransaction();
			Employer emp = new Employer();
			emp.setUsername("yuan");
			emp.setPassword("yuan@dev123");
			emp.setName("Tan Yuan Jie");
			emp.setEmail("yuan@gmail.com");
			emp.setContactNo("012-3456789");
			session.persist(emp);
		    t.commit();
		    session.close();  
		} catch (Exception e) {
			e.printStackTrace();
			t.rollback();
		}
	}
}

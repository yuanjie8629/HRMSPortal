package com.daoimpl;

import java.util.List;

import javax.persistence.TypedQuery;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import com.bean.Employee;
import com.dao.EmployeeDao;
import com.db.DBConnection;

public class EmployeeDaoImpl implements EmployeeDao {
	@Override
	public void add(Employee employee) {
		Session session = null;
		Transaction t = null;
		try {
			SessionFactory factory = DBConnection.getSessionFactory();
			session = factory.openSession();
			t = session.beginTransaction();
			session.persist(employee);
		    t.commit();
		} catch (Exception e) {
			e.printStackTrace();
			t.rollback();
		} finally {
			session.close();
		}
	}

	@Override
	public void update(Employee employee) {
		Session session = null;
		Transaction t = null;
		try {  
			SessionFactory factory = DBConnection.getSessionFactory();
			session = factory.openSession();  
			t = session.beginTransaction();   
			session.update(employee);
		    t.commit();
		} catch (Exception e) {
			e.printStackTrace();
			t.rollback();
		} finally {
			session.close();
		}
	}

	@Override
	public void delete(int id) {
		Session session = null;
		Transaction t = null;
		try {  
			SessionFactory factory = DBConnection.getSessionFactory();
			session = factory.openSession();  
			t = session.beginTransaction();
			Employee emp = session.get(Employee.class, id);
			session.delete(emp);
		    t.commit();
		} catch (Exception e) {
			e.printStackTrace();
			t.rollback();
		} finally {
			session.close();
		}
	}

	@Override
	public List<Employee> list() {
		Session session = null;
		SessionFactory factory = DBConnection.getSessionFactory();
		session = factory.openSession();
		TypedQuery<Employee> query = session.createQuery("from Employee order by id", Employee.class);   
	    List<Employee> list = query.getResultList();
	    session.close();
		return list;
	}

	@Override
	public Employee getEmployeeById(int id) {
		Session session = null;
		SessionFactory factory = DBConnection.getSessionFactory();
		session = factory.openSession();
		Employee employee = session.get(Employee.class, id);
	    session.close();
		return employee;
	}

	@Override
	public List<Employee> getAllDeveloper() {
		Session session = null;
		SessionFactory factory = DBConnection.getSessionFactory();
		session = factory.openSession();
		TypedQuery<Employee> query = session.createQuery("from Employee where lower(role) = :role order by id", Employee.class);   
	    query.setParameter("role", "developer");
		List<Employee> list = query.getResultList();
	    session.close();
		return list;
	}
}

package com.daoimpl;

import java.util.List;

import javax.persistence.TypedQuery;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.bean.EmployeeReg;
import com.dao.EmployeeRegDao;
import com.db.DBConnection;

public class EmployeeRegDaoImpl implements EmployeeRegDao {

	@Override
	public void add(EmployeeReg empReg) {
		Session session = null;
		Transaction t = null;
		try {
			SessionFactory factory = DBConnection.getSessionFactory();
			session = factory.openSession();  
			t = session.beginTransaction();   
			session.persist(empReg);
		    t.commit();
		    session.close();  
		} catch (Exception e) {
			e.printStackTrace();
			t.rollback();
		} finally {
			session.close();
		}
	}

	@Override
	public List<EmployeeReg> list() {
		Session session = null;
		SessionFactory factory = DBConnection.getSessionFactory();
		session = factory.openSession();
		TypedQuery<EmployeeReg> query = session.createQuery("from EmployeeReg order by id", EmployeeReg.class);   
	    List<EmployeeReg> list = query.getResultList();
	    session.close();
		return list;
	}
}

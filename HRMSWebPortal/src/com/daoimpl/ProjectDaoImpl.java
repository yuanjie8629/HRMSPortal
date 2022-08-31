package com.daoimpl;

import java.util.List;

import javax.persistence.TypedQuery;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.bean.Employee;
import com.bean.Project;
import com.dao.ProjectDao;
import com.db.DBConnection;

public class ProjectDaoImpl implements ProjectDao{

	@Override
	public void add(Project project) {
		Session session = null;
		Transaction t = null;
		try {
			SessionFactory factory = DBConnection.getSessionFactory();
			session = factory.openSession();  
			t = session.beginTransaction();   
			session.persist(project);
		    t.commit();
		} catch (Exception e) {
			e.printStackTrace();
			t.rollback();
		} finally {
			session.close();
		}
	}

	@Override
	public void updateProjectAccept(int id, char accept) {
		Session session = null;
		Transaction t = null;
		try {  
			SessionFactory factory = DBConnection.getSessionFactory();
			session = factory.openSession();  
			t = session.beginTransaction();
			Project project = session.get(Project.class, id);
			project.setAccept(accept);
			session.update(project);
		    t.commit();
		} catch (Exception e) {
			e.printStackTrace();
			t.rollback();
		} finally {
			session.close();
		}
	}

	@Override
	public List<Project> list() {
		Session session = null;
		SessionFactory factory = DBConnection.getSessionFactory();
		session = factory.openSession();
		TypedQuery<Project> query = session.createQuery("from Project order by id", Project.class);   
	    List<Project> list = query.getResultList();
	    session.close();
		return list;
	}

	@Override
	public List<Project> getProjectByEmployee(int id) {
		Session session = null;
		SessionFactory factory = DBConnection.getSessionFactory();
		session = factory.openSession();
		Employee employee = session.get(Employee.class, id);
		return employee.getProject();
	}
}

package com.daoimpl;

import java.util.List;

import javax.persistence.TypedQuery;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.bean.User;
import com.dao.UserDao;
import com.db.DBConnection;

public class UserDaoImpl implements UserDao{
	@Override
	public User login(String username, String password, String userType) {
		SessionFactory factory = DBConnection.getSessionFactory();
		Session session = factory.openSession();
		TypedQuery<User> query = session.createQuery("from User e where e.username = :username and e.password = :password and type = :userType", User.class);
	    query.setParameter("username", username);
	    query.setParameter("password", password);
	    query.setParameter("userType", userType);
	    List<User> list = query.getResultList();
	    User usr = null;
	    if (!list.isEmpty()) {
	    	usr = query.getResultList().get(0);
	    }
	    session.close();
	    return usr;
	}
}

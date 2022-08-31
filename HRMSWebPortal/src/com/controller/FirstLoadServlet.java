package com.controller;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import com.db.DBConnection;

/**
 * Servlet implementation class FirstLoadServlet
 */
@WebServlet("/FirstLoadServlet")
public class FirstLoadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	public void init(ServletConfig config) throws ServletException {
		DBConnection.getSessionFactory();
	}
}

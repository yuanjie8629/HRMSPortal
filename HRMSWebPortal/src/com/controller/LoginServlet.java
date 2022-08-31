package com.controller;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bean.User;
import com.dao.UserDao;
import com.daoimpl.UserDaoImpl;

/**
 * Servlet implementation class EmployerLoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDao userDao;
	private ServletContext servletcontext;
	
	public void init(ServletConfig config) throws ServletException {
		userDao = new UserDaoImpl();
		servletcontext = config.getServletContext();
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		String userType = request.getParameter("userType");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		User user = userDao.login(username, password, userType);
		if (user != null) {
			servletcontext.setAttribute("user", user);
			response.sendRedirect("PortalServlet");
		} else {
			PrintWriter out = response.getWriter();
			RequestDispatcher rd = request.getRequestDispatcher("Login.html");
			rd.include(request, response);
			out.println("<h4 style='color:red; text-align:center;'>Invalid Username or Password...</h4>");
		}
	}
}

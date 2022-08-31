package com.controller;


import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bean.Employee;
import com.bean.Employer;
import com.bean.User;

/**
 * Servlet implementation class EmployerPortalServlet
 */
@WebServlet("/PortalServlet")
public class PortalServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ServletContext servletcontext;
	
	public void init(ServletConfig config) throws ServletException {
		servletcontext = config.getServletContext();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (servletcontext.getAttribute("user") == null) {
			response.sendRedirect("index.html");
			return;
		}
		PrintWriter out=response.getWriter();
		User user = (User) servletcontext.getAttribute("user");
		RequestDispatcher rd = null;
		if (user instanceof Employer) {
			rd = request.getRequestDispatcher("/employer/EmployerPortal.html");
		} else if (user instanceof Employee) {
			if (((Employee) user).getRole().equals("Project Manager"))
				rd = request.getRequestDispatcher("/projectManager/PMPortal.html");
			else
				rd = request.getRequestDispatcher("/employee/EmployeePortal.html");
		}
		rd.include(request, response);
		out.println("<div style='position:absolute; top: 15px; left:20px;'>");
		out.println("<h4>Welcome, " + (user.getName().isEmpty() ? user.getUsername() : user.getName()) + "<br>");
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
		LocalDateTime now = LocalDateTime.now();
		out.println("Login Time: " + dtf.format(now) + "</h4></div></body></html>");
	}

}

package com.controller.projectmanager;


import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bean.Employee;
import com.bean.Project;
import com.dao.EmployeeDao;
import com.dao.ProjectDao;
import com.daoimpl.EmployeeDaoImpl;
import com.daoimpl.ProjectDaoImpl;

/**
 * Servlet implementation class ProjectServlet
 */
@WebServlet("/projectManager/ProjectAssignServlet")
public class ProjectAssignServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ServletContext servletcontext;
	private ProjectDao projectDao;
	private EmployeeDao empDao;
	
	public void init(ServletConfig config) throws ServletException {
		servletcontext = config.getServletContext();
		projectDao = new ProjectDaoImpl();
		empDao = new EmployeeDaoImpl();
	}
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		if (request.getParameter("developer_id") != null) {
			servletcontext.setAttribute("developer_id", request.getParameter("developer_id"));
			RequestDispatcher rd = request.getRequestDispatcher("/projectManager/ProjectAssignForm.html");
			rd.include(request, response);
		} else {
			PrintWriter out=response.getWriter();
			RequestDispatcher rd = request.getRequestDispatcher("ViewDeveloperServlet");
			rd.include(request, response);
			out.println("<h4 style='color:red; text-align:center;'>No Developer is selected...</h4>");
		}
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (servletcontext.getAttribute("developer_id") == null) {
			response.sendRedirect("index.html");
			return;
		}
		int developer_id = Integer.parseInt((String)servletcontext.getAttribute("developer_id"));
		Project proj = new Project();
		Employee dev = empDao.getEmployeeById(developer_id);
		proj.setEmployee(dev);
		proj.setManager((Employee)servletcontext.getAttribute("user"));
		proj.setClient(request.getParameter("client"));
		proj.setTechnology(request.getParameter("tech"));
		proj.setStartDate(LocalDate.parse(request.getParameter("start_date")));
		PrintWriter out=response.getWriter();
		RequestDispatcher rd = request.getRequestDispatcher("ViewDeveloperServlet");
		rd.include(request, response);
		try {
			projectDao.add(proj);
			out.println("<h4 style='color:green; text-align:center;'>Project Assigned to " + dev.getName() +" Successfully...</h4>");
		} catch (Exception e) {
			System.out.println(e.getMessage());
			out.println("<h4 style='color:red; text-align:center;'>Failed to assign the project to " + dev.getName() + "! Please try again later...</h4>");
		}
	}

}

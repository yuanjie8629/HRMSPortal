package com.controller.projectmanager;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bean.Project;
import com.bean.User;
import com.bean.Employee;
import com.dao.ProjectDao;
import com.daoimpl.ProjectDaoImpl;

/**
 * Servlet implementation class ViewDeveloperServlet
 */
@WebServlet("/projectManager/ViewEmpStatusServlet")
public class ViewEmpStatusServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ServletContext servletcontext;
	private ProjectDao projectDao;
	
	public void init(ServletConfig config) throws ServletException {
		servletcontext = config.getServletContext();
		projectDao = new ProjectDaoImpl();
	}
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		User usr = (User) servletcontext.getAttribute("user");
		if (usr == null || !((Employee)usr).getRole().equalsIgnoreCase("Project Manager")) {
			response.sendRedirect("../index.html");
			return;
		}
		out.println("<!DOCTYPE html><html><head>");
		out.println("<style>");
		out.println("table, th, td {");
		out.println("border: 1px solid black;");
		out.println("}");
		out.println("tr:first-child {");
		out.println("background-color: yellow;");
		out.println("}");
		out.println(".dot {");
		out.println("height: 10px;");
		out.println("width: 10px;");
		out.println("border-radius: 50%;");
		out.println("display: inline-block;");
		out.println("margin: 0 10px;");
		out.println("}");
		out.println("</style>");
		out.println("</head><body><h1 style='text-align:center;'>Employee's Project Status List</h1>");
		out.println("<hr>");
		
		List<Project> projList = projectDao.getProjectByEmployee(usr.getId());
		if (!projList.isEmpty()) {
			out.println("<table width='70%' style='border-collapse:collapse; text-align:center; margin-left:auto; margin-right:auto;'");
	        out.println("<tr><th>ID</th><th>Developer Name</th><th>Client Name</th><th>Technology</th><th>Start Date</th><th>Project Status</th>");
			for(Project proj: projList) {
				String circle_color;
				String status;
				circle_color = proj.isAccept() == 'y' ? "green" : proj.isAccept() == 'n' ? "red" : "grey";
				status = proj.isAccept() == 'y' ? "Accepted" : proj.isAccept() == 'n' ? "Rejected" : "Pending";
		        String showAccept =  "<span class='dot' style='background-color:" + circle_color + "'></span>" + status;
		        out.println("<tr><td>" + proj.getId() + "</td><td>" +proj.getEmployee().getName() + "</td><td>" + proj.getClient() + "</td><td>" 
		        		+ proj.getTechnology()  + "</td><td>" + proj.getStartDate() +"</td><td>" + showAccept + "</td>");
			}
			out.println("</table>");
		} else {
			out.print("<p style='text-align:center;'>No Project Found...</p>");
		}
		out.println("</body></html>");
	}
}

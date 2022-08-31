package com.controller.employee;


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
import com.dao.ProjectDao;
import com.daoimpl.ProjectDaoImpl;

/**
 * Servlet implementation class ViewDeveloperServlet
 */
@WebServlet("/employee/ViewProjectServlet")
public class ViewProjectServlet extends HttpServlet {
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
		if (usr == null) {
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
		out.println("<script>");
		// JavaScript to pass project id to hidden input
		out.println("function getProjectID() {");
		out.println("let acceptElement = document.querySelector('input[name=accept]:checked');");
		out.println("let id = acceptElement.parentElement.parentElement.parentElement.id;");
		out.println("document.forms['projectAcceptForm']['project_id'].value = id;");
		out.println("}");
		out.println("</script>");
		out.println("</head><body><h1 style='text-align:center;'>Project List</h1>");
		out.println("<hr>");
		
		List<Project> projList = projectDao.getProjectByEmployee(usr.getId());
		if (!projList.isEmpty()) {
			out.println("<form name='projectAcceptForm' action='ViewProjectServlet' method='post' style='text-align: center;' onsubmit='getProjectID()'>");
			out.println("<table width='70%' style='border-collapse:collapse; text-align:center; margin-left:auto; margin-right:auto;'");
	        out.println("<tr><th>ID</th><th>Client Name</th><th>Technology</th><th>Start Date</th><th>Project Accept</th>");
	        
			for (Project proj: projList) {
				String showAccept;
		        if (proj.isAccept() != 'y' && proj.isAccept() != 'n') {
			        	showAccept = "<span style='padding:0 5px;'><input type='radio' name='accept' value='y'><label for ='accept'>Accept</label></span>"
			        			+ "<span style='padding:0 5px;'><input type='radio' name='accept' value='n'><label for ='reject'>Reject</label></span>";
		        } else {
			        String circle_color;
					String status;
					circle_color = proj.isAccept() == 'y' ? "green" : "red";
					status = proj.isAccept() == 'y' ? "Accepted" : "Rejected";
		        	showAccept = "<span class='dot' style='background-color:" + circle_color + "'></span>" + status;
		        }
				 out.println("<tr id='" + proj.getId() + "'><td>" + proj.getId() + "</td><td>" + proj.getClient() + "</td><td>" + proj.getTechnology() 
				 	+ "</td><td>" + proj.getStartDate() + "</td><td>" + showAccept + "</td>");
			}
			out.println("</table>");
			out.println("<br><input id='project_id' name='project_id' type='hidden'><input type='submit' value='Submit'></form>");
		} else {
			out.print("<p style='text-align:center;'>No Project Found...</p>");
		}
		out.println("</body></html>");
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		if (request.getParameter("accept") != null && request.getParameter("project_id") != null) {
			char accept = request.getParameter("accept").charAt(0);
			int id = Integer.parseInt(request.getParameter("project_id"));
			String result = accept == 'y' ? "accepted" : "rejected";
			try {
				projectDao.updateProjectAccept(id, accept);
				this.doGet(request, response);
				out.println("<h4 style='color:green; text-align:center;'>You have " + result + " the Project with ID " + id + " successfully!</h4>");
			} catch (Exception e) {
				System.out.println(e.getMessage());
				this.doGet(request, response);
				out.println("<h4 style='color:red; text-align:center;'>Failed to " + result + " the Project with ID " + id + "! Please try again later...</h4>");
			}
		} else {
			this.doGet(request, response);
			out.println("<h4 style='color:red; text-align:center;'>No Project is selected...</h4>");
		}
	}
}

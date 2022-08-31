package com.controller.projectmanager;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bean.Employee;
import com.dao.EmployeeDao;
import com.daoimpl.EmployeeDaoImpl;

/**
 * Servlet implementation class ViewDeveloperServlet
 */
@WebServlet("/projectManager/ViewDeveloperServlet")
public class ViewDeveloperServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private EmployeeDao empDao;
	
	public void init(ServletConfig config) throws ServletException {
		empDao = new EmployeeDaoImpl();
	}
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		out.println("<!DOCTYPE html><html><head>");
		out.println("<style>");
		out.println("table, th, td {");
		out.println("border: 1px solid black;");
		out.println("}");
		out.println("tr:first-child {");
		out.println("background-color: yellow;");
		out.println("}");
		out.println("</style>");
		out.println("</head><body><h1 style='text-align:center;'>Developer List</h1>");
		out.println("<hr>");
		List<Employee> devList = empDao.getAllDeveloper();
		if (!devList.isEmpty()) {
			out.println("<form name='developerForm' action='ProjectAssignServlet' method='get' style='text-align: center;'>");
			out.println("<table width='70%' style='border-collapse:collapse; text-align:center; margin-left:auto; margin-right:auto;'");
	        out.println("<tr><th>ID</th><th>Name</th><th>Department</th><th>Email</th><th>Contact Number</th><th>Role</th><th>Assign Project</th>");
			for (Employee dev: devList) {
				 out.println("<tr><td>" + dev.getId() + "</td><td>" + dev.getName() + "</td><td>" + dev.getDept()  
				 + "</td><td>" + dev.getEmail() + "</td><td>" + dev.getContactNo() + "</td><td>" + dev.getRole() + 
						 "</td><td><input type='radio' name='developer_id' value=" + dev.getId() + "></td>");
			}
			out.println("</table>");
			out.println("<br><input type='submit' value='Proceed'></form>");
		} else {
			out.print("<p style='text-align:center;'>No Developer Found...</p>");
		}
		out.println("</body></html>");
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}
}

package com.controller.employer;
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
 * Servlet implementation class ViewEmpServlet
 */
@WebServlet("/employer/ViewEmpServlet")
public class ViewEmpServlet extends HttpServlet {
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
		out.println("</head><body><h1 style='text-align:center;'>View Employees</h1>");
		out.println("<hr>");
		List<Employee> empList = empDao.list();
		if (!empList.isEmpty()) {
			out.print("<table width='70%' style='border-collapse:collapse; text-align:center; margin-left:auto; margin-right:auto;'");  
	        out.print("<tr><th>ID</th><th>Name</th><th>Department</th><th>Email</th><th>Contact Number</th><th>Role</th>");
			for (Employee emp: empList){
				 out.print("<tr><td>" + emp.getId() + "</td><td>" + emp.getName() + "</td><td>" + emp.getDept()  
				 + "</td><td>" + emp.getEmail() + "</td><td>" + emp.getContactNo() + "</td><td>" + emp.getRole());
			}
			out.print("</table>");
		} else {
			out.print("<p style='text-align:center;'>No Employee Record Found...</p>");
		}
		out.println("</body></html>");
	}
}

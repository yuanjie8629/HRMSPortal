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
 * Servlet implementation class DeleteEmpServlet
 */
@WebServlet("/employer/DeleteEmpServlet")
public class DeleteEmpServlet extends HttpServlet {
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
		out.println("<!DOCTYPE html>");
		out.println("<head>");
		out.println("<style>");
		out.println("td {");
		out.println("padding: 5px 15px;");
		out.println("}");
		out.println("</style>");
		out.println("</head>");
		out.println("<body><h1 style='text-align:center;'>Delete Employee</h1>");
		out.println("<hr>");
		List<Employee> empList = empDao.list();
		
		out.println("<div align='center'>");
		if (!empList.isEmpty()) {
			out.println("<form action='DeleteEmpServlet' method='post'>");
			out.println("<table style='border:none;'>");
			out.println("<tr>");
			out.println("<td><label for='id'>Select ID:</label></td>");
			out.println("<td>");
			out.println("<select name='id' required>");
			for (Employee emp : empList) {
				out.println("<option value='" + emp.getId() + "'>" + emp.getId() + ": " + emp.getName() + "</option>");
			}
			out.println("</select>");
			out.println("</td></tr>");
			out.println("</table>");
			out.println("<div style='padding: 5px 15px;'>");
			out.println("<input type='submit' value='Delete'>");
			out.println("<input type='reset' value='Clear'>");
			out.println("</div>");
			out.println("</form>");
		} else {
			out.print("<p>No Employee Record Found...</p>");
		}
		out.println("</div></body></html>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		int id = Integer.parseInt(request.getParameter("id"));
		PrintWriter out=response.getWriter();
		try {
			empDao.delete(id);
			this.doGet(request, response);
			out.println("<h4 style='color:green; text-align:center;'>You have deleted the employee details successfully!</h4>");
		} catch (Exception e) {
			System.out.println(e.getMessage());
			this.doGet(request, response);
			out.println("<h4 style='color:red; text-align:center;'>Failed to delete the Employee Details! Please try again later...</h4>");
		}
	}
}

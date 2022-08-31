package com.controller.employer;


import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
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
 * Servlet implementation class AddEmployeeServlet
 */
@WebServlet("/employer/AddEmpServlet")
public class AddEmpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private EmployeeDao empDao;	
	public void init(ServletConfig config) throws ServletException {
		empDao = new EmployeeDaoImpl();
	}
       
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		Employee emp = new Employee();
		emp.setUsername(request.getParameter("name"));
		emp.setPassword(request.getParameter("email"));
		emp.setName(request.getParameter("name"));
		emp.setDept(request.getParameter("dept"));
		emp.setEmail(request.getParameter("email"));
		emp.setContactNo(request.getParameter("contact_no"));
		emp.setRole(request.getParameter("role"));
		PrintWriter out=response.getWriter();
		try {
			empDao.add(emp);
			RequestDispatcher rd = request.getRequestDispatcher("/employer/AddEmp.html");
			rd.include(request, response);
			out.println("<h4 style='color:green; text-align:center;'>You have added the new employee successfully!</h4>");
		} catch (Exception e) {
			System.out.println(e.getMessage());
			
			out.println("<h4 style='color:red; text-align:center;'>Failed to add the Employee Details! Please try again later...</h4>");
		}
	}

}

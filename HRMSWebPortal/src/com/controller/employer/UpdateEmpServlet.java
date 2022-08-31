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
 * Servlet implementation class UpdateEmpServlet
 */
@WebServlet("/employer/UpdateEmpServlet")
public class UpdateEmpServlet extends HttpServlet {
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
		
		// JavaScript to validate update input
		out.println("<script>");
		out.println("function validateUpdate() {");
		out.println("let deptSelect = document.getElementById('deptSelect');");
		out.println("deptSelect.style.display = 'none';");
		out.println("deptSelect.required = false;");
		out.println("let updateField = document.forms['updateForm']['update'];");
		out.println("let selectedField = updateField.options[updateField.selectedIndex];");
		out.println("if(selectedField.value != '') {");
		out.println("let value = document.forms['updateForm']['value'];");
		out.println("value.style.display = 'block';");
		out.println("value.required = true;");
		out.println("document.getElementById('value').innerHTML = 'Enter Updated ' + selectedField.text + ':';");
		out.println("if(selectedField.value == 'name'){");
		out.println("value.placeholder = 'Tan Yuan Jie';");
		out.println("}");
		out.println("else if(selectedField.value == 'dept'){");
		out.println("value.style.display = 'none';");
		out.println("value.required = false;");
		out.println("document.getElementById('deptSelect').style.display = 'block';");
		out.println("}");
		out.println("else if(selectedField.value == 'email'){");
		out.println("value.type = 'email';");
		out.println("value.placeholder = 'xxxxx@gmail.com';");
		out.println("}");
		out.println("else if(selectedField.value == 'contact_no'){");
		out.println("value.type = 'tel';");
		out.println("value.placeholder = '012-1234567';");
		out.println("value.pattern = '[0-9]{3}-[0-9]{7}';");
		out.println("}");
		out.println("else if(selectedField.value == 'role'){");
		out.println("value.placeholder = 'Developer';");
		out.println("}");
		out.println("}");
		out.println("}");
		out.println("</script>");
		out.println("</head>");
		out.println("<body><h1 style='text-align:center;'>Update Employee</h1>");
		out.println("<hr>");
		List<Employee> empList = empDao.list();
		
		out.println("<div align='center'>");
		if (!empList.isEmpty()) {
			out.println("<form name='updateForm' action='UpdateEmpServlet' method='post'>");
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
			out.println("<tr>");
			out.println("<td><label for='update'>Select you want to update:</label></td>");
			out.println("<td>");
			out.println("<select name='update' required onchange='validateUpdate()'>");
			out.println("<option value='name'>Name</option>");
			out.println("<option value='dept'>Department</option>");
			out.println("<option value='email'>Email</option>");
			out.println("<option value='contact_no'>Contact Number</option>");
			out.println("<option value='role'>Role</option>");
			out.println("</select>");
			out.println("</td></tr>");
			out.println("<tr>");
			out.println("<td><label id='value' for='value'>Enter Updated Name:</label></td>");
			out.println("<td><input type='text' name='value' placeholder='Tan Yuan Jie' required>");
			out.println("<select id='deptSelect' name='deptSelect' required style='display: none;'>");
			out.println("<option value='IT'>IT</option>");
			out.println("<option value='HR'>HR</option>");
			out.println("<option value='Marketing'>Marketing</option>");
			out.println("<option value='Finance'>Finance</option>");
			out.println("<option value='Accounting'>Accounting</option></select></td></tr>");
			out.println("</table>");
			out.println("<div style='padding: 5px 15px;'>");
			out.println("<input type='submit' value='Update'>");
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
		String update_field = request.getParameter("update");
		String update_value = request.getParameter("value");
		Employee emp = empDao.getEmployeeById(id);
		if (update_field.equals("name"))
			emp.setName(update_value);
		else if (update_field.equals("dept")) {
			// If the update field is department, get the value from the dropdown option
			update_value = request.getParameter("deptSelect");
			emp.setDept(update_value);
		}
		else if (update_field.equals("email"))
			emp.setEmail(update_value);
		else if (update_field.equals("contact_no"))
			emp.setContactNo(update_value);
		else if (update_field.equals("role"))
			emp.setRole(update_value);
		PrintWriter out=response.getWriter();
		try {
			empDao.update(emp);
			this.doGet(request, response);
			out.println("<h4 style='color:green; text-align:center;'>You have updated the details of " + emp.getName() + " successfully!</h4>");
		} catch (Exception e) {
			e.printStackTrace();
			this.doGet(request, response);
			out.println("<h4 style='color:red; text-align:center;'>Failed to update thedetails of " + emp.getName() + "! Please try again later...</h4>");
		}
	}
}

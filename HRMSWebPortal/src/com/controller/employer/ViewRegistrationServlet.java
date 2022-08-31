package com.controller.employer;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bean.EmployeeReg;
import com.dao.EmployeeRegDao;
import com.daoimpl.EmployeeRegDaoImpl;

/**
 * Servlet implementation class ViewRegistrationServlet
 */
@WebServlet("/employer/ViewRegistrationServlet")
public class ViewRegistrationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private EmployeeRegDao empRegDao;
	
	public void init(ServletConfig config) throws ServletException {
		empRegDao = new EmployeeRegDaoImpl();
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
		out.println(".dot {");
		out.println("height: 10px;");
		out.println("width: 10px;");
		out.println("border-radius: 50%;");
		out.println("display: inline-block;");
		out.println("margin: 0 10px;");
		out.println("}");
		out.println("</style>");
		out.println("</head><body><h1 style='text-align:center;'>Registration List</h1>");
		out.println("<hr>");
		List<EmployeeReg> empRegList = empRegDao.list();
		DateTimeFormatter dtFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		if (!empRegList.isEmpty()) {
			out.println("<table width='70%' style='border-collapse:collapse; text-align:center; margin-left:auto; margin-right:auto;'");
	        out.println("<tr><th>ID</th><th>First Name</th><th>Last Name</th><th>Email</th><th>Gender</th><th>City</th><th>Registration Date</th>");
			for(EmployeeReg empReg: empRegList) {
				out.println("<tr><td>" + empReg.getId() + "</td><td>" + empReg.getFname() + "</td><td>" + empReg.getLname()
					+ "</td><td>" + empReg.getEmail()  + "</td><td>" + empReg.getGender() + "</td><td>" + empReg.getCity() + "</td><td>" 
						+ empReg.getRegDate().format(dtFormatter) +" </td>");
			}
			out.println("</table>");
		} else {
			out.print("<p style='text-align:center;'>No Employee Registration Found...</p>");
		}
		out.println("</body></html>");
	}
}

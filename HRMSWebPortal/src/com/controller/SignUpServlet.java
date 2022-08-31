package com.controller;


import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;

import javax.servlet.RequestDispatcher;
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
 * Servlet implementation class SignUpServlet
 */
@WebServlet("/SignUpServlet")
public class SignUpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private EmployeeRegDao empRegDao;	
	
	public void init(ServletConfig config) throws ServletException {
		empRegDao = new EmployeeRegDaoImpl();
	}
    /**
     * @see HttpServlet#HttpServlet()
     */
 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		EmployeeReg empReg = new EmployeeReg();
		empReg.setUsername(request.getParameter("fname"));
		empReg.setPassword(request.getParameter("email"));
		empReg.setFname(request.getParameter("fname"));
		empReg.setLname(request.getParameter("lname"));
		empReg.setEmail(request.getParameter("email"));
		empReg.setGender(request.getParameter("gender"));
		empReg.setCity(request.getParameter("city"));
		empReg.setRegDate(LocalDateTime.now());
		PrintWriter out = response.getWriter();
		RequestDispatcher rd = request.getRequestDispatcher("SignUp.html");
		rd.include(request, response);
		try {
			empRegDao.add(empReg);
			out.println("<h4 style='color:green; text-align:center;'>You have signed up successfully!<br>Please wait for the employer to confirm your registration.</h4>");
		} catch (Exception e) {
			System.out.println(e.getMessage());
			out.println("<h4 style='color:red; text-align:center;'>Sign up failed! Please try again later...</h4>");
		}
	}
}

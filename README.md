# HRMS Portal - Web Application

<!-- ALL-CONTRIBUTORS-BADGE:START - Do not remove or modify this section -->

[![All Contributors](https://img.shields.io/badge/all_contributors-1-orange.svg?style=flat-square)](#contributors-)

<!-- ALL-CONTRIBUTORS-BADGE:END -->

## Overview

This is a web-based HRMS portal. HRMS stands for Human Resources Management System. There are two types of end users exist in the system: employer and employee. Both employer and employee have their own portals. Employer is only allowed to access employer portal, while employee is only allowed to access employee portal. In addition, if the employee is a project manager, he will access the project manager portal after logging in.

## Features

1. Sign Up<br>
   The user can sign up as the employee. However, the user can only login if the employer reviews and adds the user as an employee into the system.

2. Login<br>
   The user can log in to the system using their valid username and password. The default username is the name of the user, while the default password is the email of the user.

### Employer Features

1. Add Employee<br>
   The employer can add the employee by entering the employee's details such as name, department, email, contact number and role.

2. View Employee<br>
   The employer can view all the employees added to the system.

3. Update Employee<br>
   The employer can update the employee details by selecting the employee and entering the new details of the employee.

4. Delete Employee<br>
   The employer can delete the employee by selecting the employee and confirm the book deletion.

5. View All Project Status<br>
   The employer can view all the project status assigned to the employees and proejct managers.

6. View Registrations<br>
   The employer can view all the registrations submitted by the users.

### Project Manager Features

1. View Employees<br>
   The project manager can view all the developers. Besides, the project manager can also assign a project to the developer.

2. View Employee's Project Status<br>
   The project manager can view all the status of the assigned projects.

### Employee Features

1. View Projects<br>
   The employee can view all the projects assigned to them. Besides, the employee can either accept or reject the project assigned.

## Tech Stack

- Java 8
- Servlet
- HTML
- CSS
- JavaScript
- Hibernate
- Oracle DB
- Apache Tomcat 9.0

## System Design

The system follows the MVC design pattern.

- HTML, JavaScript and CSS act as the View.
- Servlet acts as the Controller and View.
- Data Access Object (DAO) and java bean act as the Model.

## Preview

1. Home Page <br> <img src="previews/HomePage.png"><br><br>
2. About Us <br> <img src="previews/AboutUs.png"><br><br>
3. Contact Us <br> <img src="previews/ContactUs.png"><br><br>
4. Login <br> <img src="previews/Login.png"><br><br>
5. Sign Up<br> <img src="previews/SignUp.png"><br><br>

### Employer

1. Home Page <br> <img src="previews/EmployerHomePage.png"><br><br>
2. Add Employee <br> <img src="previews/EmployerAddEmployee.png"><br><br>
3. Update Employee <br> <img src="previews/EmployerUpdateEmployee.png"><br><br>
4. View Employees <br> <img src="previews/EmployerViewEmployees.png"><br><br>
5. Delete Employee <br> <img src="previews/EmployerDeleteEmployee.png"><br><br>
6. View All Project Status <br> <img src="previews/EmployerViewProjects.png"><br><br>
7. View Registrations <br> <img src="previews/EmployerViewRegistrations.png"><br><br>

### Project Manager

1. Home Page <br> <img src="previews/PMHomePage.png"><br><br>
2. View Employees <br> <img src="previews/PMViewDevelopers.png"><br><br>
3. View Employee's Project Status <br> <img src="previews/PMViewProjects.png"><br><br>

### Employee

1. Home Page <br> <img src="previews/EmployeeHomePage.png"><br><br>
2. View Projects <br> <img src="previews/EmployeeViewProjects.png"><br><br>

## Contributors ✨

Thanks goes to these wonderful people ([emoji key](https://allcontributors.org/docs/en/emoji-key)):

<!-- ALL-CONTRIBUTORS-LIST:START - Do not remove or modify this section -->
<!-- prettier-ignore-start -->
<!-- markdownlint-disable -->
<table>
  <tr>
    <td align="center"><a href="https://github.com/yuanjie8629"><img src="https://avatars.githubusercontent.com/u/86699785?v=4?s=100" width="100px;" alt=""/><br /><sub><b>Tan Yuan Jie</b></sub></a><br /><a href="#a11y-yuanjie8629" title="Accessibility">️️️️♿️</a> <a href="#question-yuanjie8629" title="Answering Questions">💬</a> <a href="https://github.com/yuanjie8629/HRMSPortal/commits?author=yuanjie8629" title="Code">💻</a> <a href="#data-yuanjie8629" title="Data">🔣</a> <a href="#design-yuanjie8629" title="Design">🎨</a> <a href="https://github.com/yuanjie8629/HRMSPortal/commits?author=yuanjie8629" title="Documentation">📖</a> <a href="#ideas-yuanjie8629" title="Ideas, Planning, & Feedback">🤔</a> <a href="#infra-yuanjie8629" title="Infrastructure (Hosting, Build-Tools, etc)">🚇</a> <a href="#maintenance-yuanjie8629" title="Maintenance">🚧</a> <a href="https://github.com/yuanjie8629/HRMSPortal/pulls?q=is%3Apr+reviewed-by%3Ayuanjie8629" title="Reviewed Pull Requests">👀</a> <a href="#tool-yuanjie8629" title="Tools">🔧</a></td>
  </tr>
</table>

<!-- markdownlint-restore -->
<!-- prettier-ignore-end -->

<!-- ALL-CONTRIBUTORS-LIST:END -->

This project follows the [all-contributors](https://github.com/all-contributors/all-contributors) specification. Contributions of any kind welcome!

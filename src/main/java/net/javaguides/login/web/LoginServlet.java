package net.javaguides.login.web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import dao.SystemDao;


@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private SystemDao dao;

	public LoginServlet() {
        super();
        dao = new SystemDao();
    }
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String usernamevalidation = dao.loginUsernameCheck(username);
		
		if (usernamevalidation != username)
		{
			request.setAttribute("message", usernamevalidation);
			request.setAttribute("username", username);
			System.out.println(usernamevalidation);
			RequestDispatcher view = request.getRequestDispatcher("/index.jsp");
			view.forward(request, response);
		}else
		{

			try {
				String passwordvalidation = dao.passwordCheck(username, password);
				
				if (passwordvalidation == "You logged in!") {
					System.out.println("you logged in!");
					String role = dao.getRole(username);
					System.out.println("ROLE ======"+role);
					HttpSession session = request.getSession(true);
					
					if (role == "content_admin") {
						synchronized(session) {
						session.setAttribute("username", username);
                        session.setAttribute("role", role);
                        session.setAttribute("id", dao.getContentAdminID(username));
                        
                        System.out.println("INSIDE role Content Admin!");
                        System.out.println("id=" + dao.getContentAdminID(username));
                        
                        RequestDispatcher view = request.getRequestDispatcher("/logincontentadmin.jsp");
                        view.forward(request, response);
						}
					}else if (role == "admin") {
						synchronized(session) {
						session.setAttribute("username", username);
                        session.setAttribute("role", role);
                        session.setAttribute("id", dao.getAdminID(username));
                        
                        System.out.println("INSIDE role Admin!");
                        System.out.println("id=" + dao.getAdminID(username));
                        
                        RequestDispatcher view = request.getRequestDispatcher("/Admin.jsp");
                        view.forward(request, response);
						}
					}
					else {
						synchronized(session) {
						session.setAttribute("username", username);
                        session.setAttribute("role", role);
                        session.setAttribute("id", dao.getCustomerID(username));

                        session.setAttribute("id", dao.getCustomerID(username));
                        System.out.println("INSIDE role Customer!");
                        
                        RequestDispatcher view = request.getRequestDispatcher("/Customer.jsp");
                        view.forward(request, response);
						}
					}

				}else {
					request.setAttribute("message", passwordvalidation);
					System.out.println(passwordvalidation);
					RequestDispatcher view = request.getRequestDispatcher("/index.jsp");
					view.forward(request, response);
				}
				
			} catch (Exception  e) {
			    e.printStackTrace();
			}
		}
	}
}
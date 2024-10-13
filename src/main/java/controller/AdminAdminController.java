package controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.SystemDao;
import net.javaguides.login.bean.*;


@WebServlet("/AdminAdminController")
public class AdminAdminController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static String ADMIN = "/UserAdmin.jsp";
	private SystemDao dao;

    public AdminAdminController() {
        super();
		dao = new SystemDao();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String forward = "";
		String action = request.getParameter("action");
		response.setContentType("text/html; charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");

		if (action.equalsIgnoreCase("listAdmin")){
			forward = ADMIN;
			request.setAttribute("Admin", dao.getAllAdmins());
		}
		RequestDispatcher view = request.getRequestDispatcher(forward);
		view.forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		
		//content admin
		Admin Admin = new Admin();
		String ID = request.getParameter("ID");
    	if (ID != null && !ID.isEmpty()) {
    	    try {
    	    	Admin.setID(Integer.parseInt(ID));
            } catch (NumberFormatException e) {
                // Handle the parsing error gracefully
                System.out.println("Invalid ID format: " + ID);
                // You can choose to redirect to an error page or display an error message
                response.getWriter().println("Invalid ID format: " + ID);
                return;
            }
    	}
    	Admin.setNAME(request.getParameter("NAME"));
    	Admin.setUser_username(request.getParameter("user_username"));
		//user
    	Admin.setUsername(request.getParameter("username"));
		Admin.setEmail(request.getParameter("email"));
		Admin.setPassword(request.getParameter("password"));
		String create_time = request.getParameter("TIME");
    	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Timestamp timestamp = null;
		try {
		    java.util.Date parsedDate = dateFormat.parse(create_time);
		    timestamp = new Timestamp(parsedDate.getTime());
		    Admin.setCreate_time(timestamp);
		} catch (Exception e) {
		    e.printStackTrace();
		}
		Admin.setSalt(request.getParameter("salt"));
		Admin.setRole(request.getParameter("role"));

		RequestDispatcher view = request.getRequestDispatcher(ADMIN);
		request.setAttribute("Admin", dao.getAllAdmins());
		view.forward(request, response);
	}

}

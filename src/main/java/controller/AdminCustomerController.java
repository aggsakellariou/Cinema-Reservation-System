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


@WebServlet("/AdminCustomerController")
public class AdminCustomerController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static String CUSTOMER = "/UserCustomer.jsp";
	private SystemDao dao;  
    
	
    public AdminCustomerController() {
        super();
		dao = new SystemDao();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String forward = "";
		String action = request.getParameter("action");
		response.setContentType("text/html; charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");

		if (action.equalsIgnoreCase("delete")){
			String username = request.getParameter("username");
			int ID = Integer.parseInt(request.getParameter("ID"));
			dao.deleteCustomer(ID);
			dao.deleteUser(username);
			
			forward = CUSTOMER;
			request.setAttribute("Customer", dao.getAllCustomers());
		}
		else if (action.equalsIgnoreCase("listCustomer")){
			forward = CUSTOMER;
			request.setAttribute("Customer", dao.getAllCustomers());
		}
		RequestDispatcher view = request.getRequestDispatcher(forward);
		view.forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		String action = request.getParameter("action");		
		
		//Customer
		Customer Customer = new Customer();
		String ID = request.getParameter("ID");
    	if (ID != null && !ID.isEmpty()) {
    	    try {
    	    	Customer.setID(Integer.parseInt(ID));
            } catch (NumberFormatException e) {
                // Handle the parsing error gracefully
                System.out.println("Invalid ID format: " + ID);
                // You can choose to redirect to an error page or display an error message
                response.getWriter().println("Invalid ID format: " + ID);
                return;
            }
    	}
    	Customer.setNAME(request.getParameter("NAME"));
    	Customer.setUser_username(request.getParameter("user_username"));
		//user
    	Customer.setUsername(request.getParameter("username"));
    	Customer.setEmail(request.getParameter("email"));
    	Customer.setPassword(request.getParameter("password"));
		String create_time = request.getParameter("TIME");
    	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Timestamp timestamp = null;
		try {
		    java.util.Date parsedDate = dateFormat.parse(create_time);
		    timestamp = new Timestamp(parsedDate.getTime());
		    Customer.setCreate_time(timestamp);
		} catch (Exception e) {
		    e.printStackTrace();
		}
		Customer.setSalt(request.getParameter("salt"));
		Customer.setRole(request.getParameter("role"));
		
		if (action.equalsIgnoreCase("insert")) {
			
			// Get the latest ID from the database and increment it by 1
			int latestID = dao.getLatestCustomerID();
			int newID = latestID + 1;
			Customer.setID(newID);
			
			LocalDateTime now = LocalDateTime.now();

	        // Format the date and time as yyyy-mm-dd hh:mm:ss
	        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	        String formattedDateTime = now.format(formatter);

			// Convert formattedDateTime to Timestamp
	        Timestamp create_time_now = Timestamp.valueOf(formattedDateTime);
	        Customer.setCreate_time(create_time_now);

			dao.addUserCustomer(Customer);
			dao.addCustomer(Customer);
		}

		RequestDispatcher view = request.getRequestDispatcher(CUSTOMER);
		request.setAttribute("Customer", dao.getAllCustomers());
		view.forward(request, response);
	}

}

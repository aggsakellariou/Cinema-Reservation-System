package controller;

import java.text.SimpleDateFormat;

import java.sql.Timestamp;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Cinema.*;
import dao.SystemDao;


@WebServlet("/ReservationController")
public class ReservationController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static String RESERVATION = "/Reservation.jsp";
	private static String LIST_Movie_Customer = "/listMovieCustomer.jsp";

    private SystemDao dao;

    public ReservationController() {
        super();
        dao = new SystemDao();
    }

    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String forward="";
        String action = request.getParameter("action");
        response.setContentType("text/html; charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		
		if (action.equalsIgnoreCase("reservation2")) {
        	forward = RESERVATION;
			int MOVIES_ID = Integer.parseInt(request.getParameter("MOVIES_ID"));
			String TIME = request.getParameter("TIME");
			//String TIME = request.getParameter("TIME");
			if (TIME == null || TIME.isEmpty()) {
			    // Handle the case when TIME parameter is empty or null
			    response.getWriter().println("TIME parameter is missing or empty");
			    return;
			}
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Timestamp timestamp = null;

			try {
			    java.util.Date parsedDate = dateFormat.parse(TIME);
			    timestamp = new Timestamp(parsedDate.getTime());
			} catch (Exception e) {
			    e.printStackTrace();
			    response.getWriter().println("Invalid format for TIME parameter: " + TIME);
			    return;
			}
			int CINEMAS_ID = Integer.parseInt(request.getParameter("CINEMAS_ID"));

			int seats = dao.getSeats(MOVIES_ID, timestamp);
			Reservation Reservation = dao.getReservById(MOVIES_ID,timestamp);
			int CinemaSeats = dao.getCinemaSeats(CINEMAS_ID);
			int MaxSeats = CinemaSeats - seats;
			request.setAttribute("Reservation", Reservation);
			request.setAttribute("MaxSeats", MaxSeats);
        }

		RequestDispatcher view = request.getRequestDispatcher(forward);
		view.forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
    	String action = request.getParameter("action");
		
		Reservation Reservation = new Reservation();
    	
    	// Set the reservation data from the request parameters
		
		String PROVOLES_MOVIES_ID = request.getParameter("PROVOLES_MOVIES_ID");
    	if (PROVOLES_MOVIES_ID != null && !PROVOLES_MOVIES_ID.isEmpty()) {
    	    try {
    	    	Reservation.setPROVOLES_MOVIES_ID(Integer.parseInt(PROVOLES_MOVIES_ID));
            } catch (NumberFormatException e) {
                // Handle the parsing error gracefully
                System.out.println("Invalid ID format: " + PROVOLES_MOVIES_ID);
                // You can choose to redirect to an error page or display an error message
                response.getWriter().println("Invalid ID format: " + PROVOLES_MOVIES_ID);
                return;
            }
    	}
    	
    	Reservation.setPROVOLES_MOVIES_NAME(request.getParameter("PROVOLES_MOVIES_NAME"));
    	
    	String PROVOLES_CINEMAS_ID = request.getParameter("PROVOLES_CINEMAS_ID");
    	if (PROVOLES_CINEMAS_ID != null && !PROVOLES_CINEMAS_ID.isEmpty()) {
    	    try {
    	    	Reservation.setPROVOLES_CINEMAS_ID(Integer.parseInt(PROVOLES_CINEMAS_ID));
            } catch (NumberFormatException e) {
                // Handle the parsing error gracefully
                System.out.println("Invalid ID format: " + PROVOLES_CINEMAS_ID);
                // You can choose to redirect to an error page or display an error message
                response.getWriter().println("Invalid ID format: " + PROVOLES_CINEMAS_ID);
                return;
            }
    	}
    	
    	String CUSTOMERS_ID = request.getParameter("CUSTOMERS_ID");
    	if (CUSTOMERS_ID != null && !CUSTOMERS_ID.isEmpty()) {
    	    try {
    	    	Reservation.setCUSTOMERS_ID(Integer.parseInt(CUSTOMERS_ID));
            } catch (NumberFormatException e) {
                // Handle the parsing error gracefully
                System.out.println("Invalid ID format: " + CUSTOMERS_ID);
                // You can choose to redirect to an error page or display an error message
                response.getWriter().println("Invalid ID format: " + CUSTOMERS_ID);
                return;
            }
    	}
    	
    	String NUMBER_OF_SEATS = request.getParameter("NUMBER_OF_SEATS");
    	if (NUMBER_OF_SEATS != null && !NUMBER_OF_SEATS.isEmpty()) {
    	    try {
    	    	Reservation.setNUMBER_OF_SEATS(Integer.parseInt(NUMBER_OF_SEATS));
            } catch (NumberFormatException e) {
                // Handle the parsing error gracefully
                System.out.println("Invalid ID format: " + NUMBER_OF_SEATS);
                // You can choose to redirect to an error page or display an error message
                response.getWriter().println("Invalid ID format: " + NUMBER_OF_SEATS);
                return;
            }
    	}
    	
    	String PR_TIME = request.getParameter("PR_TIME");
    	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Timestamp timestamp = null;
		try {
		    if (PR_TIME != null) { // Check if PR_TIME is not null
		        java.util.Date parsedDate = dateFormat.parse(PR_TIME);
		        timestamp = new Timestamp(parsedDate.getTime());
		        Reservation.setPR_TIME(timestamp);
		    } else {
		    	System.out.println("fggdfgdfggggggggggggggg");
		        // Handle the case when PR_TIME is null
		        // Print an error message, throw an exception, or take appropriate action
		    }
		} catch (Exception e) {
		    e.printStackTrace();
		}
		
		if (action.equalsIgnoreCase("reservation2")) {
			
			HttpSession session = request.getSession();
			Integer currentUserID = (Integer) session.getAttribute("id");
			Reservation.setCUSTOMERS_ID(currentUserID);
			
			dao.addReservation(Reservation);
		}
		RequestDispatcher view = request.getRequestDispatcher(LIST_Movie_Customer);
		request.setAttribute("Movies", dao.getAllMovies());
		view.forward(request, response);
	}

}

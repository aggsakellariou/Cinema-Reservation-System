package controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.SystemDao;
import Cinema.*;


@WebServlet("/ProvolesController")
public class ProvolesController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static String INSERT = "/addProv2.jsp";
	private static String MOVIES = "/addProv.jsp";
	private static String EDIT = "/editProv.jsp";
	private static String PROVOLES = "/listProv.jsp";

    private SystemDao dao;   
    
    public ProvolesController() {
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
			int ID = Integer.parseInt(request.getParameter("ID"));
			dao.deleteProvoles(ID); 
			forward = PROVOLES;
			request.setAttribute("Provoles", dao.getAllProvoles());
		} 
		else if (action.equalsIgnoreCase("edit")){
			forward = EDIT;
			int ID = Integer.parseInt(request.getParameter("ID"));
			Provoles Provoles = dao.getProvByIdEdit(ID);
			
			request.setAttribute("Provoles", Provoles);
		}
		else if (action.equalsIgnoreCase("listProv")){
			forward = PROVOLES;
			request.setAttribute("Provoles", dao.getAllProvoles());
		}
		else if (action.equalsIgnoreCase("add")){
			forward =INSERT;
			int ID = Integer.parseInt(request.getParameter("ID"));
			Provoles Provoles = dao.getProvById(ID);
			request.setAttribute("Provoles", Provoles);
		}
		else if (action.equalsIgnoreCase("movies")){
			forward = MOVIES;
			request.setAttribute("Movies", dao.getAllMovies());
		}
		RequestDispatcher view = request.getRequestDispatcher(forward);
		view.forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		String action = request.getParameter("action");
		
		Provoles Provoles = new Provoles();
    	// Set the movie data from the request parameters
		String MOVIES_ID = request.getParameter("MOVIES_ID");
    	if (MOVIES_ID != null && !MOVIES_ID.isEmpty()) {
    	    try {
    	    	Provoles.setMOVIES_ID(Integer.parseInt(MOVIES_ID));
            } catch (NumberFormatException e) {
                // Handle the parsing error gracefully
                System.out.println("Invalid ID format: " + MOVIES_ID);
                // You can choose to redirect to an error page or display an error message
                response.getWriter().println("Invalid ID format: " + MOVIES_ID);
                return;
            }
    	}
    	
    	Provoles.setMOVIES_NAME(request.getParameter("MOVIES_NAME"));
    	
    	String CINEMAS_ID = request.getParameter("CINEMAS_ID");
    	if (CINEMAS_ID != null && !CINEMAS_ID.isEmpty()) {
    	    try {
    	    	Provoles.setCINEMAS_ID(Integer.parseInt(CINEMAS_ID));
            } catch (NumberFormatException e) {
                // Handle the parsing error gracefully
                System.out.println("Invalid ID format: " + CINEMAS_ID);
                // You can choose to redirect to an error page or display an error message
                response.getWriter().println("Invalid ID format: " + CINEMAS_ID);
                return;
            }
    	}
    	
    	String CONTENT_ADMIN_ID = request.getParameter("CONTENT_ADMIN_ID");
    	if (CINEMAS_ID != null && !CINEMAS_ID.isEmpty()) {
    	    try {
    	    	Provoles.setCONTENT_ADMIN_ID(Integer.parseInt(CONTENT_ADMIN_ID));
            } catch (NumberFormatException e) {
                // Handle the parsing error gracefully
                System.out.println("Invalid ID format: " + CONTENT_ADMIN_ID);
                // You can choose to redirect to an error page or display an error message
                response.getWriter().println("Invalid ID format: " + CONTENT_ADMIN_ID);
                return;
            }
    	}
    	
    	String TIME = request.getParameter("TIME");
    	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Timestamp timestamp = null;
		try {
		    java.util.Date parsedDate = dateFormat.parse(TIME);
		    timestamp = new Timestamp(parsedDate.getTime());
	    	Provoles.setTIME(timestamp);
		} catch (Exception e) {
		    e.printStackTrace();
		}
    	
    	String ID = request.getParameter("ID");
    	if (ID != null && !ID.isEmpty()) {
    	    try {
    	    	Provoles.setID(Integer.parseInt(ID));
            } catch (NumberFormatException e) {
                // Handle the parsing error gracefully
                System.out.println("Invalid ID format: " + ID);
                // You can choose to redirect to an error page or display an error message
                response.getWriter().println("Invalid ID format: " + ID);
                return;
            }
    	}
    	
    	
    	if (action.equalsIgnoreCase("edit")) {
			
			// content admin id who updates
			HttpSession session = request.getSession();
			Integer currentUserID = (Integer) session.getAttribute("id");
			Provoles.setCONTENT_ADMIN_ID(currentUserID);
			// id of movie stays the same
//        	int latestID = dao.getLatestMovieID();
//        	Movies.setID(latestID);

			dao.updateProvoles(Provoles);

		} else if (action.equalsIgnoreCase("insert")) {
			
			// Get the latest ID from the database and increment it by 1
			int latestID = dao.getLatestProvID();
			int newID = latestID + 1;
			Provoles.setID(newID);

			HttpSession session = request.getSession();
			Integer currentUserID = (Integer) session.getAttribute("id");
			Provoles.setCONTENT_ADMIN_ID(currentUserID);

			dao.addProvoles(Provoles);
		}

		RequestDispatcher view = request.getRequestDispatcher(PROVOLES);
		request.setAttribute("Provoles", dao.getAllProvoles());
		view.forward(request, response);

	}
}

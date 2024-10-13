package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.SystemDao;
import Cinema.Movies;

@WebServlet("/MovieController")
public class MovieController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static String INSERT = "/addMovie.jsp";
	private static String EDIT = "/editMovie.jsp";
	private static String LIST_Movie = "/listMovie.jsp";
	private SystemDao dao;

	public MovieController() {
		super();
		dao = new SystemDao();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String forward = "";
		String action = request.getParameter("action");
		response.setContentType("text/html; charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");

		if (action.equalsIgnoreCase("delete")){
			int ID = Integer.parseInt(request.getParameter("ID"));
			dao.deleteMovie(ID); 
			forward = LIST_Movie;
			request.setAttribute("Movies", dao.getAllMovies());
		}
		else if (action.equalsIgnoreCase("edit")){
			forward = EDIT;
			int ID = Integer.parseInt(request.getParameter("ID"));
			Movies Movies = dao.getMovieById(ID);
			request.setAttribute("Movies", Movies);
		}
		else if (action.equalsIgnoreCase("listMovie")){
			forward = LIST_Movie;
			request.setAttribute("Movies", dao.getAllMovies());
		} 
		else {
			forward =INSERT;
		}
		RequestDispatcher view = request.getRequestDispatcher(forward);
		view.forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		String action = request.getParameter("action");

		Movies Movies = new Movies();

		// Set the movie data from the request parameters
		Movies.setNAME(request.getParameter("NAME"));
		Movies.setCONTENT(request.getParameter("CONTENT"));
		String LENGTH = request.getParameter("LENGTH");
    	if (LENGTH != null && !LENGTH.isEmpty()) {
    	    try {
    	    	Movies.setLENGTH(Integer.parseInt(LENGTH));
            } catch (NumberFormatException e) {
                // Handle the parsing error gracefully
                System.out.println("Invalid ID format: " + LENGTH);
                // You can choose to redirect to an error page or display an error message
                response.getWriter().println("Invalid ID format: " + LENGTH);
                return;
            }
    	}    
		Movies.setTYPE(request.getParameter("TYPE"));
		Movies.setSUMMARY(request.getParameter("SUMMARY"));
		Movies.setDIRECTOR(request.getParameter("DIRECTOR"));
		String CONTENT_ADMIN_ID = request.getParameter("CONTENT_ADMIN_ID");
    	if (CONTENT_ADMIN_ID != null && !CONTENT_ADMIN_ID.isEmpty()) {
    	    try {
        	    Movies.setCONTENT_ADMIN_ID(Integer.parseInt(CONTENT_ADMIN_ID));
            } catch (NumberFormatException e) {
                // Handle the parsing error gracefully
                System.out.println("Invalid ID format: " + CONTENT_ADMIN_ID);
                // You can choose to redirect to an error page or display an error message
                response.getWriter().println("Invalid ID format: " + CONTENT_ADMIN_ID);
                return;
            }
    	}        
		String ID = request.getParameter("ID");
        if (ID != null && !ID.isEmpty()) {
            try {
                Movies.setID(Integer.parseInt(ID));
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
			Movies.setCONTENT_ADMIN_ID(currentUserID);
			// id of movie stays the same
//        	int latestID = dao.getLatestMovieID();
//        	Movies.setID(latestID);

			dao.updateMovie(Movies);

		} else if (action.equalsIgnoreCase("insert")) {
			
			// Get the latest ID from the database and increment it by 1
			int latestID = dao.getLatestMovieID();
			int newID = latestID + 1;
			Movies.setID(newID);

			HttpSession session = request.getSession();
			Integer currentUserID = (Integer) session.getAttribute("id");
			Movies.setCONTENT_ADMIN_ID(currentUserID);

			dao.addMovie(Movies);
		}

		RequestDispatcher view = request.getRequestDispatcher(LIST_Movie);
		request.setAttribute("Movies", dao.getAllMovies());
		view.forward(request, response);
	}
}

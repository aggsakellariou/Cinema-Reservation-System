package controller;


import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.SystemDao;

@WebServlet("/CustomerController")
public class CustomerController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static String RESERV = "/reservationCustomer.jsp";
	private static String LIST_Movie_Customer = "/listMovieCustomer.jsp";

    private SystemDao dao;

    public CustomerController() {
        super();
        dao = new SystemDao();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String forward="";
        String action = request.getParameter("action");
        response.setContentType("text/html; charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		
		if (action.equalsIgnoreCase("reservation")){
			forward = RESERV;
            int ID = Integer.parseInt(request.getParameter("ID"));
            request.setAttribute("Provoles", dao.getProvolesById(ID));
        } else if (action.equalsIgnoreCase("listMovieCustomer")){
            forward = LIST_Movie_Customer;
            request.setAttribute("Movies", dao.getAllMovies());
        }

		RequestDispatcher view = request.getRequestDispatcher(forward);
		view.forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		
		RequestDispatcher view = request.getRequestDispatcher(LIST_Movie_Customer);
		request.setAttribute("Movies", dao.getAllMovies());
		view.forward(request, response);
	}
}

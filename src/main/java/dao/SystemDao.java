package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.Timestamp;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import net.javaguides.login.bean.*;
import Cinema.*;
import utilities.DBUtil;

public class SystemDao {

	private Connection connection;
	public SystemDao() {
        connection = DBUtil.getConnection();
    }
	
	//
	// login
	//
	
	public String loginUsernameCheck(String username) {
        String answer=null;

        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM user WHERE username=?");
            preparedStatement.setString(1, username);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next() == false)
            {
                 answer = "There is no user with the username: "+username+", please enter a valid username!";
            }
            else
            {
                answer = username;
            }       

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return answer;
    }
	
	public String passwordCheck(String username,String password) {
        String answer=null;
        try {
                PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM user WHERE (username=? and password=?)");
                preparedStatement.setString(1, username);
                preparedStatement.setString(2, password);
                ResultSet rs = preparedStatement.executeQuery();
                if (rs.next() == false)
                {
                    answer="Wrong Password!";
                }
                else
                {
                    answer="You logged in!";
                }
            } catch (SQLException e) {
            	e.printStackTrace();
            }
        return answer;
    }
	
	public String getRole(String username) {
        String role=null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM content_admin WHERE user_username=?");
            preparedStatement.setString(1, username);
            ResultSet rs1 = preparedStatement.executeQuery();
            if (rs1.next() == true)
            {
                 role="content_admin";
            }
            else
            {
                preparedStatement = connection.prepareStatement("SELECT * FROM admins WHERE user_username=?");
                preparedStatement.setString(1, username);
                ResultSet rs2 = preparedStatement.executeQuery();               
                if (rs2.next() == true)
                {
                     role="admin";
                }
                else
                {
                    preparedStatement = connection.prepareStatement("SELECT * FROM customers WHERE user_username=?");
                    preparedStatement.setString(1, username);
                    ResultSet rs3 = preparedStatement.executeQuery();
                    if (rs3.next() == true)
                    {
                         role="customers";
                    }
                }
            }   
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return role;
    }
	
	//admin
	public int getAdminID(String username) {

		int id=0;
		try(				
			PreparedStatement preparedStatement = connection
					.prepareStatement("select ID from admins where user_username = ?")) {
			preparedStatement.setString(1, username);
			ResultSet rs = preparedStatement.executeQuery();
			if (rs.next()) {
				id=rs.getInt("id");
			}

		} catch (SQLException e) {
			 e.printStackTrace();			
		}
		return id;
	}
	
	// content admin
	public int getContentAdminID(String username) {

		int id=0;
		try(				
			PreparedStatement preparedStatement = connection
					.prepareStatement("select ID from content_admin where user_username = ?")) {
			preparedStatement.setString(1, username);
			ResultSet rs = preparedStatement.executeQuery();
			if (rs.next()) {
				id=rs.getInt("id");
			}

		} catch (SQLException e) {
			 e.printStackTrace();			
		}
		return id;
	}
	
	//customer
	public int getCustomerID(String username) {

		int id=0;
		try(				
			PreparedStatement preparedStatement = connection
					.prepareStatement("select ID from customers where user_username = ?")) {
			preparedStatement.setString(1, username);
			ResultSet rs = preparedStatement.executeQuery();
			if (rs.next()) {
				id=rs.getInt("id");
			}

		} catch (SQLException e) {
			 e.printStackTrace();			
		}
		return id;
	}
	
	//
	//Content Admin
	//
	
	// MOVIES
	public List<Movies> getAllMovies() {
        List<Movies> Movies = new ArrayList<Movies>();
        
        try {
        	Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery("SELECT * FROM movies");
            
            while (rs.next()) {
            	Movies movie = new Movies();
            	movie.setID(rs.getInt("ID"));
            	movie.setNAME(rs.getString("NAME"));
            	movie.setCONTENT(rs.getString("CONTENT"));
            	movie.setLENGTH(rs.getInt("LENGTH"));
            	movie.setTYPE(rs.getString("TYPE"));
            	movie.setSUMMARY(rs.getString("SUMMARY"));
            	movie.setDIRECTOR(rs.getString("DIRECTOR"));
            	movie.setCONTENT_ADMIN_ID(rs.getInt("CONTENT_ADMIN_ID"));
            	Movies.add(movie);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Movies;
    }
	
	public Movies getMovieById(int ID) {
		Movies Movies = new Movies();
        try {
            PreparedStatement preparedStatement = connection.
                    prepareStatement("SELECT * FROM movies WHERE ID=?");
            preparedStatement.setInt(1, ID);
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
            	Movies.setID(rs.getInt("ID"));
            	Movies.setNAME(rs.getString("NAME"));
            	Movies.setCONTENT(rs.getString("CONTENT"));
            	Movies.setLENGTH(rs.getInt("LENGTH"));
            	Movies.setTYPE(rs.getString("TYPE"));
            	Movies.setSUMMARY(rs.getString("SUMMARY"));
            	Movies.setDIRECTOR(rs.getString("DIRECTOR"));
            	Movies.setCONTENT_ADMIN_ID(rs.getInt("CONTENT_ADMIN_ID"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Movies;
    }
	
	public void updateMovie(Movies Movies) {
        try {
        	PreparedStatement preparedStatement = connection.prepareStatement("UPDATE movies SET NAME=?"
        			+ ", CONTENT=?, LENGTH=?, TYPE=?, SUMMARY=?, DIRECTOR=?"
        			+ ", CONTENT_ADMIN_ID=? WHERE ID=?");
                     
            preparedStatement.setString(1, Movies.getNAME());
            preparedStatement.setString(2, Movies.getCONTENT());
            preparedStatement.setInt(3, Movies.getLENGTH());
            preparedStatement.setString(4, Movies.getTYPE());
            preparedStatement.setString(5, Movies.getSUMMARY());
            preparedStatement.setString(6, Movies.getDIRECTOR());
            preparedStatement.setInt(7, Movies.getCONTENT_ADMIN_ID());
            preparedStatement.setInt(8, Movies.getID());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }	
	
	public void deleteMovie(int ID) {
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("DELETE FROM movies WHERE ID=?");
            preparedStatement.setInt(1, ID);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
	
	public int getLatestMovieID() {
	    int latestID = 0;
	    try {
	        Statement statement = connection.createStatement();
	        ResultSet resultSet = statement.executeQuery("SELECT MAX(ID) AS LatestID FROM movies");
	        if (resultSet.next()) {
	            latestID = resultSet.getInt("LatestID");
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return latestID;
	}
	
	public void addMovie(Movies Movies) {
        try {
            PreparedStatement preparedStatement = connection.
            		prepareStatement("INSERT INTO movies (ID, NAME, CONTENT, LENGTH, TYPE, SUMMARY, DIRECTOR, CONTENT_ADMIN_ID) VALUES (?,?,?,?,?,?,?,?)");
            // Parameters start with 1
            preparedStatement.setInt(1, Movies.getID());
            preparedStatement.setString(2, Movies.getNAME());
            preparedStatement.setString(3, Movies.getCONTENT());
            preparedStatement.setInt(4, Movies.getLENGTH());
            preparedStatement.setString(5, Movies.getTYPE());
            preparedStatement.setString(6, Movies.getSUMMARY());
            preparedStatement.setString(7, Movies.getDIRECTOR());
            preparedStatement.setInt(8, Movies.getCONTENT_ADMIN_ID());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
	
	// PROVOLES
	
	public List<Provoles> getAllProvoles() {
        List<Provoles> Provoles = new ArrayList<Provoles>();
        
        try {
        	Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery("SELECT * FROM PROV");
            
            while (rs.next()) {
            	Provoles provoles = new Provoles();
            	provoles.setMOVIES_ID(rs.getInt("MOVIES_ID"));
            	provoles.setMOVIES_NAME(rs.getString("MOVIES_NAME"));
            	provoles.setCINEMAS_ID(rs.getInt("CINEMAS_ID"));
            	provoles.setCONTENT_ADMIN_ID(rs.getInt("CONTENT_ADMIN_ID"));
            	provoles.setTIME(rs.getTimestamp("TIME"));
            	provoles.setID(rs.getInt("ID"));
            	Provoles.add(provoles);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Provoles;
    }
	
	public Provoles getProvById(int ID) {
		Provoles Provoles = new Provoles();
        try {
            PreparedStatement preparedStatement = connection.
                    prepareStatement("SELECT * FROM movies WHERE ID=?");
            preparedStatement.setInt(1, ID);
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
            	Provoles.setMOVIES_ID(rs.getInt("ID"));
            	Provoles.setMOVIES_NAME(rs.getString("NAME"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Provoles;
    }
	
	public Provoles getProvByIdEdit(int ID) {
		Provoles Provoles = new Provoles();
        try {
            PreparedStatement preparedStatement = connection.
                    prepareStatement("SELECT * FROM prov WHERE ID=?");
            preparedStatement.setInt(1, ID);
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
            	Provoles.setMOVIES_ID(rs.getInt("MOVIES_ID"));
            	Provoles.setMOVIES_NAME(rs.getString("MOVIES_NAME"));
            	Provoles.setCINEMAS_ID(rs.getInt("CINEMAS_ID"));
            	Provoles.setCONTENT_ADMIN_ID(rs.getInt("CONTENT_ADMIN_ID"));
            	Provoles.setTIME(rs.getTimestamp("TIME"));
            	Provoles.setID(rs.getInt("ID"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Provoles;
    }
	
	public void updateProvoles(Provoles Provoles) {
        try {
        	PreparedStatement preparedStatement = connection.prepareStatement("UPDATE prov SET MOVIES_ID=?"
        			+ ", MOVIES_NAME=?, CINEMAS_ID=?, CONTENT_ADMIN_ID=?, TIME=? where ID=?");
                     
            preparedStatement.setInt(1, Provoles.getMOVIES_ID());
            preparedStatement.setString(2, Provoles.getMOVIES_NAME());
            preparedStatement.setInt(3, Provoles.getCINEMAS_ID());
            preparedStatement.setInt(4, Provoles.getCONTENT_ADMIN_ID());
            preparedStatement.setTimestamp(5, Provoles.getTIME());
            preparedStatement.setInt(6, Provoles.getID());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
	
	public void deleteProvoles(int ID) {
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("DELETE FROM prov WHERE ID=?");
            preparedStatement.setInt(1, ID);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
	
	public int getLatestProvID() {
	    int latestID = 0;
	    try {
	        Statement statement = connection.createStatement();
	        ResultSet resultSet = statement.executeQuery("SELECT MAX(ID) AS LatestID FROM prov");
	        if (resultSet.next()) {
	            latestID = resultSet.getInt("LatestID");
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return latestID;
	}
	
	public void addProvoles(Provoles Provoles) {
        try {
            PreparedStatement preparedStatement = connection.
            		prepareStatement("INSERT INTO prov (MOVIES_ID, MOVIES_NAME, CINEMAS_ID, CONTENT_ADMIN_ID, TIME, ID) VALUES (?,?,?,?,?,?)");
            // Parameters start with 1
            preparedStatement.setInt(1, Provoles.getMOVIES_ID());
            preparedStatement.setString(2, Provoles.getMOVIES_NAME());
            preparedStatement.setInt(3, Provoles.getCINEMAS_ID());
            preparedStatement.setInt(4, Provoles.getCONTENT_ADMIN_ID());
            preparedStatement.setTimestamp(5, Provoles.getTIME());
            preparedStatement.setInt(6, Provoles.getID());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
	
	//
	//Customer
	//
	
	//reservation
	public List<Provoles> getProvolesById(int ID) {
        List<Provoles> Provoles = new ArrayList<Provoles>();
        
        try {
        	PreparedStatement preparedStatement = connection.
                    prepareStatement("SELECT * FROM prov WHERE MOVIES_ID=?");
            preparedStatement.setInt(1, ID);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
            	Provoles Provolh = new Provoles();
            	Provolh.setMOVIES_ID(rs.getInt("MOVIES_ID"));
            	Provolh.setMOVIES_NAME(rs.getString("MOVIES_NAME"));
            	Provolh.setCINEMAS_ID(rs.getInt("CINEMAS_ID"));
            	Provolh.setCONTENT_ADMIN_ID(rs.getInt("CONTENT_ADMIN_ID"));
            	Provolh.setTIME(rs.getTimestamp("TIME"));
            	Provolh.setID(rs.getInt("ID"));
            	Provoles.add(Provolh);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Provoles;
    }
	
	public Reservation getReservById(int MOVIES_ID, Timestamp TIME) {
		Reservation Reservation = new Reservation();
        try {
            PreparedStatement preparedStatement = connection.
                    prepareStatement("SELECT * FROM PROV WHERE MOVIES_ID=? AND TIME=?");
            preparedStatement.setInt(1, MOVIES_ID);
            preparedStatement.setTimestamp(2, TIME);

            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
            	Reservation.setPROVOLES_MOVIES_ID(rs.getInt("MOVIES_ID"));
            	Reservation.setPROVOLES_MOVIES_NAME(rs.getString("MOVIES_NAME"));
            	Reservation.setPROVOLES_CINEMAS_ID(rs.getInt("CINEMAS_ID"));
            	Reservation.setPR_TIME(rs.getTimestamp("TIME"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Reservation;
    }
	
	public int getSeats(int RPOVOLES_MOVIES_ID, Timestamp PR_TIME) {
		int seats = 0; 
		 try {
            PreparedStatement preparedStatement = connection.
                    prepareStatement("SELECT sum(NUMBER_OF_SEATS) FROM reserv "
                    		+ "WHERE PROVOLES_MOVIES_ID=? AND PR_TIME=?");
            preparedStatement.setInt(1, RPOVOLES_MOVIES_ID);
            preparedStatement.setTimestamp(2, PR_TIME);

            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
            	seats = rs.getInt("sum(NUMBER_OF_SEATS)");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
		return seats;
	}
	
	public int getCinemaSeats(int CINEMAS_ID) {
	    int CinemaSeats = 0;
	    try {
	    	PreparedStatement preparedStatement = connection.
                    prepareStatement("SELECT * FROM CINEMAS WHERE ID=?");
            preparedStatement.setInt(1, CINEMAS_ID);
            ResultSet rs = preparedStatement.executeQuery();
            
	        if (rs.next()) {
	        	CinemaSeats = rs.getInt("SEATS");
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return CinemaSeats;
	}
	
	public void addReservation(Reservation Reservation) {
        try {
            PreparedStatement preparedStatement = connection.
            		prepareStatement("INSERT INTO reserv (PROVOLES_MOVIES_ID, PROVOLES_MOVIES_NAME, PROVOLES_CINEMAS_ID, CUSTOMERS_ID, NUMBER_OF_SEATS, PR_TIME) VALUES (?,?,?,?,?,?)");
            // Parameters start with 1
            preparedStatement.setInt(1, Reservation.getPROVOLES_MOVIES_ID());
            preparedStatement.setString(2, Reservation.getPROVOLES_MOVIES_NAME());
            preparedStatement.setInt(3, Reservation.getPROVOLES_CINEMAS_ID());
            preparedStatement.setInt(4, Reservation.getCUSTOMERS_ID());
            preparedStatement.setInt(5, Reservation.getNUMBER_OF_SEATS());
            preparedStatement.setTimestamp(6, Reservation.getPR_TIME());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
	
	// HISTORY
	public List<Reservation> getCustomerHistory(int id) {
		List<Reservation> Reservation = new ArrayList<Reservation>();
	    try {
	    	PreparedStatement preparedStatement = connection.
                    prepareStatement("SELECT * FROM reserv WHERE CUSTOMERS_ID=?");
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            
            while (rs.next()) {
            	Reservation reservation = new Reservation();
            	reservation.setPROVOLES_MOVIES_ID(rs.getInt("PROVOLES_MOVIES_ID"));
            	reservation.setPROVOLES_MOVIES_NAME(rs.getString("PROVOLES_MOVIES_NAME"));
            	reservation.setPROVOLES_CINEMAS_ID(rs.getInt("PROVOLES_CINEMAS_ID"));
            	reservation.setCUSTOMERS_ID(rs.getInt("CUSTOMERS_ID"));
            	reservation.setNUMBER_OF_SEATS(rs.getInt("NUMBER_OF_SEATS"));
            	reservation.setPR_TIME(rs.getTimestamp("PR_TIME"));
            	Reservation.add(reservation);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return Reservation;
	}
	
	//
	// Admin
	//
	
	//User
    public void deleteUser(String username) {
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("DELETE FROM user WHERE username=?");
            preparedStatement.setString(1, username);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

 // admin (admin)
 	public List<Admin> getAllAdmins() {
         List<Admin> Admin = new ArrayList<Admin>();
         
         try {
         	Statement statement = connection.createStatement();
 			ResultSet rs = statement.executeQuery("select ID, NAME, user_username, email, role, create_time "
 					+ "from user join admins where username=user_username");
             
             while (rs.next()) {
             	Admin admin = new Admin();
             	admin.setID(rs.getInt("ID"));
             	admin.setNAME(rs.getString("NAME"));
             	admin.setUser_username(rs.getString("user_username"));
             	admin.setEmail(rs.getString("email"));
             	admin.setCreate_time(rs.getTimestamp("create_time"));
             	admin.setRole(rs.getString("role"));
             	
             	Admin.add(admin);
             }
         } catch (SQLException e) {
             e.printStackTrace();
         }
         return Admin;
     }
    
    // content admin (admin)
 	public List<ContentAdmin> getAllContentAdmins() {
        List<ContentAdmin> ContentAdmin = new ArrayList<ContentAdmin>();
        
        try {
        	Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery("select ID, NAME, user_username, email, role, create_time "
					+ "from user join content_admin where username=user_username");
            
            while (rs.next()) {
            	ContentAdmin contentAdmin = new ContentAdmin();
            	contentAdmin.setID(rs.getInt("ID"));
            	contentAdmin.setNAME(rs.getString("NAME"));
            	contentAdmin.setUser_username(rs.getString("user_username"));
            	contentAdmin.setEmail(rs.getString("email"));
            	contentAdmin.setCreate_time(rs.getTimestamp("create_time"));
            	contentAdmin.setRole(rs.getString("role"));
            	
            	ContentAdmin.add(contentAdmin);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ContentAdmin;
    }
 	
 	public void deleteContentAdmin(int ID) {
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("DELETE FROM content_admin WHERE ID=?");
            preparedStatement.setInt(1, ID);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
 	
 	public int getLatestContentAdminID() {
	    int latestID = 0;
	    try {
	        Statement statement = connection.createStatement();
	        ResultSet resultSet = statement.executeQuery("SELECT MAX(ID) AS LatestID FROM content_admin");
	        if (resultSet.next()) {
	            latestID = resultSet.getInt("LatestID");
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return latestID;
	}
 	
    public void addUserContentAdmin(ContentAdmin ContentAdmin) {
        try {
            PreparedStatement preparedStatement = connection.
            		prepareStatement("INSERT INTO user (username, email, password, create_time, salt, role) VALUES (?,?,?,?,?,'content_admin')");
            // Parameters start with 1
            preparedStatement.setString(1, ContentAdmin.getUser_username());
            preparedStatement.setString(2, ContentAdmin.getEmail());
            preparedStatement.setString(3, ContentAdmin.getPassword());
            preparedStatement.setTimestamp(4, ContentAdmin.getCreate_time());
            preparedStatement.setString(5, ContentAdmin.getSalt());
            
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void addContentAdmin(ContentAdmin ContentAdmin) {
        try {
            PreparedStatement preparedStatement = connection.
            		prepareStatement("INSERT INTO content_admin (ID, NAME, user_username) VALUES (?,?,?)");
            // Parameters start with 1
            preparedStatement.setInt(1, ContentAdmin.getID());
            preparedStatement.setString(2, ContentAdmin.getNAME());
            preparedStatement.setString(3, ContentAdmin.getUser_username());
            
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
	//customer (admin)
    public List<Customer> getAllCustomers() {
        List<Customer> Customer = new ArrayList<Customer>();
        
        try {
        	Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery("select ID, NAME, user_username, email, role, create_time "
					+ "from user join customers where username=user_username");
            
            while (rs.next()) {
            	Customer customer = new Customer();
            	customer.setID(rs.getInt("ID"));
            	customer.setNAME(rs.getString("NAME"));
            	customer.setUser_username(rs.getString("user_username"));
            	customer.setEmail(rs.getString("email"));
            	customer.setCreate_time(rs.getTimestamp("create_time"));
            	customer.setRole(rs.getString("role"));
            	
            	Customer.add(customer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Customer;
    }
    
    public void deleteCustomer(int ID) {
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("DELETE FROM customers WHERE ID=?");
            preparedStatement.setInt(1, ID);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public int getLatestCustomerID() {
	    int latestID = 0;
	    try {
	        Statement statement = connection.createStatement();
	        ResultSet resultSet = statement.executeQuery("SELECT MAX(ID) AS LatestID FROM customers");
	        if (resultSet.next()) {
	            latestID = resultSet.getInt("LatestID");
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return latestID;
	}
    
    public void addUserCustomer(Customer Customer) {
        try {
            PreparedStatement preparedStatement = connection.
            		prepareStatement("INSERT INTO user (username, email, password, create_time, salt, role) VALUES (?,?,?,?,?,'customer')");
            // Parameters start with 1
            preparedStatement.setString(1, Customer.getUser_username());
            preparedStatement.setString(2, Customer.getEmail());
            preparedStatement.setString(3, Customer.getPassword());
            preparedStatement.setTimestamp(4, Customer.getCreate_time());
            preparedStatement.setString(5, Customer.getSalt());
            
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
	public void addCustomer(Customer Customer) {
        try {
            PreparedStatement preparedStatement = connection.
            		prepareStatement("INSERT INTO customers (ID, NAME, user_username) VALUES (?,?,?)");
            // Parameters start with 1
            preparedStatement.setInt(1, Customer.getID());
            preparedStatement.setString(2, Customer.getNAME());
            preparedStatement.setString(3, Customer.getUser_username());
            
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
	
}

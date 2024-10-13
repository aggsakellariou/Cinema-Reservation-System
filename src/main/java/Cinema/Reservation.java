package Cinema;
import java.sql.Timestamp;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Reservation {
	private int PROVOLES_MOVIES_ID;
	private String PROVOLES_MOVIES_NAME;
	private int PROVOLES_CINEMAS_ID;
	private int CUSTOMERS_ID;
	private int NUMBER_OF_SEATS;
	private Timestamp PR_TIME;
	
    public int getPROVOLES_MOVIES_ID() {
		return PROVOLES_MOVIES_ID;
	}
	public void setPROVOLES_MOVIES_ID(int pROVOLES_MOVIES_ID) {
		PROVOLES_MOVIES_ID = pROVOLES_MOVIES_ID;
	}
	
	public String getPROVOLES_MOVIES_NAME() {
		return PROVOLES_MOVIES_NAME;
	}
	public void setPROVOLES_MOVIES_NAME(String pROVOLES_MOVIES_NAME) {
		PROVOLES_MOVIES_NAME = pROVOLES_MOVIES_NAME;
	}
	
	public int getPROVOLES_CINEMAS_ID() {
		return PROVOLES_CINEMAS_ID;
	}
	public void setPROVOLES_CINEMAS_ID(int pROVOLES_CINEMA_ID) {
		PROVOLES_CINEMAS_ID = pROVOLES_CINEMA_ID;
	}
	
	public int getCUSTOMERS_ID() {
		return CUSTOMERS_ID;
	}
	public void setCUSTOMERS_ID(int cUSTOMER_ID) {
		CUSTOMERS_ID = cUSTOMER_ID;
	}
	
	public int getNUMBER_OF_SEATS() {
		return NUMBER_OF_SEATS;
	}
	public void setNUMBER_OF_SEATS(int nUMBER_OF_SEATS) {
		NUMBER_OF_SEATS = nUMBER_OF_SEATS;
	}
	
	public Timestamp getPR_TIME() {
		return PR_TIME;
	}
	public void setPR_TIME(Timestamp pR_TIME) {
		PR_TIME = pR_TIME;
	}
	@Override
    public String toString() {
        return "Reservation [PROVOLES_MOVIES_ID=" + PROVOLES_MOVIES_ID 
        		+ ", PROVOLES_MOVIES_NAME=" + PROVOLES_MOVIES_NAME
                + ", PROVOLES_CINEMA_ID=" + PROVOLES_CINEMAS_ID 
                + ", CUSTOMER_ID=" + CUSTOMERS_ID+ ", NUMBER_OF_SEATS=" 
                + NUMBER_OF_SEATS + ", PR_TIME=" + PR_TIME + "]";
	}
	
}

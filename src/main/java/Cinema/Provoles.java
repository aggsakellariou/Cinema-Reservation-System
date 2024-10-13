package Cinema;

import java.sql.Timestamp;

public class Provoles {
	
	private int MOVIES_ID;
	private String MOVIES_NAME;
	private int CINEMAS_ID;
	private int CONTENT_ADMIN_ID;
	private Timestamp TIME;
	private int ID;
	
	public int getMOVIES_ID() {
		return MOVIES_ID;
	}
	public void setMOVIES_ID(int mOVIES_ID) {
		MOVIES_ID = mOVIES_ID;
	}
	
	public String getMOVIES_NAME() {
		return MOVIES_NAME;
	}
	public void setMOVIES_NAME(String mOVIES_NAME) {
		MOVIES_NAME = mOVIES_NAME;
	}
	
	public int getCINEMAS_ID() {
		return CINEMAS_ID;
	}
	public void setCINEMAS_ID(int cINEMAS_ID) {
		CINEMAS_ID = cINEMAS_ID;
	}
	
	public int getCONTENT_ADMIN_ID() {
		return CONTENT_ADMIN_ID;
	}
	public void setCONTENT_ADMIN_ID(int cONTENT_ADMIN_ID) {
		CONTENT_ADMIN_ID = cONTENT_ADMIN_ID;
	}
	
	public Timestamp getTIME() {
		return TIME;
	}
	public void setTIME(Timestamp tIME) {
		TIME = tIME;
	}
	
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	
	@Override
    public String toString() {
        return "Provoles [MOVIES_ID=" + MOVIES_ID + ", MOVIES_NAME=" + MOVIES_NAME
                + ", CINEMA_ID=" + CINEMAS_ID + ", CONTENT_ADMIN_ID=" 
        		+ CONTENT_ADMIN_ID+ ", TIME=" + TIME + ", ID=" + ID + "]";
	}  
}

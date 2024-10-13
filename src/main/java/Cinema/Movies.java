package Cinema;

public class Movies {
	
	private int ID;
	private String NAME;
	private String CONTENT;
	private int LENGTH;
	private String TYPE;
	private String SUMMARY;
	private String DIRECTOR;
	private int CONTENT_ADMIN_ID;
	
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	
	public String getNAME() {
		return NAME;
	}
	public void setNAME(String nAME) {
		NAME = nAME;
	}
	
	public String getCONTENT() {
		return CONTENT;
	}
	public void setCONTENT(String cONTENT) {
		CONTENT = cONTENT;
	}
	
	public int getLENGTH() {
		return LENGTH;
	}
	public void setLENGTH(int lENGTH) {
		LENGTH = lENGTH;
	}
	
	public String getTYPE() {
		return TYPE;
	}
	public void setTYPE(String tYPE) {
		TYPE = tYPE;
	}
	
	public String getSUMMARY() {
		return SUMMARY;
	}
	public void setSUMMARY(String sUMMARY) {
		SUMMARY = sUMMARY;
	}
	
	public String getDIRECTOR() {
		return DIRECTOR;
	}
	public void setDIRECTOR(String dIRECTOR) {
		DIRECTOR = dIRECTOR;
	}
	
	public int getCONTENT_ADMIN_ID() {
		return CONTENT_ADMIN_ID;
	}
	public void setCONTENT_ADMIN_ID(int cONTENT_ADMIN_ID) {
		CONTENT_ADMIN_ID = cONTENT_ADMIN_ID;
	}
	
	
	@Override
    public String toString() {
        return "Movies [ID=" + ID + ", NAME=" + NAME
                + ", CONTENT=" + CONTENT + ", LENGTH=" + LENGTH+ ", SUMMARY=" + SUMMARY + ", DIRECTOR=" + DIRECTOR + ", CONTENT_ADMIN_ID=" + CONTENT_ADMIN_ID + "]";
	}       
}

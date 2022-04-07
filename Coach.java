
public class Coach {

	private int availability; //0 if free, 1 if occupied
	
	public Coach(int availability) {
		this.availability = availability;
	}
	
	public boolean isAvailable() {
		if (availability == 0)
			return true;
		else
			return false;
	}
	
	public void setAvailable() {
		this.availability = 0;
	}
	
	public void setUnavailable() {
		this.availability = 1;
	}
}

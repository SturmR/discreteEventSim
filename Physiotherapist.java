
public class Physiotherapist {
	
	private int availability; //0 if free, 1 if occupied
	private double serviceTime;
	
	public Physiotherapist(int availability, double serviceTime) {
		this.availability = availability;
		this.serviceTime = serviceTime;
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
	
	public double getServiceTime() {
		return this.serviceTime;
	}
	
}

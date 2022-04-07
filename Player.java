
public class Player {
	
	private int id;
	private int skillLevel;
	private double entranceTime; //time that the player entered a queue
	private double currentEventDuration;
	private double previousTrainingTime;
	private int availability; //0 if free, 1 if occupied
	private int NofInvalidAttempts;
	private int NofCancelledAttempts;
	private int NofMassagesDone=0;
	private double turnaroundStartTime;
	private double turnaroundEndTime;
	private double totalWaitingTimeInPhysiotherapyQueue;
	private double totalWaitingTimeInMassageQueue;
	
	public Player(int id, int skillLevel, int availability) {
		this.id = id;
		this.skillLevel = skillLevel;
		this.availability = availability;
	}
	
	public double getEntranceTime() {
		return this.entranceTime;
	}
	
	public int getId() {
		return this.id;
	}
	
	public double getPreviousTrainingTime() {
		return this.previousTrainingTime;
	}
	
	public int getSkillLevel() {
		return this.skillLevel;
	}
	
	public int getAvailability() {
		return this.availability;
	}
	
	public double getTurnaroundStartTime() {
		return this.turnaroundStartTime;
	}
	
	public double getTurnaroundEndTime() {
		return this.turnaroundEndTime;
	}
	
	public double getCurrentEventDuration() {
		return this.currentEventDuration;
	}
	
	public double getTotalWaitingTimeInPhysiotherapyQueue() {
		return this.totalWaitingTimeInPhysiotherapyQueue;
	}
	
	public double getTotalWaitingTimeInMassageQueue() {
		return this.totalWaitingTimeInMassageQueue;
	}
	
	public int getNofMassagesDone() {
		return this.NofMassagesDone;
	}
	
	public int getNofInvalidAttempts() {
		return this.NofInvalidAttempts;
	}
	
	public int getNofCancelledAttempts() {
		return this.NofCancelledAttempts;
	}
	
	public void incrementNofInvalidAttempts() {
		this.NofInvalidAttempts += 1;
	}
	
	public void incrementNofCancelledAttempts() {
		this.NofCancelledAttempts += 1;
	}
	
	public void incrementNofMassagesDone() {
		this.NofMassagesDone += 1;
	}
	
	public void incrementTotalWaitingTimeInPhysiotherapyQueue(double time) {
		this.totalWaitingTimeInPhysiotherapyQueue += time;
	}
	
	public void incrementTotalWaitingTimeInMassageQueue(double time) {
		this.totalWaitingTimeInMassageQueue += time;
	}
	
	public void setTurnaroundStartTime(double time) {
		this.turnaroundStartTime = time;
	}
	
	public void setTurnaroundEndTime(double time) {
		this.turnaroundEndTime = time;
	}
	
	public void setUnavailable() {
		this.availability = 1;
	}
	
	public void setAvailable() {
		this.availability = 0;
	}
	
	public void setEntranceTime(double time) {
		this.entranceTime = time;
	}
	
	public void setPreviousTrainingTime(double time) {
		this.previousTrainingTime = time;
	}
	
	public void setCurrentEventDuration(double time) {
		this.currentEventDuration = time;
	}
}

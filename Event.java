import java.util.Comparator;

public class Event {

	private int playerId;
	private double duration;
	private double startTime;
	private int type;//0=training 1=massage 2=physiotherapy 3=quitmassage 4=quitphysiotherapy
	private int workerId;
	
	public Event(int playerId, double startTime, double duration, int type) {
		this.playerId = playerId;
		this.startTime = startTime;
		this.duration = duration;
		this.type = type;
	}
	
	Comparator<Event> chronologicalOrderer = new Comparator<Event>() {
		public int compare(Event e1, Event e2) {
			if (e1.startTime<e2.startTime)
				return -1;
			else
				return 1;
		}
	};
	
	public int getType() {
		return this.type;
	}
	
	public int getPlayerId() {
		return this.playerId;
	}
	
	public double getStartTime() {
		return this.startTime;
	}
	
	public double getDuration() {
		return this.duration;
	}
	
	public int getWorkerId() {
		return this.workerId;
	}
	
	public void reduceTimeBy(double interval) {
		this.startTime -= interval;
	}
	
	public void setType(int s) {
		this.type = s;
	}
	
	public void setDuration(double time) {
		this.duration = time;
	}
	
	public void setWorkerId(int id) {
		this.workerId = id;
	}
}

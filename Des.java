import java.io.File;
import java.io.PrintStream;
import java.util.Comparator;
import java.util.Locale;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.FileNotFoundException;
import java.util.PriorityQueue;
import java.util.TreeMap;



public class Des {
	
	static Comparator<Event> chronologicalOrderer = new Comparator<Event>() {
		public int compare(Event e1, Event e2) {
			if (Math.abs(e1.getStartTime()-e2.getStartTime()) < 0.000001)
				return (e2.getType() - e1.getType());
			else {
				if (e1.getStartTime() < e2.getStartTime())
					return -1;
				else
					return 1;
			}
				
		}
	};
	public static PriorityQueue<Event> eventQueue = new PriorityQueue<Event>(chronologicalOrderer);
	
	static Comparator<Player> trainingComparator = new Comparator<Player>() {
		public int compare(Player p1, Player p2) {
			if (p1.getEntranceTime() < p2.getEntranceTime())
				return -1;
			else if (p1.getEntranceTime() > p2.getEntranceTime())
				return 1;
			else {
				if (p1.getId() < p2.getId())
					return -1;
				else
					return 1;
			}
		}
	};
	public static PriorityQueue<Player> trainingQueue = new PriorityQueue<Player>(trainingComparator);

	static Comparator<Player> physiotherapyComparator = new Comparator<Player>() {
		public int compare(Player p1, Player p2) {
			if (p1.getPreviousTrainingTime() > p2.getPreviousTrainingTime())
				return -1;
			else if (p1.getPreviousTrainingTime() < p2.getPreviousTrainingTime())
				return 1;
			else {
				if (p1.getEntranceTime() < p2.getEntranceTime())
					return -1;
				else if (p1.getEntranceTime() > p2.getEntranceTime())
					return 1;
				else 
					return (p1.getId() - p2.getId());
			}
		}
	};
	public static PriorityQueue<Player> physiotherapyQueue = new PriorityQueue<Player>(physiotherapyComparator);
	
	static Comparator<Player> massageComparator = new Comparator<Player>() {
		public int compare(Player p1, Player p2) {
			if (p1.getSkillLevel() > p2.getSkillLevel())
				return -1;
			else if (p1.getSkillLevel() < p2.getSkillLevel())
				return 1;
			else {
				if (p1.getEntranceTime() < p2.getEntranceTime())
					return -1;
				else if (p1.getEntranceTime() > p2.getEntranceTime())
					return 1;
				else 
					return (p1.getId() - p2.getId());
			}
		}
	};
	public static PriorityQueue<Player> massageQueue = new PriorityQueue<Player>(massageComparator);
	
	public static int maxLengthOfTrainingQueue=0;
	public static int maxLengthOfPhysiotherapyQueue=0;
	public static int maxLengthOfMassageQueue=0;
	
	public static double totalTime=0;
	
	public static double totalTrainingTime=0;
	public static double totalPhysiotherapyTime=0;
	public static double totalMassageTime=0;
	
	public static double totalTrainingQueueTime=0;
	public static double totalPhysiotherapyQueueTime=0;
	public static double totalMassageQueueTime=0;
	
	public static double totalTurnaroundTime=0;
	
	public static int totalTrainingOccurences=0;
	public static int totalPhysiotherapyOccurences=0;
	public static int totalMassageOccurences=0;
			
	public static double maxWaitingTimeInPhysiotherapyQueue=0;
	public static int maxWaitingTimeInPhysiotherapyQueuePlayerId;
	
	public static double minWaitingTimeInMassageQueue;
	public static int minWaitingTimeInMassageQueuePlayerId;
	
	public static int totalNofInvalidAttempts=0;
	public static int totalNofCancelledAttempts=0;
	
	static TreeMap<Integer, Player> players = new TreeMap<Integer, Player>();
	static ArrayList<Coach> coaches = new ArrayList<Coach>();
	static ArrayList<Physiotherapist> physiotherapists = new ArrayList<Physiotherapist>();
	static ArrayList<Masseur> masseurs = new ArrayList<Masseur>();
	
	static ArrayList<Player> threeMassagePlayers = new ArrayList<Player>();
	
	public static int currentCoachId;
	public static boolean FindCoach() {
		for (int i=0; i<coaches.size(); i++) {
			Coach c = coaches.get(i);
			if (c.isAvailable()) {
				c.setUnavailable();
				currentCoachId = i;
				return true;
			}
		}
		return false;
	}
	
	public static int currentPhysiotherapistId;
	public static double ActiveTherapistServiceTime;
	public static boolean FindPhysiotherapist() {
		for (int i=0; i<physiotherapists.size(); i++) {
			Physiotherapist pt = physiotherapists.get(i);
			if (pt.isAvailable()) {
				ActiveTherapistServiceTime = pt.getServiceTime();
				pt.setUnavailable();
				currentPhysiotherapistId = i;
				return true;
			}
		}
		return false;
	}
	
	public static int currentMasseurId;
	public static boolean FindMasseur() {
		for (int i=0; i<masseurs.size(); i++) {
			Masseur m = masseurs.get(i);
			if (m.isAvailable()) {
				m.setUnavailable();
				currentMasseurId = i;
				return true;
			}
		}
		return false;
	}
	
	public static void main(String[] args) throws FileNotFoundException {
						
		Scanner in = new Scanner(new File(args[0]));
		PrintStream out = new PrintStream(new File(args[1]));
		
		in.useLocale(Locale.US);
		
		int NofPlayers = in.nextInt();
		
		for (int i=0; i<NofPlayers; i++) {
			int id = in.nextInt();
			int skillLevel = in.nextInt();
			Player p = new Player(id, skillLevel, 0);
			players.put(p.getId(), p);
		}

		int NofArrivals = in.nextInt();
		
		for (int i=0; i<NofArrivals; i++) {
			
			String x = in.next();
			
			if (x.equals("t")) {
				int playerId = in.nextInt();
				double startTime = in.nextDouble();
				double duration = in.nextDouble();
				Event t = new Event(playerId, startTime, duration, 0);
				eventQueue.add(t);
			}
			
			else if (x.equals("m")) {
				int playerId = in.nextInt();
				double startTime = in.nextDouble();
				double duration = in.nextDouble();
				Event m = new Event(playerId, startTime, duration, 1);
				eventQueue.add(m);
			}
		}
		
		int NofPhysiotherapists = in.nextInt();
		
		for (int i=0; i<NofPhysiotherapists; i++) {
			double serviceTime = in.nextDouble();
			Physiotherapist pt = new Physiotherapist(0, serviceTime);
			physiotherapists.add(pt);
		}
		
		int NofCoaches = in.nextInt();
		int NofMasseurs = in.nextInt();
		
		for (int i=0; i<NofCoaches; i++) {
			Coach c = new Coach(0); 
			coaches.add(c);
		}
		
		for (int i=0; i<NofMasseurs; i++) {
			Masseur m = new Masseur(0); 
			masseurs.add(m);
		}
		
		//loop begins
		
		while (!eventQueue.isEmpty()) {
			double interval = (eventQueue.peek().getStartTime() - totalTime);
			totalTime += interval;
			
			while (Math.abs(totalTime - (eventQueue.peek().getStartTime())) <= 0.00001 ) {
				Event ee = eventQueue.poll();
				Player p = players.get(ee.getPlayerId());
				if (ee.getType() == 2) {
					p.setEntranceTime(totalTime);
					physiotherapyQueue.add(p);
					coaches.get(ee.getWorkerId()).setAvailable();
				}
				else if (ee.getType() == 4){
					p.setAvailable();
					physiotherapists.get(ee.getWorkerId()).setAvailable();
				}
				else if (ee.getType() == 3) {
					p.setAvailable();
					masseurs.get(ee.getWorkerId()).setAvailable();
				}
				if(p.getAvailability() == 0) {
					p.setCurrentEventDuration(ee.getDuration());
					if (ee.getType() == 0) { 
						p.setPreviousTrainingTime(ee.getDuration());
						p.setEntranceTime(totalTime);
						trainingQueue.add(p);
						p.setUnavailable();
					}
					else if (ee.getType() == 1) {
						if (p.getNofMassagesDone() < 3) {
							p.setEntranceTime(totalTime);
							p.incrementNofMassagesDone();
							massageQueue.add(p);
							p.setUnavailable();
						}
						else
							p.incrementNofInvalidAttempts();
					}
				}
				else {
					if (p.getNofMassagesDone() == 3 && ee.getType() == 1)
						p.incrementNofInvalidAttempts();
					else if(ee.getType() == 0 || ee.getType() == 1)
						p.incrementNofCancelledAttempts();
				}
				if(eventQueue.isEmpty())
					break;
			}
				
			//start checking each queue
			if (!trainingQueue.isEmpty()) {
				if(FindCoach()) {
					Player p = trainingQueue.poll();
					totalTrainingQueueTime += (totalTime - p.getEntranceTime());
					totalTrainingTime += p.getCurrentEventDuration();
					totalTrainingOccurences += 1;
					Event pt = new Event(p.getId(), (totalTime + p.getCurrentEventDuration()), 0, 2);
					pt.setWorkerId(currentCoachId);
					eventQueue.add(pt);
				}
			}

			if (!physiotherapyQueue.isEmpty()) {
				if(FindPhysiotherapist()) {
					Player p = physiotherapyQueue.poll();
					p.setCurrentEventDuration(ActiveTherapistServiceTime);
					totalPhysiotherapyQueueTime += (totalTime - p.getEntranceTime());
					totalPhysiotherapyTime += p.getCurrentEventDuration();
					totalPhysiotherapyOccurences += 1;
					p.incrementTotalWaitingTimeInPhysiotherapyQueue(totalTime - p.getEntranceTime());
					Event qpt = new Event(p.getId(), (totalTime + p.getCurrentEventDuration()), 0, 4);
					qpt.setWorkerId(currentPhysiotherapistId);
					eventQueue.add(qpt);
				}
			}
			
			if (!massageQueue.isEmpty()) {
				if(FindMasseur()) {
					Player p = massageQueue.poll();
					totalMassageQueueTime += (totalTime - p.getEntranceTime());
					totalMassageTime += p.getCurrentEventDuration();
					totalMassageOccurences += 1;
					p.incrementTotalWaitingTimeInMassageQueue(totalTime - p.getEntranceTime());
					Event qm = new Event(p.getId(), (totalTime + p.getCurrentEventDuration()), 0, 3);
					qm.setWorkerId(currentMasseurId);
					eventQueue.add(qm);
				}
			}
			
			if (trainingQueue.size() > maxLengthOfTrainingQueue) {
				maxLengthOfTrainingQueue = trainingQueue.size();
			}
			
			if (physiotherapyQueue.size() > maxLengthOfPhysiotherapyQueue) {
				maxLengthOfPhysiotherapyQueue = physiotherapyQueue.size();
			}
			
			if (massageQueue.size() > maxLengthOfMassageQueue) {
				maxLengthOfMassageQueue = massageQueue.size();
			}
		}
		
		for(int i=0; i<players.size(); i++) {
			Player p = players.get(i);
			if(p.getTotalWaitingTimeInPhysiotherapyQueue() > maxWaitingTimeInPhysiotherapyQueue) {
				maxWaitingTimeInPhysiotherapyQueue = p.getTotalWaitingTimeInPhysiotherapyQueue();
				maxWaitingTimeInPhysiotherapyQueuePlayerId = p.getId();
			}
			if(p.getNofMassagesDone() == 3) {
				threeMassagePlayers.add(p);
			}
			totalNofInvalidAttempts += p.getNofInvalidAttempts();
			totalNofCancelledAttempts += p.getNofCancelledAttempts();
		}
		
		if(!threeMassagePlayers.isEmpty()) {
			minWaitingTimeInMassageQueue = threeMassagePlayers.get(0).getTotalWaitingTimeInMassageQueue();
			minWaitingTimeInMassageQueuePlayerId = threeMassagePlayers.get(0).getId();
		}
		else
			minWaitingTimeInMassageQueue = 0;
		
		for(int i=0; i<threeMassagePlayers.size(); i++) {
			Player p = threeMassagePlayers.get(i);
			if(p.getTotalWaitingTimeInMassageQueue() < minWaitingTimeInMassageQueue) {
				minWaitingTimeInMassageQueue = p.getTotalWaitingTimeInMassageQueue();
				minWaitingTimeInMassageQueuePlayerId = p.getId();
			}
		}
		
		if(totalTrainingOccurences == 0)
			totalTrainingOccurences=1;
		
		if(totalPhysiotherapyOccurences == 0)
			totalPhysiotherapyOccurences=1;
		
		if(totalMassageOccurences == 0)
			totalMassageOccurences=1;
		
		out.println(maxLengthOfTrainingQueue);
		out.println(maxLengthOfPhysiotherapyQueue);
		out.println(maxLengthOfMassageQueue);
		out.printf(Locale.US, "%.3f", totalTrainingQueueTime/totalTrainingOccurences);
		out.println("");
		out.printf(Locale.US, "%.3f", totalPhysiotherapyQueueTime/totalPhysiotherapyOccurences);
		out.println("");
		out.printf(Locale.US, "%.3f", totalMassageQueueTime/totalMassageOccurences);
		out.println("");
		out.printf(Locale.US, "%.3f", totalTrainingTime/totalTrainingOccurences);
		out.println("");
		out.printf(Locale.US, "%.3f", totalPhysiotherapyTime/totalPhysiotherapyOccurences);
		out.println("");
		out.printf(Locale.US, "%.3f", totalMassageTime/totalMassageOccurences);
		out.println("");
		out.printf(Locale.US, "%.3f", (totalTrainingQueueTime + totalPhysiotherapyQueueTime + totalTrainingTime + totalPhysiotherapyTime)/totalTrainingOccurences);
		out.println("");
		out.print(maxWaitingTimeInPhysiotherapyQueuePlayerId);
		out.print(" ");
		out.printf(Locale.US, "%.3f", maxWaitingTimeInPhysiotherapyQueue);
		out.println("");
		if(!threeMassagePlayers.isEmpty()) {
			out.print(minWaitingTimeInMassageQueuePlayerId);
			out.print(" ");
			out.printf(Locale.US, "%.3f", minWaitingTimeInMassageQueue);
			out.println("");
		}else {
			out.println("-1 -1");
		}
		out.println(totalNofInvalidAttempts);
		out.println(totalNofCancelledAttempts);
		out.printf(Locale.US, "%.3f", totalTime);
	}
}

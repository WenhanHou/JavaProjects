import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class Round extends Observable implements Observer {

	public static final int TOTAL_HOLE_NUMBER = 3;
	private long duration;
	private Date start_time;
	private Date end_time;
	private int total_score;
	private boolean is_finished = false;
	private int par_no = 1;
	List<Hole> hole_list = new ArrayList<Hole>(); // hole_list

	Round() {
		Start_Round();
	}
	
	public void update(Observable obj, Object arg) {
		setChanged();
		notifyObservers(arg.toString());
	}

	public void Start_Hole() {
		if (par_no > TOTAL_HOLE_NUMBER) {
			setChanged();
			notifyObservers("All Holes finished!");
		} else if (hole_list.size() == 0 || getCurrentHole().isIs_finished()) {
			Hole hole = new Hole(par_no);
			hole_list.add(hole);
			hole.addObserver(this);
			par_no++;
		} else {
			setChanged();
			notifyObservers("Hole not ended!");
		}
	}

	public void End_Hole() {
		if (hole_list.size() != 0) {
			getCurrentHole().end_Hole();
			getCurrentHole().deleteObserver(this);
		} else {
			setChanged();
			notifyObservers("Hole not Started!");
		}
	}

	public void Start_Strike(String club) {
		if (hole_list.size() != 0) {
			getCurrentHole().Start_Strike(club);
		} else {
			setChanged();
			notifyObservers("Hole not Started!");
		}
	}

	public void End_Strike() {
		if (hole_list.size() != 0) {
			getCurrentHole().End_Strike();
		} else {
			setChanged();
			notifyObservers("Hole not Started!");
		}
	}

	public void Start_Round() {
		if (par_no <= TOTAL_HOLE_NUMBER){
			setStartTime();
			setChanged();
			System.out.println("Round Started");
		}else{
			setChanged();
			notifyObservers("All Holes ended!");
		}
	}

	public void End_Round() {
		if (is_finished) {
			setChanged();
			notifyObservers("Round already ended!");
		} else if (hole_list.size() == TOTAL_HOLE_NUMBER && getCurrentHole().isIs_finished()) {
			setEndTime();
			calculate_duration();
			calculate_score();
			is_finished = true;
			setChanged();
			notifyObservers("Round ended! time:" + duration + "s score:" + total_score);
			try {
				XML_File.create_XMLFile(this, "test.xml");
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else{
			setChanged();
			notifyObservers("Hole not ended!");
		}
	}

	public void calculate_duration() {
		long starttime = start_time.getTime();
		long endtime = end_time.getTime();
		duration = Math.abs(starttime - endtime) / 1000;
	}

	public void calculate_score() {
		for (int i = 0; i < TOTAL_HOLE_NUMBER; i++) {
			total_score += hole_list.get(i).getTotal_score();
		}
	}

	public Hole getCurrentHole() {
		return hole_list.get(hole_list.size() - 1);
	}

	public void setStartTime() {
		start_time = Clock.getTime();
	}

	public void setEndTime() {
		end_time = Clock.getTime();
	}

	// gets sets
	public long getDuration() {
		return duration;
	}

	public void setDuration(long duration) {
		this.duration = duration;
	}

	public Date getStart_time() {
		return start_time;
	}

	public void setStart_time(Date start_time) {
		this.start_time = start_time;
	}

	public Date getEnd_time() {
		return end_time;
	}

	public void setEnd_time(Date end_time) {
		this.end_time = end_time;
	}

	public int getTotal_score() {
		return total_score;
	}

	public void setTotal_score(int total_score) {
		this.total_score = total_score;
	}

	public boolean isIs_finished() {
		return is_finished;
	}

	public void setIs_finished(boolean is_finished) {
		this.is_finished = is_finished;
	}

	public int getPar_no() {
		return par_no;
	}

	public void setPar_no(int par_no) {
		this.par_no = par_no;
	}

	public List<Hole> getHole_list() {
		return hole_list;
	}

	public void setHole_list(List<Hole> hole_list) {
		this.hole_list = hole_list;
	}
}

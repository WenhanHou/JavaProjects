import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class Hole extends Observable implements Observer {
	private int par_no;
	private Date start_time;
	private Date end_time;
	private long duration; // time for a hole
	private int total_score; // score for a hole
	private boolean is_finished = false;
	private List<Strike> strike_list = new ArrayList<Strike>();
	
	public void update(Observable obj, Object arg) {
		setChanged();
		notifyObservers(arg.toString());
	}

	public Hole(int par_no) {
		start_Hole(par_no);
	}

	public void start_Hole(int par_no) {
		this.par_no = par_no;
		setStartTime();
		System.out.println("Start a hole: No." + par_no);
	}

	public void end_Hole() {
		if (is_finished) {
			setChanged();
			notifyObservers("Hole already ended!");
		} else if (strike_list.size() != 0 && getCurrentStrike().isIs_finished()) {
			setEndTime();
			calculate_duration();
			calculate_score();
			is_finished = true;
			System.out.println("Hole ended! time:" + duration);
		} else{
			setChanged();
			notifyObservers("Strike not ended!");
		}
	}

	public void Start_Strike(String club) {
		if (strike_list.size() == 0 || getCurrentStrike().isIs_finished()) {
			Strike strike = new Strike(club);
			strike_list.add(strike);
			strike.addObserver(this);
		} else {
			setChanged();
			notifyObservers("Strike not ended!");
		}
	}

	public void End_Strike() {
		if (strike_list.size()>0) {
			getCurrentStrike().End_Strike();
			//getCurrentStrike().deleteObserver(this);
		} else{
			setChanged();
			notifyObservers("Strike not Started!");
		}
	}

	public void setStartTime() {
		start_time = Clock.getTime();
	}

	// set end time
	public void setEndTime() {
		end_time = Clock.getTime();
	}

	public void calculate_duration() {
		long starttime = start_time.getTime();
		long endtime = end_time.getTime();
		duration = Math.abs(starttime - endtime)/1000;
	}

	public void calculate_score() {
		total_score = strike_list.size();
	}

	private Strike getCurrentStrike() {
		return strike_list.get(strike_list.size() - 1);
	}

	// gets sets
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

	public long getDuration() {
		return duration;
	}

	public void setDuration(long duration) {
		this.duration = duration;
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

	public List<Strike> getStrike_list() {
		return strike_list;
	}

	public void setStrike_list(List<Strike> strike_list) {
		this.strike_list = strike_list;
	}

	public int getPar_no() {
		return par_no;
	}

	public void setPar_no(int par_no) {
		this.par_no = par_no;
	}
}

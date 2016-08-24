import java.util.Observable;

public class Strike extends Observable{

	private GPS start_location = new GPS();
	private GPS end_location = new GPS();
	private String club;
	private float distance;
	private boolean is_finished = false;

	Strike(String club) {
		Start_Strike(club);
	}

	public void Start_Strike(String club) {
		if (club == null)
			club = Club.map.get(1);
		else {
			setStartLocation();
			this.club = club;
		}
		System.out.println("Start a strike:" + club);
	}

	public void End_Strike() {
		if (!is_finished) {
			setEndLocation();
			calculate_distance();
			is_finished = true;
			System.out.println("End a strike");
		}
		else{
			setChanged();
			notifyObservers("Strike already finished!");
		}
	}

	// get start location
	public void setStartLocation() {
		start_location.get_location();
	}

	// get end location
	public void setEndLocation() {
		end_location.get_location();
	}

	public void calculate_distance() {
		setDistance(Math.abs(end_location.getLatitude() - start_location.getLatitude()));
	}

	// gets sets
	public String getClub() {
		return club;
	}

	public float getDistance() {
		return distance;
	}

	public void setDistance(float distance) {
		this.distance = distance;
	}

	public boolean isIs_finished() {
		return is_finished;
	}

	public GPS getStart_location() {
		return start_location;
	}

	public void setStart_location(GPS start_location) {
		this.start_location = start_location;
	}

	public GPS getEnd_location() {
		return end_location;
	}

	public void setEnd_location(GPS end_location) {
		this.end_location = end_location;
	}

	public void setClub(String club) {
		this.club = club;
	}

	public void setIs_finished(boolean is_finished) {
		this.is_finished = is_finished;
	}
}

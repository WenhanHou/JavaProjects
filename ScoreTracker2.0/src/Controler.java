import java.util.Observable;
import java.util.Observer;

public class Controler extends Observable implements Observer {
	Round r = null;

	public void update(Observable obj, Object arg) {
		setChanged();
		notifyObservers(arg.toString());
	}
	public void start_Round() {
		if (r == null){
			r = new Round();
			r.addObserver(this);
		}
		else{
			setChanged();
			notifyObservers("Round Already started!");
		}
	}

	public void start_Hole() {
		if (r != null)
			r.Start_Hole();
		else{
			setChanged();
			notifyObservers("Round not started!");
		}
	}

	public void start_Strike(String club) {
		if (r != null)
			r.Start_Strike(club);
		else{
			setChanged();
			notifyObservers("Round not started!");
		}
	}

	public void end_Round() {
		if (r != null)
			r.End_Round();
		else{
			setChanged();
			notifyObservers("Round not started!");
		}
	}

	public void end_Hole() {
		if (r != null)
			r.End_Hole();
		else{
			setChanged();
			notifyObservers("Round not started!");
		}
	}

	public void end_Strike() {
		if (r != null)
			r.End_Strike();
		else{
			setChanged();
			notifyObservers("Round not started!");
		}
	}
}

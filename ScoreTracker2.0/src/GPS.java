import java.util.*;

public class GPS {

	float latitude;
	float longtitude;
	
	public float getLatitude() {
		return latitude;
	}

	public float getLongtitude() {
		return longtitude;
	}

	// random number as the location
	public void get_location() {
		Random random = new Random();
		latitude = Math.abs(random.nextInt())%10;
		longtitude = Math.abs(random.nextInt())%10;
	}
}

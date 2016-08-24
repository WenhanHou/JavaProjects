
import java.util.*;

public class Club {

    public static Map<Integer, String> map;
//	Map<Integer, String> map = new HashMap<Integer, String> ();
//	map.put(1, "a");
	//Map map = new HashMap();
    static {
    	map = new HashMap<Integer, String>();
    	map.put(1, "Driver");
    	map.put(2, "3-Hood");
    	map.put(3, "3_Hybrid");
    	map.put(4, "4-Hybrid");
    	map.put(5, "Iron4");
    	map.put(6, "Iron5");
    	map.put(7, "Iron6");
    	map.put(8, "Iron7");
    	map.put(9, "Iron8");
    	map.put(10, "Iron9");
    	map.put(11, "Pitching Wedge");
    	map.put(12, "52-degree");
    	map.put(13, "60-degree");
    	map.put(14, "Putter");
    	map = Collections.unmodifiableMap(map);
    }

//    public String getClub(int clubId){
//    	
//    }
}

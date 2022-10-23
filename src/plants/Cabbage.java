package plants;

import graphics.ZooFrame;
import graphics.ZooPanel;
import utilities.MessageUtility;

/**
 * @author baroh
 *
 */
public class Cabbage extends Plant {
	
	static private Cabbage instance=null; 
	private Cabbage(ZooFrame f) {
		super(f);
		MessageUtility.logConstractor("Cabbage", "Cabbage");
	
	}
	/*public Cabbage(ZooFrame f) {
		super(f);
		MessageUtility.logConstractor("Cabbage", "Cabbage");
	}*/
	public static synchronized Cabbage getInstance(ZooFrame f) {
		if(instance==null) {
			instance=new Cabbage(f);
		}
		return instance;
	}
		

}

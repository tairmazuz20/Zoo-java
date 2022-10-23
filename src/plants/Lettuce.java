package plants;

import graphics.ZooFrame;
import graphics.ZooPanel;
import utilities.MessageUtility;

/**
 * @author baroh
 *
 */
public class Lettuce extends Plant {

	static private Lettuce instance=null; 
	private Lettuce(ZooFrame f) {
		super(f);
		MessageUtility.logConstractor("Cabbage", "Cabbage");
	
	}
	/*public Lettuce(ZooFrame f) {
	super(f);
	MessageUtility.logConstractor("Lettuce", "Lettuce");
	}*/
	public static synchronized Lettuce getInstance(ZooFrame f) {
		if(instance==null) {
			instance=new Lettuce(f);
		}
		return instance;
	}
		
}

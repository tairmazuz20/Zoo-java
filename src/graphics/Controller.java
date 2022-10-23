package graphics;

import java.util.Observable;
import java.util.Observer;

import animals.Animal;

 
public class Controller  implements Observer {
  
	ZooPanel zooPanel =  ZooPanel.getInstance(null);
	@Override
	public void update(Observable o, Object arg) {
		 zooPanel.manageZoo();
	}

	 
	 
	

}

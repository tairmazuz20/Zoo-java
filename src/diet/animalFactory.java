package diet;

import animals.Animal;
import graphics.ZooPanel;

public interface animalFactory {
	public Animal getTypeAnimal(ZooPanel panel,String type, int size, String color, int speedH, int speedV);
	public String[] arrAnimal();
}

package diet;

import java.util.Arrays;

import animals.Animal;
import animals.Bear;
import animals.Elephant;
import animals.Giraffe;
import animals.Lion;
import animals.Turtle;
import food.EFoodType;
import food.IEdible;
import graphics.Controller;
import graphics.ZooPanel;
import mobility.Point;
import utilities.MessageUtility;



/**
 * Defines Animals That Eats Both Meat And Vegtables.
 * @version ....
 * @author  Tair Mazuz-211809181, Reut Avitan-211685037 Ashdod Campus
 * @see Bear
 */

public class Omnivore  implements IDiet,animalFactory{
	private Carnivore carn=new Carnivore();
	private Herbivore herb=new Herbivore();

	/**
	 * Default Constructor
	 */
	public Omnivore() {
		
	}

	
	/**
	 * Checks If Food Type Given Is Legal For carnivore To eat.
	 * @parameter EFoodType food- Gets an Enum-EFoodType Parameter that Mentions Any Kind Of Animal's Food Type.
	 * @return If Food Type Is Meat or Vegtable Returns True, Else False.
	 * @see EFoodType
	 */
	@Override
	public boolean canEat(EFoodType food) {
		
		
			if(food==EFoodType.NOTFOOD) {
				
				return false;
			}
			return true;
	}
	
	
	
	/**
	 * Checks if animal can eat given food and returns the weight the animal gained.
	 * @param  animal: animal from type Animal to Access Animal's Weight.
	 * 		   food: food from type IEdible to feed the animal
	 * @return how much weight the animal gained throughout eating.
	 * @see IEdible
	 */

	@Override
	public double eat(Animal animal, IEdible food)
	{

			if(this.carn.canEat(food.getFoodtype())==true) 
			{
				
				return this.carn.eat(animal, food);
			}
			else if(this.herb.canEat(food.getFoodtype())==true) 
			{
				return this.herb.eat(animal, food);
			}
		
		
		return 0;
	}


	@Override
	public Animal getTypeAnimal(ZooPanel panel,String type, int size, String color, int speedH, int speedV) {
		Animal animal = null;
		if (type.equalsIgnoreCase("Bear")) {
			animal= new Bear(size,color,panel,speedH,speedV);
			 
		}
		
		return animal;
	}



	@Override
	public String[] arrAnimal() {
		String arr[]= {"Bear"};
		return arr;
	}
	
	
	
	

	
	
	
}

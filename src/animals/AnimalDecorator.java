package animals;

import java.awt.Graphics;

public abstract class AnimalDecorator implements IColor {

	protected IColor col;
	
	public AnimalDecorator(IColor decoratorAnimal) {
		this.col=decoratorAnimal;
	}
	public void draw(String color) {
		col.draw(color);
	}




}

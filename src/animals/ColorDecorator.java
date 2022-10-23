package animals;

import java.awt.Graphics;

import javax.sound.midi.VoiceStatus;

public class ColorDecorator extends AnimalDecorator {
	
	public ColorDecorator(IColor decoratorAnimal) {
		super(decoratorAnimal);
	}
	public void draw(String color) {
		super.draw(color);
	}
	public void setColor(String color) {
		this.draw(color);
	}
	
	
	

}

package plants;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.security.KeyStore.PrivateKeyEntry;
import java.util.List;

import javax.imageio.ImageIO;

import graphics.Food;
import graphics.ZooFrame;
import graphics.ZooPanel;
import utilities.MessageUtility;

public class Meat extends Plant{
	private ZooPanel zooPanel;

	static private Meat instance=null; 
	private Meat(ZooFrame f) {
		super(f);
		MessageUtility.logConstractor("Cabbage", "Cabbage");
	
	}
	/*	public Meat(ZooFrame f) {
		super(f);
		System.out.println("Created Meat Object");
		
	}*/
	public static synchronized Meat getInstance(ZooFrame f) {
		if(instance==null) {
			instance=new Meat(f);
		}
		return instance;
	}
	
	
	
	
}

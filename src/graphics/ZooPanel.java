package graphics;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import animals.*;
import mobility.Point;
import plants.Meat;
import plants.Plant;

import javax.imageio.ImageIO;
//import javax.sql.PooledConnection;
import javax.swing.*;

import java.util.List;
import java.util.Random;
import java.util.Vector;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import graphics.AddAnimalDialog;

/**
 * Sets Up The Drawing For The Zoo.
 * 
 * @author  Tair Mazuz-211809181, Reut Avitan-211685037 Ashdod Campus
 *
 */
public class ZooPanel extends JPanel implements ActionListener,Runnable
{
	static private ZooPanel instance=null;
	private JButton addAnimal=new JButton("Add Animal");
	private JButton sleep=new JButton("Sleep");
	private JButton wakeup=new JButton("Wake up");
	private JButton clearAll=new JButton("Clear All");
	private JButton food=new JButton("Food");
	private JButton info=new JButton("Info");
	private JButton exit=new JButton("Exit");
	JButton jsave  = new JButton("Save");
    JButton restor  = new JButton("restor");
	private JButton  color=new JButton("Color");
	private static final int mainPanelWidth=2200;
	private static final int mainPanelHeight=667;
	private JPanel buttonsPanel=new JPanel();
	private final static String PICTURE_PATH="C:/Users/vivoy/Desktop/e1.zip_expanded/images/";
	private static List<Animal> animals=new ArrayList<>();
	private static List<Thread> threads=new ArrayList<>();
	private BufferedImage img;
	static JPanel p=new JPanel();
	private Food foodType;
	private ZooFrame frame;
	private Caretaker history  ;
	public static Controller controller = new Controller();
	public final static int THREAD_COUNT=10;
	static int corePoolSize = 10, maxPoolSize = 10, keepAliveTime = 5000;
	public static ExecutorService poolExecutor = new ThreadPoolExecutor(
			corePoolSize, maxPoolSize, keepAliveTime, TimeUnit.MILLISECONDS,
			new LinkedBlockingQueue<Runnable>(5));
	/**
	 * constructor recieves zoo frame in order to have access to the zooframe functionality.
	 * @param f- Of Type ZooFrame - all the options are displayed on the zooframe.
	 */
	 private ZooPanel(ZooFrame f)
	 
	{
		super();
		frame=f;
		this.history= new Caretaker( this);
		addAnimal.addActionListener(this);
		sleep.addActionListener(this);
		wakeup.addActionListener(this);
		clearAll.addActionListener(this);
		food.addActionListener(this);
		info.addActionListener(this);
		exit.addActionListener(this);
		color.addActionListener(this);
		restor.addActionListener(this);
		jsave.addActionListener(this);
		
		this.designButton(addAnimal);
		this.designButton(sleep);
		this.designButton(wakeup);
		this.designButton(clearAll);
		this.designButton(food);
		this.designButton(info);
		this.designButton(exit);
		this.designButton(color);
		this.designButton(restor);
		this.designButton(jsave);
		
		this.setLayout(new BorderLayout());
		this.setOpaque(true);
		this.setButtonPanel(buttonsPanel);
		this.setBounds(0,40,mainPanelWidth,mainPanelHeight);
		this.setPreferredSize(getPreferredSize());
		this.setVisible(true);
		//this.controller=new Thread(this);
		//controller.start();
		
	}
	
	/**
	 * set up the buttons on a panel
	 * @param buttonsPanel -recieves JButton object and adds it to the displayButton panel
	 */
	private void setButtonPanel(JPanel buttonsPanel)
	{
		buttonsPanel.setSize(new Dimension(2200,70));
		buttonsPanel.add(addAnimal);
		buttonsPanel.add(sleep);
		buttonsPanel.add(wakeup);
		buttonsPanel.add(clearAll);
		buttonsPanel.add(food);
		buttonsPanel.add(info);
		buttonsPanel.add(color);
		buttonsPanel.add(jsave);
		buttonsPanel.add(restor);
		buttonsPanel.add(exit);
		 
		
		this.add(buttonsPanel,BorderLayout.SOUTH);
		
	}
	/**
	 * Clears All Animals And Plants Thats Displayed On The Screen.
	 */
	private void clearAll()
	{
		for(int i=0;i<getAnimalList().size();++i)
		{
			animals.removeAll(animals);
	
		}
		for(int i=0;i<Food.getFood().size();++i)
		{
			Food.getFood().removeAll(Food.getFood());
		}
		this.food.setEnabled(true);
		frame.resize(new Dimension(2201,771));
		 frame.resize(new Dimension(2200,667));
		//frame.resize(mainPanelWidth, mainPanelHeight+1);
		repaint();
	}

	/*
	 * overriding action listener and adding events to the buttons
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==addAnimal)
		{
			AddAnimalDialog d=new AddAnimalDialog(frame,this);
			repaint();
			
		}
		if(e.getSource()==sleep)
		{
			System.out.println("Sleep");
			for(int i=0;i<animals.size();i++) {
				animals.get(i).setSuspended();
			}
		}
		if(e.getSource()==wakeup)
		{
			System.out.println("wake up");
			for(int i=0;i<animals.size();i++) {
				animals.get(i).setResumed();
			}
		}
		if(e.getSource()==clearAll)
		{
			poolExecutor.shutdownNow();
			poolExecutor = new ThreadPoolExecutor(corePoolSize, maxPoolSize, keepAliveTime, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>(5));
			for(int i=0;i<animals.size();i++) {
				animals.get(i).setThreadDead();
			}
			clearAll();
			repaint();
		}
		if(e.getSource()==info)
		{
			new Info(frame);
		}
		if(e.getSource()==exit)
		{
			poolExecutor.shutdownNow();
			/*for(int i=0;i<animals.size();i++) {
				animals.get(i).setThreadDead();
			}
			controller.stop();*/
			System.exit(0);
		}
		if(e.getSource()==food)
		{
			this.foodType= new Food(frame);
			 
		}
		if(e.getSource()== color)
		{
			  new ColorChange(); 
			 
		}
		if (e.getSource()== jsave) 
		{
			saveStatus();
		}
		if (e.getSource()== restor)
		{
			restor();
			 repaint();
			 
			
		}
	}
	
	/*
	 * designs the buttons
	 */
	private void designButton(JButton button) {
		button.setSize(250,250);
		button.setFont(new Font(Font.MONOSPACED,Font.BOLD,25));
		
	}
	/**
	 * appends an animal to the animals list
	 * @param animal- Object type animal to add to the list
	 */
	public static void  setAnimalList(Animal animal)
	{
		
			poolExecutor.execute(animal);
			animals.add(animal);
			animal.addObserver(controller);
	
	}
	/**
	 * returns the animals list
	 * @return-List of type animal
	 */
	public static  List<Animal> getAnimalList()
	{	
		return animals;
	}
	/**
	 * overriding JPanel paintComponent method in order to customize panel drawings.
	 */
	@Override
	public void paintComponent(Graphics g) {

		 super.paintComponent(g) ;
		 Graphics2D g2d=(Graphics2D) g;
	 
		 if(img!=null)
			 g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			 g.drawImage(img,0,0,mainPanelWidth,mainPanelHeight, this);
			 if(getAnimalList().size()>0)
			 { 
				 for(int i=0;i<getAnimalList().size();++i)
				 { 
					 
					if( getAnimalList().get(i).getThreadDead()==false) {
						 getAnimalList().get(i).loadImages(getAnimalList().get(i).getColor());
						 getAnimalList().get(i).drawObject(g2d);
					}
				 }
			 }
			 if(foodType!=null)
			 {
				 for (int i=0;i<Food.getFood().size();++i)
				 {
					 Food.getFood().get(i).loadImages(foodType.getFoodName());
					 Food.getFood().get(i).drawObject(g2d);
					 
				 }
			 }
			 
			
		}
	
	/**
	 * loads an image 
	 * @param path-String , the picture's name.
	 */
	public  void loadImage(String path)
	{
		try {
			this.img=ImageIO.read(new File(PICTURE_PATH+path));
		} catch (IOException e) {
			
			System.out.println("Cant Load Image.");
		}
		
		
	}
	/**
	 * checks eating conditions and returns true if they returns true.
	 * @return Boolean-true if condition is valid.
	 */
	public boolean eatPlants()
	{
		Plant plantFood;
		Animal animal;
		for(int i=0;i<Food.getFood().size();++i)
		{
			for(int j=0;j<getAnimalList().size();++j)
			{
				plantFood=Food.getFood().get(i);
				animal=getAnimalList().get(j);
				if(animal.getDiet().canEat(plantFood.getFoodtype()))
				{
					synchronized (animal) {
						if(animal.getLocation().getX()>plantFood.getLocation().getX()) {
							animal.setX_dir(-1);
						}
						else if(animal.getLocation().getX()<plantFood.getLocation().getX()) {
							animal.setX_dir(1);
						}
						if(animal.getLocation().getY()+10>plantFood.getLocation().getY()) {
							animal.setY_dir(-1);
						}
						else if(animal.getLocation().getY()<plantFood.getLocation().getY()) {
							animal.setY_dir(1);
						}


					}
						if(animal.calcDistance(plantFood.getLocation())<=10) {
						animal.setSuspended();
						animal.move(plantFood.getLocation());
						animal.setResumed();
						animal.eat(plantFood);
						Food.getFood().remove(i);
						 JOptionPane.showMessageDialog(this, animal.getAnimalName()+" Ate :"+plantFood.getFoodtype());
						 return true;
						}
				}
			}
			
		}
		return false;
	}
	
	/**
	 * checks if animal can eat another animal in the zoo according to certain criterias.
	 * @return Boolean- true if conditions are valid.
	 */
	private boolean tryEatAnotherAnimal()
	{
		Animal predator,prey;
		Plant food;
		for(int i=0;i<getAnimalList().size();++i)
		{
			for(int j=0; j<getAnimalList().size();++j)
			{
					predator=getAnimalList().get(i);
					 prey=getAnimalList().get(j);
					 if((predator.getDiet().canEat(prey.getFoodtype())&&predator.getWeight()>=2*prey.getWeight() &&
						predator.calcDistance(prey.getLocation())<prey.getSize() && predator.calcDistance(prey.getLocation())<prey.getSize())) 
					 {
						 predator.eat(prey);
						 System.out.println("preditor :"+predator.toString2()+"Prey: "+prey.toString2());
						 getAnimalList().remove(j);
					
						 JOptionPane.showMessageDialog(this, predator.getAnimalName()+" Ate :"+prey.getAnimalName());
						 frame.resize(new Dimension(2201,771));
						 frame.resize(new Dimension(2200,667));
						 return true;
						 
					 }
			}
		}
		return false;
	}
	/**
	 * controller of the program responsible of repainting the zoo frame in any moment.
	 */
	public void manageZoo()
	{
		Animal predator,prey;
		Food food;
		if(eatPlants())
		{
			repaint();
		}
		if(tryEatAnotherAnimal())
		{
			 repaint();	
		}	
}
	
	/**
	 * returns current panel
	 * @return
	 */
	public static JPanel getP() {
		return p;
	}
	/**
	 * sets the move buttons state
	 * @param state- boolean-if state is true ,user can click on the move button,else the oppisite.
	 */

	public JPanel getButtonsPanel()
	{
			return this.buttonsPanel;
	}
    @Override
    public void run() {
    	while(true) {
    		try {
    			Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		manageZoo();
    	}
    
    	
    }

	public static ZooPanel getInstance(ZooFrame f) {
		if(instance==null) {
			instance= new ZooPanel(f);
		}
		return instance;
	}
	private static class originator implements Memento {
		private ArrayList<Animal> animals;
		public originator(ArrayList<Animal> animalArrayList) { this.animals =  animals;}
		public ArrayList<Animal> getAnimalArrayList() { return this.animals; }
	}
    public Memento save() {
        return new originator(cloneAnimalList());
    }
	public void restore(Memento state) {
		poolExecutor.shutdownNow();
		poolExecutor = new ThreadPoolExecutor(corePoolSize, maxPoolSize, keepAliveTime, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>(5));
		this.animals = ((originator)state).getAnimalArrayList();
		
		for(Animal animal : this.animals)
		{
				poolExecutor.execute(animal);
		}
		repaint();
	}
	private ArrayList<Animal> cloneAnimalList()
	{
		ArrayList<Animal> clonedList = new ArrayList<>();
		for (Animal animal : this.animals)
		{
	
			try {
				Animal clonedAnimal = (Animal)animal.clone();
				clonedAnimal.setSuspended();
				clonedList.add(clonedAnimal);
			} catch (CloneNotSupportedException ignored) {
				
			}
		}
		return clonedList;
	}
     
    public void notifyEmptyHistory()
    {
        JOptionPane.showMessageDialog(null, "No save to restore");
    }
 
    public void notifyFullHistory()
    {
        JOptionPane.showMessageDialog(null, "Can not add more than 3 states ");
    }
    public void saveStatus()
    {
        history.hitSave();
    }
     
    public void restor()
    {
        history.hitUndo();
    }
 
	 
}
  

	

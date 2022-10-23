package graphics;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import animals.Animal;
import animals.Bear;
import animals.Elephant;
import animals.Giraffe;
import animals.Lion;
import animals.Turtle;
import diet.animalFactory;
import diet.attributeFactory;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTabbedPane;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.security.PublicKey;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Color;

public class AddAnimalDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private Animal animal ;
	private int index,size,speedH,speedV;
	private String col, name ;
	Boolean flag1=false,flag2=false,flag3=false;
	JComboBox comboBox = new JComboBox();
	private JTextField textField;
	private JTextField textField_1;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JTextField textField_2;
	private ZooFrame zFrame;
	private ZooPanel panel;
	private ZooPanel zooPanel;
	private animalFactory factorAnimal;
	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the dialog.
	 */
	public Animal getAnimal() {
		return animal;
	}
	

	public AddAnimalDialog(ZooFrame zf,ZooPanel pan) {
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.setVisible(true);
		this.zFrame=zf;
		this.zooPanel=ZooPanel.getInstance(zFrame);
		setBounds(450, 150, 450, 300);
		this.panel=pan;
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(255, 245, 238));
		 
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		
		JComboBox comboBox_2 = new JComboBox();
	
		comboBox_2.setModel(new DefaultComboBoxModel(new String[] {"Carnivore", "Omnivore", "Herbivore"}));
		comboBox_2.setToolTipText("");
		comboBox_2.setBounds(188, 11, 96, 22);
		
		contentPanel.add(comboBox_2);
		comboBox.setEnabled(true);
		comboBox.setBounds(188, 53, 96, 22);
		comboBox.setToolTipText(" \n");
		  factorAnimal=attributeFactory.createFactory(comboBox_2.getSelectedItem().toString());
		comboBox.setModel(new DefaultComboBoxModel(factorAnimal.arrAnimal()));	
		comboBox_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 factorAnimal=attributeFactory.createFactory(comboBox_2.getSelectedItem().toString());
				comboBox.setModel(new DefaultComboBoxModel(factorAnimal.arrAnimal()));	
			}
		});
		JLabel lblNewLabel_2 = new JLabel("Insert size");
		lblNewLabel_2.setFont(new Font("SimSun-ExtB", Font.BOLD, 11));
		lblNewLabel_2.setBounds(55, 95, 100, 22);
		lblNewLabel_2.addKeyListener((KeyListener) new KeyAdapter() {
	         public void keyTyped(KeyEvent e) {
	             char c = e.getKeyChar();
	             if ( ((c < '0') || (c > '9')) && (c != KeyEvent.VK_BACK_SPACE)) {
	                  e.consume();  // if it's not a number, ignore the event
	             }   
	         }
		});
		textField = new JTextField();
		textField.setBounds(188, 95, 96, 22);
		textField.setColumns(10);
		textField.addKeyListener((KeyListener) new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if ( ((c < '0') || (c > '9')) && (c != KeyEvent.VK_BACK_SPACE)) {
					e.consume();  // if it's not a number, ignore the event
				}
		    }
		         
		});
			
		textField.addFocusListener(new FocusAdapter() {
			    public void focusLost(FocusEvent e) {
			  
						size = Integer.parseInt(textField.getText());
						 if (size<50||size>300)
						 	  {
					    	   JOptionPane.showMessageDialog(null, " ERORR \nEnter number between 50-300\n");
					   	      }   
						 else
							 flag1=true;
			    }
		});
	
			
		lblNewLabel = new JLabel("Insert horizontal speed");
		lblNewLabel.setFont(new Font("SimSun-ExtB", Font.BOLD, 11));
		lblNewLabel.setBounds(20, 145, 178, 14);
		lblNewLabel.setVerticalAlignment(SwingConstants.BOTTOM);
		

		textField_1 = new JTextField();
		textField_1.setBounds(188, 142, 96, 22);
		textField_1.setColumns(10);
		
		textField_1.addKeyListener((KeyListener) new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if ( ((c < '0') || (c > '9')) && (c != KeyEvent.VK_BACK_SPACE)) {
					e.consume();  // if it's not a number, ignore the event
				}
		    }
		         
		});
			
		textField_1.addFocusListener(new FocusAdapter() {
			    public void focusLost(FocusEvent e) {
			  
						speedH = Integer.parseInt(textField_1.getText());
						 if (speedH>10)
						 	  {
					    	   JOptionPane.showMessageDialog(null, " ERORR \n Enter number between 1-10\n");
					   	      }
						 else 
							 flag2=true;
			    }
		});
		//JOptionPane.showMessageDialog(null,textField.getText() );
		
		lblNewLabel_1 = new JLabel("Insert vertical speed");
		lblNewLabel_1.setFont(new Font("SimSun-ExtB", Font.BOLD, 11));
		lblNewLabel_1.setBounds(30, 179, 140, 14);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(188, 175, 96, 22);
		
		textField_2.addKeyListener((KeyListener) new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if ( ((c < '0') || (c > '9')) && (c != KeyEvent.VK_BACK_SPACE)) {
					e.consume();  // if it's not a number, ignore the event
				}
		    }
		         
		});
			
		textField_2.addFocusListener(new FocusAdapter() {
			    public void focusLost(FocusEvent e) {
			  
						speedV = Integer.parseInt(textField_2.getText());
						 if (speedV>10)
						 	  {
					    	   JOptionPane.showMessageDialog(null, " ERORR\n Enter number between 1-10\n  ");
					   	      }  
						 else {
							flag3=true;
						}
			    }
		});
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setForeground(new Color(255, 250, 240));
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"Natural", "Red", "Blue"}));
		comboBox_1.setBounds(188, 208, 96, 22);
	
		contentPanel.setLayout(null);
		contentPanel.add(comboBox);
		contentPanel.add(lblNewLabel);
		contentPanel.add(textField);
		contentPanel.add(lblNewLabel_1);
		contentPanel.add(textField_1);
		contentPanel.add(textField_2);
		contentPanel.add(lblNewLabel_2);
		contentPanel.add(comboBox_1);
		
		JLabel lblNewLabel_3 = new JLabel("Animal type");
		lblNewLabel_3.setFont(new Font("SimSun-ExtB", Font.BOLD, 11));
		lblNewLabel_3.setBounds(55, 11, 88, 22);
		contentPanel.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel(" kind animal");
		lblNewLabel_4.setFont(new Font("SimSun-ExtB", Font.BOLD, 11));
		lblNewLabel_4.setBounds(50, 57, 88, 14);
		contentPanel.add(lblNewLabel_4);
		
	
		JPanel buttonPane = new JPanel();
		buttonPane.setForeground(new Color(255, 245, 238));
		buttonPane.setBackground(new Color(255, 245, 238));
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);
	
		JButton okButton = new JButton("Add");
		
		okButton.addActionListener(new ActionListener() {
		
			public void actionPerformed(ActionEvent e) {
				if(flag3==true&&flag3==true&&flag3==true) {		
				index= comboBox.getSelectedIndex();
				name= comboBox.getSelectedItem().toString();
				col=comboBox_1.getSelectedItem().toString();
				animal=factorAnimal.getTypeAnimal(pan, name, size, col, speedH, speedV);
				zooPanel.setAnimalList(animal);
				zFrame.resize(new Dimension(2204,774));
				zFrame.resize(new Dimension(2200,667));
						}
				else
					JOptionPane.showMessageDialog(null, " ERORR\n Enter Input for all fields\n  ");
				
			}
					});
			
			okButton.setActionCommand("Add");
			buttonPane.add(okButton);
			getRootPane().setDefaultButton(okButton);
			
			JButton cancelButton = new JButton("Cancel");
			cancelButton.setActionCommand("Cancel");
			buttonPane.add(cancelButton);
			
		
		
	}
}

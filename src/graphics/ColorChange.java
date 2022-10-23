package graphics;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import animals.Animal;
import animals.ColorDecorator;
import animals.IColor;

import javax.swing.JComboBox;
import javax.swing.JRadioButton;
import javax.swing.JToggleButton;
import javax.swing.JSlider;
import javax.swing.JScrollBar;
import javax.swing.JTree;
import javax.swing.JFormattedTextField;
import javax.swing.JProgressBar;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JCheckBoxMenuItem;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ColorChange extends JDialog {

	private final JPanel contentPanel = new JPanel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ColorChange dialog = new ColorChange();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ColorChange() {
		setBounds(100, 100, 183, 225);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		List< String> animalsList =new ArrayList<>();
		for (int i = 0; i < ZooPanel.getAnimalList().size(); i++) {
			animalsList.add(ZooPanel.getAnimalList().get(i).getClass().getSimpleName());
		}
		contentPanel.setLayout(null);
		

			JComboBox comboBox = new JComboBox( animalsList.toArray());
			comboBox.setBounds(31, 22, 70, 22);
			contentPanel.add(comboBox);
			 
			

		
		
		JRadioButton rdbtnNewRadioButton = new JRadioButton("Natural");
		rdbtnNewRadioButton.setActionCommand("Natural");
		rdbtnNewRadioButton.setBounds(31, 64, 111, 23);
		contentPanel.add(rdbtnNewRadioButton);
		
		JRadioButton rdbtnNewRadioButton_1 = new JRadioButton("Red");
		rdbtnNewRadioButton_1.setActionCommand("Red");
		rdbtnNewRadioButton_1.setBounds(31, 90, 111, 23);
		contentPanel.add(rdbtnNewRadioButton_1);
		
		JRadioButton rdbtnNewRadioButton_2 = new JRadioButton("Blue");
		rdbtnNewRadioButton_2.setActionCommand("Blue");
		rdbtnNewRadioButton_2.setBounds(31, 116, 111, 23);
		ButtonGroup group=  new ButtonGroup();
		group.add(rdbtnNewRadioButton_2);
		group.add(rdbtnNewRadioButton_1);
		group.add(rdbtnNewRadioButton);
		contentPanel.add(rdbtnNewRadioButton_2);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Change");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						IColor animalCol=new ColorDecorator(ZooPanel.getAnimalList().get(comboBox.getSelectedIndex()));
						((ColorDecorator) animalCol).setColor(group.getSelection().getActionCommand());
						
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		this.setVisible( true);
	}
}

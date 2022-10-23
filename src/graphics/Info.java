package graphics;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import animals.Animal;
import plants.Cabbage;

public class Info{

	private JTable jt;
	DefaultTableModel tableModel=new DefaultTableModel();
	private int total=0;
	private ZooPanel zoopanel;
	public Info(ZooFrame frame) 
	{
		
		zoopanel=ZooPanel.getInstance(frame);
		this.initTable();
		JPanel panel=new JPanel();
		panel.setSize(500,500);
		panel.setBackground(Color.BLACK);
		jt.setFocusable(false);
		panel.add(jt);
        jt.setRowSelectionAllowed(false);
		JDialog d=new JDialog();
		d.setSize(500,500);
		d.add(panel);
		d.setVisible(true);
		JScrollPane js=new JScrollPane(jt);
		d.add(js);
		
	}
	public Info(String s)
	{
		this.initTable();
	}
	public  JTable getTable()
	{
		return jt;
	}
	public void initTable()
	{
		jt=new JTable(tableModel);
		jt.setFont(new Font(Font.MONOSPACED,Font.BOLD,15));
	     jt.setEnabled(false);
	     jt.setGridColor(Color.black);
	     jt.setRowHeight(50);
	     jt.getTableHeader().setFont(new Font(Font.MONOSPACED, Font.BOLD, 12));
		tableModel.addColumn("Animal");
		tableModel.addColumn("Color");
		tableModel.addColumn("Weight");
		tableModel.addColumn("Hor Speed");
		tableModel.addColumn("Ver SPeed");
		tableModel.addColumn("Eat Count");
		
		
		for(int i=0;i<zoopanel.getAnimalList().size();++i)
		{
			tableModel.addRow(new Object[] {zoopanel.getAnimalList().get(i).getClass().getSimpleName(),
					zoopanel.getAnimalList().get(i).getColor(),zoopanel.getAnimalList().get(i).getWeight(),zoopanel.getAnimalList().get(i).getHorSpeed(),zoopanel.getAnimalList().get(i).getVerSpeed(),zoopanel.getAnimalList().get(i).getEatCount()});
		
			total+=zoopanel.getAnimalList().get(i).getEatCount();
		}
		tableModel.addRow(new Object[] {"Total","","","","",total});

		jt.setFillsViewportHeight(true);
		jt.setPreferredScrollableViewportSize(new Dimension(450,63));
		
	}

}

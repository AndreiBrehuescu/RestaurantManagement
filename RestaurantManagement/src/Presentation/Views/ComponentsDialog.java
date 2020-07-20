package Presentation.Views;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import BLL.MenuItem;
import Presentation.Controllers.RestaurantController;

public class ComponentsDialog extends JFrame{
	
	private static JButton doneButton;
	private static JButton addButton;

	private static JLabel addComponentLabel;
	
	private static JScrollPane componentScrollPane;
	private static JTable componentTable;
	
	private static JPanel contentPane;
	
	protected static HashSet<MenuItem> componentsOfItem ;
	
	public ComponentsDialog(JScrollPane menuSP, JTable menuTable) {
		componentsOfItem = new HashSet<MenuItem>();
		
		this.setTitle("Add components for a composite product");
		this.setSize(Constants.Frames.WIDTH, Constants.Frames.HEIGHT);
        this.setLayout(null);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setResizable(false);
        
        addComponentLabel = new JLabel("Select components and click add");
        addComponentLabel.setFont(Constants.Fonts.LabelsFont);
        addComponentLabel.setHorizontalAlignment(JLabel.LEFT);
        addComponentLabel.setVerticalAlignment(JLabel.CENTER);
        addComponentLabel.setBounds(225, 50, 600, 50);
        this.add(addComponentLabel);
        
        contentPane = new JPanel();
        contentPane.setBackground(Constants.Colors.BackgroundColor);
        contentPane.setBounds(0, 0, Constants.Frames.WIDTH, Constants.Frames.HEIGHT);
        contentPane.setLayout(null);
        this.add(contentPane);
        
        componentScrollPane = menuSP;
        componentScrollPane.setBounds(90, 125, 735, 350);
        contentPane.add(componentScrollPane);
        
        componentTable = menuTable;
        componentScrollPane.setViewportView(componentTable);
        componentScrollPane.setVisible(true);
        
        doneButton = new JButton("Done");
        doneButton.setFont(Constants.Fonts.ButtonsFont);
        doneButton.setBackground(Constants.Colors.ButtonsColor);
        doneButton.setVisible(true);
        doneButton.setBounds(450, 500, 100, 50);
        contentPane.add(doneButton);
        doneButton.addActionListener(e -> {

			this.dispose();
			RestaurantController.deleteTable();
			RestaurantController.displayTable();
        	
        });
        
        
        addButton = new JButton("Add");
        addButton.setFont(Constants.Fonts.ButtonsFont);
        addButton.setBackground(Constants.Colors.ButtonsColor);
        addButton.setVisible(true);
        addButton.setBounds(335, 500, 100, 50);
        contentPane.add(addButton);
        
        this.setVisible(true);
     
	}

	public static JScrollPane getComponentScrollPane() {
		return componentScrollPane;
	}

	public static void setComponentScrollPane(JScrollPane componentScrollPane) {
		ComponentsDialog.componentScrollPane = componentScrollPane;
	}

	public static JTable getComponentTable() {
		return componentTable;
	}

	public static void setComponentTable(JTable componentTable) {
		ComponentsDialog.componentTable = componentTable;
	}
	
	public void setAddButtonActionListener(ActionListener actionListener) {
		addButton.addActionListener(actionListener);
	}
	
	public void setDoneButtonActionListener(ActionListener actionListener) {
		doneButton.addActionListener(actionListener);
	}

	public static HashSet<MenuItem> getComponentsOfItem() {
		return componentsOfItem;
	}
	
	
}

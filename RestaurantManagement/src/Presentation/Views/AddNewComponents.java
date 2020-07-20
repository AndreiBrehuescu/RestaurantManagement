package Presentation.Views;

import java.awt.event.ActionListener;
import java.util.HashSet;

import javax.swing.*;

import BLL.MenuItem;
import Presentation.Controllers.RestaurantController;
@SuppressWarnings("serial")
public class AddNewComponents extends JFrame{
	
	protected static JPanel contentPanel;
	
	protected static JScrollPane menuTablePane;
	protected static JTable menuTable;
	
	private JButton confirmButton;
	private JButton cancelButton;
	
	private static HashSet<MenuItem> orderedProducts;
	private static MenuItem product;
	
	public AddNewComponents(MenuItem productComp) {
		orderedProducts = new HashSet<MenuItem>();
		product = productComp;
		
		this.setTitle("Create new order!");
		this.setSize(800, 500);
		this.setLayout(null);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		contentPanel = new JPanel();
		contentPanel.setBackground(Constants.Colors.BackgroundColor);
		contentPanel.setBounds(0, 0, 800, 500);
		contentPanel.setVisible(true);
		contentPanel.setLayout(null);
		this.add(contentPanel);	
		
		
		confirmButton = new JButton("Confirm");
		confirmButton.setFont(Constants.Fonts.ButtonsFont);
		confirmButton.setBackground(Constants.Colors.ButtonsColor);
		confirmButton.setBounds(250, 415, 100, 25);
		contentPanel.add(confirmButton);
		confirmButton.addActionListener(e ->{
			RestaurantController.deleteTable();
			RestaurantController.displayTable();
			this.dispose();
		});
		
		cancelButton = new JButton("Cancel");
		cancelButton.setFont(Constants.Fonts.ButtonsFont);
		cancelButton.setBackground(Constants.Colors.ButtonsColor);
		cancelButton.setBounds(375,415,100,25);
		contentPanel.add(cancelButton);
		cancelButton.addActionListener(e ->{
			this.dispose();
		});
		

		menuTablePane = new JScrollPane();
		menuTablePane.setBounds(85, 50, 600, 350);
		contentPanel.add(menuTablePane);
		
		menuTable = new JTable();
		menuTablePane.setViewportView(menuTable);
		menuTablePane.setVisible(true);
		
		String tableHeader[]= {"Name", "Price"};
		String[][] data = {};
   	 
		if( RestaurantController.restaurant.getMenuItems() != null ) {
       	 data = new String[RestaurantController.restaurant.getMenuItems().size()][tableHeader.length];
       	 int j = 0;
       	 
       	 for (MenuItem item : RestaurantController.restaurant.getMenuItems()) {
				data[j][0] = item.getName();
				data[j][1] = Float.toString(item.getPrice());
				j++;
			}
        }
        
        menuTable = new JTable(data, tableHeader);
		menuTablePane.setViewportView(menuTable);
		menuTablePane.setVisible(true);

		this.setVisible(true);
	}

	public static JTable getMenuTable() {
		return menuTable;
	}

	public static HashSet<MenuItem> getOrderedProducts() {
		return orderedProducts;
	}	
	
	public void setConfirmButtonActionListener(ActionListener actionListener) {
		confirmButton.addActionListener(actionListener);
	}

	public static MenuItem getProduct() {
		return product;
	}

	public static void setMenuTable(JTable menuTable) {
		AddNewComponents.menuTable = menuTable;
	}

	public static JScrollPane getMenuTablePane() {
		return menuTablePane;
	}

	public static void setMenuTablePane(JScrollPane menuTablePane) {
		AddNewComponents.menuTablePane = menuTablePane;
	}

	public static JPanel getContentPanel() {
		return contentPanel;
	}
	
	
	
}

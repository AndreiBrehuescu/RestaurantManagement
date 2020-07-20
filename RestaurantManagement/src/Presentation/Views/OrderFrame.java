package Presentation.Views;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashSet;

import javax.swing.*;

import BLL.MenuItem;
import Presentation.Controllers.RestaurantController;
public class OrderFrame extends JFrame{

	protected static JPanel contentPanel;
	
	private JLabel createOrderLabel;
	private JLabel tableNumberLabel;
	private JLabel addProductsLabel;
	
	private JButton addButton;
	
	private JTextField tableNumberTextField;
	
	protected static JScrollPane menuTablePane;
	protected static JTable menuTable;
	
	private JButton confirmButton;
	private JButton cancelButton;
	
	private static ArrayList<MenuItem> orderedProducts;
	
	public OrderFrame() {
		orderedProducts = new ArrayList<MenuItem>();
		
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
		
		tableNumberLabel = new JLabel("Table number:");
		tableNumberLabel.setFont(new Font("Times New Roman",Font.ITALIC, 16));
		tableNumberLabel.setForeground(Constants.Colors.BLACK);
		tableNumberLabel.setHorizontalAlignment(JLabel.CENTER);
		tableNumberLabel.setVerticalAlignment(JLabel.VERTICAL);
		tableNumberLabel.setBounds(5, 25, 100, 50);
		tableNumberLabel.setVisible(true);
		contentPanel.add(tableNumberLabel);

		tableNumberTextField = new JTextField();
		tableNumberTextField.setHorizontalAlignment(JLabel.CENTER);
		tableNumberTextField.setText("");
		tableNumberTextField.setBounds(125, 22, 100, 25);
		contentPanel.add(tableNumberTextField);
		
		confirmButton = new JButton("Confirm");
		confirmButton.setFont(Constants.Fonts.ButtonsFont);
		confirmButton.setBackground(Constants.Colors.ButtonsColor);
		confirmButton.setBounds(5, 50, 100, 25);
		contentPanel.add(confirmButton);
		confirmButton.addActionListener(e ->{
			if( tableNumberTextField.getText().isEmpty() ) {
				JOptionPane.showConfirmDialog(null, "Please insert the table!","Warning",JOptionPane.DEFAULT_OPTION,JOptionPane.PLAIN_MESSAGE);
				return;
			}
			
			if ( orderedProducts.size() == 0 ) {
				JOptionPane.showConfirmDialog(null, "Please insert products!","Warning",JOptionPane.DEFAULT_OPTION,JOptionPane.PLAIN_MESSAGE);
				return;
			}
			
			RestaurantController.restaurant.createOrder(Integer.parseInt(tableNumberTextField.getText()), orderedProducts);
			RestaurantController.deleteTableOrder();
			RestaurantController.displayTableOrder();
			this.dispose();
		});
		
		cancelButton = new JButton("Cancel");
		cancelButton.setFont(Constants.Fonts.ButtonsFont);
		cancelButton.setBackground(Constants.Colors.ButtonsColor);
		cancelButton.setBounds(125,50,100,25);
		contentPanel.add(cancelButton);
		cancelButton.addActionListener(e ->{
			RestaurantController.deleteTableOrder();
			RestaurantController.displayTableOrder();
			this.dispose();
		});
		
		createOrderLabel = new JLabel("Select the products");
		createOrderLabel.setFont(Constants.Fonts.LabelsFont);
		createOrderLabel.setHorizontalAlignment(JLabel.CENTER);
		createOrderLabel.setVerticalAlignment(JLabel.CENTER);
		createOrderLabel.setBounds( 300, 30, 400, 50);
		contentPanel.add(createOrderLabel);
		
		addButton = new JButton("ADD");
		addButton.setFont(Constants.Fonts.ButtonsFont);
		addButton.setBackground(Constants.Colors.ButtonsColor);
		addButton.setBounds(50, 200, 120, 40);
		contentPanel.add(addButton);
		
		addProductsLabel = new JLabel("Add Selected products to order");
		addProductsLabel.setVerticalAlignment(JLabel.CENTER);
		addProductsLabel.setHorizontalAlignment(JLabel.CENTER);
		addProductsLabel.setFont(new Font("Times New Roman", Font.ITALIC, 14));
		addProductsLabel.setBackground(Constants.Colors.BackgroundColor);
		addProductsLabel.setBounds(25, 150, 175, 70);
		contentPanel.add(addProductsLabel);
		
		menuTablePane = new JScrollPane();
		menuTablePane.setBounds(275, 100, 500, 350);
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

	public static ArrayList<MenuItem> getOrderedProducts() {
		return orderedProducts;
	}	
	
	public void addButtonActionListener(ActionListener actionListener) {
		addButton.addActionListener(actionListener);
	}
}

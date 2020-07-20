package Presentation.Views;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import BLL.CompositeProduct;
import BLL.MenuItem;
import Presentation.Controllers.EditExistingCompositeController;
import Presentation.Controllers.RestaurantController;
import Run.MainClass;

@SuppressWarnings("serial")
public class EditCompositeDialog extends JFrame{
	
	private static JPanel contentPanel;
	private static JPanel toolPanel;
	
	private static JButton addComponent;
	private static JButton removeComponent;
	private static JButton editName;
	
	private static JButton confirmButton;
	private static JButton cancelButtonl;
	private static JButton doneButton;
	
	private static JLabel productComponents;
	
	protected static JTable table;
	protected static JScrollPane scrollPane;
	
	protected static MenuItem product;
	
	private JLabel newNameLabel;
	private static JTextField newNameText;
	
	public EditCompositeDialog(MenuItem productEdit) {
		product = productEdit;
		
		this.setTitle("Edit composite product!");
		this.setSize(Constants.Frames.WIDTH, Constants.Frames.HEIGHT);
        this.setLayout(null);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setResizable(false);
        
        toolPanel = new JPanel();
        toolPanel.setBackground(Constants.Colors.ToolbarColor);
        toolPanel.setBounds(0, 0, Constants.Frames.WIDTH, 50);
        toolPanel.setVisible(true);
        toolPanel.setLayout(null);
        this.add(toolPanel);
        
        addComponent = new JButton("Add Item");
        addComponent.setBounds(0, 0, 150, 50);
        addComponent.setFont(Constants.Fonts.ButtonsFont);
        addComponent.setBackground(Constants.Colors.ToolbarColor);
        toolPanel.add(addComponent);
        
        editName = new JButton("Edit Product Name");
        editName.setBounds(300, 0, 200, 50);
        editName.setFont(Constants.Fonts.ButtonsFont);
        editName.setBackground(Constants.Colors.ToolbarColor);
        toolPanel.add(editName);
        
        newNameLabel = new JLabel("New Name:");
        newNameLabel.setBounds(525,0,100,50);
        newNameLabel.setFont( new Font("Times New Roman", Font.ITALIC, 16));
        newNameLabel.setBackground(Constants.Colors.ToolbarColor);
        toolPanel.add(newNameLabel);
        
        newNameText = new JTextField();
        newNameText.setHorizontalAlignment(JTextField.CENTER);
        newNameText.setText("");
        newNameText.setFont(Constants.Fonts.TextFiledsFont);
        newNameText.setBounds(630,10,200,30);
        toolPanel.add(newNameText);
        
        removeComponent = new JButton("Remove Item");
        removeComponent.setBounds(150, 0, 150, 50);
        removeComponent.setFont(Constants.Fonts.ButtonsFont);
        removeComponent.setBackground(Constants.Colors.ToolbarColor);
        toolPanel.add(removeComponent);
        removeComponent.addActionListener(e ->{
			if( EditCompositeDialog.getTable().getSelectedRow() == -1) {
				JOptionPane.showConfirmDialog(null, "Please select a product!","Warning",JOptionPane.DEFAULT_OPTION,JOptionPane.PLAIN_MESSAGE);
				return ;
			}
			
			if(((CompositeProduct) EditCompositeDialog.getProduct()).getProductParts().size() == 1 ) {
				JOptionPane.showConfirmDialog(null, "Composite Product Empty! Product Deleted!","Warning",JOptionPane.DEFAULT_OPTION,JOptionPane.PLAIN_MESSAGE);
				RestaurantController.restaurant.getRs().write(RestaurantController.restaurant.getMenuItems(), MainClass.serFile);
				RestaurantController.restaurant.setMenuItems(RestaurantController.restaurant.getRs().read(MainClass.serFile));
				
				RestaurantController.restaurant.deleteMenuItem(EditCompositeDialog.getProduct());
				
				this.dispose();
				RestaurantController.deleteTable();
				RestaurantController.displayTable();
				return ;
			}
			
			String productName = (String) EditCompositeDialog.getTable().getModel().getValueAt(EditCompositeDialog.getTable().getSelectedRow(), 0);
			MenuItem searchedItem = null;
			for (MenuItem menuItem : RestaurantController.restaurant.getMenuItems()) {
				if(menuItem.getName().equals(productName)) {
					searchedItem = menuItem;
					break;
				}
			}
	
			((CompositeProduct) EditCompositeDialog.getProduct()).getProductParts().remove(searchedItem);
			EditCompositeDialog.getProduct().setPrice(EditCompositeDialog.getProduct().computePrice());
			
			EditExistingCompositeController.deleteEditCompositeTable();
			EditExistingCompositeController.displayEditCompositeTable();
 
        });
        
        
        contentPanel = new JPanel();
        contentPanel.setBackground(Constants.Colors.BackgroundColor);
        contentPanel.setBounds(0, 50, Constants.Frames.WIDTH, Constants.Frames.HEIGHT - 50);
        contentPanel.setLayout(null);
        this.add(contentPanel);
        
        doneButton = new JButton("Done");
        doneButton.setBounds(375,500,150,50);
        doneButton.setFont(Constants.Fonts.ButtonsFont);
        doneButton.setBackground(Constants.Colors.ButtonsColor);
        doneButton.setVisible(true);
        doneButton.addActionListener( e -> {
        	RestaurantController.deleteTable();
        	RestaurantController.displayTable();
        	RestaurantController.restaurant.getRs().write(RestaurantController.restaurant.getMenuItems(), MainClass.serFile);
			RestaurantController.restaurant.setMenuItems(RestaurantController.restaurant.getRs().read(MainClass.serFile));

        	this.dispose();
        });
        contentPanel.add(doneButton);
        
        productComponents = new JLabel("Components");
        productComponents.setFont(Constants.Fonts.LabelsFont);
        productComponents.setForeground(Constants.Colors.BLACK);
        productComponents.setHorizontalAlignment(JLabel.CENTER);
        productComponents.setVerticalAlignment(JLabel.CENTER);
        productComponents.setBounds(200, 75, 500, 40);
        productComponents.setVisible(true);
        contentPanel.add(productComponents);
        
        scrollPane = new JScrollPane();
        scrollPane.setBounds(89, 126, 734, 359);
        contentPanel.add(scrollPane);
        
        table = new JTable();
        scrollPane.setViewportView(table);
        scrollPane.setVisible(false);
        
        this.setVisible(true);
	}
	
	public static void setEditNameActionListener(ActionListener actionListener) {
		editName.addActionListener(actionListener);
	}
	
	public static void setAddComponentActionListener(ActionListener actionListener) {
		addComponent.addActionListener(actionListener);
	}
	
	public static void setRemoveComponentActionListener(ActionListener actionListener) {
		removeComponent.addActionListener(actionListener);
	}

	public static JButton getConfirmButton() {
		return confirmButton;
	}

	public static void setConfirmButton(JButton confirmButton) {
		EditCompositeDialog.confirmButton = confirmButton;
	}

	public static JButton getCancelButtonl() {
		return cancelButtonl;
	}

	public static void setCancelButtonl(JButton cancelButtonl) {
		EditCompositeDialog.cancelButtonl = cancelButtonl;
	}

	public static JTable getTable() {
		return table;
	}

	public static void setTable(JTable table) {
		EditCompositeDialog.table = table;
	}

	public static JScrollPane getScrollPane() {
		return scrollPane;
	}

	public static void setScrollPane(JScrollPane scrollPane) {
		EditCompositeDialog.scrollPane = scrollPane;
	}

	public static MenuItem getProduct() {
		return product;
	}

	public static JPanel getContentPanel() {
		return contentPanel;
	}

	public static JTextField getNewNameText() {
		return newNameText;
	}
	
	public void setAddButtonActionListener(ActionListener actionListener) {
		addComponent.addActionListener(actionListener);
	}

}

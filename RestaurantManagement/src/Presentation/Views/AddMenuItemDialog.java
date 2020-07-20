package Presentation.Views;

import javax.swing.*;
import javax.swing.event.ChangeListener;

import BLL.BaseProduct;
import BLL.CompositeProduct;
import Presentation.Controllers.RestaurantController;

import java.awt.event.ActionListener;

public class AddMenuItemDialog extends AbstractDialog {

    private JLabel baseProductLabel;
    private JLabel compositeProductLabel;
    private JLabel nameLabel;
    private static JLabel priceLabel;

    private static JRadioButton baseProductButton;
    private static JRadioButton compositeProductButton;

    private static JTextField nameTextField;
    private static JTextField priceTextField;
    
    private static JButton addComponentsButton;

    public AddMenuItemDialog() {
        super();

        this.titleLabel.setText("Add New Menu Item");

        baseProductLabel = new JLabel("Base product");
        baseProductLabel.setFont(Constants.Fonts.LabelsFont);
        baseProductLabel.setHorizontalAlignment(JLabel.LEFT);
        baseProductLabel.setVerticalAlignment(JLabel.CENTER);
        baseProductLabel.setBounds(25, 75, 250, 50);
        this.contentPanel.add(baseProductLabel);

        compositeProductLabel = new JLabel("Composite product");
        compositeProductLabel.setFont(Constants.Fonts.LabelsFont);
        compositeProductLabel.setHorizontalAlignment(JLabel.LEFT);
        compositeProductLabel.setVerticalAlignment(JLabel.CENTER);
        compositeProductLabel.setBounds(25, 130, 250, 50);
        this.contentPanel.add(compositeProductLabel);

        baseProductButton = new JRadioButton();
        baseProductButton.setBackground(Constants.Colors.BackgroundColor);
        baseProductButton.setBounds(280, 95, 20, 20);
        this.contentPanel.add(baseProductButton);

        compositeProductButton = new JRadioButton();
        compositeProductButton.setBackground(Constants.Colors.BackgroundColor);
        compositeProductButton.setBounds(280, 150, 20, 20);
        this.contentPanel.add(compositeProductButton);

        nameLabel = new JLabel("Name :");
        nameLabel.setFont(Constants.Fonts.LabelsFont);
        nameLabel.setHorizontalAlignment(JLabel.LEFT);
        nameLabel.setVerticalAlignment(JLabel.CENTER);
        nameLabel.setBounds(25, 185, 150, 50);
        this.contentPanel.add(nameLabel);

        nameTextField = new JTextField();
        nameTextField.setHorizontalAlignment(JTextField.CENTER);
        nameTextField.setFont(Constants.Fonts.TextFiledsFont);
        nameTextField.setBounds(180, 200, 400, 30);
        this.contentPanel.add(nameTextField);

        priceLabel = new JLabel("Price :");
        priceLabel.setFont(Constants.Fonts.LabelsFont);
        priceLabel.setHorizontalAlignment(JLabel.LEFT);
        priceLabel.setVerticalAlignment(JLabel.CENTER);
        priceLabel.setBounds(25, 240, 150, 50);
        this.contentPanel.add(priceLabel);
        priceLabel.setVisible(false);

        priceTextField = new JTextField();
        priceTextField.setHorizontalAlignment(JTextField.CENTER);
        priceTextField.setFont(Constants.Fonts.TextFiledsFont);
        priceTextField.setBounds(180, 255, 400, 30);
        this.contentPanel.add(priceTextField);
        priceTextField.setVisible(false);
        
        addComponentsButton = new JButton("Add Components");
        addComponentsButton.setBounds(240,255, 200, 50);
        addComponentsButton.setFont(Constants.Fonts.ButtonsFont);
        addComponentsButton.setBackground(Constants.Colors.ButtonsColor);
        addComponentsButton.setVisible(false);
        this.contentPanel.add(addComponentsButton);
        
        this.confirmButton.addActionListener(e ->{
        	if (!AddMenuItemDialog.getBaseProductButton().isSelected() && !AddMenuItemDialog.getCompositeProductButton().isSelected()) {
                new ErrorDialog("Invalid data");
            }else if( AddMenuItemDialog.getBaseProductButton().isSelected() ) {
            	 if (AddMenuItemDialog.getNameTextField().getText().isEmpty() || AddMenuItemDialog.getPriceTextField().getText().isEmpty()) {
                     new ErrorDialog("Invalid data");
                     return;
            	}
            	try {
            	RestaurantController.restaurant.createMenuItem(new BaseProduct( AddMenuItemDialog.getNameTextField().getText(), Float.valueOf(AddMenuItemDialog.getPriceTextField().getText())) );
            	}catch(Exception ex){
            		JOptionPane.showConfirmDialog(null, "Invalid Price!","Warning",JOptionPane.DEFAULT_OPTION,JOptionPane.PLAIN_MESSAGE);
            	}
            	RestaurantController.deleteTable();
            	RestaurantController.displayTable();
            }else if( AddMenuItemDialog.getCompositeProductButton().isSelected()) {
            	if( AddMenuItemDialog.getNameTextField().getText().isEmpty() ) {
            		new ErrorDialog("Invalid Name!");
            		return ;
            	}
            	if( ComponentsDialog.getComponentsOfItem() == null || ComponentsDialog.getComponentsOfItem().size() == 0 ) {
            		new ErrorDialog("No Components");
            		return ;
            	}
            	RestaurantController.restaurant.createMenuItem(new CompositeProduct(  AddMenuItemDialog.getNameTextField().getText(), 0, ComponentsDialog.getComponentsOfItem()) );
            	RestaurantController.deleteTable();
            	RestaurantController.displayTable();
            }
        	
        	this.dispose();
        });
        
    }

    public static JRadioButton getBaseProductButton() {
        return baseProductButton;
    }

    public static JRadioButton getCompositeProductButton() {
        return compositeProductButton;
    }

    public static JTextField getNameTextField() {
        return nameTextField;
    }

    public static JTextField getPriceTextField() {
        return priceTextField;
    }

    public void setConfirmButtonActionListener(ActionListener actionListener) {
        this.confirmButton.addActionListener(actionListener);
    }

	public static JLabel getPriceLabel() {
		return priceLabel;
	}

	public void setPriceLabel(JLabel priceLabel) {
		this.priceLabel = priceLabel;
	}

	public static JButton getAddComponentsButton() {
		return addComponentsButton;
	}

	public static void setAddComponentsButton(JButton addComponentsButton) {
		AddMenuItemDialog.addComponentsButton = addComponentsButton;
	}
    
	public void setBaseProductButtonChangeListener(ChangeListener listener) {
		baseProductButton.addChangeListener(listener);
	}
	
	public void setCompositeProductButtonChangeListener(ChangeListener listener) {
		compositeProductButton.addChangeListener(listener);
	}
	
	public void setAddComponentsButtonActionListenet(ActionListener actionListener) {
		addComponentsButton.addActionListener(actionListener);
	}
}

package Presentation.Views;

import javax.swing.*;

import BLL.BaseProduct;
import Presentation.Controllers.RestaurantController;

import java.awt.event.ActionListener;

public class EditDialog extends AbstractDialog {

    private JLabel nameLabel;
    private JLabel priceLabel;

    private static JTextField nameTextField;
    private static JTextField priceTextField;
    
    private static JTextField oldNameTextField;
    
    private static String nameOfProduct;

    public EditDialog(String s) {
        super();
        EditDialog.nameOfProduct = s;

        this.titleLabel.setText("Edit Existing Item");

        nameLabel = new JLabel("New Name:");
        nameLabel.setFont(Constants.Fonts.LabelsFont);
        nameLabel.setHorizontalAlignment(JLabel.LEFT);
        nameLabel.setVerticalAlignment(JLabel.CENTER);
        nameLabel.setBounds(25, 100, 150, 50);
        this.contentPanel.add(nameLabel);

        nameTextField = new JTextField();
        nameTextField.setHorizontalAlignment(JTextField.CENTER);
        nameTextField.setFont(Constants.Fonts.TextFiledsFont);
        nameTextField.setText("");
        nameTextField.setBounds(180, 115, 400, 30);
        this.contentPanel.add(nameTextField);

        priceLabel = new JLabel("New Price:");
        priceLabel.setFont(Constants.Fonts.LabelsFont);
        priceLabel.setHorizontalAlignment(JLabel.LEFT);
        priceLabel.setVerticalAlignment(JLabel.CENTER);
        priceLabel.setBounds(25, 200, 150, 50);
        this.contentPanel.add(priceLabel);

        priceTextField = new JTextField();
        priceTextField.setHorizontalAlignment(JTextField.CENTER);
        priceTextField.setFont(Constants.Fonts.TextFiledsFont);
        priceTextField.setText("");
        priceTextField.setBounds(180, 215, 400, 30);
        this.contentPanel.add(priceTextField);

        confirmButton.addActionListener( e -> {
        	 if (EditDialog.getNameTextField().getText().isEmpty() || EditDialog.getPriceTextField().getText().isEmpty()) {
                 new ErrorDialog("Invalid data");
             }else {
             	RestaurantController.restaurant.editMenuItem(new BaseProduct(EditDialog.getNameOfProduct(),0), new BaseProduct(EditDialog.getNameTextField().getText(),Float.valueOf(EditDialog.getPriceTextField().getText())));
             	RestaurantController.deleteTable();
             	RestaurantController.displayTable();
             	new ErrorDialog("Succes!");
             }
        	 
        	 this.dispose();
        });
    }

    public static JTextField getNameTextField() {
        return nameTextField;
    }

    public static JTextField getPriceTextField() {
        return priceTextField;
    }

    public void setConfirmActionListener(ActionListener actionListener) {
        this.confirmButton.addActionListener(actionListener);
    }

    public static JTextField getOldNameTextField() {
    	return oldNameTextField;
    }

	public static String getNameOfProduct() {
		return nameOfProduct;
	}

	public void setNameOfProduct(String nameOfProduct) {
		EditDialog.nameOfProduct = nameOfProduct;
	}
}

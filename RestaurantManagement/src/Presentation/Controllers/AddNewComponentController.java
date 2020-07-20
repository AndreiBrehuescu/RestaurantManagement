package Presentation.Controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import BLL.MenuItem;
import BLL.CompositeProduct;
import Presentation.Views.AddNewComponents;

public class AddNewComponentController {

	public AddNewComponentController(AddNewComponents newComponents) {
		newComponents.setConfirmButtonActionListener(new AddButtonActionListener());
	}
	
	private class AddButtonActionListener implements ActionListener{

		@SuppressWarnings("unlikely-arg-type")
		@Override
		public void actionPerformed(ActionEvent e) {
			int[] rows = AddNewComponents.getMenuTable().getSelectedRows();
			if( rows.length == 0)
				return ;
			HashSet<MenuItem> toBeAdded = new HashSet<MenuItem>();
			for (int i : rows) {
				String productName =  (String) AddNewComponents.getMenuTable().getValueAt(i, 0);
				if( productName.equals(AddNewComponents.getProduct())) {
					JOptionPane.showConfirmDialog(null, "Can not add the product to itself!","Warning",JOptionPane.DEFAULT_OPTION,JOptionPane.PLAIN_MESSAGE);
					EditExistingCompositeController.deleteEditCompositeTable();
					EditExistingCompositeController.displayEditCompositeTable();
					return ;
				}
				for ( MenuItem menuItem : RestaurantController.restaurant.getMenuItems()) {
					if( menuItem.getName().equals(productName)) {
						toBeAdded.add(menuItem);
					}
				}
				
				for (MenuItem menuItem : toBeAdded) {
					((CompositeProduct) AddNewComponents.getProduct()).getProductParts().add(menuItem);
				}
			}
			JOptionPane.showConfirmDialog(null, "Components Added","Warning",JOptionPane.DEFAULT_OPTION,JOptionPane.PLAIN_MESSAGE);
			AddNewComponents.getProduct().setPrice(AddNewComponents.getProduct().computePrice() );
			EditExistingCompositeController.deleteEditCompositeTable();
			EditExistingCompositeController.displayEditCompositeTable();
			
		}
		
	}
	
}

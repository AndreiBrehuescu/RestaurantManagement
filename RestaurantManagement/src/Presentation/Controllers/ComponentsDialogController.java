package Presentation.Controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import BLL.MenuItem;
import Presentation.Views.ComponentsDialog;

public class ComponentsDialogController {

	public ComponentsDialogController(ComponentsDialog componentsDialog) {
		componentsDialog.setAddButtonActionListener(new AddComponent());
	}
	
	private class AddComponent implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			int[] selectedRows = ComponentsDialog.getComponentTable().getSelectedRows();

			if ( selectedRows.length == 0 ) {
				JOptionPane.showConfirmDialog(null, "Please select a product!","Warning",JOptionPane.DEFAULT_OPTION,JOptionPane.PLAIN_MESSAGE);
				return ;
			}
			
			for( int i = 0; i < selectedRows.length; i++) {
				int row = selectedRows[i];
				String productName = (String) ComponentsDialog.getComponentTable().getModel().getValueAt(row, 0);
				
				for (MenuItem item : RestaurantController.restaurant.getMenuItems() ) {
					if( item.getName() == productName ) {
						ComponentsDialog.getComponentsOfItem().add(item);
					}
				}
			}
			JOptionPane.showConfirmDialog(null, "Components Added!","Warning",JOptionPane.DEFAULT_OPTION,JOptionPane.PLAIN_MESSAGE);
		}
	}
	
}

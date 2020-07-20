package Presentation.Controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashSet;

import javax.swing.JOptionPane;

import BLL.MenuItem;
import Presentation.Views.ComponentsDialog;
import Presentation.Views.OrderFrame;

public class OrderFrameController {

	public OrderFrameController(OrderFrame order) {
		order.addButtonActionListener(new AddProducts());
	}
	
	
	public class AddProducts implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			int[] rows = OrderFrame.getMenuTable().getSelectedRows();
			if( rows.length == 0) {
				JOptionPane.showConfirmDialog(null, "No products inserted!","Warning",JOptionPane.DEFAULT_OPTION,JOptionPane.PLAIN_MESSAGE);
				return;
			}
			ArrayList<MenuItem> ordered = OrderFrame.getOrderedProducts();
			for (int r : rows) {
				String productName = (String) OrderFrame.getMenuTable().getModel().getValueAt(r, 0);
				MenuItem m = null;
				for (MenuItem menuItem : RestaurantController.restaurant.getMenuItems()) {
					if( menuItem.getName().equals(productName)) {
						ordered.add(menuItem);
					}
				}
			}
		}
	}
}

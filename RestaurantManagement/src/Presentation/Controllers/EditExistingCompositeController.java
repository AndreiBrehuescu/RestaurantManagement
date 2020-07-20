package Presentation.Controllers;

import java.util.HashSet;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import BLL.CompositeProduct;
import BLL.MenuItem;
import Presentation.Views.AddNewComponents;
import Presentation.Views.EditCompositeDialog;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class EditExistingCompositeController {

	public EditExistingCompositeController(EditCompositeDialog editDialog) {
		
		deleteEditCompositeTable();
		displayEditCompositeTable();

		EditCompositeDialog.setEditNameActionListener(new EditName());
		EditCompositeDialog.setAddComponentActionListener(new AddItemToCompositeProduct());
	}
	
	public static void displayEditCompositeTable() {
		String tableHeader[] = {"Name","Price"};
		String[][] data = {};
		HashSet<MenuItem> parts = ((CompositeProduct) EditCompositeDialog.getProduct()).getProductParts();
		if( parts.size() != 0 ) {
       	 data = new String[parts.size()][tableHeader.length];
       	 int j = 0;
       	 
       	 for (MenuItem item : parts) {
				data[j][0] = item.getName();
				data[j][1] = Float.toString(item.getPrice());
				j++;
			}
        }
        
        JTable table = new JTable(data, tableHeader);
        EditCompositeDialog.setTable(table);
       
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(89, 126, 734, 359);
        scrollPane.setViewportView(table);
        scrollPane.setVisible(true);
        
        EditCompositeDialog.setScrollPane(scrollPane);
        EditCompositeDialog.getContentPanel().add(scrollPane); 
	}
	
	public static void deleteEditCompositeTable() {
		EditCompositeDialog.getScrollPane().setVisible(false);
		EditCompositeDialog.getScrollPane().remove(EditCompositeDialog.getTable());
		EditCompositeDialog.getContentPanel().remove(EditCompositeDialog.getScrollPane()); 
 
	}
	
	private class AddItemToCompositeProduct implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			new AddNewComponentController(new AddNewComponents(EditCompositeDialog.getProduct()));
		}
	}
	
	
	private class EditName implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			if( EditCompositeDialog.getNewNameText().getText().equals("")) {
				JOptionPane.showConfirmDialog(null, "Please insert the new name!","Warning",JOptionPane.DEFAULT_OPTION,JOptionPane.PLAIN_MESSAGE);
				return ;
			}
			
			RestaurantController.restaurant.editMenuItem(EditCompositeDialog.getProduct(), 
					new CompositeProduct(EditCompositeDialog.getNewNameText().getText(), EditCompositeDialog.getProduct().getPrice(),
					((CompositeProduct) EditCompositeDialog.getProduct()).getProductParts() ));
			
			JOptionPane.showConfirmDialog(null, "Name Changed!","Warning",JOptionPane.DEFAULT_OPTION,JOptionPane.PLAIN_MESSAGE);
			return ;
		}
		
	}
}

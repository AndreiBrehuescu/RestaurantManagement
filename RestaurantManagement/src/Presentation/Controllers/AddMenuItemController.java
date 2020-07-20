package Presentation.Controllers;

import Presentation.Views.AddMenuItemDialog;
import Presentation.Views.ComponentsDialog;
import Presentation.Views.ErrorDialog;
import Presentation.Views.RestaurantFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import BLL.BaseProduct;

public class AddMenuItemController {

    public AddMenuItemController(AddMenuItemDialog addMenuItemDialog) {
 
        addMenuItemDialog.setBaseProductButtonChangeListener(new BaseProductSelected());
        addMenuItemDialog.setCompositeProductButtonChangeListener(new CompositeProductSelected());
        addMenuItemDialog.setAddComponentsButtonActionListenet(new ComponentsButton());
    }

    private class BaseProductSelected implements ChangeListener{

		@Override
		public void stateChanged(ChangeEvent e) {
			if( AddMenuItemDialog.getBaseProductButton().isSelected()) {
				AddMenuItemDialog.getCompositeProductButton().setSelected(false);
				AddMenuItemDialog.getPriceTextField().setVisible(true);
				AddMenuItemDialog.getPriceLabel().setVisible(true);
				AddMenuItemDialog.getAddComponentsButton().setVisible(false);
			}else {
				AddMenuItemDialog.getBaseProductButton().setSelected(false);
				AddMenuItemDialog.getAddComponentsButton().setVisible(true);
				AddMenuItemDialog.getPriceTextField().setVisible(false);
				AddMenuItemDialog.getPriceLabel().setVisible(false);
			}
			
		}
    }
    
    private class CompositeProductSelected implements ChangeListener{

		@Override
		public void stateChanged(ChangeEvent e) {
			if( AddMenuItemDialog.getCompositeProductButton().isSelected()) {
				AddMenuItemDialog.getBaseProductButton().setSelected(false);
				AddMenuItemDialog.getPriceTextField().setVisible(false);
				AddMenuItemDialog.getPriceLabel().setVisible(false);
				AddMenuItemDialog.getAddComponentsButton().setVisible(true);
			}else {
				AddMenuItemDialog.getCompositeProductButton().setSelected(false);
				AddMenuItemDialog.getAddComponentsButton().setVisible(false);
				AddMenuItemDialog.getPriceTextField().setVisible(true);
				AddMenuItemDialog.getPriceLabel().setVisible(true);
			}
		}
    }
    
    private class ComponentsButton implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			new ComponentsDialogController(new ComponentsDialog(RestaurantFrame.getScrollPane(), RestaurantFrame.getTable()));
		}
    	
    }
}

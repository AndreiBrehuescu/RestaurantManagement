package Presentation.Controllers;

import Presentation.Views.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.HashSet;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import BLL.BaseProduct;
import BLL.CompositeProduct;
import BLL.MenuItem;
import BLL.Order;
import BLL.Restaurant;
import DAL.FileWriterr;

public class RestaurantController {
	
	public static RestaurantFrame rFrame = new RestaurantFrame("TP Restaurant");
	public static Restaurant restaurant = new Restaurant();
	
    public RestaurantController(RestaurantFrame restaurantFrame) {
        restaurantFrame.setAdminButtonActionListener(new AdminButtonActionListener());
        restaurantFrame.setWaiterButtonActionListener(new WaiterButtonActionListener());
        restaurantFrame.setChefButtonActionListener(new ChefButtonActionListener());
        restaurantFrame.setHomePageButtonActionListener(new HomePageActionListener());
        restaurantFrame.setAddNewItemButtonActionListener(new NewItemButtonActionListener());
        restaurantFrame.setEditItemButtonActionListener(new EditExistingItemButtonActionListener());
        restaurantFrame.setDeleteItemButtonActionListener(new DeleteItemActionListener());
        restaurantFrame.setComputePriceButtonActionListenet(new ComputePriceOrder());
        restaurantFrame.setGenerateBillButtonActionListener(new GenerateBill());
        restaurantFrame.setNewOrderButtonActionListener(new NewOrder());
    }

    private class NewOrder implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			new OrderFrameController(new OrderFrame());
		}	
    }
    
    private class AdminButtonActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            RestaurantFrame.getAdminButton().setBackground(Constants.Colors.ButtonsColor);
            RestaurantFrame.getWaiterButton().setBackground(Constants.Colors.ToolbarColor);
            RestaurantFrame.getChefButton().setBackground(Constants.Colors.ToolbarColor);
            RestaurantFrame.getHomePageButton().setBackground(Constants.Colors.ToolbarColor);

            RestaurantFrame.getAdminActionsPanel().setVisible(true);
            RestaurantFrame.getWaiterActionsPanel().setVisible(false);

            RestaurantFrame.getMenuLabel().setVisible(true);
            RestaurantFrame.getOrderLabel().setVisible(false);

            RestaurantFrame.getWelcomeLabel().setVisible(false);
            RestaurantFrame.getRoleLabel().setVisible(false);
            
            deleteTable();
            displayTable();
            
            RestaurantFrame.getScrollPane().setVisible(true);
            
        }
    }

    private class WaiterButtonActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            RestaurantFrame.getAdminButton().setBackground(Constants.Colors.ToolbarColor);
            RestaurantFrame.getWaiterButton().setBackground(Constants.Colors.ButtonsColor);
            RestaurantFrame.getChefButton().setBackground(Constants.Colors.ToolbarColor);
            RestaurantFrame.getHomePageButton().setBackground(Constants.Colors.ToolbarColor);

            RestaurantFrame.getAdminActionsPanel().setVisible(false);
            RestaurantFrame.getWaiterActionsPanel().setVisible(true);

            RestaurantFrame.getMenuLabel().setVisible(false);
            RestaurantFrame.getOrderLabel().setVisible(true);

            RestaurantFrame.getWelcomeLabel().setVisible(false);
            RestaurantFrame.getRoleLabel().setVisible(false);
            
            deleteTableOrder();
            displayTableOrder();
            
            RestaurantFrame.getScrollPane().setVisible(true);
        }
    }
    
    private class ChefButtonActionListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			RestaurantFrame.getAdminButton().setBackground(Constants.Colors.ToolbarColor);
            RestaurantFrame.getWaiterButton().setBackground(Constants.Colors.ToolbarColor);
            RestaurantFrame.getChefButton().setBackground(Constants.Colors.ButtonsColor);
            
            RestaurantFrame.getAdminActionsPanel().setVisible(false);
            RestaurantFrame.getWaiterActionsPanel().setVisible(false);
            
            RestaurantFrame.getMenuLabel().setVisible(false);
            RestaurantFrame.getOrderLabel().setVisible(false);
            
            RestaurantFrame.getWelcomeLabel().setVisible(false);
            RestaurantFrame.getRoleLabel().setVisible(false);  
            RestaurantFrame.getScrollPane().setVisible(false);
           
            deleteChefTable();
            displayChefTable();
           
            RestaurantFrame.getChefScrollPane().setVisible(true);
		}
    }

    private class HomePageActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            RestaurantFrame.getAdminButton().setBackground(Constants.Colors.ToolbarColor);
            RestaurantFrame.getWaiterButton().setBackground(Constants.Colors.ToolbarColor);
            RestaurantFrame.getChefButton().setBackground(Constants.Colors.ToolbarColor);
            RestaurantFrame.getHomePageButton().setBackground(Constants.Colors.ButtonsColor);

            RestaurantFrame.getAdminActionsPanel().setVisible(false);
            RestaurantFrame.getWaiterActionsPanel().setVisible(false);

            RestaurantFrame.getMenuLabel().setVisible(false);
            RestaurantFrame.getOrderLabel().setVisible(false);

            RestaurantFrame.getWelcomeLabel().setVisible(true);
            RestaurantFrame.getRoleLabel().setVisible(true);
            
            RestaurantFrame.getScrollPane().setVisible(false);
        }
    }

    private class NewItemButtonActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            new AddMenuItemController(new AddMenuItemDialog());
        }
    }

    private class EditExistingItemButtonActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if( RestaurantFrame.getTable().getSelectedRow() == -1 ) {
				JOptionPane.showConfirmDialog(null, "Please select a product!","Warning",JOptionPane.DEFAULT_OPTION,JOptionPane.PLAIN_MESSAGE);
				return ;
			}
			String productName = (String) RestaurantFrame.getTable().getModel().getValueAt(RestaurantFrame.getTable().getSelectedRow(), 0);
			MenuItem searchedItem = null;
			for (MenuItem menuItem : restaurant.getMenuItems()) {
				if(menuItem.getName().equals(productName)) {
					searchedItem = menuItem;
					break;
				}
			}
			
			if( searchedItem instanceof BaseProduct ) {
				new EditDialog(searchedItem.getName());
			}else {
				new EditExistingCompositeController(new EditCompositeDialog(searchedItem));
			}
		}
    }
    
    private class ComputePriceOrder implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			if( RestaurantFrame.getTable().getSelectedRow() != -1 ) {
				String orderIdString = (String) RestaurantFrame.getTable().getModel().getValueAt(RestaurantFrame.getTable().getSelectedRow(), 0);
				int orderId = Integer.parseInt(orderIdString);
				float totalPrice = 0;
				for (Order order : restaurant.getOrders().keySet()) {
					 if( order.getOrderId() == orderId ) {
						 for (MenuItem orderItem : restaurant.getOrders().get(order)) {
							totalPrice += orderItem.getPrice();
						}
						order.setTotal(totalPrice);
						deleteTableOrder();
						displayTableOrder();
						break;
					 }
				}
				JOptionPane.showConfirmDialog(null, "Price Computed!","Warning",JOptionPane.DEFAULT_OPTION,JOptionPane.PLAIN_MESSAGE);
				
			}else {
				JOptionPane.showConfirmDialog(null, "Please select an order!","Warning",JOptionPane.DEFAULT_OPTION,JOptionPane.PLAIN_MESSAGE);
			}
		}
    }
    
    private class GenerateBill implements ActionListener{
    	
		@Override
		public void actionPerformed(ActionEvent e) {
			if( RestaurantFrame.getTable().getSelectedRow() != -1 ) {
				String orderIdString = (String) RestaurantFrame.getTable().getModel().getValueAt(RestaurantFrame.getTable().getSelectedRow(), 0);
				String orderTotalPrice = (String) RestaurantFrame.getTable().getModel().getValueAt(RestaurantFrame.getTable().getSelectedRow(), 3);
				if( orderTotalPrice.equals("") )
					JOptionPane.showConfirmDialog(null, "Please compute the price first!","Warning",JOptionPane.DEFAULT_OPTION,JOptionPane.PLAIN_MESSAGE);
			
				int orderId = Integer.parseInt(orderIdString);
				for (Order order : restaurant.getOrders().keySet()) {
					 if( order.getOrderId() == orderId ) {
						restaurant.generateBill(order);
						break;
					 }
				}
				JOptionPane.showConfirmDialog(null, "Bill Generated!","Warning",JOptionPane.DEFAULT_OPTION,JOptionPane.PLAIN_MESSAGE);
			}else {
				JOptionPane.showConfirmDialog(null, "Please select an order!","Warning",JOptionPane.DEFAULT_OPTION,JOptionPane.PLAIN_MESSAGE);
			}
		}
    }
    
    private class DeleteItemActionListener implements ActionListener {

        @SuppressWarnings("unlikely-arg-type")
		@Override
        public void actionPerformed(ActionEvent e) {
            if( RestaurantFrame.getTable().getSelectedRow() == -1 ) {
				JOptionPane.showConfirmDialog(null, "Please select a product!","Warning",JOptionPane.DEFAULT_OPTION,JOptionPane.PLAIN_MESSAGE);
				return ;
			}
            String productName = (String) RestaurantFrame.getTable().getModel().getValueAt(RestaurantFrame.getTable().getSelectedRow(), 0);
            MenuItem searchedItem = null;
			for (MenuItem menuItem : restaurant.getMenuItems()) {
				if(menuItem.getName().equals(productName)) {
					searchedItem = menuItem;
					break;
				}
			}
			
			HashSet<MenuItem> itemsToDelete = new HashSet<MenuItem>();
			
			for (MenuItem menuItem : restaurant.getMenuItems()) {
				if( menuItem instanceof BaseProduct) {
					if(menuItem.equals(menuItem.getName())) {
						itemsToDelete.add(menuItem);
					}
				}else { //Composite Product
					if( containsDeletedProduct(((CompositeProduct) menuItem).getProductParts(), searchedItem) ) {
						itemsToDelete.add(menuItem);
					}
				}
			}
			for (MenuItem menuItem : itemsToDelete) {
				restaurant.deleteMenuItem(menuItem);
			}
			
			restaurant.deleteMenuItem(searchedItem);
			deleteTable();
			displayTable();
        }
    }
    
    private boolean containsDeletedProduct(HashSet<MenuItem> toProcess, MenuItem searchedItem) {
    	for (MenuItem menuItem : toProcess) {
    		if( menuItem.getName().equals(searchedItem.getName())) {
    			return true;
    		}

			if( menuItem instanceof CompositeProduct ) {
				return containsDeletedProduct( ((CompositeProduct) menuItem).getProductParts(), searchedItem);
			}
		}
    	
    	return false;
    }
    public static void displayChefTable() {
    	HashMap<MenuItem, Integer> ordered = FileWriterr.computeOrderForBill(RestaurantFrame.getPrepareItems());
    	String tableHeader[]= {"Name", "Type","Quantity"};
    	String[][] data = {};
    	if (  RestaurantFrame.getPrepareItems().size() != 0 ) {
	
	    	data = new String[ordered.keySet().size()][tableHeader.length];
	    	int j = 0;
	    	for (MenuItem item : ordered.keySet()) {
	    		data[j][0] = item.getName();
				if ( item instanceof BaseProduct)
					data[j][1] = "BaseProduct";
				else
					data[j][1] = "CompositeProduct";
				data[j][2] = Integer.toString(ordered.get(item));
				j++;
			}
    	}
    	
    	JTable table = new JTable(data, tableHeader);
    	RestaurantFrame.setChefTable(table);
    	
    	JScrollPane scrollPane = new JScrollPane();
    	scrollPane.setBounds(75, 125, 750, 350);
    	scrollPane.setViewportView(table);
    	scrollPane.setVisible(true);
    	
    	 RestaurantFrame.setScrollPane(scrollPane);
         RestaurantFrame.getContentPanel().add(scrollPane); 
    }
    
    public static void deleteChefTable() {
    	RestaurantFrame.getChefScrollPane().setVisible(false);
    	RestaurantFrame.getChefScrollPane().remove(RestaurantFrame.getChefTable());
    	RestaurantFrame.getContentPanel().remove(RestaurantFrame.getChefScrollPane()); 
    }
    
    public static void displayTable() {
    	 String tableHeader[]= {"Nume", "Price"};
    	 String[][] data = {};
    	 if( restaurant.getMenuItems() != null ) {
        	 data = new String[restaurant.getMenuItems().size()][tableHeader.length];
        	 int j = 0;
        	 
        	 for (MenuItem item : restaurant.getMenuItems()) {
				data[j][0] = item.getName();
				data[j][1] = Float.toString(item.getPrice());
				j++;
			}
         }
         
         JTable table = new JTable(data, tableHeader);
         RestaurantFrame.setTable(table);
         
         JScrollPane scrollPane = new JScrollPane();
         scrollPane.setBounds(75, 125, 750, 350);
         scrollPane.setViewportView(table);
         scrollPane.setVisible(true);
         
         RestaurantFrame.setScrollPane(scrollPane);
         RestaurantFrame.getContentPanel().add(scrollPane); 
    }
    
    public static void deleteTable() { 
    	RestaurantFrame.getScrollPane().setVisible(false);
    	RestaurantFrame.getScrollPane().remove(RestaurantFrame.getTable());
    	RestaurantFrame.getContentPanel().remove(RestaurantFrame.getScrollPane()); 
    }
    
    public static void displayTableOrder() {
   	 String tableHeader[]= {"OrderID", "Table", "Date", "Total" };
   	 String[][] data = {};
   	 if( restaurant.getOrders().size() != 0 ) {
       	 data = new String[restaurant.getOrders().size()][tableHeader.length];
       	 int j = 0;
       	 
       	 for (Order order : restaurant.getOrders().keySet()) {
				data[j][0] = Integer.toString(order.getOrderId());
				data[j][1] = Integer.toString(order.getTable());
				data[j][2] = order.getDate().toString();
				if( order.getTotal() == 0)
					data[j][3] = "";
				else
					data[j][3] = Float.toString(order.getTotal());
				j++;
			}
        }
        
        JTable table = new JTable(data, tableHeader);
        RestaurantFrame.setTable(table);
       
        
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(75, 125, 750, 350);
        scrollPane.setViewportView(table);
        scrollPane.setVisible(true);
        
        RestaurantFrame.setScrollPane(scrollPane);
        RestaurantFrame.getContentPanel().add(scrollPane); 
   }
   
   public static void deleteTableOrder() { 
   	RestaurantFrame.getScrollPane().setVisible(false);
   	RestaurantFrame.getScrollPane().remove(RestaurantFrame.getTable());
   	RestaurantFrame.getContentPanel().remove(RestaurantFrame.getScrollPane()); 
   }
    
}

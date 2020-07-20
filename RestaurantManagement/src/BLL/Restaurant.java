package BLL;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Observable;
import java.util.ArrayList;

import DAL.FileWriterr;
import DAL.RestaurantSerialization;
import Presentation.Controllers.RestaurantController;
import Run.MainClass;

public class Restaurant extends Observable implements IRestaurantProcessing{

	
	private HashSet<MenuItem> menuItems;
	private HashMap<Order, ArrayList<MenuItem>> orders;
	
	final private RestaurantSerialization rs = new RestaurantSerialization();
	
	public Restaurant() {
		
		this.orders = new HashMap<>();
		
		this.menuItems = null;
		this.menuItems = rs.read(MainClass.serFile);
		if( this.menuItems == null )
		{
			this.menuItems = new HashSet<MenuItem>();
		}
	
		this.addObserver(RestaurantController.rFrame);
	}
	
	@Override
	public void createMenuItem(MenuItem newItem) {
		assert newItem != null;
		assert !menuItems.contains(newItem);
		int size = menuItems.size();
		
		MenuItem checkForExisting = null;
		for (MenuItem menuItem : menuItems) {
			if( menuItem.getName().equals(newItem.getName()))
				checkForExisting = menuItem;
		}
		if( checkForExisting == null )
			this.menuItems.add(newItem);
		rs.write(this.menuItems, MainClass.serFile);
		
		assert menuItems.size() == size + 1;
		assert invariantCheck(); //wellFormed
		
	}

	@Override
	public void deleteMenuItem(MenuItem existingItem) {
		assert existingItem != null;
		assert menuItems.contains(existingItem);
		int size = menuItems.size();
		
		MenuItem toBeDeleted = null;
		for (MenuItem menuItem : menuItems) {
			if( menuItem.getName().equals(existingItem.getName())) {
				toBeDeleted = menuItem;
				break;
			}
		}
		if( toBeDeleted != null )
			this.menuItems.remove(toBeDeleted);
		
		rs.write(this.menuItems, MainClass.serFile);
		
		assert menuItems.size() == size - 1;
		assert invariantCheck(); //wellFormed
	} 

	@Override
	public void editMenuItem(MenuItem existingItem, MenuItem newItem) {
		assert existingItem != null && newItem != null;
		assert menuItems.contains(existingItem);
		int size = menuItems.size();
		
		MenuItem toBeEdited = null;
		if( existingItem instanceof BaseProduct) {
			for( MenuItem menuItem : menuItems) {
				if( menuItem.getName().equals(existingItem.getName())) {
					toBeEdited = menuItem;
				}
			}
			toBeEdited.setName(newItem.getName());
			toBeEdited.setPrice(newItem.getPrice());
	
		}else {
			for( MenuItem menuItem : menuItems) {
				if( menuItem.getName().equals(existingItem.getName())) {
					toBeEdited = menuItem;
				}
			}
			toBeEdited.setName(newItem.getName());
			((CompositeProduct) toBeEdited).setProductParts( ((CompositeProduct) newItem).getProductParts());
			toBeEdited.setPrice(toBeEdited.computePrice());
			
		}
		
		for (MenuItem menuItem : menuItems) {
			menuItem.setPrice(menuItem.computePrice());
		}

		rs.write(this.menuItems, MainClass.serFile);
		
		assert menuItems.size() == size;
		assert invariantCheck(); //wellFormed
	}

	@Override
	public void createOrder(int table, ArrayList<MenuItem> orderedItems) {
		assert table > 0;
		assert orderedItems != null;
		int size = orders.keySet().size();
		
		Order o = new Order(table);
		this.orders.put(o, orderedItems);
		
		//notify chef if a composite product is ordered
//		for (MenuItem menuItem : orderedItems) {
//			if( menuItem instanceof CompositeProduct ) {
//				setChanged();
//				notifyObservers(orderedItems);
//				break;
//			}
//		}
		
		//notify chef if a order is created
		setChanged();
		notifyObservers(orderedItems);
		
		assert orders.keySet().size() == size + 1;
		assert invariantCheck(); //wellFormed
	}

	@Override
	public void generateBill(Order order) {
		assert order != null;
		assert order.getTable() > 0;
		
		
		for (Order currentOrder : this.orders.keySet()) {
			if( currentOrder.equals(order) ) {			
				FileWriterr f = new FileWriterr(order, this.orders.get(currentOrder));
				f.write();
				
				break;
			}
		}
	}

	@Override
	public float computePrice(Order order) {
		assert order != null;
		assert order.getTable() > 0; 
		
		float totalPrice = 0;
		
		for (Order currentOrder : this.orders.keySet()) {
			if( currentOrder.equals(order) ) {			
				
				for( MenuItem item : this.orders.get(currentOrder) ) {
					totalPrice += item.computePrice();
				}
				
				break;
			}
		}
		return totalPrice;
	}

	@Override
	public boolean invariantCheck() { // wellFormed()
		assert !menuItems.contains(null);
		
		if( this.menuItems == null || this.orders == null )
			return false;
		if( orders.containsKey(null) )
			return false;
		
		return !menuItems.contains(null);
	}
	
	public HashSet<MenuItem> getMenuItems() {
		return menuItems;
	}

	public void setMenuItems(HashSet<MenuItem> menuItems) {
		this.menuItems = menuItems;
	}

	public HashMap<Order, ArrayList<MenuItem>> getOrders() {
		return orders;
	}

	public void setOrders(HashMap<Order, ArrayList<MenuItem>> orders) {
		this.orders = orders;
	}

	public RestaurantSerialization getRs() {
		return rs;
	}
}

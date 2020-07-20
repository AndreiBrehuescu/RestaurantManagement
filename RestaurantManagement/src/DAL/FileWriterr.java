package DAL;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import BLL.MenuItem;
import BLL.Order;

public class FileWriterr {
	
	private Order order;
	private ArrayList<MenuItem> items;
	private HashMap<MenuItem, Integer> orderedIt;
	
	public FileWriterr(Order order, ArrayList<MenuItem> orderedItems) {
		this.order = order;
		this.items = orderedItems;
		orderedIt = computeOrderForBill(items);
	}
	
	public void write() {
			
		File file = new File("Bill_" + this.order.getOrderId() + ".txt");
	
		try {
			BufferedWriter buffer = new BufferedWriter(new FileWriter(file));
			
			buffer.write("Bill for order : " + this.order.getOrderId()) ;
			buffer.newLine();
			buffer.newLine();
			
			buffer.write("Table : " + this.order.getTable());
			buffer.newLine();
			buffer.newLine();
			
			buffer.write("Products: ");
			buffer.newLine();
			for (MenuItem menuItem : orderedIt.keySet()) {
				buffer.write( menuItem.getName() + " " + menuItem.getPrice() + "    x" 
						+ orderedIt.get(menuItem) + " : " + orderedIt.get(menuItem) * menuItem.getPrice() + " RON");
				buffer.newLine();
			}
			buffer.newLine();
			
			buffer.newLine();
			buffer.write("Total : " + order.getTotal() + " RON");
			buffer.newLine();
			buffer.newLine();
			
			buffer.write("Data : " + order.getDate());
			buffer.close();
		}catch( IOException e) {
			e.printStackTrace();
		}
	}
	
	public static HashMap<MenuItem, Integer> computeOrderForBill(ArrayList<MenuItem> item) {
		HashMap<MenuItem, Integer > orderedItems = new HashMap<>();
		boolean finded = false;
		
		for (MenuItem menuItem : item) {
			finded = false;
			if( orderedItems.keySet().size() == 0)
				orderedItems.put(menuItem, 1);
			else {
				for (MenuItem menuItem2 : orderedItems.keySet()) {
					if( menuItem.equals(menuItem2) ) {
						orderedItems.put(menuItem2, orderedItems.get(menuItem2) + 1);
						finded = true;
						break;
					}
				}
				if ( finded == false ) {
					orderedItems.put(menuItem, 1);
				}
			}
		}
		return orderedItems;
	}
}

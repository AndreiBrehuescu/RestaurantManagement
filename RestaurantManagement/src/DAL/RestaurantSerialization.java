package DAL;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashSet;

import BLL.MenuItem;
import Run.MainClass;

public class RestaurantSerialization {
	
	@SuppressWarnings("unchecked")
	public HashSet<MenuItem> read(String args) {
		HashSet<MenuItem> menuItems = null;
		
		try {
			FileInputStream file = new FileInputStream(args);
			ObjectInputStream inputStream = new ObjectInputStream(file);
			
			menuItems = (HashSet<MenuItem>) inputStream.readObject();
			
			inputStream.close();
			file.close();
			
		}catch( FileNotFoundException e) {
			System.out.println("File not found!");
			System.out.println("A file named: " + MainClass.serFile + " will be created!");
			new File(MainClass.serFile);
		}catch( IOException e) {
			//System.out.println("End of file!");
		}catch( ClassNotFoundException e ) {
			e.printStackTrace();
		}
		
		return menuItems;
	}
	
	public void write(HashSet<MenuItem> items, String args) {
		try {
			FileOutputStream file = new FileOutputStream(args);
			ObjectOutputStream outputStream = new ObjectOutputStream(file);
			
			outputStream.writeObject(items);
			
			outputStream.close();
			file.close();
			
		}catch( FileNotFoundException e) {
			e.printStackTrace();
		}catch( IOException e) {
			e.printStackTrace();
		}
		
	}
}

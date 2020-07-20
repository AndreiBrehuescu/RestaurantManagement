package BLL;
import java.util.ArrayList;
import java.util.HashSet;

public interface IRestaurantProcessing {
	
	/**
	 * @param newItem Produs de adaugat in meniul restaurantului
	 * @pre newItem != null
	 * @pre menuItems.contains(newItem) == false
	 * @post size() == size()@pre + 1
	 */
	void createMenuItem(MenuItem newItem);
	
	/**
	 * @param existingItem Produsul ce trebuie sters din meniul restaurantului
	 * @pre existingItem != null
	 * @pre menuItems.contains(existingItem) == true
	 * @post size() == size()@pre - 1
	 */
	void deleteMenuItem(MenuItem existingItem);
	
	/**
	 * @param existingItem - Produsul ce trebuie actualizat
	 * @param newItem - Noul produs
	 * @pre existingItem != null && newItem != null
	 * @pre menuItems.contains(existingItem) == true
	 * @post size() == size()@pre
	 */
	void editMenuItem(MenuItem existingItem, MenuItem newItem);
	
	/**
	 * @param table Masa pentru care va fi creata
	 * @param orderedItems Toate produsele comandate pe masa
	 * @pre table > 0
	 * @pre orderItems != null
	 * @post size() == size()@pre + 1
	 */
	void createOrder(int table, ArrayList<MenuItem> orderedItems);
	
	/**
	 * 
	 * @param order Comanda pentru care se genereaza bonul
	 * @pre order != null
	 * @pre order.table > 0
	 */
	void generateBill(Order order);

	/**
	 * @param order Comanda pentru care se calculeaza pretul
	 * @pre order != null
	 * @pre order.table > 0
	 * @return Totalul pretului pentru comanda
	 */
	float computePrice(Order order);
	
	/**
	 * @pre menuItems.contains(null) == false
	 * @return !menuItems.contains(null)
	 */
	boolean invariantCheck();
}

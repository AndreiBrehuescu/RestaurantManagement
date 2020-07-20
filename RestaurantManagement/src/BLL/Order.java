package BLL;

import java.util.Date;

public class Order {

	private int orderId;
	private Date date;
	private int table;
	private float total;
	
	static int id = 1;
	
	public Order(int orderId, Date date, int table) {
		super();
		this.orderId = orderId;
		this.date = date;
		this.table = table;
	}
	
	public Order(int table) {
		this.table = table;
		this.date = new Date();
		this.orderId = id;
		this.total = 0;
		id++;
	}

	@Override
	public int hashCode() {
		int result = 17;
		result = 31 * result + date.hashCode();
		result = 31 * result + orderId;
		result = 31 * result + table;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		
		if( !(obj instanceof Order) )
			return false;
		
		Order o = (Order) obj;
		
		if( this.orderId == o.orderId && this.date.equals(o.date) && this.table == o.table )
			return true;
		
		return false;
		
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getTable() {
		return table;
	}

	public void setTable(int table) {
		this.table = table;
	}

	public float getTotal() {
		return total;
	}

	public void setTotal(float total) {
		this.total = total;
	}
	
	
	
}

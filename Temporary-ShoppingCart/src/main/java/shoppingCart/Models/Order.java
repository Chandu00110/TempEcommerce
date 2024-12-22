package shoppingCart.Models;

public class Order extends Product {

	private int orderId;
	private int userId;
	private int quantity;
	private String Date;

	public Order() {
		
	}
	
	public Order(int orderId, int userId, int quantity, String date) {
		this.orderId = orderId;
		this.userId = userId;
		this.quantity = quantity;
		Date = date;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}


	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getDate() {
		return Date;
	}

	public void setDate(String date) {
		Date = date;
	}

	@Override
	public String toString() {
		return "Order [orderId=" + orderId + ", userId=" + userId + ", quantity=" + quantity + ", Date=" + Date + "]";
	}
}

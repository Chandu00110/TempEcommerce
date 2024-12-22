package shoppingCart.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import shoppingCart.Connection.DbCon;
import shoppingCart.Models.Order;
import shoppingCart.Models.Product;

public class OrderDao {

	private Connection con;
	private String query;
	private PreparedStatement pst;
	private ResultSet rs;
	
	public OrderDao(Connection con) {
		this.con = con;
	}
	
	public boolean insertOrders(Order order) {
		boolean result = false;
		
		try {
			query = "insert into orders(p_id,u_id,o_quantity,o_date) values(?,?,?,?)";
			pst = this.con.prepareStatement(query);
			pst.setInt(1, order.getId());
			pst.setInt(2, order.getUserId());
			pst.setInt(3, order.getQuantity());
			pst.setString(4, order.getDate());
			pst.executeUpdate();
			result = true;
		}
		catch(Exception ex) {
			System.out.println(ex.getMessage());
		}
		return result;
	}

	public List<Order> getUserOrders(int userId){
		List<Order> order = new ArrayList<Order>();
		try {
			query = "select * from orders where u_id=? order by orders.o_id desc";
			pst = this.con.prepareStatement(query);
			pst.setInt(1, userId);
			rs = pst.executeQuery();
			while(rs.next()) {
				Order addOrder = new Order();
				ProductDao pDao = new ProductDao(DbCon.getConnection());
				int p_Id = rs.getInt("p_id");
				Product product = pDao.getSingleProduct(p_Id);
				addOrder.setId(p_Id);
				addOrder.setOrderId(rs.getInt("o_id"));
				addOrder.setName(product.getName());
				addOrder.setPrice(product.getPrice());
				addOrder.setCategory(product.getCategory());
				addOrder.setQuantity(rs.getInt("o_quantity"));
				addOrder.setDate(rs.getString("o_date"));
				order.add(addOrder);				
			}
		}catch(Exception ex) {
			System.out.println(ex.getMessage());
		}
		return order;
	}

	public boolean cancelOrder(int orderId) {
		boolean result = false;
		try {
			if(orderId > 0) {
				query = "delete from orders where o_id = ?";
				pst = this.con.prepareStatement(query);
				pst.setInt(1, orderId);
				pst.execute();
				result = true;
			}
		}catch(Exception ex) {
			System.out.println(ex.getMessage());
		}
		return result;
	}
}

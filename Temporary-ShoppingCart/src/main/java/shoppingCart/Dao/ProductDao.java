
package shoppingCart.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;

import shoppingCart.Models.Cart;
import shoppingCart.Models.Product;

public class ProductDao {

	private Connection con;
	private String query;
	private PreparedStatement pst;
	private ResultSet rs;

	public ProductDao(Connection con) {
		this.con = con;
	}

	public List<Product> getAllProducts() {
		List<Product> product = new ArrayList<Product>();
		try {
			query = "select * from products";
			pst = this.con.prepareStatement(query);
			rs = pst.executeQuery();
			while (rs.next()) {
				Product row = new Product();
				row.setId(rs.getInt("id"));
				row.setName(rs.getString("name"));
				row.setCategory(rs.getString("category"));
				row.setImage(rs.getString("image"));
				row.setPrice(rs.getDouble("price"));

				product.add(row);
			}
		} catch (Exception ex) {
			ex.getMessage();
		}
		return product;
	}

	public List<Cart> getCartProducts(ArrayList<Cart> cartProducts) {
		List<Cart> products = new ArrayList<Cart>();
		try {
			if (cartProducts.size() > 0) {
				for (Cart c : cartProducts) {
					query = "select * from products where id=?";
					pst = this.con.prepareStatement(query);
					pst.setInt(1, c.getId());
					rs = pst.executeQuery();
					while (rs.next()) {
						Cart row = new Cart();
						row.setId(rs.getInt("id"));
						row.setName(rs.getString("name"));
						row.setCategory(rs.getString("category"));
						row.setPrice(rs.getDouble("price"));
						row.setQuantity(c.getQuantity());
						products.add(row);
					}
				}
			}
		} catch (Exception ex) {
			ex.getMessage();
		}
		return products;
	}

	public Product getSingleProduct(int id) {
		Product row = null;
		try {
			query = "select * from products where id=?";
			pst = this.con.prepareStatement(query);
			pst.setInt(1, id);
			rs = pst.executeQuery();
			while (rs.next()) {
				row = new Product();
				row.setId(rs.getInt("id"));
				row.setName(rs.getString("name"));
				row.setCategory(rs.getString("category"));
				row.setImage(rs.getString("image"));
				row.setPrice(rs.getDouble("price"));
			}
		} catch (Exception ex) {
			System.out.println(ex.getMessage());;
		}
		return row;
	}

	public double getCartPrice(ArrayList<Cart> cart_list){
		double sum = 0;
		try {
			if(cart_list.size() > 0) {
				for(Cart item : cart_list) {
					query = "select price from products where id=?";
					pst = this.con.prepareStatement(query);
					pst.setInt(1,item.getId());
					rs = pst.executeQuery();
					while(rs.next()) {
						sum += rs.getDouble("price") * item.getQuantity();
					}
				}
			}
		}catch(Exception ex) {
			System.out.println(ex.getMessage());
		}
		return sum;
	}
}

package com.eshopping.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import com.eshopping.model.Cart;

public class CartDAOImp implements CartDAO {
	private static final String countOfCustomerId="select count(customer_id) from cart where customer_id=?";
	private static final String url="jdbc:mysql://localhost:3306/teca63_e_shopping?user=root&password=12345";
	private static final String insertCartDetails="insert into cart (customer_id, product_id) values(?,?)";
	@Override
	public int getCustomerCartCount( int customerId) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection c=DriverManager.getConnection(url);
			PreparedStatement ps=c.prepareStatement(countOfCustomerId);
			ps.setInt(1, customerId);
			ResultSet rs=ps.executeQuery();
			if(rs.next()) {
				ResultSetMetaData meta=rs.getMetaData();
				int colCount=meta.getColumnCount();
				System.out.println("Column number in buff memory:"+colCount);
				System.out.println("Column name in buffer memory:"+meta.getColumnLabel(1));
				return rs.getInt(1);
			}
			return 0;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;

		}
	}
	@Override
	public int insertCartDetails(Cart cart) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection c=DriverManager.getConnection(url);
			PreparedStatement ps=c.prepareStatement(insertCartDetails);
			ps.setInt(1, cart.getCustomer_id());
			ps.setInt(2, cart.getProduct_id());
			return ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		} 
	}

}

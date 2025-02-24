package com.eshopping.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.eshopping.model.ProductDetails;

public class ProductDAOImp implements ProductDAO {
	private static String url="jdbc:mysql://localhost:3306/teca63_e_shopping?user=root&password=12345";
	private static String insert="insert into product_details( pro_name, pro_brand, pro_price, pro_discount, pro_category, pro_quantity)"
			+ "values(?,?,?,?,?,?)";
	private static String getAllDetails="select * from product_details";
	private static  String chooseOption="select * from product_details where  pro_name LIKE ? or pro_brand LIKE ?  or pro_category LIKE ?";

	

	@Override
	public int insertProductDetails(ProductDetails pd) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection c=DriverManager.getConnection(url);
			PreparedStatement ps=c.prepareStatement(insert);
			ps.setString(1, pd.getPro_name());
			ps.setString(2, pd.getPro_brand());
			ps.setDouble(3, pd.getPro_price());
			ps.setDouble(4, pd.getPro_discount());
			ps.setString(5, pd.getPro_category());
			ps.setInt(6, pd.getPro_quantity());
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


	@Override
	public List<ProductDetails> getAllProductDetails() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection c=DriverManager.getConnection(url);
			PreparedStatement ps=c.prepareStatement(getAllDetails);
			ResultSet rs=ps.executeQuery();
			List<ProductDetails>list=new ArrayList<ProductDetails>();
			if(rs.isBeforeFirst()) {
				while(rs.next()) {
					ProductDetails pd=new ProductDetails();
					pd.setPro_id(rs.getInt("pro_id"));
					pd.setPro_name(rs.getString("pro_name"));
					pd.setPro_brand(rs.getString("pro_brand"));
					pd.setPro_category(rs.getString("pro_category"));
					pd.setPro_discount(rs.getDouble("pro_discount"));
					pd.setPro_price(rs.getDouble("pro_price"));
					pd.setPro_quantity(rs.getInt("pro_quantity"));
					list.add(pd);
				}
				return list;
			}
			else
				return null;
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
	}


	@Override
	public List<ProductDetails> getProductsBasedOnSelection(String search) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection c = DriverManager.getConnection(url);
			PreparedStatement ps=c.prepareStatement(chooseOption);
			ProductDetails pd=new ProductDetails();
			ps.setString(1, "%"+search+"%");
			ps.setString(2, "%"+search+"%");
			ps.setString(3, "%"+search+"%");
			ResultSet rs=ps.executeQuery();
			List<ProductDetails> l=new ArrayList<ProductDetails>();
			if(rs.isBeforeFirst()) {
				while(rs.next()) {
					pd.setPro_id(rs.getInt("pro_id"));
					pd.setPro_name(rs.getString("pro_name"));
					pd.setPro_brand(rs.getString("pro_brand"));
					pd.setPro_category(rs.getString("pro_category"));
					pd.setPro_discount(rs.getDouble("pro_discount"));
					pd.setPro_price(rs.getDouble("pro_price"));
					pd.setPro_quantity(rs.getInt("pro_quantity"));
					l.add(pd);
				}
				return l;
			}
			else
				return null;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	
	}


	@Override
	public ProductDetails getProductDetailsById(int pro_id) {
		String sql ="select * from product_details where pro_id=?";
		ProductDetails pd=new ProductDetails();
		try {
			Connection c=DriverManager.getConnection(url);
			PreparedStatement ps=c.prepareStatement(sql);
			ps.setInt(1, pro_id);
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				pd.setPro_brand(rs.getString(3));
				pd.setPro_name(rs.getString(2));
				pd.setPro_price(rs.getDouble(4));
				pd.setPro_category(rs.getString(6));
				pd.setPro_discount(rs.getDouble(5));
			}
			return pd;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}

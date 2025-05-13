package com.eshopping.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.eshopping.model.CustomerDetails;

public class CustomerDAOImp implements CustomerDAO {
	private static final String url="jdbc:mysql://localhost:3306/teca63_e_shopping?user=root&password=12345";
	private static final String insert="insert into customer_details (cus_first_name,cus_last_name,cus_middle_name, cus_mobile_no, cus_email_id, cus_gender, cus_address, cus_password)"
			+ "values(?,?,?,?,?,?,?,?)";
	private static final String getDetails="select * from customer_details";
	private static final String getDetailsBasedOnEmailidAndPassword="select * from customer_details where cus_email_id=? and cus_password=?";
	private static final String forgetPassword="select * from customer_details where cus_mobile_no=?";
	private static final String updatePassword="update customer_details set cus_password = ? where cus_mobile_no = ?";
	@Override
	public int insertCustomerDetails(CustomerDetails cd) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection c=DriverManager.getConnection(url);
			PreparedStatement ps=c.prepareStatement(insert);
			ps.setString(1, cd.getCus_first_name());
			ps.setString(2, cd.getCus_last_name());
			ps.setString(3, cd.getCus_middle_name());
			ps.setLong(4, cd.getCus_mobile_no());
			ps.setString(5, cd.getCus_email_id());
			ps.setString(6, cd.getCus_gender());
			ps.setString(7, cd.getCus_address());
			ps.setString(8, cd.getCus_password());
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
	public List<CustomerDetails> getCustomerDetails() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection c=DriverManager.getConnection(url);
			PreparedStatement ps=c.prepareStatement(getDetails);
			ResultSet rs=ps.executeQuery();
			CustomerDetails cd=new CustomerDetails();
			List<CustomerDetails> list=new ArrayList<CustomerDetails>();
				if(rs.isBeforeFirst()) {
					while(rs.next()) {
					cd.setCus_email_id(rs.getString("cus_email_id"));
					cd.setCus_mobile_no(rs.getLong("cus_mobile_no"));
					cd.setCus_email_id(rs.getString("cus_password"));
					list.add(cd);
					}
					return list;	
				}
			else {
				return null;
			}		
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
	 public CustomerDetails getCustomerDetailsBasedOnEmailIdAndPassword(String emailId, String password) {

			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection c=DriverManager.getConnection(url);
				PreparedStatement ps=c.prepareStatement(getDetailsBasedOnEmailidAndPassword);
				ps.setString(1, emailId);
				ps.setString(2, password);
				ResultSet rs=ps.executeQuery();
				CustomerDetails cd=new CustomerDetails();
				if(rs.next()) {
					cd.setCus_first_name(rs.getString("cus_first_name"));
					cd.setCus_last_name(rs.getString("cus_last_name"));
					cd.setCus_middle_name(rs.getString("cus_middle_name"));
					cd.setCus_email_id(rs.getString("cus_email_id"));
					cd.setCus_password(rs.getString("cus_password"));
					cd.setCus_id(rs.getInt("cus_id"));
					cd.setCus_gender(rs.getString("cus_gender"));
				}
				return cd;
				
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}	
	}
	@Override
	public CustomerDetails getCustomerDetailsBasedOnMobileNo(long mobileNo) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection c=DriverManager.getConnection(url);
			PreparedStatement ps=c.prepareStatement(forgetPassword);
			ps.setLong(1, mobileNo);
			ResultSet rs=ps.executeQuery();
			CustomerDetails cd= new CustomerDetails();
			if(rs.next()) {
				cd.setCus_mobile_no(rs.getLong("cus_mobile_no"));
				return cd;
			}
			return null;
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
	}
	@Override
	public boolean updateCustomerPasswordByUsingMobileNo(long mobileNo, String password) {
		  if (password == null || password.trim().isEmpty()) {
		        return false;  // Or throw an exception based on your requirement
		    }
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection c=DriverManager.getConnection(url);
			PreparedStatement ps=c.prepareStatement(updatePassword);
			ps.setString(1, password);
			ps.setLong(2, mobileNo);
			int rows=ps.executeUpdate();
			return rows>0;
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

}
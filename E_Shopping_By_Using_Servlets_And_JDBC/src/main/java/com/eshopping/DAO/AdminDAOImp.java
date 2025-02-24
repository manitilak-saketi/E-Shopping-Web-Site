package com.eshopping.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.eshopping.model.Admin;

public class AdminDAOImp implements AdminDAO {
	private static String url="jdbc:mysql://localhost:3306/teca63_e_shopping?user=root&password=12345";
	private static String adminDetails="select * from admin where admin_email_id=? and admin_password=?";
	private static String getAllAdminDetails="select * from admin";
	@Override
	public boolean getAdminDetailsByUsingEmailAndPassword(String email, String password) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection c=DriverManager.getConnection(url);
			PreparedStatement ps=c.prepareStatement(adminDetails);
			ps.setString(1, email);
			ps.setString(2, password);
			ResultSet rs=ps.executeQuery();
			if(rs.next())
				return true;
			return false;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	@Override
	public List<Admin> getAllAdminDetails() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection c=DriverManager.getConnection(url);
			PreparedStatement ps=c.prepareStatement(getAllAdminDetails);
			ResultSet rs=ps.executeQuery();
			List<Admin>l=new ArrayList<Admin>();
			if(rs.isBeforeFirst()) {
				while(rs.next()) {
					Admin a=new Admin();
					a.setAdmin_email_id(rs.getString("admin_email_id"));
					a.setAdmin_password(rs.getString("admin_password"));
					l.add(a);
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


}

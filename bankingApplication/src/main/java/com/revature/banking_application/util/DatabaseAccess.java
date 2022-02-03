package com.revature.banking_application.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.revature.banking_application.models.BankUser;

public class DatabaseAccess {
	
	public static String SearchUsername(String searchValue) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String returnValue = null;
	
		try {
			conn = ConnectionFactory.getInstance().getConnection();
			String sql = ("select * from userinfo where user_login_name = '"+searchValue+"'");
			ps = conn.prepareStatement(sql);
			//ps.setString(1, searchValue);
			rs = ps.executeQuery();
			while(rs.next()) {
				returnValue = rs.getString("user_login_name");
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) { /* Ignored */}
			}
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) { /* Ignored */}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) { /* Ignored */}
			}
		}
		return returnValue;
	}
	
	public static String SearchEmail(String searchValue) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String returnValue = null;
	
		try {
			conn = ConnectionFactory.getInstance().getConnection();
			String sql = ("select * from userinfo where user_email = '"+searchValue+"'");
			ps = conn.prepareStatement(sql);
			//ps.setString(1, searchValue);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				returnValue = rs.getString("user_email");
			}
		} catch(SQLException e) {
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) { /* Ignored */}
			}
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) { /* Ignored */}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) { /* Ignored */}
			}
		}
		return returnValue;
	}

	public static Boolean CreateUser(BankUser newUser) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		boolean returnValue = false;
	
		try {
			conn = ConnectionFactory.getInstance().getConnection();
			String sql = ("insert into userinfo (user_login_name, user_first_name, user_last_name, user_email, user_password) values (?, ?, ?, ?, ?)");
			ps = conn.prepareStatement(sql);

			ps.setString(1, newUser.getUsername());
			ps.setString(2, newUser.getFirstName());
			ps.setString(3, newUser.getLastName());
			ps.setString(4, newUser.getEmail());
			ps.setString(5, newUser.getPassword());
			
			int executed = ps.executeUpdate();
			if(executed !=0) {
				returnValue = true;
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) { /* Ignored */}
			}
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) { /* Ignored */}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) { /* Ignored */}
			}
		}
		return returnValue;
	}

	public static BankUser PullUserFromUsername(String searchValue) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		BankUser returnUser = null;
	
		try {
			conn = ConnectionFactory.getInstance().getConnection();
			String sql = ("select * from userinfo where user_login_name = '"+searchValue+"'");
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				returnUser = new BankUser(rs.getString("user_login_name"), 
										  rs.getString("user_first_name"),
										  rs.getString("user_last_name"),
										  rs.getString("user_email"),
										  rs.getString("user_password"));
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) { /* Ignored */}
			}
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) { /* Ignored */}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) { /* Ignored */}
			}
		}
		return returnUser;
	}

	public static Boolean ChangePassword(BankUser bankUserToChange) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		boolean returnValue = false;
	
		try {
			conn = ConnectionFactory.getInstance().getConnection();
			String sql = ("update userinfo set user_password = ? where user_login_name = ? ");
			ps = conn.prepareStatement(sql);

			ps.setString(1, bankUserToChange.getPassword());
			ps.setString(2, bankUserToChange.getUsername());
			
			int executed = ps.executeUpdate();
			if(executed !=0) {
				returnValue = true;
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) { /* Ignored */}
			}
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) { /* Ignored */}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) { /* Ignored */}
			}
		}
		return returnValue;
	}
}

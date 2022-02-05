package com.revature.banking_application.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import com.revature.banking_application.models.AccountNodes;
import com.revature.banking_application.models.BankAccount;
import com.revature.banking_application.models.BankUser;
import com.revature.banking_application.models.Transaction;
import com.revature.banking_application.models.TransactionNodes;

public class DatabaseAccess {
	
	public static int SearchUsername(String searchValue) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int returnValue = 0;
	
		try {
			conn = ConnectionFactory.getInstance().getConnection();
			String sql = ("select * from userinfo where user_login_name = ?");
			ps = conn.prepareStatement(sql);
			ps.setString(1, searchValue);
			rs = ps.executeQuery();
			while(rs.next()) {
				returnValue = rs.getInt("user_id");
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
			String sql = ("select * from userinfo where user_email = ?");
			ps = conn.prepareStatement(sql);
			ps.setString(1, searchValue);
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
			String social = "";
			String address = "";
			String city = "";
			String state = "";
			String zip = "";
			int jointUser = 0;
 			while(rs.next()) {
				if(rs.getString("user_social") != null) {
					social = rs.getString("user_social");					 
				}
				if(rs.getString("user_address") != null) {
					address = rs.getString("user_address");
				}
				if(rs.getString("user_city") != null) {
					city = rs.getString("user_city");
				}
				if(rs.getString("user_state") != null) {
					state = rs.getString("user_state");
				}
				if(rs.getString("user_zip_code") != null) {
					zip = rs.getString("user_zip_code");
				}
				if(rs.getString("joint_user") != null) {
					jointUser = rs.getInt("joint_user");
				}
				returnUser = new BankUser(rs.getInt("user_id"),
										  rs.getString("user_login_name"), 
										  rs.getString("user_first_name"),
										  rs.getString("user_last_name"),
										  rs.getString("user_email"),
										  rs.getString("user_password"),
										  social,
										  address,
										  city,
										  state,
										  zip,
										  jointUser);
											
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

	public static Boolean UpdateUser(BankUser updateUser) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		boolean returnValue = false;
	
		try {
			conn = ConnectionFactory.getInstance().getConnection();
			String sql = ("update userinfo set user_first_name = ?, user_last_name = ?, user_email = ?, user_social = ?, user_address = ?, user_city = ?, user_state = ?, user_zip_code = ? where user_id = ?");
			ps = conn.prepareStatement(sql);

			
			ps.setString(1, updateUser.getFirstName());
			ps.setString(2, updateUser.getLastName());
			ps.setString(3, updateUser.getEmail());
			ps.setString(4, updateUser.getSocial());
			ps.setString(5, updateUser.getAddress());
			ps.setString(6, updateUser.getCity());
			ps.setString(7, updateUser.getState());
			ps.setString(8, updateUser.getZipCode());
			ps.setInt(9, updateUser.getUserID());
			
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

	public static Boolean CreateBankAccount(BankAccount newAccount) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		boolean returnValue = false;
	
		try {
			conn = ConnectionFactory.getInstance().getConnection();
			String sql = ("insert into useraccount (user_id, user_account_type, user_account_nickname, user_account_value) values (?, ?, ?, ?)");
			ps = conn.prepareStatement(sql);

			ps.setInt(1, newAccount.getUserID());
			ps.setInt(2, newAccount.getUserAccountType());
			ps.setString(3, newAccount.getAccountNickname());
			ps.setDouble(4, newAccount.getAccountValue());
			
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
	
	public static Boolean CreateJointBankAccount(BankAccount newAccount) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		boolean returnValue = false;
	
		try {
			conn = ConnectionFactory.getInstance().getConnection();
			String sql = ("insert into useraccount (user_id, user_account_type, joint_account_id, user_account_nickname, user_account_value) values (?, ?, ?, ?, ?)");
			ps = conn.prepareStatement(sql);

			ps.setInt(1, newAccount.getUserID());
			ps.setInt(2, newAccount.getUserAccountType());
			ps.setInt(3, newAccount.getJointUserID());
			ps.setString(4, newAccount.getAccountNickname());
			ps.setDouble(5, newAccount.getAccountValue());
			
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
	
	public static AccountNodes PullBankAccountsFromBankUser(BankUser currentAccount) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		AccountNodes allAccounts = new AccountNodes();
		
		try {
			conn = ConnectionFactory.getInstance().getConnection();
			String sql = ("select * from useraccount where user_id = ? or joint_account_id = ?");
			ps = conn.prepareStatement(sql);
			ps.setInt(1, currentAccount.getUserID());
			ps.setInt(2, currentAccount.getUserID());
			rs = ps.executeQuery();
			int jointUser = 0;
			
 			while(rs.next()) {
 				
 				if(rs.getString("joint_account_id") != null) {
					jointUser = rs.getInt("joint_account_id");
				}
 				BankAccount anAccount = new BankAccount(rs.getInt("user_account_id"),
										  	rs.getInt("user_id"), 
										  	jointUser,
										  	rs.getInt("user_account_type"),
										  	rs.getString("user_account_nickname"),
										  	rs.getDouble("user_account_value"));
				if(anAccount != null) {
					allAccounts.addNode(anAccount);	
				}					  	
											
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
		
		
		return allAccounts;
	}
	
	public static AccountNodes PullBankAccountsFromBankUser(int checkID) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		AccountNodes allAccounts = new AccountNodes();
		
		try {
			conn = ConnectionFactory.getInstance().getConnection();
			String sql = ("select * from useraccount where user_id = ? or joint_account_id = ?");
			ps = conn.prepareStatement(sql);
			ps.setInt(1, checkID);
			ps.setInt(2, checkID);
			rs = ps.executeQuery();
			int jointUser = 0;
			
 			while(rs.next()) {
 				
 				if(rs.getString("joint_account_id") != null) {
					jointUser = rs.getInt("joint_account_id");
				}
 				BankAccount anAccount = new BankAccount(rs.getInt("user_account_id"),
										  	rs.getInt("user_id"), 
										  	jointUser,
										  	rs.getInt("user_account_type"),
										  	rs.getString("user_account_nickname"),
										  	rs.getDouble("user_account_value"));
				if(anAccount != null) {
					allAccounts.addNode(anAccount);	
				}					  	
											
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
		
		
		return allAccounts;
	}

	public static Boolean CheckifUserHasABankAccount(BankUser currentUser) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		boolean returnValue = false;
		
		try {
			conn = ConnectionFactory.getInstance().getConnection();
			String sql = ("select * from useraccount where user_id = ? or joint_account_id = ?");
			ps = conn.prepareStatement(sql);
			ps.setInt(1, currentUser.getUserID());
			ps.setInt(2, currentUser.getUserID());
			rs = ps.executeQuery();
			
 			while(rs.next()) {					  	
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
	
	public static Boolean CheckifUserHasABankAccount(int checkID) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		boolean returnValue = false;
		
		try {
			conn = ConnectionFactory.getInstance().getConnection();
			String sql = ("select * from useraccount where user_id = ? or joint_account_id = ?");
			ps = conn.prepareStatement(sql);
			ps.setInt(1, checkID);
			ps.setInt(2, checkID);
			rs = ps.executeQuery();
			
 			while(rs.next()) {					  	
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

	public static Boolean updateJointAccounts(BankUser updateUser) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		boolean returnValue = false;
	
		try {
			conn = ConnectionFactory.getInstance().getConnection();
			String sql = ("update userinfo set joint_user = ? where user_id = ?");
			ps = conn.prepareStatement(sql);

			
			ps.setInt(1, updateUser.getJointUserID());
			ps.setInt(2, updateUser.getUserID());
			
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

	public static Boolean updateBankAccount(BankAccount updateAccount) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		boolean returnValue = false;
	
		try {
			conn = ConnectionFactory.getInstance().getConnection();
			String sql = ("update useraccount set user_account_nickname = ?, user_account_value = ? where user_account_id = ?");
			ps = conn.prepareStatement(sql);

			
			ps.setString(1, updateAccount.getAccountNickname());
			ps.setDouble(2, updateAccount.getAccountValue());
			ps.setInt(3, updateAccount.getUserAccountID());
			
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

	public static Boolean UpdateTransactionList(int accountID, Double transactionAmount, java.sql.Date date) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		boolean returnValue = false;
	
		try {
			conn = ConnectionFactory.getInstance().getConnection();
			String sql = ("insert into transactions (user_account_id, transaction_value, transaction_date) values (?, ?, ?)");
			ps = conn.prepareStatement(sql);

			ps.setInt(1, accountID);
			ps.setDouble(2, transactionAmount);
			ps.setDate(3, date);
			
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

	public static TransactionNodes ReturnAllTransactions(BankUser currentUser) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		TransactionNodes allTransaction = new TransactionNodes();
		
		try {
			conn = ConnectionFactory.getInstance().getConnection();
			String sql = ("select * from transactions natural join useraccount where user_id = ?");
			ps = conn.prepareStatement(sql);
			ps.setInt(1, currentUser.getUserID());
			rs = ps.executeQuery();
			
 			while(rs.next()) {
 				
 				Transaction transaction = new Transaction(rs.getDouble("transaction_value"),
										  	rs.getDate("transaction_date"));
				if(transaction != null) {
					allTransaction.addNode(transaction);	
				}					  	
											
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
		
		
		return allTransaction;
		
	}
}


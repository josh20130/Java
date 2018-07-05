
package com.bank.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import com.bank.pojos.Account;
import com.bank.pojos.User;
import com.bank.util.ConnectionFactory;

public class DAOImpl implements DAO{

	@Override
	public HashMap<Integer, String> getEmails() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User getUserById(int id) {
		User u = new User();
		try(Connection conn = ConnectionFactory
				.getInstance().getConnection();){
			String sql = "select * from users where u_id =  ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet info = ps.executeQuery();

			while(info.next()){
				u.setId(info.getInt(1));
				u.setFirstname(info.getString(2));
				u.setLastname(info.getString(3));
				u.setEmail(info.getString(4));
				u.setPassword(info.getString(5));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return u;
	}

	@Override
	public User addUser(String fn, String ln, String email, String pass) {
		try(Connection conn  = ConnectionFactory
				.getInstance().getConnection();){
			conn.setAutoCommit(false);

			String sql = "insert into users "
					+ "(firstname, lastname, email, password) "
					+ "values(?, ?, ?, ? )";
			String [] key = new String[1];
			key[0] = "u_id";
			PreparedStatement ps = conn.prepareStatement(sql, key);
			ps.setString(1, fn);
			ps.setString(2, ln);
			ps.setString(3, email);
			ps.setString(4, pass);

			ps.executeUpdate();
			int id = 0;
			ResultSet pk = ps.getGeneratedKeys();
			while(pk.next()){
				id = pk.getInt(1);
			}

			conn.commit();
			User u = new User(fn, ln, email, pass);
			u.setId(id);
			return u;


		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public Account createAccount(User u, double initialBal) {
		Account a = new Account();

		try(Connection conn  = ConnectionFactory
				.getInstance().getConnection();){
			conn.setAutoCommit(false);

			String sql = "insert into accounts(user_id, balance)"
					+ " values (?,?)";
			String[] key = new String[1];
			key[0] = "acc_id";
			PreparedStatement ps = conn.prepareStatement(sql, key);
			ps.setInt(1, u.getId());
			ps.setDouble(2, initialBal);

			ps.executeUpdate();
			int id = 0;
			ResultSet pk = ps.getGeneratedKeys();
			while(pk.next()){
				id = pk.getInt(1);
			}
			System.out.println("id is " + id);
			a.setId(id);
			a.setBalance(initialBal);
			//	a.setType();

			conn.commit();



		} catch (SQLException e) {
			e.printStackTrace();
		}



		return a;
	}

	@Override
	public ArrayList<Account> getAccountsByUser(User u) {
		ArrayList<Account> accounts = new ArrayList<Account>();

		try(Connection conn = ConnectionFactory
				.getInstance().getConnection();){
			String sql = "select * from accounts where user_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, u.getId());
			ResultSet info = ps.executeQuery();

			while(info.next()){
				Account temp = new Account();
				temp.setId(info.getInt(1));
				System.out.println("Account " + temp.getId());
				temp.setBalance(info.getDouble(3));
				temp.setUser(u);
				accounts.add(temp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}




		return accounts;
	}

	@Override
	public double getBalance(int id) {
		double bal = 0.0;
		try(Connection conn = ConnectionFactory
				.getInstance().getConnection();){
			String sql = "select balance from accounts where acc_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet info = ps.executeQuery();
			
			while(info.next()){
				bal = info.getDouble(1);
			}
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return bal;
	}

	@Override
	public void updateBalance(int id, double amt) {
		try(Connection conn = ConnectionFactory
				.getInstance().getConnection();){
			String sql = "update accounts set balance = ? where acc_id=?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setDouble(1, amt );
			ps.setInt(2, id);
			ps.executeUpdate();
		}
		catch(SQLException e){
			e.printStackTrace();
		}

	}

	@Override
	public User getUser(String username) {
		User u = new User();
		try(Connection conn = ConnectionFactory
				.getInstance().getConnection();){
			username = username.toLowerCase();
			String sql = "select * from users where lower(email) = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, username);
			ResultSet info = ps.executeQuery();
			
			while(info.next()){
				u.setId(info.getInt(1));
				u.setFirstname(info.getString(2));
				u.setLastname(info.getString(3));
				u.setEmail(info.getString(4));
				u.setPassword(info.getString(5));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if(u.getId() == 0) return null;
		return u;
	}

}
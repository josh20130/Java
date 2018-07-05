package com.bank.ex;

import java.util.ArrayList;

import com.bank.dao.DAO;
import com.bank.dao.DAOImpl;
import com.bank.pojos.Account;
import com.bank.pojos.User;

public class Service {
	
	static DAO dao = new DAOImpl();
	
	public User login(String username, String password) {
		User user = dao.getUser(username);
		if(user== null) return null;
		else if (user.getPassword().equals(password)) {
			return user;
		}
		else return null;
	}
	
	public User addUser(User u) {
		return dao.addUser(u.getFirstname(), u.getLastname(), u.getEmail(), u.getPassword());
	}
	
	public boolean emailExists(String email) {
		User u = dao.getUser(email);
		if(u == null) return false;
		else return true;
	}
	
	public ArrayList<Account> getAccounts (User u){
		return dao.getAccountsByUser(u);
	}

}
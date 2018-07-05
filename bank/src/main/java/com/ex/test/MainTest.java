package com.ex.test;

import java.util.ArrayList;

import com.bank.dao.DAO;
import com.bank.dao.DAOImpl;
import com.bank.pojos.Account;
import com.bank.pojos.User;

public class MainTest {

	public static void main(String[] args) {
		DAO dao = new DAOImpl();
//		User u = dao.getUserById(21);
//		//Account a = dao.createAccount(u, 1985.2);
//		ArrayList<Account> accounts = dao.getAccountsByUser(u);
//		for(Account a : accounts) {
//		System.out.println(a.toString());
//		}
//		User user = dao.getUser("test");
//		System.out.println(user.toString());
		
		dao.updateBalance(2, 5);
	}

}

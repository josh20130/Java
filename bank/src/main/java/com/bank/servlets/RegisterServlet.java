package com.bank.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bank.ex.Service;
import com.bank.pojos.User;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet{
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		Service service = new Service();
		
		ObjectMapper mapper = new ObjectMapper();
		User u = mapper.readValue(req.getInputStream(), User.class);
		
		System.out.println(u.toString());
		u = service.addUser(u);
		
		PrintWriter print = resp.getWriter();
		resp.setContentType("application/json");
		
	}

}
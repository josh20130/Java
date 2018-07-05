package com.bank.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bank.ex.Service;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebServlet("/validate")
public class ValidationServlet extends HttpServlet {
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		ObjectMapper mapper = new ObjectMapper();
		String username = mapper.readValue(req.getInputStream(), String.class);
		
		System.out.println(username);
		
		Service service = new Service();
		boolean exists = service.emailExists(username);
		
		
		PrintWriter out = resp.getWriter();
		resp.setContentType("application/json");
		
		out.write(mapper.writeValueAsString(exists));
		
	}

}
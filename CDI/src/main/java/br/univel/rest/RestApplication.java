package br.univel.rest;

import java.io.IOException;
import java.io.PrintWriter;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



@WebServlet("/restApplication")
public class RestApplication extends HttpServlet
{
	@Inject
	ItemProcessor ip;
	
	
protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try (PrintWriter pw = response.getWriter()){
			
			
			ip.execute();
			
			pw.print("enviado");
			pw.flush();
		}
		
	}
	
}
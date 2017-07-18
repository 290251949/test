package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.UserDao;
import com.entity.User;

@WebServlet("/loginroom")
public class LoginServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doGet(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String username=req.getParameter("username");
		String password=req.getParameter("password");
		User user=new User();
		user.setUsername(username);
		user.setPassword(password);
		System.out.println(user.toString());
		UserDao ud=new UserDao();
		PrintWriter out=resp.getWriter();
		if(ud.login(user)!=null){
			HttpSession session=req.getSession();
			session.setAttribute("user", ud.login(user));
			out.print("yes");
		}
		else{
			out.print("no");
		}
	}
  
}

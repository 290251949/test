package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.MsgDao;
import com.entity.Message;
/**
 * 用户与朋友之间记录的查询Servlet类
 * @author bob
 *
 */
@WebServlet("/queryrecord")
public class RecordServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String user=req.getParameter("name1");
		String friend=req.getParameter("name2");
		MsgDao mg=new MsgDao();
		ArrayList<Message> list=mg.queryRecord(user, friend);
		mg.updateRecord(friend, user);
		PrintWriter out=resp.getWriter();
		out.println("<?xml version='1.0' encoding='UTF-8'?>");
		out.println("<record>");
	    for(Message msg:list){
	        out.println("<message>");
	        out.println("<from>"+msg.getFrom()+"</from>");
	        out.println("<to>"+msg.getTo()+"</to>");
	        out.println("<msg>"+msg.getMsg()+"</msg>");
	        out.println("<isRead>"+msg.getIsRead()+"</isRead>");
	        out.println("</message>");
	    }
		out.println("</record>");
		out.flush();
		out.close();
	}
}

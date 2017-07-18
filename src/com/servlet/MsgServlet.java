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

@WebServlet("/sendmsg")
public class MsgServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String aim = req.getParameter("aim");
		//System.out.println(aim);
		if (aim.equals("send")) {
			String from = req.getParameter("from");
			String to = req.getParameter("to");
			String msg = req.getParameter("data");
			MsgDao md = new MsgDao();
			md.insertRecord(from, to, msg);
		} else if (aim.equals("receive")) {
            String name=req.getParameter("name");
            MsgDao md = new MsgDao();
            ArrayList<Message> list=md.queryMsg(name);
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
	
}

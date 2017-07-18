package com.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.entity.Message;
import com.entity.User;
import com.util.DBUtil;
public class MsgDao {
/**
 * 查询聊天记录
 */
	public ArrayList<Message> queryRecord(String name1,String name2){
		Connection conn=null;
		PreparedStatement stmt=null;
		ResultSet rs=null;
		ArrayList<Message> list=new ArrayList();
		try{
			conn=DBUtil.getConnection();
			String sql="select * from message WHERE (`from`="+"? AND `to`="+"?) OR(`from`="+"? AND `to`="+"?)";
			stmt=conn.prepareStatement(sql);
			stmt.setString(1, name1);
			stmt.setString(2, name2);
			stmt.setString(3, name2);
			stmt.setString(4, name1);
			rs=stmt.executeQuery();
			while(rs.next()){
				Message msg=new Message();
				msg.setFrom(rs.getString("from"));
				msg.setTo(rs.getString("to"));
				msg.setMsg(rs.getString("msg"));
				msg.setIsRead(rs.getInt("isRead"));
				list.add(msg);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		finally{
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return list;
	}

    
/**
 * 插入聊天记录
 * @param from
 * @param to
 * @param msg
 */
	public void insertRecord(String from,String to,String msg){
		Connection conn=null;
		PreparedStatement stmt=null;
		try{
			conn=DBUtil.getConnection();
			String sql="INSERT message VALUES( "+"?,?,?,0);";
			stmt=conn.prepareStatement(sql);
			stmt.setString(1, from);
			stmt.setString(2, to);
			stmt.setString(3, msg);
			stmt.execute();
		}catch(Exception e){
			e.printStackTrace();
		}
		finally{
			try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
/**
 * 查询还没被读到的记录	
 * @param name
 * @return
 */
	public ArrayList<Message> queryMsg(String name){
		Connection conn=null;
		PreparedStatement stmt=null;
		ResultSet rs=null;
		ArrayList<Message> list=new ArrayList();
		try{
			conn=DBUtil.getConnection();
			String sql="SELECT * FROM message WHERE `to`="+"? AND isRead=0";
			stmt=conn.prepareStatement(sql);
			stmt.setString(1, name);
			rs=stmt.executeQuery();
			while(rs.next()){
				Message msg=new Message();
				msg.setFrom(rs.getString("from"));
				msg.setTo(rs.getString("to"));
				msg.setMsg(rs.getString("msg"));
				msg.setIsRead(rs.getInt("isRead"));
				list.add(msg);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		finally{
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return list;
	}
	
	
	public void updateRecord(String from,String to){
		Connection conn=null;
		PreparedStatement stmt=null;
		try{
			conn=DBUtil.getConnection();
			String sql="UPDATE message SET isRead=1 WHERE (`from`="+"? AND `to`="+"? AND isRead=0);";
			stmt=conn.prepareStatement(sql);
			stmt.setString(1, from);
			stmt.setString(2, to);
			int flag=stmt.executeUpdate();
			System.out.println(flag);
		}catch(Exception e){
			e.printStackTrace();
		}
		finally{
			try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
    public static void main(String[] args){
    	new MsgDao().updateRecord("哈尼779", "哈尼780");
    }
}

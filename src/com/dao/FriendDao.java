package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.entity.User;
import com.util.DBUtil;

public class FriendDao {
	/**
	 * ²éÑ¯userµÄfriend
	 * @param user
	 * @return
	 */
	public ArrayList<User> queryUser(User user){
		Connection conn=null;
		PreparedStatement stmt=null;
		ResultSet rs=null;
		ArrayList<User> list=new ArrayList();
		try{
			conn=DBUtil.getConnection();
			String sql="SELECT * from user WHERE username in (SELECT name2 FROM friend WHERE name1="+"?);";
			stmt=conn.prepareStatement(sql);
			stmt.setString(1, user.getUsername());
			rs=stmt.executeQuery();
			while(rs.next()){
				User friend=new User();
				friend.setUsername(rs.getString("username"));
				friend.setPassword(rs.getString("password"));
				friend.setHead(rs.getString("head"));
				friend.setLabel(rs.getString("label"));
				list.add(friend);
			}
			sql="SELECT * from user WHERE username in (SELECT name1 FROM friend WHERE name2="+"?);";
			stmt=conn.prepareStatement(sql);
			stmt.setString(1, user.getUsername());
			rs=stmt.executeQuery();
			while(rs.next()){
				User friend=new User();
				friend.setUsername(rs.getString("username"));
				friend.setPassword(rs.getString("password"));
				friend.setHead(rs.getString("head"));
				friend.setLabel(rs.getString("label"));
				list.add(friend);
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


    public static void main(String[] args){
    	User user=new User();
    	user.setUsername("¹þÄá779");
    	ArrayList<User> list=new FriendDao().queryUser(user);
    	for(User friend:list){
    		System.out.println(friend.toString());
    	}
    }
}

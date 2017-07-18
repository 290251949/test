package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.entity.User;
import com.util.DBUtil;

public class UserDao {

	/**
	 * 查询所有的User
	 * @return user的集合
	 */
	public ArrayList<User> queryUser(){
		Connection conn=null;
		PreparedStatement stmt=null;
		ResultSet rs=null;
		ArrayList<User> list=new ArrayList();
		try{
			conn=DBUtil.getConnection();
			String sql="select * from user;";
			stmt=conn.prepareStatement(sql);
			rs=stmt.executeQuery();
			while(rs.next()){
				User user=new User();
				user.setUsername(rs.getString("username"));
				user.setPassword(rs.getString("password"));
				user.setHead(rs.getString("head"));
				user.setLabel(rs.getString("label"));
				list.add(user);
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
     * 判断用户能否登录
     * @param aUser 需要验证登录的user
     * @return
     */
    public User login(User aUser){
    	ArrayList<User> list=queryUser();
    	for(User user:list){
    		if(user.equals(aUser)){
    			System.out.println(user.toString());
    			return user;
    		}}
    	return null;
    }
	
	public static void main(String[] args){
    	//ArrayList<User> list=new UserDao().queryUser();
		User user=new User();
		user.setUsername("哈尼779");
		user.setPassword("123456789");
    		System.out.println(new UserDao().login(user));
    
    }
}

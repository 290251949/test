package com.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;



public class DBUtil {
	// jdbc������mysql���ݿ� ����ַ ���˿ں�/test���ݿ�
		private final static String url = "jdbc:mysql://127.0.0.1:3306/chatroom?useUnicode=true&characterEncoding=utf-8&useSSL=false";
		private final static String user = "root";
		private final static String password = "root";
		private static Connection conn;

		static {
			// 1.������������
			try {
				Class.forName("com.mysql.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// 2.������ݿ������
			try {
				conn = DriverManager.getConnection(url, user, password);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		public static Connection getConnection() {
			return conn;
		}
		
		public static void main(String[] args){
			try{
				Connection conn=DBUtil.getConnection();
				if(conn!=null){
					System.out.println("��������");
				}
				else{
					System.out.println("�쳣");
				}
			}catch(Exception e){
				e.printStackTrace();
			}
		}
}

package com.snai.jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;

import org.junit.Test;

public class ProcedureDemo {

	@Test
	public void callP(){
		Connection connection = null;
		CallableStatement statement = null;
		try {
			Class.forName("org.gjt.mm.mysql.Driver");
			String url="jdbc:mysql://localhost:3306/snail16";//数据库的url地址，操作的数据的地址 jdbc:mysql://dbip:port/databasename
			String user="root";//用户名
			String password="123456";//密码
			connection = DriverManager.getConnection(url, user, password);
			//获取CallStatement对象
			String sql="call p();";
			statement=connection.prepareCall(sql);
			//执行语句
			statement.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(statement!=null){
					statement.close();
				}
				if(connection!=null){
					connection.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	@Test
	public void countUsers(){
		Connection connection = null;
		CallableStatement statement = null;
		try {
			Class.forName("org.gjt.mm.mysql.Driver");
			String url="jdbc:mysql://localhost:3306/snail16";//数据库的url地址，操作的数据的地址 jdbc:mysql://dbip:port/databasename
			String user="root";//用户名
			String password="123456";//密码
			connection = DriverManager.getConnection(url, user, password);
			//获取CallStatement对象
			String sql="{call countUsersByName(?,?)}";
			statement=connection.prepareCall(sql);
			//绑定数据
			statement.setString(1, "李四");
//			//注册输出参数
			statement.registerOutParameter(2, Types.INTEGER);
			//执行语句
			statement.execute();
			//获取输出参数
			int count = statement.getInt(2);
			System.out.println("数量为："+count);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(statement!=null){
					statement.close();
				}
				if(connection!=null){
					connection.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
}

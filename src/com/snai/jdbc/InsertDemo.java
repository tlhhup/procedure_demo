package com.snai.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.Test;

public class InsertDemo {

	@Test //注解
	public void statementDemo() {
		Connection connection = null;
		Statement statement = null;
		try {
			// 1. 加载驱动-->第三方的实现JDBC规范的类加载JVM中
			Class.forName("org.gjt.mm.mysql.Driver");
			// 2. 获取数据库连接对象
			String url="jdbc:mysql://localhost:3306/snail16";//数据库的url地址，操作的数据的地址 jdbc:mysql://dbip:port/databasename
			String user="root";//用户名
			String password="123456";//密码
			connection = DriverManager.getConnection(url, user, password);
			// 3. 创建sql语句
			String sql="insert into students(stuNum,`name`) VALUES('1231231901','张三')";
			statement = connection.createStatement();
			// 4. 执行语句
			statement.execute(sql);
			// 5. 处理结果集--->select
			// 6. 关闭数据库资源
			// 7. 异常处理
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

	@Test //注解
	public void prepareStatementDemo() {
		Connection connection = null;
		PreparedStatement prepareStatement = null;
		try {
			// 1. 加载驱动-->第三方的实现JDBC规范的类加载JVM中
			Class.forName("org.gjt.mm.mysql.Driver");
			// 2. 获取数据库连接对象
			String url="jdbc:mysql://localhost:3306/snail16";//数据库的url地址，操作的数据的地址 jdbc:mysql://dbip:port/databasename
			String user="root";//用户名
			String password="123456";//密码
			connection = DriverManager.getConnection(url, user, password);
			//准备sql语句
			// 3. 创建sql语句
			String sql="insert into students(stuNum,`name`) VALUES(?,?)";
			prepareStatement = connection.prepareStatement(sql);
			//绑定数据
			prepareStatement.setString(1, "12");
			prepareStatement.setString(2, "王五");
			
			//设置任意类型的数据
//			prepareStatement.setObject(parameterIndex, x);
			// 4. 执行语句
			prepareStatement.execute();
			// 5. 处理结果集--->select
			// 6. 关闭数据库资源
			// 7. 异常处理
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(prepareStatement!=null){
					prepareStatement.close();
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

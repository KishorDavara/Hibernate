/**
 * 
 */
package com.love2code.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * @author KDavara
 *
 */
public class TestJDBC {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String JDBCurl = "jdbc:mysql://localhost:3306/hb_student_tracker?useSSL=false&serverTimezone=UTC";
		String user = "hbstudent";
		String password = "hbstudent";
		try {
		System.out.println("Connecting to database: "+JDBCurl);
		Connection connection = DriverManager.getConnection(JDBCurl,user,password);
		System.out.println("Connection successful...!");
		}
		catch(Exception e) {
			e.printStackTrace();
		}

	}

}

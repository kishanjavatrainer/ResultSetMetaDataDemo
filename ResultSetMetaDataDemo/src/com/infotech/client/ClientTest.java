package com.infotech.client;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import com.infotech.util.DBUtil;

public class ClientTest {

	public static void main(String[] args) throws SQLException {
		String SQL = "SELECT *FROM employee_table";
		try (Connection connection = DBUtil.getConnection();
				PreparedStatement ps = connection.prepareStatement(SQL,ResultSet.TYPE_SCROLL_SENSITIVE,
						ResultSet.CONCUR_UPDATABLE);ResultSet rs = ps.executeQuery()) {
			
			ResultSetMetaData metaData = rs.getMetaData();
			int columnCount = metaData.getColumnCount();
			System.out.println("Total no. of columns:"+columnCount);
			
			for (int i = 1; i <= columnCount; i++) {
				String columnName = metaData.getColumnName(i);
				String columnTypeName = metaData.getColumnTypeName(i);
				
				System.out.println(columnName+" is a type of "+columnTypeName);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	
	}

}

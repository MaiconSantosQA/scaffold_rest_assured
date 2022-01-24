package org.facily.core_commerce.core;

import java.sql.Connection;
import java.sql.DriverManager;


public class ConnectionDB {
	
	
	private ConnectionDB() {

	}

	public static Connection getConnection() {
		
		String jbdcURL = "jdbc:postgresql://localhost:5432/";
		String username = "username";
		String password = "password";
		
			try {
				Class.forName("org.postgresql.Driver");
				return DriverManager.getConnection(jbdcURL, username, password);
			} catch (Exception e) {
				e.printStackTrace();
				throw new DAOException("Erro ao conectar no banco!");
				
			}

	}


}

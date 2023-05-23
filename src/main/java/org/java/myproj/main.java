package org.java.myproj;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class main {

	public static void main(String[] args) {
			
			String url = "jdbc:mysql://localhost:3306/Nations";
			String user = "root";
			String password = "Root";
			
			try (Scanner sc = new Scanner(System.in);
					Connection con = DriverManager.getConnection(url, user, password)){
					
				
				
			    
				String sql = "SELECT countries.name, countries.country_id, regions.name, continents.name "
						+ "FROM countries "
						+ "JOIN regions " + "ON countries.region_id = regions.region_id "
						+ "JOIN continents " + "ON regions.continent_id = continents.continent_id "
						+ "WHERE countries.name LIKE ? "
						+ "ORDER BY countries.name;";
				
				try (PreparedStatement ps = con.prepareStatement(sql)) {
					
					System.out.println("Inserisci parametro di ricerca");
					String param = sc.nextLine();
					
					ps.setString(1, "%" + param + "%");
					
					try (ResultSet rs = ps.executeQuery()) {
						
						System.out.println("\nNations - ID - Regions - Continents: \n");
						
						while(rs.next()) {
							
							final String countryName = rs.getString(1);
							final int countryId = rs.getInt(2);
							final String regionName = rs.getString(3);
							final String continentName = rs.getString(4);
							System.out.println(countryName 
									+ " - " + countryId 
									+ " - " + regionName 
									+ " - " + continentName);   
					}
				} catch (SQLException ex) {}
			} catch (SQLException ex) {}
		} catch (SQLException ex) {}
	}
}

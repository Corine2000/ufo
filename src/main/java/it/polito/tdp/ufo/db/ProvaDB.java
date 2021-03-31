package it.polito.tdp.ufo.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ProvaDB {

	public static void main(String[] args) {
		
      String jdbcURL = "jdbc:mysql://localhost/ufo_sightings?user=root&password=root";
      //è una stringa che contiene tutte le informazioni per poter collegarsi al data base stesso
      //Nota: tutte le query sql possono generare un'eccezione
      
      try {
		Connection conn = DriverManager.getConnection(jdbcURL); //apro una connezione
		//Statement st= conn.createStatement(); //conterra la nostra query da mandare
		
		String sql = "SELECT DISTINCT shape " //questa è la nostra query
				+ "FROM sighting "
				+ "WHERE shape<>'' "
				+ "ORDER BY shape ASC";
		PreparedStatement st= conn.prepareStatement(sql); 
		
		String sql1= "SELECT shape,id "
				+ "FROM sighting "
				+ "WHERE  city='edna'";
		
		ResultSet result1 = st.executeQuery(sql1);
		
		ResultSet result = st.executeQuery();
		//ora devo recuperare i risultati ottenuti e salvarli in una lista
		
		List<String> listaForme = new ArrayList<>();
		
		while(result.next()) {
			String forma = result.getString("shape"); //posso fare anche getString(#numcolumn) ma conviene
			listaForme.add(forma);
			
		}
		
		String sql2 = "SELECT COUNT(*) AS cnt FROM sighting WHERE shape=? ";
		String shapescelta  = "circle";
		PreparedStatement st2= conn.prepareStatement(sql2);
		
		st2.setString(1, shapescelta);
		ResultSet result2 = st2.executeQuery();
		result2.first(); //per dire mettiti subito sulla prima riga
		System.out.println(result2.getInt("cnt"));
		
	/*	while(result1.next()) {
			String forma = result1.getString("shape"); //posso fare anche getString(#numcolumn) ma conviene
			int id = result1.getInt("id");
			System.out.println(id +" "+forma);
			
		}*/
		
		System.out.println("prima query "+listaForme);
		conn.close();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}

}

package it.polito.tdp.meteo.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import it.polito.tdp.meteo.model.Citta;
import it.polito.tdp.meteo.model.Rilevamento;

public class MeteoDAO {
	
	public List<Rilevamento> getAllRilevamenti() {

		final String sql = "SELECT Localita, Data, Umidita FROM situazione ORDER BY data ASC";

		List<Rilevamento> rilevamenti = new ArrayList<Rilevamento>();

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);

			ResultSet rs = st.executeQuery();

			while (rs.next()) {

				Rilevamento r = new Rilevamento(rs.getString("Localita"), rs.getDate("Data"), rs.getInt("Umidita"));
				rilevamenti.add(r);
			}

			conn.close();
			return rilevamenti;

		} catch (SQLException e) {

			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
	}
	public List<Citta> getAllCitta() {

		final String sql = "SELECT DISTINCT localita FROM situazione ORDER BY localita";

		List<Citta> citta = new ArrayList<Citta>();

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);

			ResultSet rs = st.executeQuery();

			while (rs.next()) {

				//Rilevamento r = new Rilevamento(rs.getString("Localita"), rs.getDate("Data"), rs.getInt("Umidita"));
				Citta c = new Citta(rs.getString("localita"));
				citta.add(c);
			}

			conn.close();
			return citta;

		} catch (SQLException e) {

			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
	}
	

	public List<Rilevamento> getAllRilevamentiLocalitaMese(int mese, Citta citta) {
		final String sql = "SELECT localita, DATA, umidita"
				+ " FROM situazione "
				+ " WHERE MONTH(data) = ? AND localita = ? ORDER BY ASC";
		
		List<Rilevamento> rilevamentiLocalitaMese = new ArrayList<Rilevamento>();
	
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			//st.setString(1, mese.getValue()); se fosse un oggetto month
			st.setString(1,Integer.toString(mese));
			st.setString(2, citta.getNome());

			ResultSet rs = st.executeQuery();

			while (rs.next()) {

				Rilevamento r = new Rilevamento(rs.getString("Localita"), rs.getDate("Data"), rs.getInt("Umidita"));
				rilevamentiLocalitaMese.add(r);
			}

			conn.close();
			return rilevamentiLocalitaMese;

		} catch (SQLException e) {

			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
		
	}
	public double getUmiditaMedia(int mese, Citta citta) {
		final String sql = "SELECT AVG(Umidita) AS U FROM situazione" //chiedo a sql di fare la media
	                      + " WHERE loclita = ? AND MONTH(data) = ?"; //chiedo di fare la media dell'umidita per ogni citta per ogni mese
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			//st.setString(1, mese.getValue()); se fosse un oggetto month
			st.setString(1,Integer.toString(mese));
			st.setString(2, citta.getNome());

			ResultSet rs = st.executeQuery();

			rs.next(); // si posizione sulla prima ed unica riga
			Double u = rs.getDouble("U");

			conn.close();
			return u;

		} catch (SQLException e) {

			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}


}

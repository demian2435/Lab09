package it.polito.tdp.borders.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import it.polito.tdp.borders.model.Border;
import it.polito.tdp.borders.model.Country;

public class BordersDAO {

	public void loadAllCountries(Map<Integer, Country> idMap) {

		String sql = "SELECT ccode, StateAbb, StateNme FROM country ORDER BY StateAbb";

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("ccode");
				if (!idMap.containsKey(id)) {
					idMap.put(id, new Country(id, rs.getString("StateNme"), rs.getString("StateAbb")));
				}
			}

			conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Error Connection Database");
		}
	}

	public Map<Country, Border> getCountryPairs(int anno, Map<Integer, Country> idMap) {

		String sql = "SELECT state1no, state2no, YEAR, conttype FROM contiguity WHERE YEAR <= ? ORDER BY year";
		Map<Country, Border> result = new HashMap<Country, Border>();

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, anno);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				Country c = idMap.get(rs.getInt("state1no"));
				if (result.containsKey(c)) {
					result.get(c).addBorder(idMap.get(rs.getInt("state2no")), rs.getInt("conttype"));
				} else {
					Border b = new Border(c);
					b.addBorder(idMap.get(rs.getInt("state2no")), rs.getInt("conttype"));
					result.put(c, b);
				}

			}

			conn.close();

			return result;

		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Error Connection Database");
		}
	}
}

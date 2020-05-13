package it.polito.tdp.borders.model;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import it.polito.tdp.borders.db.BordersDAO;

public class Model {
	private Map<Integer, Country> idMap;
	private BordersDAO dao;

	public Model() {
		idMap = new HashMap<Integer, Country>();
		dao = new BordersDAO();
		dao.loadAllCountries(idMap);
	}

	public Collection<Country> getAllCountry() {
		return idMap.values();
	}

	public Map<Country, Border> loadAllBorder(int anno) {
		return dao.getCountryPairs(anno, idMap);
	}

	public void printAllCountry() {
		idMap.forEach((k, v) -> System.out.println(v.getName()));
	}

}

package it.polito.tdp.borders.model;

import java.util.HashMap;
import java.util.Map;

public class Border {

	private Country country;
	private Map<Country, Integer> border;

	public Border(Country country) {
		this.country = country;
		border = new HashMap<Country, Integer>();
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	public Map<Country, Integer> getBorder() {
		return border;
	}

	public void setBorder(Map<Country, Integer> border) {
		this.border = border;
	}

	public void addBorder(Country country, int mode) {
		this.border.put(country, mode);
	}

	@Override
	public String toString() {
		String resC = "";
		int tot = 0;
		for (Country c : border.keySet()) {
			if (border.get(c) == 1) {
				resC = resC + "\n* " + c.getName();
				tot++;
			}
		}
		String res = country + ": " + tot + resC + "\n";
		return res;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((country == null) ? 0 : country.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Border other = (Border) obj;
		if (country == null) {
			if (other.country != null)
				return false;
		} else if (!country.equals(other.country))
			return false;
		return true;
	}

}

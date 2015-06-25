package data_representation;

public class Country {

	private String name;
	private double longitude;
	private double lattitude;
	
	/***
	 * Straightforward data class to caontain infos from the DBPedia-database
	 * @author LeonBornemann
	 *
	 */
	public Country(String name, double longitude, double lattitude) {
		super();
		this.name = name;
		this.longitude = longitude;
		this.lattitude = lattitude;
	}

	public String getName() {
		return name;
	}

	public double getLongitude() {
		return longitude;
	}

	public double getLattitude() {
		return lattitude;
	}
}

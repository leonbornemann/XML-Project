package logic;

public class Country {

	private String name;
	private double longitude;
	private double lattitude;
	
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

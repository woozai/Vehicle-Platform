package Vehicles;

public class SeaVehicle extends Vehicle implements ISeaVehicle{
	private String countryFlag;
	private boolean withWind;
	public SeaVehicle(String model, int passengers, int maxSpeed, boolean withWind, String flag, String path, String vehicleType) {
		super(model, passengers, maxSpeed, path, vehicleType);
		this.withWind = withWind;
		countryFlag = flag;
	}
	public boolean setWithWind(boolean w) {
		/*Set if the vehicle sails with or against the wind*/
		withWind = w;
		return true;
	}
	public boolean getWithWind() {
		/*Get if the vehicle sails with or against the wind*/
		return withWind;
	}

	public boolean setFlag(String flag) {
//		/*Set the vehicle flag*/
		countryFlag = flag;
		return true;
	}
	public String getFlag() {
		/*Get the vehicle flag*/
		return countryFlag;
	}
	public boolean equals(Object sv) {
		if (!(sv instanceof SeaVehicle newSV)) {
			return false;
			}
		if (!(super.equals(sv))) {
			return false;
		}
		return withWind == newSV.withWind;
	}
	public String toString() {
		String vehi = super.toString();
		if (withWind) {
			return vehi + "<br>Flag: " + countryFlag + "<br>Wind: With";
		}
		return vehi + "<br>Flag: " + countryFlag + "<br>Wind: Against";
	}
}

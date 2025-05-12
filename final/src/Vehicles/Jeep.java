package Vehicles;

public class Jeep extends GroundVehicle implements Motorized, Comercial{

	private final String licenseType;
	private int averageFuel;
	private int engineLife;

	public Jeep(String model, int maxSpeed, 
			int averageFuel, int engineLife, String path) {
		super(model, 5, maxSpeed, "dirt", 4, path, "Jeep");
		this.licenseType = "MINI";
		this.averageFuel = averageFuel;
		this.engineLife = engineLife;
	}

	public String getLicenseType() {
		/*Get license type*/
		return licenseType;
	}
	public int getAverageFuel() {
		/*Get average fuel use*/
		return averageFuel;
	}
	public boolean setAverageFuel(int af) {
		/*Set average fuel use*/
		if (af > 0) {
			averageFuel = af;
			return true;
		}
		return false;
	}
	public int getEngineLife() {
		/*Get the engine life of the vehicle*/
		return engineLife;
	}
	public boolean setEngineLife(int el) {
		/*Set the engine life of the vehicle*/
		if (el > 0) {
			engineLife = el;
			return true;
		}
		return false;
	}
	public boolean equals(Object j) {
		if (!(j instanceof Jeep newJ)) {
			return false;
			}
		if (!(super.equals(j))) {
			return false;
		}
		return licenseType.equals(newJ.licenseType)
				&& averageFuel == newJ.averageFuel 
				&& engineLife == newJ.engineLife;
		
	}
	public String toString() {
		return getClassName() + "<br>" + super.toString() +
				"<br>" + "License type: " + licenseType +
				"<br>" + "Average fuel: " + averageFuel + " km/L"+
				"<br>" + "Engin life: " + engineLife + " years";
	}
}

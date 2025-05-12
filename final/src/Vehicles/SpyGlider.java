package Vehicles;

public class SpyGlider extends AirVehicle implements NonMotorized{
	private String powerSource;
	private char energyPoints;



	public SpyGlider(String powerSource, String path) {
		super("classified", 1, 50, "Military", path, "SpyGlider");
		this.energyPoints = 'C';
		this.powerSource = powerSource;
	}

	public String getPowerSource() {
		/*Get the power source of the vehicle*/
		return powerSource;
	}
	public boolean setPowerSource(String ps) {
		/*Set the power source of the vehicle*/
		if (powerSource.equals("manual")  || powerSource.equals("auto")) {
			powerSource = ps;
			return true;
		}
		return false;
	}
	public char getEnergyPoints() {
		/*Get the engine points of the vehicle*/
		return energyPoints;
	}
	public boolean setEnergyPoints(char ep) {
		/*Set the engine points of the vehicle*/
		if (energyPoints == 'A' || energyPoints == 'B' || energyPoints == 'C') {
			energyPoints = ep;
			return true;
		}
		return false;
	}
	public boolean equals(Object sg) {
		if (!(sg instanceof SpyGlider newSG)) {
			return false;
		}
		if (!(super.equals(sg))) {
			return false;
		}
		return powerSource.equals(newSG.powerSource)  &&
				energyPoints == newSG.energyPoints;
		
	}
	public String toString() {
		return getClassName() + "<br>" + super.toString() +
				"<br>" + "Power Source: " + powerSource +
				"<br>" + "Energy points: " + energyPoints ;
	}
}

package Vehicles;

public class GameGlider extends AirVehicle implements NonMotorized {
	private String powerSource;
	private char energyPoints;



	public GameGlider(String path) {
		super("toy", 0, 10, "Civil", path, "GameGlider");
		this.energyPoints = 'A';
		this.powerSource = "manual";
	}

	public String getPowerSource() {
		/*Get the power source of the vehicle*/
		return powerSource;
	}
	public boolean setPowerSource(String ps) {
		/*Set the power source of the vehicle*/
		if (powerSource == "manual" || powerSource == "auto") {
			powerSource = ps;
			return true;
		}
		return false;
	}
	public char getEnergyPoints() {
		/*Get the vehicle energy points*/
		return energyPoints;
	}
	public boolean setEnergyPoints(char ep) {
		/*Set the vehicle energy points*/
		if (energyPoints == 'A' || energyPoints == 'B' || energyPoints == 'C') {
			energyPoints = ep;
			return true;
		}
		return false;
	}
	public boolean equals(Object gg) {
		if (!(gg instanceof GameGlider newGG)) {
			return false;
		}
		if (!(super.equals(gg))) {
			return false;
		}
		return powerSource.equals(newGG.powerSource ) &&
				energyPoints == newGG.energyPoints;
		
	}
	public String toString() {
		return getClassName() + "<br>" + super.toString() + "<br>" + "Power Source: " + powerSource +
				"<br>" + "Energy points: " + energyPoints ;
	}
}

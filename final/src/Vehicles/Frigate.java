package Vehicles;

public class Frigate extends SeaVehicle implements Motorized{
	private int averageFuel;
	private int engineLife;


	public Frigate(String model, int maxSpeed,
				   int passenger, boolean withWind, String path) {
		super(model, passenger, maxSpeed, withWind, "Israel", path, "Frigate");
		this.averageFuel = 500;
		this.engineLife = 4;

	}

	public int getAverageFuel() {
		/*Get the average fuel*/
		return averageFuel;
	}
	public boolean setAverageFuel(int af) {
		/*set  the average fuel*/
		if (af > 0) {
			averageFuel = af;
			return true;
		}
		return false;
	}
	public int getEngineLife() {
		/*Get engine life*/
		return engineLife;
	}
	public boolean setEngineLife(int el) {
		/*Set  engine life*/
		if (el > 0) {
			engineLife = el;
			return true;
		}
		return false;	
	}
	public boolean equals(Object f) {
		if (!(f instanceof Frigate)) {
			return false;
			}
		if (!(super.equals(f))) {
			return false;
		}
		Frigate newF = (Frigate)f;
		return averageFuel == newF.averageFuel 
			&& engineLife == newF.engineLife;
		
	}
	public String toString() {
		return getClassName() +"<br>"+
				super.toString() + "<br>" + "Average fuel: " +
				averageFuel + " KM/L" + "<br>" + "Engine life: " +
				engineLife + " years";

	}
}

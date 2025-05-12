package Vehicles;

public class AirVehicle extends Vehicle implements IAirVehicle{

	private String usedFor;
	
	public AirVehicle(String model, int passengers, int maxSpeed, String usedFor, String path, String vehicleType) {
		super(model, passengers, maxSpeed, path, vehicleType);
		this.usedFor = usedFor; 
	}
	public boolean setUsedFor(String uf) {
		/*Set the reason the vehicle is used for*/
		if (uf.equals("Military") || uf.equals("Civil")) {
			usedFor = uf;
			return true;
		}
		return false;
	}
	public String getUsedFor() {
		/*Get the reason the vehicle is used for*/
		return usedFor;
	}
	public boolean equals(Object av) {
		if (!(av instanceof AirVehicle newAV)) {
			return false;
			}
		if (!(super.equals(av))) {
			return false;
		}
		return usedFor.equals(newAV.usedFor);
	}
	public String toString() {
		return super.toString() + "<br> Service type: " + usedFor;
	}

}
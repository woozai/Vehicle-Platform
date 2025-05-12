package Vehicles;

public class GroundVehicle extends Vehicle implements IGroundVehicle{
	private final int wheelsNum;
	private final String roadType;
	public GroundVehicle(String model, int passengers, int maxSpeed, String roadType, int wheelsNum, String path, String vehicleType) {
		super(model, passengers, maxSpeed, path, vehicleType);
		this.roadType = roadType;
		this.wheelsNum = wheelsNum;
	}
	public int getWheelsNum() {
		/*Get vehicle wheels number*/
		return wheelsNum;
	}
	public String getRoadType() {
		/*Get vehicle road type*/
		return roadType;
	}
	public boolean equals(Object gv) {
		if (!(gv instanceof GroundVehicle newGV)) {
			return false;
			}
		if (!(super.equals(gv))) {
			return false;
		}
		return wheelsNum == newGV.wheelsNum && roadType.equals(newGV.roadType);
		
	}
	public String toString() {
		return super.toString() + "<br>Wheels number: " + wheelsNum +
				"<br>" + "Road type: " + roadType ;
	}
}

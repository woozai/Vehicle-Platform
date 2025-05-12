package Vehicles;

public abstract class Vehicle implements IVehicle {
	private int travel;
	/**
	 * Returns the model of the vehicle.
	 * @return the vehicle model
	 */
	private final String model , vehicleImagePath, vehicleType;
	private final int passengers, maxSpeed;
	/**
	 * Returns the type of the vehicle.
	 * @return the vehicle type
	 */
	public String getModel() {
		return model;
	}
	public String getVehicleType() {
		return vehicleType;
	}
	/**
	 * Returns the file path to the vehicle's image.
	 * @return the vehicle image path
	 */
	public String getVehicleImage() {
		return vehicleImagePath;
	}
	/**
	 * Returns the total travel distance of the vehicle.
	 * @return the travel distance
	 */
	public int getTravel() {
		return travel;
	}
	/**
	 * Constructs a Vehicle object with the specified model, passengers, maxSpeed,
	 * vehicleImagePath, and vehicleType.
	 * @param model            the model of the vehicle
	 * @param passengers       the number of passengers the vehicle can accommodate
	 * @param maxSpeed         the maximum speed of the vehicle
	 * @param vehicleImagePath the file path to the vehicle's image
	 * @param vehicleType      the type of the vehicle
	 */
	public Vehicle(String model, int passengers, int maxSpeed, String vehicleImagePath, String vehicleType) {
		this.vehicleType = vehicleType;
		this.travel = 0;
		this.vehicleImagePath = vehicleImagePath;
		this.model = model;
		this.passengers = passengers;
		this.maxSpeed = maxSpeed;

	}
	/**
	 * Adding km to traveled distance
	 * @param km
	 */
	public void movement(int km) {
		travel += km;
	}
	/**
	 * Sets the travel distance of the vehicle to the given value.
	 * @param km The travel distance to be set.
	 * @return true if the travel distance is set successfully, false otherwise.
	 */
	public boolean setTravel(int km) {
		/*Set the travel distance the vehicle went through*/
		if (km >= 0) {
			this.travel = km;
			return true;
		}
		return false;
	}

	public boolean equals(Object v) {
	if (!(v instanceof Vehicle newV)) {
		return false;}
		return travel == newV.travel &&
			model.equals(newV.model) &&
			passengers == newV.passengers &&
			maxSpeed == newV.maxSpeed &&
			vehicleImagePath.equals(newV.vehicleImagePath);
	}

	/**
	 * Retrieves the class name of the vehicle.
	 * @return The class name.
	 */
	public String getClassName(){
		return getClass().getSimpleName();
	}
	/**
	 * Retrieves the superclass name of the vehicle.
	 * @return The superclass name.
	 */
	public String getSuperClassName(){
		return getClass().getSuperclass().getSimpleName();
	}
	public String toString() {
	        return "Model: " + model +
	                "<br>" + "Traveled: " + travel + " Km" +
					"<br>" + "Max speed: " + maxSpeed + " Mph " +
					"<br>" + "Passengers: " + passengers;
	}
}


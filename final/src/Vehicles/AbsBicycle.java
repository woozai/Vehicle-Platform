package Vehicles;

public abstract class AbsBicycle extends GroundVehicle{

    public AbsBicycle(String model, int passengers, int maxSpeed, String roadType, String path, String vehicleType){
        super(model, passengers, maxSpeed, roadType,2, path, vehicleType);
    }
    public boolean equals(Object cy) {
        if (!(cy instanceof AbsBicycle)) {
            return false;
        }
        return super.equals(cy);
    }

    public String toString() {
        return super.toString();

    }
}

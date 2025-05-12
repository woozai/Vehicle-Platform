package Vehicles;

public abstract class VehicleDecorator implements IVehicle{
    public IVehicle getVehicle() {
        return vehicle;
    }

    protected IVehicle vehicle;
    /**
     * Constructs a VehicleDecorator object with the specified decorated vehicle.
     * @param decoratedVehicle the vehicle to be decorated
     */
    public VehicleDecorator(IVehicle decoratedVehicle) {
        this.vehicle = decoratedVehicle;
    }

    @Override
    public void movement(int km) {
        vehicle.movement(km);
    }

    @Override
    public String getVehicleImage() {
        return vehicle.getVehicleImage();
    }

    @Override
    public boolean setTravel(int km) {
        return vehicle.setTravel(km);
    }
    /**
     * Returns the type of the decorated vehicle.
     * @return the vehicle type
     */
    public String getVehicleType() {
        return vehicle.getVehicleType();
    }
}

package Vehicles;

import javax.swing.*;
import java.awt.*;

public class StatusDecorator extends VehicleDecorator{
    private String status;
    /**
     * Constructs a ColorStatusDecorator object with the specified decorated vehicle.
     *
     * @param decoratedVehicle the vehicle to be decorated
     */
    public StatusDecorator(IVehicle decoratedVehicle) {
        super(decoratedVehicle);
    }
    /**
     * Sets the status of the decorated vehicle.
     * @param status the status to be set
     */
    public void setStatus(String status){
        this.status = status;
    }
    /**
     * Returns the status of the decorated vehicle.
     * @return the status of the vehicle
     */
    public String  getStatus(){
        return status;
    }

    public String toString(){
        return vehicle.toString() + "<br>" + "Status: " + status;
    }
    @Override
    public String getClassName() {
        return vehicle.getClassName();
    }
    @Override
    public String getSuperClassName() {
        return vehicle.getSuperClassName();
    }
    @Override
    public int getTravel() {
        return vehicle.getTravel();
    }
}

package Vehicles;

import javax.swing.*;
import java.awt.*;

public class ColorDecorator extends VehicleDecorator{
    private Color color;
    /**
     * Constructs a ColorStatusDecorator object with the specified decorated vehicle.
     *
     * @param decoratedVehicle the vehicle to be decorated
     */
    public ColorDecorator(IVehicle decoratedVehicle) {
        super(decoratedVehicle);
    }
    /**
     * Sets the color of the decorated vehicle by displaying a color chooser dialog.
     */
    public void setColor(){
        color = JColorChooser.showDialog(null, "Choose your color!",  Color.WHITE);
    }
    /**
     * Returns the color of the decorated vehicle.
     * @return the color of the vehicle
     */
    public Color getColor(){
        return color;
    }
    /**
     * Sets the status of the decorated vehicle.
     * @param status the status to be set
     */
    /**
     * Returns the status of the decorated vehicle.
     * @return the status of the vehicle
     */


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

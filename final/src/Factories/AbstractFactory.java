package Factories;

import Graphics.DataPanel;
import Graphics.ImagesForVehiclePanel;
import system.Agency;
import javax.swing.*;

public abstract class AbstractFactory {
    protected DataPanel data;
    /**
     Constructs a new AbstractFactory object with the specified DataPanel.
     @param data the DataPanel object to use for creating vehicles
     */
    public AbstractFactory(DataPanel data){
        this.data = data;
    }
    /**
     Creates a vehicle of the specified type with the given parameters and adds it to the agency.
     @param vType the type of the vehicle to create
     @param model the model JTextField for the vehicle
     @param maxSpeed the maxSpeed JTextField for the vehicle
     @param passNum the passNum JTextField for the vehicle
     @param imagesAllPanel the ImagesForVehiclePanel object for displaying vehicle images
     @param a the Agency object to add the created vehicle to
     */
    public abstract void getVehicle(String vType, JTextField model, JTextField maxSpeed, JTextField passNum, ImagesForVehiclePanel imagesAllPanel, Agency a);

}

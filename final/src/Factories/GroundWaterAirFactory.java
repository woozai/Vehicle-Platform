package Factories;

import Graphics.DataPanel;
import Graphics.ImagesForVehiclePanel;
import Graphics.InputErrorOption;
import Vehicles.ColorDecorator;
import Vehicles.StatusDecorator;
import Vehicles.HybridAirplane;
import system.Agency;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static java.lang.Boolean.parseBoolean;
import static java.lang.Integer.parseInt;

public class GroundWaterAirFactory extends AbstractFactory{
    /**
     * Constructs a GroundWaterAirFactory object with the specified DataPanel.
     * @param dataPanel the DataPanel to be associated with the factory
     */
    public GroundWaterAirFactory(DataPanel dataPanel) {
        super(dataPanel);
    }
    /**
     * Creates a specific type of vehicle based on the given vehicle type and input fields.
     * Adds the created vehicle to the specified Agency.
     * @param vType           the type of vehicle to be created
     * @param model           the model of the vehicle
     * @param maxSpeed        the maximum speed of the vehicle
     * @param passNum         the passenger number of the vehicle
     * @param imagesAllPanel  the ImagesForVehiclePanel containing the vehicle images
     * @param a               the Agency to which the vehicle will be added
     */
    @Override
    public void getVehicle(String vType, JTextField model, JTextField maxSpeed, JTextField passNum, ImagesForVehiclePanel imagesAllPanel, Agency a) {
        if(vType.equals("HybridAirplane")){
            data.getAddButton().addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if(e.getSource() == data.getAddButton()){
                        if (model.getText().length() < 2){
                            new InputErrorOption("Model name must contains at least 2 character!");
                        }
                        else if (maxSpeed.getText().equals("")){
                            new InputErrorOption( "Max speed field is Empty");
                        }
                        else if (passNum.getText().equals("")){
                            new InputErrorOption( "Passenger field field is Empty");
                        }
                        else if (data.getWheelNum().getText().equals("")){
                            new InputErrorOption( "Wheels number field is Empty");
                        }
                        else if (data.getAvgFuel().getText().equals("")){
                            new InputErrorOption( "Average fuel field is Empty");
                        }
                        else if (data.getEngLife().getText().equals("")){
                            new InputErrorOption( "Engine life field is Empty");
                        }
                        else if (imagesAllPanel.getImgPath() == null){
                            new InputErrorOption( "No picture chosen");
                        }
                        else {
                            ColorDecorator v = new ColorDecorator(new StatusDecorator(new HybridAirplane(model.getText(), parseInt(passNum.getText().toString()),
                                    parseInt(maxSpeed.getText().toString()), parseBoolean((String) data.getWind().getSelectedItem()),
                                    parseInt(data.getWheelNum().getText().toString()),parseInt(data.getAvgFuel().getText().toString()),
                                    parseInt(data.getEngLife().getText().toString()), data.getFlag().getSelectedItem().toString(),
                                    imagesAllPanel.getImgPath())));
                            v.setColor();
                            ((StatusDecorator)v.getVehicle()).setStatus("Available");
                            a.addVehicle(v,"added","Hybrid Airplane");
                        }
                    }
                }
            });

        }
    }
}

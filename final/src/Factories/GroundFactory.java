package Factories;

import Graphics.DataPanel;
import Graphics.ImagesForVehiclePanel;
import Graphics.InputErrorOption;
import Vehicles.*;
import system.Agency;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static java.lang.Integer.parseInt;

public class GroundFactory extends AbstractFactory{
    /**
     * Constructs a GroundFactory with the specified DataPanel.
     * @param data the DataPanel used for vehicle creation
     */
    public GroundFactory(DataPanel data) {
        super(data);
    }
    /**
     * Creates a ground vehicle based on the given vehicle type and input fields.
     * @param vType           the type of the vehicle
     * @param model           the JTextField containing the model name
     * @param maxSpeed        the JTextField containing the maximum speed
     * @param passNum         the JTextField containing the passenger number
     * @param imagesAllPanel  the ImagesForVehiclePanel containing the vehicle images
     * @param a               the Agency to which the vehicle will be added
     */
    @Override
    public void getVehicle(String vType, JTextField model, JTextField maxSpeed, JTextField passNum, ImagesForVehiclePanel imagesAllPanel, Agency a) {
        switch (vType) {
            case "Jeep" -> {
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
                                ColorDecorator v = new ColorDecorator(new StatusDecorator(new Jeep(data.getModel().getText(),
                                        parseInt(data.getMaxSpeed().getText().toString()),
                                        parseInt(data.getAvgFuel().getText().toString()),
                                        parseInt(data.getEngLife().getText().toString()),
                                        imagesAllPanel.getImgPath())));
                                v.setColor();
                                ((StatusDecorator)v.getVehicle()).setStatus("Available");
                                a.addVehicle(v,"added","Jeep");
                            }
                        }
                    }
                });
            }
            case "NonMotorBicycle" -> {
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
                            else if (passNum.getText().equals("")) {
                                new InputErrorOption("Passenger field is Empty");
                            }
                            else if (imagesAllPanel.getImgPath() == null){
                                new InputErrorOption( "No picture chosen");
                            }
                            else {
                                ColorDecorator v = new ColorDecorator(new StatusDecorator(new NonMotorBicycle(model.getText(),
                                        parseInt(maxSpeed.getText().toString()),
                                        parseInt(passNum.getText().toString()),
                                        data.getRoadType().getSelectedItem().toString(),
                                        imagesAllPanel.getImgPath())));
                                v.setColor();
                                ((StatusDecorator)v.getVehicle()).setStatus("Available");
                                a.addVehicle(v,"added","Non motorized bicycle");
                            }
                        }
                    }
                });
            }
            case "ElectricBicycle" -> {
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
                                new InputErrorOption( "Passenger field is Empty");
                            }
                            else if (imagesAllPanel.getImgPath() == null){
                                new InputErrorOption( "No picture chosen");
                            }
                            else if (data.getEngLife().getText() == null){
                                new InputErrorOption( "Engin life field is Empty");
                            }
                            else {
                                ColorDecorator v = new ColorDecorator(new StatusDecorator(new ElectricBicycle(model.getText(),
                                        parseInt(maxSpeed.getText().toString()),
                                        parseInt(passNum.getText().toString()),
                                        data.getRoadType().getSelectedItem().toString(),
                                        parseInt(data.getEngLife().getText().toString()),
                                        imagesAllPanel.getImgPath())));
                                v.setColor();
                                ((StatusDecorator)v.getVehicle()).setStatus("Available");
                                a.addVehicle(v, "added","Electric bicycle");
                            }
                        }
                    }
                });
            }
        }
    }
}

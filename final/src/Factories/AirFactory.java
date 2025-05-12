package Factories;

import Graphics.DataPanel;
import Graphics.ImagesForVehiclePanel;
import Graphics.InputErrorOption;
import Vehicles.*;
import system.Agency;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AirFactory extends AbstractFactory{
    /**
     * Constructs a new AirFactory object with the specified DataPanel.
     *
     * @param dataPanel the DataPanel object to use for creating air vehicles
     */
    public AirFactory(DataPanel dataPanel) {
        super(dataPanel);
    }
    /**
     * Creates an air vehicle of the specified type with the given parameters and adds it to the agency.
     * @param vType           the type of the air vehicle to create
     * @param model           the model JTextField for the vehicle
     * @param maxSpeed        the maxSpeed JTextField for the vehicle
     * @param passNum         the passNum JTextField for the vehicle
     * @param imagesAllPanel  the ImagesForVehiclePanel object for displaying vehicle images
     * @param a               the Agency object to add the created vehicle to
     */

    @Override
    public void getVehicle(String vType, JTextField model, JTextField maxSpeed, JTextField passNum, ImagesForVehiclePanel imagesAllPanel, Agency a) {
        if(vType.equals("SpyGlider")){
            data.getAddButton().addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if(e.getSource() == data.getAddButton()){
                        if (data.getPwrSrc().getText().length() < 2){
                            new InputErrorOption("Model name must contains at least 2 character!");
                        }
                        else if (imagesAllPanel.getImgPath() == null){
                            new InputErrorOption( "No picture chosen");
                        }
                        else{
                            ColorDecorator v = new ColorDecorator(new StatusDecorator(new SpyGlider(data.getPwrSrc().getText(), imagesAllPanel.getImgPath())));
                            v.setColor();
                            ((StatusDecorator)v.getVehicle()).setStatus("Available");
                            a.addVehicle(v, "added","Spy glider");
                        }
                    }
                }
            });
        }
        else if(vType.equals("GameGlider")){
            data.getAddButton().addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if(e.getSource() == data.getAddButton()){
                        if (imagesAllPanel.getImgPath() == null){
                            new InputErrorOption( "No picture chosen");
                        }
                        else {
                            ColorDecorator v = new ColorDecorator(new StatusDecorator(new GameGlider(imagesAllPanel.getImgPath())));
                            v.setColor();
                            ((StatusDecorator)v.getVehicle()).setStatus("Available");
                            a.addVehicle(v,"added","Game Glider");
                        }
                    }
                }
            });
        }

    }
}

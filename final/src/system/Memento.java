package system;

import Vehicles.ColorDecorator;
import Vehicles.StatusDecorator;
import Vehicles.IVehicle;
import Vehicles.SeaVehicle;

import java.util.ArrayList;

public class Memento {
    private final IVehicle[] vehicles;
    private final int[] vehiclesKM;
    private final ArrayList<String> vehiclesFlags;
    private final int agencyTotalKM;

    /**

     Retrieves the list of flags for the vehicles.
     @return an ArrayList of strings representing the flags for the vehicles
     */
    public ArrayList<String> getVehiclesFlags() {
        return vehiclesFlags;
    }
    /**

     Retrieves the array of kilometers traveled for each vehicle.
     @return an array of integers representing the kilometers traveled for each vehicle
     */
    public int[] getVehiclesKM() {
        return vehiclesKM;
    }
    /**

     Retrieves the total kilometers traveled by the agency.
     @return the total kilometers traveled
     */
    public int getAgencyTotalKM() {
        return agencyTotalKM;
    }

    /**

     Retrieves the array of vehicles stored in the memento.
     @return an array of IVehicle objects representing the vehicles
     */
    public IVehicle[] getVehicles() {
        return vehicles;
    }
    /**
     Constructs a new Memento object with the provided vehicles and totalKM.
     @param vehicles an array of IVehicle objects representing the vehicles
     @param totalKM the total kilometers traveled by the agency
     */
    public Memento(IVehicle[] vehicles, int totalKM) {
        this.vehiclesFlags = new ArrayList<>();
        this.vehicles = new IVehicle[vehicles.length];
        vehiclesKM = new int[vehicles.length];
        for (int i = 0; i < vehicles.length; i++) {
            vehiclesKM[i] = vehicles[i].getTravel();
            this.vehicles[i] = vehicles[i];
            if (vehicles[i].getSuperClassName().equals("SeaVehicle")){
                vehiclesFlags.add(((SeaVehicle)((StatusDecorator)((ColorDecorator)vehicles[i]).getVehicle()).getVehicle()).getFlag());
            }
        }
        this.agencyTotalKM = totalKM;
    }
}

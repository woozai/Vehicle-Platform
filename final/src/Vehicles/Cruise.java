package Vehicles;

public class Cruise extends SeaVehicle implements Motorized, Comercial{
    private final String licenseType;

    private int averageFuel;
    private int engineLife;
    public Cruise(String model, int  maxSpeed, int passengers,
                int averageFuel, int engineLife, String flagName, String path) {
        super(model, passengers, maxSpeed,true, flagName, path, "Cruise");
        this.licenseType = "unlimited";
        this.averageFuel = averageFuel;
        this.engineLife = engineLife;
    }


    public String getLicenseType() {
        /*Get license type*/
        return licenseType;
    }
    public int getAverageFuel() {
        /*Get average fuel use*/
        return averageFuel;
    }
    public boolean setAverageFuel(int af) {
        /*Set average fuel use*/
        if (af > 0) {
            averageFuel = af;
            return true;
        }
        return false;
    }
    public int getEngineLife() {
        /*Get the engine life of the vehicle*/
        return engineLife;
    }
    public boolean setEngineLife(int el) {
        /*Set the engine life of the vehicle*/
        if (el > 0) {
            engineLife = el;
            return true;
        }
        return false;
    }
    public boolean equals(Object c) {
        if (!(c instanceof Cruise)) {
            return false;
        }
        if (!(super.equals(c))) {
            return false;
        }
        Cruise newC = (Cruise)c;
        return licenseType.equals(newC.licenseType)
                && averageFuel == newC.averageFuel
                && engineLife == newC.engineLife;

    }
    public String toString() {
        return getClassName() +
                "<br>"+ super.toString() + "<br>" + "License type: " +
                licenseType + "<br>" + "Average fuel: " +
                averageFuel + " km/L" + "<br>" + "Engine life: " +
                engineLife + " years";
    }



}

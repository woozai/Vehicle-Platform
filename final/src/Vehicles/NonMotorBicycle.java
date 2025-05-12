package Vehicles;

public class NonMotorBicycle extends AbsBicycle implements NonMotorized {
    private String powerSource;
    private char energyPoints;


    public NonMotorBicycle(String model, int passengers, int maxSpeed, String roadType, String path) {
        super(model, passengers, maxSpeed, roadType, path, "NonMotorBicycle");
        this.powerSource = "manual";
        this.energyPoints = 'A';
    }


    public String getPowerSource() {
        /*Get the power source of the vehicle*/
        return powerSource;
    }

    public boolean setPowerSource(String ps) {
        /*Set the power source of the vehicle*/
        if (powerSource.equals("manual")  || powerSource.equals("auto")) {
            powerSource = ps;
            return true;
        }
        return false;
    }

    public char getEnergyPoints() {
        /*Get the engine points of the vehicle*/
        return energyPoints;
    }
    public boolean setEnergyPoints(char ep) {
        /*Set the engine points of the vehicle*/
        if (energyPoints == 'A' || energyPoints == 'B' || energyPoints == 'C') {
            energyPoints = ep;
            return true;
        }
        return false;
    }
    public boolean equals(Object cy) {
        if (!(cy instanceof NonMotorBicycle newCY)) {
            return false;
        }
        if (!(super.equals(cy))) {
            return false;
        }
        return powerSource.equals(newCY.powerSource)  &&
                energyPoints == newCY.energyPoints;

    }
    public String toString() {
        return getClassName() + "<br>" + super.toString() +
                "<br>" + "Power Source: " + powerSource +
                "<br>" + "Energy points: " + energyPoints;

    }
}

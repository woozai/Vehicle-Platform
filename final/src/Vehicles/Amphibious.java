package Vehicles;

public class Amphibious extends SeaVehicle implements IGroundVehicle, Motorized{
    private final GroundVehicle gv;
    private int averageFuel;
    private int engineLife;

    public Amphibious(String model, int passengers, int maxSpeed, boolean withWind,
                      int wheelsNum, int averageFuel, int engineLife, String flag, String path){
        super(model, passengers, maxSpeed, withWind, flag, path, "Amphibious" );
        gv = new GroundVehicle(model,passengers,maxSpeed, "paved",wheelsNum, path, "Amphibious");
        this.averageFuel = averageFuel;
        this.engineLife = engineLife;
    }
    public GroundVehicle getGv() {
        return gv;
    }


    public int getWheelsNum() {
        return gv.getWheelsNum();
    }

    public String getRoadType() {
        return gv.getRoadType();
    }

    public int getAverageFuel() {
        return averageFuel;
    }

    public boolean setAverageFuel(int af) {
        averageFuel = af;
        return true;
    }
    public int getEngineLife() {
        return engineLife;
    }

    public boolean setEngineLife(int el) {
        engineLife = el;
        return true;
    }
    public boolean equals(Object a){
        if (!(a instanceof Amphibious newA)) {
            return false;
        }
        if (!(super.equals(a))) {
            return false;
        }
        return  gv.equals(newA.getGv())
                && averageFuel == newA.averageFuel
                && engineLife == newA.engineLife;
    }
    public String toString(){
        return  getClassName() + "<br>"+ super.toString() + "<br>" + "Wheels number: "
                + getWheelsNum() +  "<br>" + "Road type: " + getRoadType() ;

    }
}

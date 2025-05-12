package Vehicles;

public class HybridAirplane extends Amphibious implements IAirVehicle{

    private final AirVehicle av;

    public HybridAirplane(String model, int passengers, int maxSpeed, boolean withWind, int wheelsNum, int averageFuel, int engineLife, String flag, String path) {
        super(model, passengers, maxSpeed, withWind, wheelsNum, averageFuel, engineLife, flag, path);
        av = new AirVehicle(model,passengers,maxSpeed,"Military",path, "HybridAirplane");
    }
    public AirVehicle getAv() {
        return av;
    }

    public String getUsedFor() {
        return av.getUsedFor();
    }

    public boolean setUsedFor(String uf) {
        return av.setUsedFor(uf);
    }

    public boolean equals(Object a){
        if (!(a instanceof HybridAirplane newA)) {
            return false;
        }
        if (!(super.equals(a))) {
            return false;
        }
        return av.equals(newA.getAv());

    }
    public String toString(){
        return  getClassName() + "<br>" + "\b\b\bFIX\b\b\b" + super.toString()
                + "<br>" + "Service type: " + getUsedFor();
}
}
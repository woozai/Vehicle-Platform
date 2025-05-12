package Vehicles;

public class ElectricBicycle extends AbsBicycle implements Motorized{

    private int averageFuel;
    private int engineLife;

    public ElectricBicycle(String model, int passengers, int maxSpeed, String roadType, int engineLife,  String path) {
        super(model, passengers, maxSpeed, roadType, path, "ElectricBicycle");
        this.averageFuel = 20;
        this.engineLife = engineLife;
    }

    @Override
    public int getAverageFuel() {
        return averageFuel;
    }

    @Override
    public boolean setAverageFuel(int af) {
        averageFuel = af;
        return true;
    }

    @Override
    public int getEngineLife() {
        return engineLife;
    }

    @Override
    public boolean setEngineLife(int el) {
        engineLife = el;
        return true;
    }

    public boolean equals(Object cy) {
        if (!(cy instanceof ElectricBicycle newCY)) {
            return false;
        }
        if (!(super.equals(cy))) {
            return false;
        }
        return averageFuel == newCY.getAverageFuel()  &&
                engineLife == newCY.getEngineLife();

    }
    public String toString() {
        return getClassName() + "<br>" + super.toString() +
                "<br>" + "Average fuel: " + averageFuel + " km/L"+
                "<br>" + "Engin life: " + engineLife + " years";

    }
}

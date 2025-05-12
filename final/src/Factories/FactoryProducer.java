package Factories;

import Graphics.DataPanel;

public class FactoryProducer {
    /**
     * Returns the corresponding factory based on the given factory type.
     * @param fType      the type of the factory
     * @param dataPanel  the DataPanel object to pass to the factory constructor
     * @return the AbstractFactory object based on the factory type, or null if the type is not recognized
     */
    public static AbstractFactory getFactory(String fType, DataPanel dataPanel) {
        if (fType.equalsIgnoreCase("Sea"))
            return new WaterFactory(dataPanel);
        else if (fType.equalsIgnoreCase("Ground"))
            return new GroundFactory(dataPanel);
        else if (fType.equalsIgnoreCase("GroundSea"))
            return new GroundWaterFactory(dataPanel);
        else if (fType.equalsIgnoreCase("Air"))
            return new AirFactory(dataPanel);
        else if (fType.equalsIgnoreCase("GroundSeaAir"))
            return new GroundWaterAirFactory(dataPanel);
        else return null;
    }
}

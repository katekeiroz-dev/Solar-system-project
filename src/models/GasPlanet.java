package models;

import utils.CoreCompositionUtility;
import utils.Utilities;

public class  GasPlanet extends Planet {

    private String gasComposition = null;
    private String coreComposition = "Unknown";
    private double radiationLevel = 0.9;

    public GasPlanet(String name, double mass, double diameter,  double averageTemperature, String surfaceType, boolean hasLiquidWater, String gasComposition, String coreComposition, double radiationLevel)
    {
        super(name,mass,diameter,averageTemperature,surfaceType,hasLiquidWater);// Call the constructor of the superclass Planet to initialize inherited properties
        // Initialize GasPlanet- specific properties
        this.gasComposition = gasComposition;
        this.coreComposition = coreComposition;
        this.radiationLevel = radiationLevel;

    }

    public String getGasComposition() {
        return gasComposition;
    }

    public void setGasComposition(String gasComposition) {
        this.gasComposition = Utilities.truncateString(gasComposition, 20);
    }

    public String getCoreComposition() {
        return coreComposition;
    }

    public void setCoreComposition(String coreComposition) {
        if (CoreCompositionUtility.isValidCoreType(coreComposition)) {
            this.coreComposition = coreComposition;
        }

    }

    public double getRadiationLevel() {
        return radiationLevel;
    }

    public void setRadiationLevel(double radiationLevel) {

        if(Utilities.validRange(radiationLevel,.01,200.05)){
            this.radiationLevel = radiationLevel;
        }
    }

    // overriding the method that is originally implemented in its superclass(i.e Planet)
    // which is a clear example of polymorphism !
    @Override
    public String displayInfo() {

        return "";
    }

    @Override
    public void classifyBody() {

    }

    @Override
    public String toString() {
        return super.toString() +
                ", GasComposition: " + gasComposition +
                ", CoreComposition: " + coreComposition +
                ", RadiationLevel: " + radiationLevel;
    }


}



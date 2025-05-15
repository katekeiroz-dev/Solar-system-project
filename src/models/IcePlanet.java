package models;

import utils.CoreCompositionUtility;
import utils.Utilities;

public class IcePlanet extends Planet {
    private String iceComposition = null;

    public IcePlanet(String name, double mass, double diameter,  double averageTemperature, String surfaceType, boolean hasLiquidWater, String iceComposition)
    {
        super(name,mass,diameter,averageTemperature,surfaceType,hasLiquidWater);// Call the constructor of the superclass Planet to initialize inherited properties
        // Initialize IcePlanet- specific properties
        this.iceComposition = iceComposition;

    }


    public String getIceComposition() {
        return iceComposition;
    }

    public void setIceComposition(String iceComposition) {
        this.iceComposition = Utilities.truncateString(iceComposition, 30);

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
}

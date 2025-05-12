package models;

public class  GasPlanet extends Planet {

    private String gasComposition = null;
    private String coreComposition = "Unknown";
    private double radiationLevel = 0.9;

    public GasPlanet(String name, double mass, double diameter,  double averageTemperature, String surfaceType, boolean hasLiquidWater, String gasComposition, String coreComposition, double radiationLevel)
    {
        super(name,mass,diameter,averageTemperature,surfaceType,hasLiquidWater);// this method is calling the Planet class constructor;
        this.coreComposition = coreComposition;
        this.gasComposition = gasComposition;
        this.radiationLevel = radiationLevel;

    }
    @Override
    public String displayInfo() {
        return "";
    }

    @Override
    public void classifyBody() {

    }
}

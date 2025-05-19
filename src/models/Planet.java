package models;

import utils.Utilities;

public abstract class Planet {

   private int id = 0;
   private boolean hasLiquidWater = false;
   private String surfaceType = null;
   private double averageTemperature = 0.0;
   private double mass  = 0.1;
   private static int nextId  = 1000;
   private String name = null;
   private  double diameter = 0.5;

    public Planet(String surfaceType,double averageTemperature,double mass,double diameter,String name,boolean hasLiquidWater){
        setSurfaceType(surfaceType);
        setAverageTemperature(averageTemperature);
        setMass(mass);
        setDiameter(diameter);
        setName(name);
        setHasLiquidWater(false);

        this.id = nextId++;
    }
//super.toString() gets the base Planet info
//Appends extra fields for the specific subclass
    @Override
    public String toString() {
        return "Name: " + name +
                ", Mass: " + mass +
                ", Diameter: " + diameter +
                ", AvgTemp: " + averageTemperature +
                ", Surface: " + surfaceType +
                ", HasWater: " + hasLiquidWater;
    }


    public int getId() {

        return id;
    }

    public void setId(int id) {

        this.id = id;
    }

    public boolean isHasLiquidWater() {

        return hasLiquidWater;
    }

    public void setHasLiquidWater(boolean hasLiquidWater) {

        this.hasLiquidWater = hasLiquidWater;
    }

    public String getSurfaceType() {
        return surfaceType;
    }

    public void setSurfaceType(String surfaceType) {
        
        this.surfaceType = Utilities.truncateString(surfaceType,20);
    }

    public double getAverageTemperature() {
        
        return averageTemperature;
    }

    public void setAverageTemperature(double averageTemperature) {
        // this if statement from below line ,replaces my previous manual implementation(see last commits)
        if(Utilities.validRange(averageTemperature,-399, 399)){
            this.averageTemperature = averageTemperature;
        }

    }

    public double getMass() {

        return mass;
    }

    public void setMass(double mass) {
        //we only store values above 0.1. if it's lower than 0.1 we do nothing .
        // this way, we are only storing valid values.
        if(mass > 0.1) {
            this.mass = mass;
        }

    }

    public static int getNextId() {

        return nextId;
    }

    public static void setNextId(int nextId) {

        Planet.nextId = nextId;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = Utilities.truncateString(name , 30);
    }

    public double getDiameter() {
        return diameter;
    }

    public void setDiameter(double diameter) {
        if(diameter > 0.5){
            this.diameter = diameter;
        }

    }

    public abstract String displayInfo();
    public abstract void classifyBody();


    public double calculateGravity(){

        return (this.mass * 6.67430e-11)/(Math.pow(this.diameter,2));
    }

    //public String truncate(String input, int maxLength) {
      //  return input.length() <= maxLength ? input : input.substring(0, maxLength);
    //}


}

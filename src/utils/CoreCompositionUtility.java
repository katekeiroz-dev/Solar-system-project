package utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CoreCompositionUtility {

    private static Map<String, String> coreTypes = new HashMap<>(){{
        put("Rocky and Metallic".toUpperCase(), "Composed of heavier elements like rock and nickel-iron alloy");
        put("Proportionally Small".toUpperCase(), "While the cores are large in absolute terms" +
                ", they are proportionally smaller compared to the cores of terrestrial planets");
        put("Liquid Metallic Hydrogen".toUpperCase(), "Surrounding the core is a layer of liquid hydrogen, " +
                "which becomes metallic under the immense pressure of the planet's interior");
        put("Compressed Hydrogen".toUpperCase(), "The outermost layer of gas giants is made up of compressed molecular hydrogen");
        put("Ice Giant".toUpperCase(), "Their interiors are believed to contain a mixture of rock, water, methane, and ammonia");
    }};


    public static boolean isValidCoreType(String type) {
        //must not be case sensitive
        if(coreTypes.containsKey(type.toUpperCase()))
            return true;

        return false;
    }
    public static String getCoreDescription(String type) {
        //must not be case sensitive
        if(coreTypes.containsKey(type.toUpperCase()))
            coreTypes.get(type);

        return "No Description for this type";
    }
}

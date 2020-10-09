package ru.appline.logic;

import java.util.HashMap;
import java.util.Map;

public class CompassModel {
    private static final CompassModel instance = new CompassModel();

    private static final String North = "North";
    private static final String NorthEast = "NorthEast";
    private static final String East = "East";
    private static final String SouthEast = "SouthEast";
    private static final String South = "South";
    private static final String SouthWest = "SouthWest";
    private static final String West = "West";
    private static final String NorthWest = "NorthWest";

    private final Map<String,String> compass;

    public static CompassModel getInstance() {
        return instance;
    }

    public CompassModel() {
        compass = new HashMap<String, String>();
        compass.put(North,"");
        compass.put(NorthEast,"");
        compass.put(East,"");
        compass.put(SouthEast,"");
        compass.put(South,"");
        compass.put(SouthWest,"");
        compass.put(West,"");
        compass.put(NorthWest,"");
    }

    public boolean addRanges(Map<String,String> param){
        try {
            compass.put(North,param.get("North"));
            compass.put(NorthEast,param.get("NorthEast"));
            compass.put(East,param.get("East"));
            compass.put(SouthEast,param.get("SouthEast"));
            compass.put(South,param.get("South"));
            compass.put(SouthWest,param.get("SouthWest"));
            compass.put(West,param.get("West"));
            compass.put(NorthWest,param.get("NorthWest"));
            return true;
        }catch (Exception ex){
            System.out.print(ex.getMessage());
            return false;
        }
    }

    public HashMap<String,String> getSide(Integer degrees){
        if (degrees >= 0 && degrees <= 360) {
            try {
                HashMap<String, String> response = new HashMap<String, String>();
                for (Map.Entry<String, String> entry : compass.entrySet()) {
                    int degrees1 = Integer.parseInt(entry.getValue().split("-")[0]);
                    int degrees2 = Integer.parseInt(entry.getValue().split("-")[1]);
                    if (degrees >= degrees1 && degrees <= degrees2) {
                        response.put("Side", entry.getKey());
                    }
                }
                return response;
            }catch (Exception ex){
                System.out.print(ex.getMessage());
                return null;
            }
        }else {
            return null;
        }
    }

    public Map<String, String> getCompass() {
        return compass;
    }
}

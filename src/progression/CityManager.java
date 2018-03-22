/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package progression;

/**
 *
 * @author Ewan
 */
public class CityManager {

    public static final int max_cities = 3;
    public static city cityArray[] = new city[max_cities];
    
    public static void start() {
        for (int i = 0;i <max_cities; i++) {
            cityArray[i] = new city();
        }
    }
    
    public static void addCity() {
        
    }

    public static class city {

        String name;
        int population;
        int xPos;
        int yPos;
        int radius;
    }
}

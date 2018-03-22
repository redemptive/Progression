/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package progression;

import java.awt.Image;

/**
 *
 * @author Computer
 */
public class InteriorManager {

    public static final int interiorX = 3;
    public static final int interiorY = 3;
    public static int[][] shopInterior = new int[interiorX][interiorY];
    public static Image[] interiorSprites = new Image[2];
    private static int pXHolder = 0;
    private static int pYHolder = 0;

    public static void Start() {
        for (int i = 0; i < interiorX; i++) {
            for (int j = 0; j < interiorY; j++) {
                shopInterior[i][j] = 0;
            }
        }
        shopInterior[1][2] = 1;
    }
    public static void enterInterior(){
        pXHolder = Progression.playerPosX;
        pYHolder = Progression.playerPosY;
        Progression.playerPosX = 0;
        Progression.playerPosY = 0;
        Progression.interior = true;
    }
    public static void exitInterior(){
        Progression.playerPosX = pXHolder;
        Progression.playerPosY = pYHolder;
        Progression.interior = false;
    }
}

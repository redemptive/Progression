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
public class ItemHandler {

    public static final int itemQuantity = 6;
    public static gameItem itemArray[] = new gameItem[itemQuantity];

    public static void start() {
        for (int i = 0; i < itemQuantity; i++) {
            itemArray[i] = new gameItem();
        }
        itemArray[0].name = "Logs";
        itemArray[0].craftable = false;
        
        itemArray[1].name = "Shelter";
        itemArray[1].craftable = true;
        itemArray[1].component[0] = 0;
        itemArray[1].amount[0] = 7;
        
        itemArray[2].name = "Stone";
        itemArray[2].craftable = false;
        
        itemArray[3].name = "Copper";
        itemArray[3].craftable = false;
        
        itemArray[4].name = "Iron";
        itemArray[4].craftable = false;
        
        itemArray[5].name = "Campfire";
        itemArray[5].craftable = true;
        itemArray[5].component[0] = 0;
        itemArray[5].amount[0] = 3;
    }

    public static class gameItem {

        String name;
        int inventoryNo;
        Image sprite;
        boolean craftable;
        int[] component = new int[2];
        int[] amount = new int[2];
        int craftingScreenPosY;
    }
}

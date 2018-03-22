/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package progression;

/**
 *
 * @author Computer
 */
public class CraftingEngine {

    public static int craftItems[] = new int[ItemHandler.itemQuantity];
    public static int craftNo = 0;

    public static boolean craftCheck(int itemNo) {
        if ((ItemHandler.itemArray[itemNo].craftable == true) && (itemNo != 0)) {
            if ((ItemHandler.itemArray[ItemHandler.itemArray[itemNo].component[0]].inventoryNo) >= (ItemHandler.itemArray[itemNo].amount[0])) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public static void craftByMouse(int mX, int mY) {
        for (int i = 0; i < (ItemHandler.itemQuantity - 1); i++) {
            if ((mX < 300) && (mY > ItemHandler.itemArray[i].craftingScreenPosY) && (mY < (ItemHandler.itemArray[i].craftingScreenPosY + Progression.height))) {
                if (craftCheck(i)) {
                    craft(i);
                }
            }
        }
    }

    public static void craft(int itemNo) {
        if (ItemHandler.itemArray[itemNo].craftable == true) {
            if ((ItemHandler.itemArray[ItemHandler.itemArray[itemNo].component[0]].inventoryNo) > ItemHandler.itemArray[itemNo].amount[0]) {
                ItemHandler.itemArray[ItemHandler.itemArray[itemNo].component[0]].inventoryNo = -ItemHandler.itemArray[itemNo].amount[0];
                ItemHandler.itemArray[itemNo].inventoryNo += 1;
            }
        }
    }

    public static void updateCraftItems() {
        craftNo = 0;
        for (int i = 0; i < ItemHandler.itemQuantity; i++) {
            if (craftCheck(i) == true) {
                craftItems[craftNo] = i;
                craftNo++;
            }
        }
    }
}

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
public class MapManager {

    public static String textBox;
    public static bgSprite bgSpriteArray[] = new bgSprite[ResourceHandler.bgSpriteNo];

    public static void start() {
        for (int i = 0; i < ResourceHandler.bgSpriteNo; i++) {
            bgSpriteArray[i] = new bgSprite();
        }

        bgSpriteArray[0].name = "Stone";
        bgSpriteArray[0].intersectable = true;
        bgSpriteArray[0].destroysTo = 0;
        bgSpriteArray[0].givesItem = 0;
        bgSpriteArray[0].value = 1;

        bgSpriteArray[1].name = "Dirt";
        bgSpriteArray[1].intersectable = true;
        bgSpriteArray[1].destroysTo = 0;
        bgSpriteArray[1].givesItem = 0;
        bgSpriteArray[1].value = 1;

        bgSpriteArray[2].name = "Tree";
        bgSpriteArray[2].intersectable = false;
        bgSpriteArray[2].destroysTo = 12;
        bgSpriteArray[2].givesItem = 0;
        bgSpriteArray[2].value = 2;

        bgSpriteArray[3].name = "Grass";
        bgSpriteArray[3].intersectable = true;
        bgSpriteArray[3].destroysTo = 0;
        bgSpriteArray[3].givesItem = 0;
        bgSpriteArray[3].value = 0;

        bgSpriteArray[4].name = "Flower Grass";
        bgSpriteArray[4].intersectable = true;
        bgSpriteArray[4].destroysTo = 0;
        bgSpriteArray[4].givesItem = 0;
        bgSpriteArray[4].value = 0;

        bgSpriteArray[5].name = "Tree Stump";
        bgSpriteArray[5].intersectable = true;
        bgSpriteArray[5].destroysTo = 0;
        bgSpriteArray[5].givesItem = 0;
        bgSpriteArray[5].value = 0;

        bgSpriteArray[6].name = "House";
        bgSpriteArray[6].intersectable = false;
        bgSpriteArray[6].destroysTo = 0;
        bgSpriteArray[6].givesItem = 0;
        bgSpriteArray[6].value = 0;

        bgSpriteArray[7].name = "Water";
        bgSpriteArray[7].intersectable = true;
        bgSpriteArray[7].destroysTo = 0;
        bgSpriteArray[7].givesItem = 0;
        bgSpriteArray[7].value = 2;

        bgSpriteArray[8].name = "Rock";
        bgSpriteArray[8].intersectable = false;
        bgSpriteArray[8].destroysTo = 1;
        bgSpriteArray[8].givesItem = 2;
        bgSpriteArray[8].value = 1;

        bgSpriteArray[9].name = "Copper";
        bgSpriteArray[9].intersectable = false;
        bgSpriteArray[9].destroysTo = 8;
        bgSpriteArray[9].givesItem = 3;
        bgSpriteArray[9].value = 3;

        bgSpriteArray[10].name = "Iron";
        bgSpriteArray[10].intersectable = false;
        bgSpriteArray[10].destroysTo = 8;
        bgSpriteArray[10].givesItem = 4;
        bgSpriteArray[10].value = 4;

        bgSpriteArray[12].name = "Tree Stump";
        bgSpriteArray[12].intersectable = true;
        bgSpriteArray[12].destroysTo = 0;
        bgSpriteArray[12].givesItem = 0;
        bgSpriteArray[12].value = 0;

        bgSpriteArray[13].name = "Fire";
        bgSpriteArray[13].intersectable = false;
        bgSpriteArray[13].destroysTo = 0;
        bgSpriteArray[13].givesItem = 0;
    }

    public static boolean AllowMove(int x, int y) {
        if ((x >= 0) && (x < Progression.mapSize) && (y >= 0) && (y < Progression.mapSize)) {
            if (Progression.interior == false) {
                return bgSpriteArray[Progression.tiles[x][y]].intersectable;
            } else if ((Progression.playerPosX < (InteriorManager.interiorX)) && (Progression.playerPosY < (InteriorManager.interiorY))) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public static int getSurrounds(int tile, int radius, int xPos, int yPos, boolean includeCenter) {
        int count = 0;
        for (int i = xPos - radius; i < xPos + (radius + 1); i++) {
            for (int j = yPos - radius; j < yPos + (radius + 1); j++) {
                if ((xPos < Progression.mapSize) && (xPos < Progression.mapSize)) {
                    if ((Progression.tiles[i][j] == tile) && (i != xPos) && (j != yPos)) {
                        count++;
                    } else if ((Progression.tiles[i][j] == tile) && (i == xPos) && (j == yPos) && (includeCenter)) {
                        count++;
                    }
                }
            }
        }
        return count;
    }

    public static class bgSprite {

        String name;
        int destroysTo;
        int givesItem;
        boolean intersectable;
        Image spriteImage;
        int value;
    }

    public static void actionPress(int tilex, int tiley) {
        if (tilex >= 0 && tiley >= 0) {
            if (bgSpriteArray[Progression.tiles[tilex][tiley]].destroysTo != 0) {
                ItemHandler.itemArray[bgSpriteArray[Progression.tiles[tilex][tiley]].givesItem].inventoryNo++;
                textBox = ItemHandler.itemArray[bgSpriteArray[Progression.tiles[tilex][tiley]].givesItem].name + " " + ItemHandler.itemArray[bgSpriteArray[Progression.tiles[tilex][tiley]].givesItem].inventoryNo;
                Progression.tiles[tilex][tiley] = bgSpriteArray[Progression.tiles[tilex][tiley]].destroysTo;
            } else if (Progression.tiles[tilex][tiley] == 6) {
                InteriorManager.enterInterior();
            } else {
                textBox = "No Effect on " + bgSpriteArray[Progression.tiles[tilex][tiley]].name;
            }
        }
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package progression;

import java.util.Random;

/**
 *
 * @author Computer
 */
public class MapBuilder {

    static Random rand = new Random();
    private static int tileHolder;
    private static int nextRand;
    private static int clusterX;
    private static int clusterY;
    private static int holderX;
    private static int holderY;

    public static void start() {
        System.out.println("Map builder starting:");
        drawCluster(2, 3, 5, rand.nextInt(4) + 2);
        drawVillage(5, rand.nextInt(31), rand.nextInt(31));
        drawCluster(7, 0, 3, rand.nextInt(3) + 2);
        drawCluster(8, 3, 3, rand.nextInt(3) + 2); //rocks
        drawCluster(9, 3, 3, rand.nextInt(3) + 2); //copper
        drawCluster(10, 3, 3, rand.nextInt(3) + 2); //iron
        drawLine(1);
        drawLine(7);
        fillGaps();
        NpcHandler.spawnNpc(10);
        System.out.println("Map built.");
        InteriorManager.Start();
    }

    private static void drawVillage(int size, int posX, int posY) {
        System.out.println("Village at " + posX + "." + posY);
        for (int i = posX - size; i <= posX + size; i++) {
            for (int j = posY - size; j <= posY + size; j++) {
                if ((i > 0) && (i < Progression.mapSize) && (j > 0) && (j < Progression.mapSize)) {
                    if ((i == posX) && (j == posY)) {
                        Progression.tiles[i][j] = 13;
                    } else if ((Progression.tiles[i][j] != 6) && (MapManager.getSurrounds(13, 1, i, j, true) == 0)) {
                        if ((Progression.tiles[i][j - 1] != 6) && (Progression.tiles[i - 1][j] != 6) && (Progression.tiles[i + 1][j] != 6) && (Progression.tiles[i][j + 1] != 6)) {
                            if (rand.nextBoolean() && (MapManager.getSurrounds(6, 1, i, j, false) <= 1)) {
                                Progression.tiles[i][j] = 6;
                            } else {
                                Progression.tiles[i][j] = 1;
                            }
                        } else {
                            Progression.tiles[i][j] = 1;
                        }
                    }
                }
            }
        }
    }

    private static void drawConnectingLine(int startX, int startY, int endX, int endY, int tile) {
        holderX = startX;
        holderY = startY;
        Progression.tiles[holderX][holderY] = tile;
        while ((holderX != endX) && (holderY != endY)) {
            if (holderX > endX) {
                holderX--;
            } else if (holderX < endX) {
                holderX++;
            }
            if (holderY > endY) {
                holderY--;
            } else if (holderY < endY) {
                holderY++;
            }
            Progression.tiles[holderX][holderY] = tile;
        }
    }

    private static void drawLine(int spriteNo) {
        tileHolder = rand.nextInt(31);
        Progression.tiles[0][tileHolder] = 1;
        for (int x = 1; x < Progression.mapSize; x++) {
            nextRand = rand.nextInt(3) - 1;
            if (((tileHolder + nextRand) > 1) && ((tileHolder + nextRand) < 31)) {
                tileHolder = tileHolder + nextRand;
            } else if ((tileHolder + nextRand) < 1) {
                tileHolder = 0;
            } else {
                tileHolder = 31;
            }
            Progression.tiles[x][tileHolder] = spriteNo;
            Progression.tiles[x - 1][tileHolder] = spriteNo;
        }
    }

    private static void drawCluster(int SpriteNo, int Density, int size, int number) {
        for (int i = 0; i < number; i++) {
            clusterX = rand.nextInt(Progression.mapSize);
            clusterY = rand.nextInt(Progression.mapSize);
            System.out.print(MapManager.bgSpriteArray[SpriteNo].name + " at " + clusterX + ".");
            System.out.println(clusterY + " Density: " + Density + " Size: " + size);
            for (int x = clusterX - size; x < clusterX + size; x++) {
                for (int y = clusterY - size; y < clusterY + size; y++) {
                    if ((x >= 0) && (x < Progression.mapSize) && (y >= 0) && (y < Progression.mapSize)) {
                        if (Density == 0) {
                            Progression.tiles[x][y] = SpriteNo;
                        } else if (rand.nextInt(Density) > 1) {
                            Progression.tiles[x][y] = SpriteNo;
                        }
                    }
                }
            }
        }
    }

    private static void fillGaps() {
        System.out.println("Filling gaps in map...");
        for (int x = 0; x < Progression.mapSize; x++) {
            for (int y = 0; y < Progression.mapSize; y++) {
                if (Progression.tiles[x][y] == 0) {
                    if ((rand.nextInt(7) + 1) == 1) {
                        Progression.tiles[x][y] = 2;
                    }
                    Progression.tiles[x][y] = rand.nextInt(3) + 3;
                }
            }
        }
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package progression;

import java.awt.Image;
import java.util.Random;
import java.awt.Color;

/**
 *
 * @author Computer
 */
public class NpcHandler {

    static Random rand = new Random();
    public static final int NpcNumber = 10;
    public static Npc npcArray[] = new Npc[NpcNumber];
    private static int nextRandX;
    private static int nextRandY;
    private static int npcDelay = 0;
    public static Image NpcSprites[] = new Image[3];

    public static class Npc {

        int posX;
        int posY;
        int sprite;
        boolean movable = true;
        boolean enemy = false;
    }

    public static void Start() {
        for (int i = 0; i < 3; i++) {
            NpcSprites[i] = Renderer.makeColorTransparent(NpcSprites[i], Color.BLACK);
        }
    }

    public static int getNpcSprite(int x, int y) {
        for (int i = 0; i < NpcNumber; i++) {
            if ((x == npcArray[i].posX) && (y == npcArray[i].posY)) {
                return npcArray[i].sprite;
            }
        }
        return 0;
    }

    public static void spawnNpc(int number) {
        System.out.println("Spawning NPCs");
        for (int i = 0; i < NpcNumber; i++) {
            npcArray[i] = new Npc();
            npcArray[i].posX = rand.nextInt(Progression.mapSize);
            npcArray[i].posY = rand.nextInt(Progression.mapSize);
            npcArray[i].sprite = (rand.nextInt(3));
        }
    }

    public static void moveAllNpc(int delay) {
        if (npcDelay > delay) {
            for (int i = 0; i < NpcNumber; i++) {
                nextRandX = 1 - (rand.nextInt(3));
                nextRandY = (rand.nextInt(3)) - 1;
                if (MapManager.AllowMove((npcArray[i].posX + nextRandX), (npcArray[i].posY + nextRandY))) {
                    npcArray[i].posX = npcArray[i].posX + nextRandX;
                    npcArray[i].posY = npcArray[i].posY + nextRandY;
                }
            }
            npcDelay = 0;
        } else {
            npcDelay++;
        }
    }
}

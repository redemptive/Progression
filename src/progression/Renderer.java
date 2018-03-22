/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package progression;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.FilteredImageSource;
import java.awt.image.ImageFilter;
import java.awt.image.ImageProducer;
import java.awt.image.RGBImageFilter;

/**
 *
 * @author Computer
 */
public class Renderer {
    
    private static int currentTileX = 0;
    private static int currentTileY = 0;
    private static int tileMinX = 0;
    private static int tileMinY = 0;
    private static int brightness;
    
    public static void inventory(Graphics2D g) {
        g.setColor(Color.white);
        g.fillRect(0, 0, Progression.width, Progression.height);
        g.setColor(Color.BLACK);
        g.drawString("Inventory", 0, 10);
        for (int i = 0; i < ItemHandler.itemQuantity; i++) {
            g.drawString(ItemHandler.itemArray[i].name + ": " + ItemHandler.itemArray[i].inventoryNo, 0, (i * 50) + 20);
        }
    }
    
    public static void crafting(Graphics2D g) {
        g.setColor(Color.white);
        g.fillRect(0, 0, Progression.width, Progression.height);
        CraftingEngine.updateCraftItems();
        for (int i = 1; i < ItemHandler.itemQuantity; i++) {
            if (CraftingEngine.craftCheck(i) == true) {
                g.setColor(Color.green);               
            } else {
                g.setColor(Color.red);
            }
            g.drawImage(ItemHandler.itemArray[i].sprite, 0, (i * Progression.dispSizeHeight) + (i * 10), null);
            ItemHandler.itemArray[i].craftingScreenPosY = (i * Progression.dispSizeHeight) + (i * 10);
            g.drawString(ItemHandler.itemArray[i].name + " Required: " + ItemHandler.itemArray[ItemHandler.itemArray[i].component[0]].name + ItemHandler.itemArray[i].amount[0], 0, (i * Progression.dispSizeHeight) + 10);
        }
    }
    
    public static void pauseScreen(Graphics2D g) {
        g.setColor(Color.white);
        g.fillRect((Progression.width / 4), (Progression.height / 4), (Progression.width / 2), (Progression.height / 2));
    }
    
    public static void hud(Graphics2D g) {
        g.setColor(Color.BLACK);
        g.fillRect(20, Progression.height - 200, Progression.width - 40, 120);
        g.setColor(Color.white);
        for (int i = 0; i < 4; i++) {
            g.fillRect(i * 110 + 30, Progression.height - 190, 100, 100);
        }
        g.setColor(Color.black);
        g.drawString("Inventory (i)", 40, Progression.height - 140);
        g.drawString("Crafting (c)", 150, Progression.height - 140);
        g.drawString("Health: ", 20, 20);
    }
    
    public static void map(Graphics2D g, int playerPosX, int playerPosY) {
        if (Progression.pause == false) {
            if (playerPosX < 6) {
                tileMinX = 0;
            } else if (playerPosX > Progression.mapSize - 8) {
                tileMinX = Progression.mapSize - 9;
            } else {
                tileMinX = playerPosX - 6;
            }
            if (playerPosY < 3) {
                tileMinY = 0;
            } else if (playerPosY > Progression.mapSize - 7) {
                tileMinY = Progression.mapSize - 7;
            } else {
                tileMinY = playerPosY - 3;
            }
            for (int i = tileMinX; i < tileMinX + 14; i++) {
                for (int j = tileMinY; j < tileMinY + 7; j++) {
                    g.drawImage(MapManager.bgSpriteArray[Progression.tiles[i][j]].spriteImage, currentTileX, currentTileY, null);
                    if ((i == playerPosX) && (j == playerPosY)) {
                        g.drawImage(PlayerHandler.TPlayer[PlayerHandler.getSpriteNo()], currentTileX, currentTileY, null);
                        
                    }
                    currentTileY = currentTileY + Progression.dispSizeHeight;
                }
                currentTileY = 0;
                currentTileX = currentTileX + Progression.dispSizeWidth;
            }
            drawAllNpc(g, tileMinX, tileMinY);
            NpcHandler.moveAllNpc(25);
            currentTileX = 0;
            currentTileY = 0;
            blackOverlay(TimeManager.gTHours, g);
            hud(g);
        }
    }
    
    public static void interior(Graphics2D g) {
        g.setColor(Color.black);
        g.fillRect(0, 0, Progression.width, Progression.height);
        for (int i = 0; i < InteriorManager.interiorX; i++) {
            for (int j = 0; j < InteriorManager.interiorY; j++) {
                if ((i == Progression.playerPosX) && (j == Progression.playerPosY)) {
                    g.drawImage(InteriorManager.interiorSprites[InteriorManager.shopInterior[i][j]], i * 100, j * 100, null);
                    g.drawImage(PlayerHandler.TPlayer[PlayerHandler.getSpriteNo()], i * 100, j * 100, null);
                } else {
                    g.drawImage(InteriorManager.interiorSprites[InteriorManager.shopInterior[i][j]], i * 100, j * 100, null);
                }
            }
        }
        if (Progression.playerPosY >= InteriorManager.interiorY) {
            InteriorManager.exitInterior();
        }
    }
    
    public static void drawAllNpc(Graphics2D g, int tileMinXin, int tileMinYin) {
        for (int i = 0; i < NpcHandler.NpcNumber; i++) {
            if ((NpcHandler.npcArray[i].posX < (tileMinXin + 14)) && (NpcHandler.npcArray[i].posY < (tileMinYin + 7))) {
                if ((NpcHandler.npcArray[i].posX > (tileMinXin)) && (NpcHandler.npcArray[i].posY > (tileMinYin))) {
                    g.drawImage(NpcHandler.NpcSprites[NpcHandler.npcArray[i].sprite], ((NpcHandler.npcArray[i].posX - tileMinXin) * Progression.dispSizeWidth), (NpcHandler.npcArray[i].posY - tileMinYin) * Progression.dispSizeHeight, null);
                }
            }
        }
    }
    
    private static void blackOverlay(int hour, Graphics2D g) {
        if (hour > 12) {
            brightness = (int) (0 + ((hour - 12) * 10));
        }
        if (hour < 12) {
            brightness = (int) (0 + ((12 - hour) * 10));
        }
        if (hour == 12) {
            brightness = 0;
        }
        g.setColor(new Color(0, 0, 0, brightness));
        g.fillRect(0, 0, Progression.width, Progression.height);
    }
    
    public static Image makeColorTransparent(Image im, final Color color) {
        ImageFilter filter = new RGBImageFilter() {
            // the color we are looking for... Alpha bits are set to opaque
            public int markerRGB = color.getRGB() | 0xFF000000;
            
            @Override
            public final int filterRGB(int x, int y, int rgb) {
                if ((rgb | 0xFF000000) == markerRGB) {
                    // Mark the alpha bits as zero - transparent
                    return 0x00FFFFFF & rgb;
                } else {
                    // nothing to do
                    return rgb;
                }
            }
        };
        
        ImageProducer ip = new FilteredImageSource(im.getSource(), filter);
        return Toolkit.getDefaultToolkit().createImage(ip);
    }
    
}

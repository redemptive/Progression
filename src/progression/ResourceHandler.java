/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package progression;

import java.awt.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author Ewan
 */
public class ResourceHandler {

    public static BufferedImage bigimg;
    public static final int bgSpriteNo = 16;
    public static final int spriteSize = 64;
    public static Image[] playerSprites = new Image[16];
    private static final int spriteHolder = 0;

    public static void start() {
        System.out.println("Resource Handler starting: ");
        MapManager.start();
        BufferedImage bigimg = null;
        try {
            bigimg = ImageIO.read(new File("Res/Spritesheet.png"));
        } catch (IOException ex) {
            Logger.getLogger(ResourceHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (bigimg != null) {
            System.out.println("Spritesheet loaded");
        }
        for (int j = 0; j < 2; j++) {
            for (int i = 0; i < 8; i++) {
                MapManager.bgSpriteArray[((j * 8) + i)].spriteImage = bigimg.getSubimage(i * spriteSize, j * spriteSize, spriteSize, spriteSize);
                MapManager.bgSpriteArray[((j * 8) + i)].spriteImage = MapManager.bgSpriteArray[((j * 8) + i)].spriteImage.getScaledInstance(Progression.dispSizeWidth, Progression.dispSizeHeight, 0);
                if (MapManager.bgSpriteArray[((j * 8) + i)].spriteImage != null) {
                    System.out.println("Sprite " + ((j * 8) + i) + " loaded");
                }
            }
        }
        bigimg = null;
        try {
            bigimg = ImageIO.read(new File("Res/ItemSpritesheet.png"));
        } catch (IOException ex) {
            Logger.getLogger(ResourceHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (bigimg != null) {
            System.out.println("Item Spritesheet loaded");
        }
        for (int i = 0; i < (ItemHandler.itemQuantity); i++) {
            ItemHandler.itemArray[i].sprite = bigimg.getSubimage(i * spriteSize, 0, spriteSize, spriteSize);
            ItemHandler.itemArray[i].sprite = ItemHandler.itemArray[i].sprite.getScaledInstance(Progression.dispSizeWidth, Progression.dispSizeHeight, 0);
            ItemHandler.itemArray[i].sprite = Renderer.makeColorTransparent(ItemHandler.itemArray[i].sprite, Color.black);
            if (ItemHandler.itemArray[i].sprite != null) {
                System.out.println("Item Sprite " + i + " loaded");
            }
        }
        bigimg = null;
        try {
            bigimg = ImageIO.read(new File("Res/PlayerSpritesheet.png"));
        } catch (IOException ex) {
            Logger.getLogger(ResourceHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (bigimg != null) {
            System.out.println("Player Spritesheet loaded");
        }
        for (int j = 0; j < 2; j++) {
            for (int i = 0; i < (8); i++) {
                ResourceHandler.playerSprites[((j * 8) + i)] = bigimg.getSubimage(i * spriteSize, j * spriteSize, spriteSize, spriteSize);
                ResourceHandler.playerSprites[((j * 8) + i)] = ResourceHandler.playerSprites[((j * 8) + i)].getScaledInstance(Progression.dispSizeWidth, Progression.dispSizeHeight, 0);
                if (ResourceHandler.playerSprites[((j * 8) + i)] != null) {
                    System.out.println("Player Sprite " + ((j * 8) + i) + " loaded");
                }
            }
        }
        bigimg = null;
        try {
            bigimg = ImageIO.read(new File("Res/InteriorSpritesheet.png"));
        } catch (IOException ex) {
            Logger.getLogger(ResourceHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (bigimg != null) {
            System.out.println("Interior Spritesheet loaded");
        }
        for (int i = 0; i < (2); i++) {
            InteriorManager.interiorSprites[i] = bigimg.getSubimage(i * spriteSize, 0, spriteSize, spriteSize);
            InteriorManager.interiorSprites[i] = InteriorManager.interiorSprites[i].getScaledInstance(Progression.dispSizeWidth, Progression.dispSizeHeight, 0);
            if (InteriorManager.interiorSprites[i] != null) {
                System.out.println("Interior Sprite " + i + " loaded");
            }
        }
        bigimg = null;
        try {
            bigimg = ImageIO.read(new File("Res/NPCSpritesheet.png"));
        } catch (IOException ex) {
            Logger.getLogger(ResourceHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (bigimg != null) {
            System.out.println("Npc Spritesheet loaded");
        }
        for (int i = 0; i < (3); i++) {
            NpcHandler.NpcSprites[i] = bigimg.getSubimage(i * spriteSize, 0, spriteSize, spriteSize);
            NpcHandler.NpcSprites[i]= NpcHandler.NpcSprites[i].getScaledInstance(Progression.dispSizeWidth, Progression.dispSizeHeight, 0);
            if (NpcHandler.NpcSprites[i] != null) {
                System.out.println("NPC Sprite " + i + " loaded");
            }
        }
    }
}

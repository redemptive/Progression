/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package progression;

import java.awt.Color;
import java.awt.Image;

/**
 *
 * @author Computer
 */
public class PlayerHandler {

    public static int pMoveSpeed = 10;
    public static int pHealth = 100;
    public static Image TPlayer[] = new Image[16];
    private static int animateCounterRight = 0;
    private static int animateCounterLeft = 0;
    private static int animateCounterUp = 0;
    private static int animateCounterDown = 0;

    public static int getSpriteNo() {
        if (Progression.tiles[Progression.playerPosX][Progression.playerPosY] != 7) {
            switch (InputHandler.lastMove) {
                case 1:
                    if (animateCounterUp > 10 && animateCounterUp < 14) {
                        return 3;
                    } else if (animateCounterUp > 13) {
                        return 8;
                    } else {
                        return 9;
                    }
                case 2:
                    if (animateCounterRight > 10) {
                        return 1;
                    } else {
                        return 4;
                    }
                case 3:
                    if (animateCounterDown > 7 && animateCounterDown < 14) {
                        return 6;
                    } else if (animateCounterDown > 13) {
                        return 7;
                    } else {
                        return 0;
                    }
                case 4:
                    if (animateCounterLeft > 10) {
                        return 2;
                    } else {
                        return 5;
                    }
                default:
                    return 0;
            }
        } else {
            return 10;
        }
    }

    public static void Start() {
        for (int i = 0; i < 16; i++) {
            TPlayer[i] = Renderer.makeColorTransparent(ResourceHandler.playerSprites[i], Color.BLACK);
        }
    }

    public static void toggleWalkAnimation(int direction) {
        switch (direction) {
            case 1:
                if (animateCounterUp < 21) {
                    animateCounterUp++;
                } else {
                    animateCounterUp = 0;
                }
                animateCounterRight = 0;
                animateCounterDown = 0;
                animateCounterLeft = 0;
                break;
            case 2:
                if (animateCounterRight < 21) {
                    animateCounterRight++;
                } else {
                    animateCounterRight = 0;
                }
                animateCounterUp = 0;
                animateCounterDown = 0;
                animateCounterLeft = 0;
                break;
            case 3:
                if (animateCounterDown < 21) {
                    animateCounterDown++;
                } else {
                    animateCounterDown = 0;
                }
                animateCounterRight = 0;
                animateCounterUp = 0;
                animateCounterLeft = 0;
                break;
            case 4:
                if (animateCounterLeft < 21) {
                    animateCounterLeft++;
                } else {
                    animateCounterLeft = 0;
                }
                animateCounterRight = 0;
                animateCounterDown = 0;
                animateCounterUp = 0;
                break;
            default:
                break;
        }
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package progression;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 *
 * @author Computer
 */
public class InputHandler implements MouseListener, KeyListener {

    public static int lastMove;
    private int moveTimerX = 0;
    private int moveTimerY = 0;
    public static int mX = 0;
    public static int mY = 0;
    public static boolean mouseDone = true;

    public InputHandler() {
        Progression.frame.requestFocus();
         
    }

    public static int getActionX() {
        if (lastMove == 2) {
            return Progression.playerPosX + 1;
        } else if (lastMove == 4) {
            return Progression.playerPosX - 1;
        } else {
            return Progression.playerPosX;
        }
    }

    public static int getActionY() {
        if (lastMove == 3) {
            return Progression.playerPosY + 1;
        } else if (lastMove == 1) {
            return Progression.playerPosY - 1;
        } else {
            return Progression.playerPosY;
        }
    }
    
    
    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (Progression.cPress == true) {
            CraftingEngine.craftByMouse(e.getXOnScreen(), e.getYOnScreen());
            System.out.println(e.getXOnScreen() + " " + e.getYOnScreen());
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) { 
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) { 
    }

    @Override
    public void keyTyped(KeyEvent e) {
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
        toggleKey(e.getKeyCode(), true);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        
    }
    
    public class Key {

        private boolean pressed = false;

        public boolean isPressed() {
            return pressed;
        }

        public void Toggle(boolean isPressed) {
            pressed = isPressed;
        }
    }
    public Key up = new Key();
    public Key down = new Key();
    public Key left = new Key();
    public Key right = new Key();

    public void toggleKey(int KeyCode, boolean isPressed) {
        if (KeyCode == KeyEvent.VK_W || KeyCode == KeyEvent.VK_UP) {
            up.Toggle(isPressed);
            lastMove = 1;
            if (MapManager.AllowMove(Progression.playerPosX, (Progression.playerPosY) - 1)) {
                if (moveTimerY < -PlayerHandler.pMoveSpeed) {
                    Progression.playerPosY--;
                    moveTimerY = 0;
                } else {
                    moveTimerY--;
                    PlayerHandler.toggleWalkAnimation(lastMove);
                }
            }
        }
        if (KeyCode == KeyEvent.VK_S || KeyCode == KeyEvent.VK_DOWN) {
            down.Toggle(isPressed);
            lastMove = 3;
            if (MapManager.AllowMove(Progression.playerPosX, (Progression.playerPosY) + 1)) {
                if (moveTimerY > PlayerHandler.pMoveSpeed) {
                    Progression.playerPosY++;
                    moveTimerY = 0;
                } else {
                    moveTimerY++;
                    PlayerHandler.toggleWalkAnimation(lastMove);
                }
            }
        }
        if (KeyCode == KeyEvent.VK_A || KeyCode == KeyEvent.VK_LEFT) {
            left.Toggle(isPressed);
            lastMove = 4;
            if (MapManager.AllowMove((Progression.playerPosX) - 1, Progression.playerPosY)) {
                if (moveTimerX > PlayerHandler.pMoveSpeed) {
                    Progression.playerPosX--;
                    moveTimerX = 0;
                } else {
                    moveTimerX++;
                    PlayerHandler.toggleWalkAnimation(lastMove);
                }
            }
        }
        if (KeyCode == KeyEvent.VK_D || KeyCode == KeyEvent.VK_RIGHT) {
            right.Toggle(isPressed);
            lastMove = 2;
            if (MapManager.AllowMove((Progression.playerPosX) + 1, Progression.playerPosY)) {
                if (moveTimerX < -PlayerHandler.pMoveSpeed) {
                    Progression.playerPosX++;
                    moveTimerX = 0;
                } else {
                    moveTimerX--;
                    PlayerHandler.toggleWalkAnimation(lastMove);
                }
            }
        }
        if (KeyCode == KeyEvent.VK_SPACE) {
            MapManager.actionPress(getActionX(), getActionY());
        }
        if (KeyCode == KeyEvent.VK_I) {
            Progression.iPress = Progression.iPress != true;
        }
        if (KeyCode == KeyEvent.VK_C) {
            Progression.cPress = Progression.cPress != true;
        }
        if (KeyCode == KeyEvent.VK_ESCAPE) {
            Progression.pause = Progression.pause != true;
        }
    }
}

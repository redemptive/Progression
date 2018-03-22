/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package progression;

/**
 *
 * @author Ewan
 */
import java.awt.Canvas;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsEnvironment;
import java.awt.Toolkit;
import java.awt.Transparency;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class Progression extends Thread {

    private boolean isRunning = true;
    private final Canvas canvas;
    private BufferStrategy strategy;
    private final BufferedImage background;
    private Graphics2D backgroundGraphics;
    private Graphics2D graphics;
    public static JFrame frame;
    public static final int width = Toolkit.getDefaultToolkit().getScreenSize().width;
    public static final int height = Toolkit.getDefaultToolkit().getScreenSize().height;
    private static final int scale = (Toolkit.getDefaultToolkit().getScreenSize().height / 7) /100;
    public static int dispSizeWidth = Toolkit.getDefaultToolkit().getScreenSize().width / 13;
    public static int dispSizeHeight = Toolkit.getDefaultToolkit().getScreenSize().height / 7;
    private int x = 0;
    private int y = 0;
    public static int xOffset = 0;
    public static int yOffset = 0;
    public static int items[] = new int[2];
    public static int playerPosX = 0;
    public static int playerPosY = 0;
    public static boolean iPress = false;
    public static boolean cPress = false;
    private static final int moveSpeed = 5;
    public static final int maxSprites = 7;
    public InputHandler input;
    private final GraphicsConfiguration config
            = GraphicsEnvironment.getLocalGraphicsEnvironment()
            .getDefaultScreenDevice()
            .getDefaultConfiguration();
    public static final int mapSize = 64;
    public static int tiles[][] = new int[mapSize][mapSize];
    public static long gameTime = 0;
    public static boolean pause = false;
    public static boolean interior = false;
    

    // create a hardware accelerated image
    public final BufferedImage create(final int width, final int height,
            final boolean alpha) {
        return config.createCompatibleImage(width, height, alpha
                ? Transparency.TRANSLUCENT : Transparency.OPAQUE);
    }

    // Setup
    public Progression() {
        // JFrame
        frame = new JFrame();
        frame.addWindowListener(new FrameClose());
        frame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        frame.setSize(width * scale, height * scale);
        frame.setVisible(true);
        input = new InputHandler();
        frame.addKeyListener(input);
        frame.addMouseListener(input);

        // Canvas
        canvas = new Canvas(config);
        canvas.setSize(width * scale, height * scale);
        frame.add(canvas, 0);
        canvas.addMouseListener(input);
        canvas.addKeyListener(input);

        // Background & Buffer
        background = create(width, height, false);
        canvas.createBufferStrategy(2);
        do {
            strategy = canvas.getBufferStrategy();
        } while (strategy == null);
        MapManager.textBox = "Start";
        startUp();
        start();
    }

    public static void startUp() {
        ItemHandler.start();
        ResourceHandler.start();
        NpcHandler.Start();
        PlayerHandler.Start();
        MapBuilder.start();
    }

    private class FrameClose extends WindowAdapter {

        @Override
        public void windowClosing(final WindowEvent e) {
            isRunning = false;
        }
    }

    // Screen and buffer stuff
    private Graphics2D getBuffer() {
        if (graphics == null) {
            try {
                graphics = (Graphics2D) strategy.getDrawGraphics();
            } catch (IllegalStateException e) {
                return null;
            }
        }
        return graphics;
    }

    private boolean updateScreen() {
        graphics.dispose();
        graphics = null;
        try {
            strategy.show();
            Toolkit.getDefaultToolkit().sync();
            return (!strategy.contentsLost());

        } catch (NullPointerException e) {
            return true;

        } catch (IllegalStateException e) {
            return true;
        }
    }

    public void run() {
        backgroundGraphics = (Graphics2D) background.getGraphics();
        long fpsWait = (long) (1.0 / 30 * 1000);
        main:
        while (isRunning) {
            long renderStart = System.nanoTime();
            updateGame();

            // Update Graphics
            do {
                Graphics2D bg = getBuffer();
                if (!isRunning) {
                    break main;
                }
                renderGame(backgroundGraphics); // this calls your draw method
                // thingy
                if (scale != 1) {
                    bg.drawImage(background, 0, 0, width * scale, height
                            * scale, 0, 0, width, height, null);
                } else {
                    bg.drawImage(background, 0, 0, null);
                }
                bg.dispose();
            } while (!updateScreen());

            // Better do some FPS limiting here
            long renderTime = (System.nanoTime() - renderStart) / 1000000;
            try {
                Thread.sleep(Math.max(0, fpsWait - renderTime));
            } catch (InterruptedException e) {
                Thread.interrupted();
                break;
            }
            renderTime = (System.nanoTime() - renderStart) / 1000000;
        }
        frame.dispose();
    }

    public void updateGame() {

    }

    public void renderGame(Graphics2D g) {
        TimeManager.addTime();
        if ((cPress == false) && (iPress == false) && (pause == false) && (interior == false)) {
            Renderer.map(g, playerPosX, playerPosY);
        } else if (pause == true) {
            Renderer.pauseScreen(g);
        } else if (iPress == true) {
            Renderer.inventory(g);
        } else if (cPress == true) {
            Renderer.crafting(g);
        } else if (interior == true) {
            Renderer.interior(g);
        }
    }

    public static void main(final String args[]) {
        new Progression();
    }

}

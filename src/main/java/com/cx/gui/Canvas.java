package com.cx.gui;

import com.cx.Logic.Engine;
import com.cx.Logic.GameObject;
import com.cx.game.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;

public class Canvas extends JPanel {

    private Engine engine;

    int FPS = 60;

    public Canvas(Engine engine) {
        this.engine = engine;

        setPreferredSize(new Dimension(engine.getWidth(), engine.getHeight()));
        setBackground(Color.getHSBColor(0.58f, 0.42f, 0.92f));

        setFocusable(true);

        new Thread(() -> {

            while(true){

                try {
                    Thread.sleep(1000 / FPS);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }

                repaint();

            }

        }).start();
        addKeyListener(new KeyAdapter() {

            @Override
            public void keyPressed(KeyEvent e) {

                Player player = engine.getPlayer();
                if (player == null) return;

                switch (e.getKeyCode()) {
                    case KeyEvent.VK_W -> player.setUp(true);
                    case KeyEvent.VK_S -> player.setDown(true);
                    case KeyEvent.VK_A -> player.setLeft(true);
                    case KeyEvent.VK_D -> player.setRight(true);
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

                Player player = engine.getPlayer();
                if (player == null) return;

                switch (e.getKeyCode()) {
                    case KeyEvent.VK_W -> player.setUp(false);
                    case KeyEvent.VK_S -> player.setDown(false);
                    case KeyEvent.VK_A -> player.setLeft(false);
                    case KeyEvent.VK_D -> player.setRight(false);
                }
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (engine == null) return;

        Graphics2D g2 = (Graphics2D) g;

        // Camera transform
        g2.translate(-engine.getCameraX(), -engine.getCameraY());

        // Safe copy of object list (prevents ConcurrentModificationException)
        List<GameObject> objects = List.copyOf(engine.getObjects());

        // Render objects
        for (GameObject obj : objects) {
            if (!obj.isDead()) {
                obj.draw(g2);
            }
        }
    }
}
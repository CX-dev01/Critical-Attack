package com.cx;


import com.cx.Logic.*;
import com.cx.gui.Canvas;
import com.cx.gui.TeamSelector;

import javax.swing.*;
import java.awt.*;

public class Main{
    public static void main(String[] args) {


        Engine engine = new Engine(600, 500);
        Canvas canvas = new Canvas(engine);
        JFrame frame = new JFrame("CriticalAttack");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(canvas);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        canvas.requestFocusInWindow();

        new Thread(() -> {

            long lastTime = System.nanoTime();
            double nsPerFrame = 1_000_000_000.0 / 90.0;

            double delta = 0;

            while (true) {

                long now = System.nanoTime();
                delta += (now - lastTime) / nsPerFrame;
                lastTime = now;

                if (delta >= 1) {

                    engine.update();
                    canvas.repaint();
                    delta--;
                }
            }

        }).start();

        GameLogic logic = new GameLogic(engine);
        logic.startGame(canvas);

    }
}

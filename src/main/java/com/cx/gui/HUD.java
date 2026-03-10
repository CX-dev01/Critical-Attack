package com.cx.gui;

import com.cx.Logic.PlayerStats;

import java.awt.*;
import java.awt.image.BufferedImage;

import static com.cx.Logic.ImageLoader.loadImage;

public class HUD {

    private PlayerStats stats;
    private BufferedImage heartTexture;

    public HUD(PlayerStats stats){
        this.stats = stats;
        heartTexture = loadImage("health.png");
    }

    public void draw(Graphics g){

        int size = 40; // heart size
        int startX = 80;
        int startY = 60;
        int spacing = 35;

        for(int i = 0; i < stats.HP; i++){
            g.drawImage(
                    heartTexture,
                    startX + (i * spacing),
                    startY,
                    size,
                    size,
                    null
            );
        }
    }
}
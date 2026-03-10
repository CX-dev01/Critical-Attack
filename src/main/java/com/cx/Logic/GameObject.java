package com.cx.Logic;

import com.cx.game.Player;
import com.cx.gui.Canvas;

import java.awt.*;

public abstract class GameObject {

    protected int x;
    protected int y;
    protected boolean isDead;
    public GameObject(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public abstract void draw(Graphics g);

    public abstract void update();

    public int getX() { return x; }

    public int getY() { return y; }

    public boolean isDead() {
        return isDead;
    }
}


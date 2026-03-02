package com.cx.Logic;

import com.cx.game.Player;
import com.cx.game.map.MapGenerator;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

public class Engine{

    public MapGenerator mapGenerator;
    private Player player;

    private int width;
    private int height;
    public static final int world_Width = 3800;
    public static final int world_Height = 600;

    private double cameraX = 0;
    private double cameraY = 0;
    private double cameraSpeed = 0.1;

    public void setPlayer(Player player) {
        this.player = player;
    }

    private List<GameObject> gameObjects = new ArrayList<>();
    ArrayList<ItemManager> items = new ArrayList<>();

    public Engine(int width, int height) {
        Dimension screensize = Toolkit.getDefaultToolkit().getScreenSize();
        player = new Player(100,300, false, null);
        mapGenerator = new MapGenerator(this);
        this.width = screensize.width;
        this.height = screensize.height;
        System.out.println("Engine started");
    }

    public int getWidth() { return width; }

    public void update() {
    if (player == null) return;



    double targetX = player.getX() - width / 2;
    double targetY = player.getY() - height / 2;

    cameraX += (targetX - cameraX) * cameraSpeed;
    cameraX += (targetY - cameraY) * cameraSpeed;

    if (cameraX < 0) cameraX = 0;
    if (cameraY < 0) cameraY = 0;




        for (GameObject obj : gameObjects) {
            obj.update();
        }

        checkCollisions();
        checkItemPickup();
    }

    public Player getPlayer(){
        return player;
    }

    public int getCameraX() {
        return (int) cameraX;
    }

    public int getCameraY() {
        return (int) cameraY;
    }

    private void checkItemPickup() {
        if (player == null) return;

        var iterator = items.iterator();

        while (iterator.hasNext()) {
            ItemManager item = iterator.next();

            if (!item.isPickedUp() &&
                    player.getBounds().intersects(item.getBounds())) {

                item.pickUp();

                // Remove from game world
                iterator.remove();
                gameObjects.remove(item);
                player.inHand = true;
                System.out.println("Item picked up: " + item.item);
            }
        }
    }

    public void addItem(ItemManager item) {
        items.add(item);
        gameObjects.add(item);

    }

    private void checkCollisions() {
        // player + items logic here
    }
    public int getHeight() { return height; }
    public List<GameObject> getGameObject() {
        return gameObjects;
    }
}

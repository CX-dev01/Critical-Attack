package com.cx.Logic;

import com.cx.game.Player;
import com.cx.game.map.Fa;
import com.cx.game.map.Ground;
import com.cx.game.map.MapGenerator;
import com.cx.gui.Canvas;
import com.cx.gui.HUD;

import java.util.Random;

import static com.cx.Logic.ImageLoader.loadImage;

public class GameLogic {
    private MapGenerator mapGenerator;
    private Engine engine;

    public GameLogic(Engine engine) {
        this.engine = engine;
    }

    public void startGame(Canvas canvas) {

        int p_spawnX = engine.getWidth() / 5;
        int p_spawnY = engine.getHeight() / 1;

        Player player = new Player(p_spawnX, p_spawnY, false, null);

        int stat_spawnX = engine.getWidth() / 2;
        int stat_spawnY = engine.getHeight() / 2;

        engine.setPlayer(player);
        PlayerStats p_stats = new PlayerStats();
        HUD hud = new HUD(p_stats);
        engine.getGameObject().add(player);
        Random random = new Random();
        int randomSeed = random.nextInt(1000000); // big random number



        ItemManager kard = new ItemManager("kard", 1000, 683, loadImage("kard.png"));

        engine.addItem(kard);
        engine.getGameObject().add(kard);
        engine.mapGenerator.Generate_Map(randomSeed);


        canvas.repaint();
    }
}

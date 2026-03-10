package com.cx.game.map;


import com.cx.Logic.Engine;

import java.util.Random;

public class MapGenerator {

    private Engine engine;
    private boolean created;

    public MapGenerator(Engine engine) {
        this.engine = engine;
    }

    public void Generate_Map(int seed) {
        if (created) return;
        created = true;

        Random random = new Random(seed);

        Ground ground = new Ground(0, 740, 3800, 280);
        engine.getGameObject().add(ground);

        for (int i = 0; i < 5; i++) {
            int x = random.nextInt(3500) + 120; // proper random range
            Fa fa = new Fa(x, 580, 280, 280);
            engine.getGameObject().add(fa);
        }
    }
    public void Generate_biom(int seed) {

    }
}
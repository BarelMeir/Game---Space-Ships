package com.main;

import java.awt.*;
import java.util.Random;

/**
 * Created by Barel on 28/09/2017.
 */
public class HardEnemy extends GameObject {
    private int hardEnemyW = 16, hardEnemyH = 16;
    private Handler handler;
    private Random r = new Random();

    public HardEnemy(float x, float y, ID id, Handler handler) {
        super(x, y, id);
        this.handler = handler;
        velX = 5;
        velY = 5;
    }

    public Rectangle getBounds() {
        return new Rectangle((int) x, (int) y, hardEnemyW, hardEnemyH);
    }

    public void tick() {
        x += velX;
        y += velY;

        if (y <= 0 || y >= Game.HEIGHT - 64) {
            if (velY < 0)
                velY = -(r.nextInt(7) + 1) * -1;
            else
                velY = (r.nextInt(7) + 1) * -1;
        }
        if (x <= 0 || x >= Game.WIDTH - 32) {
            if (velX < 0)
                velX = -(r.nextInt(7) + 1) * -1;
            else
                velX = (r.nextInt(7) + 1) * -1;
        }
        handler.addObject(new Trail(x, y, ID.Trail, Color.yellow, hardEnemyW, hardEnemyH,0.2,handler));
}

    public void render(Graphics g) {
        g.setColor(Color.yellow);
        g.fillRect((int) x, (int) y, hardEnemyW, hardEnemyH);
    }
}

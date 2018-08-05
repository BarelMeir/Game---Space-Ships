package com.main;

import java.awt.*;

/**
 * Created by Barel on 17/09/2017.
 */
public class BasicEnemy extends GameObject{
    private int basicEnemyW = 16, basicEnemyH = 16;
    private Handler handler;

    public BasicEnemy(float x, float y, ID id,Handler handler){
        super(x,y,id);
        this.handler =handler;
        velX = 5;
        velY = 5;
    }

    public Rectangle getBounds(){
        return new Rectangle((int) x,(int) y,basicEnemyW,basicEnemyH);
    }
    public void tick(){
        x += velX;
        y += velY;

        if(y <= 0 || y >= Game.HEIGHT-64) velY *= -1;
        if(x <= 0 || x >= Game.WIDTH-32) velX *= -1;

        handler.addObject(new Trail(x,y,ID.Trail,Color.red,basicEnemyW,basicEnemyH,0.1,handler));
    }

    public void render(Graphics g){
        g.setColor(Color.red);
        g.fillRect((int) x,(int) y,basicEnemyW,basicEnemyH);
    }
}

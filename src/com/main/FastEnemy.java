package com.main;

import java.awt.*;

public class FastEnemy extends GameObject {

    private int fastEnemyW = 24,fastEnemyH = 24;
    private Handler handler;

    public FastEnemy(float x, float y, ID id,Handler handler){
        super(x,y,id);
        this.handler = handler;
        velX = 2;
        velY = 9;
    }

    public Rectangle getBounds(){
        return new Rectangle((int) x,(int) y,fastEnemyW,fastEnemyH);
    }
    public void tick(){
        x += velX;
        y += velY;

        if(y <= 0 || y >= Game.HEIGHT-64) velY *= -1;
        if(x <= 0 || x >= Game.WIDTH-32) velX *= -1;

        handler.addObject(new Trail( x, y,ID.Trail,Color.CYAN,fastEnemyW,fastEnemyH,0.1,handler));
    }

    public void render(Graphics g){
        g.setColor(Color.CYAN);
        g.fillRect((int) x,(int) y,fastEnemyW,fastEnemyH);
    }

}

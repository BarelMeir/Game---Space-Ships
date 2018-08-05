package com.main;

import java.awt.*;
import java.util.Random;

/**
 * Created by Barel on 18/09/2017.
 */
public class BossBullet extends GameObject {
    private int bossBulletW = 8, bossBulletH = 8;
    private Handler handler;
    Random r = new Random();

    public BossBullet(float x, float y, ID id,Handler handler){
        super(x,y,id);
        this.handler =handler;
        velX = (r.nextInt(5 - -5) + -5);
        velY = 5;
    }

    public Rectangle getBounds(){
        return new Rectangle((int) x,(int) y,bossBulletW,bossBulletH);
    }
    public void tick(){
        x += velX;
        y += velY;

        //if(y <= 0 || y >= Game.HEIGHT-64) velY *= -1;
        //if(x <= 0 || x >= Game.WIDTH-32) velX *= -1;
        if (y >= Game.HEIGHT)
            handler.removeObject(this);

        handler.addObject(new Trail(x,y,ID.Trail,Color.yellow,bossBulletW,bossBulletW,0.1,handler));
    }

    public void render(Graphics g){
        g.setColor(Color.yellow);
        g.fillRect((int) x,(int) y,bossBulletW,bossBulletH);
    }
}


package com.main;

import java.awt.*;
import java.util.Random;

/**
 * Created by Barel on 25/09/2017.
 */
public class MenuParticals extends GameObject {
    private Handler handler;
    private Random r = new Random();
    private Color col;
    private int mpW = r.nextInt(30),mpH = r.nextInt(30);


    public MenuParticals(float x, float y, ID id,Handler handler){
        super(x,y,id);
        this.handler = handler;
        velX =  (r.nextInt(5 - -5) + -5);
        velY =  (r.nextInt(5 - -5) + -5);
        if(velX == 0 ) velX = 1;
        if (velY == 0 ) velY = 1;

        col = new Color(r.nextInt(255),r.nextInt(255),r.nextInt(255));
    }

    public Rectangle getBounds(){
        return new Rectangle((int) x,(int) y,mpW,mpH);
    }
    public void tick(){
        x += velX;
        y += velY;

        if(y <= 0 || y >= Game.HEIGHT-64) velY *= -1;
        if(x <= 0 || x >= Game.WIDTH-32) velX *= -1;

        handler.addObject(new Trail( x, y,ID.Trail,col,mpW,mpH,0.05,handler));
    }

    public void render(Graphics g){
        g.setColor(col);
        g.fillRect((int) x,(int) y,mpW,mpH);
    }
}

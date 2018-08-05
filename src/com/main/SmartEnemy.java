package com.main;

import java.awt.*;
import java.util.Map;

/**
 * Created by Barel on 18/09/2017.
 */
public class SmartEnemy extends GameObject {
    private int smartEnemyW = 20, smartEnemyH = 20;
    private Handler handler;
    private GameObject player;

    public SmartEnemy(float x, float y, ID id,Handler handler){
        super(x,y,id);
        this.handler =handler;
        for (int i = 0; i <handler.object.size() ; i++) {
            if(handler.object.get(i).getId() == ID.Player ){
                player = handler.object.get(i);
            }
        }

    }

    public Rectangle getBounds(){
        return new Rectangle((int) x,(int) y,smartEnemyW,smartEnemyH);
    }
    public void tick(){
        x += velX;
        y += velY;

        float diffX = x - player.getX() - 8;
        float diffY = y - player.getY() - 8;
        float distance = (float) Math.sqrt(Math.pow(x-player.getX(),2) + Math.pow(y-player.getY(),2));
        velX =  ((-1/distance) * diffX);
        velY =  ((-1/distance) * diffY);


        if(y <= 0 || y >= Game.HEIGHT-64) velY *= -1;
        if(x <= 0 || x >= Game.WIDTH-32) velX *= -1;

        handler.addObject(new Trail( x,y,ID.Trail,Color.green,smartEnemyW,smartEnemyH,0.2,handler));
    }

    public void render(Graphics g){
        g.setColor(Color.green);
        g.fillRect((int) x,(int) y,smartEnemyW,smartEnemyH);
    }



}

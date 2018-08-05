package com.main;

import java.awt.*;
import java.util.Random;

/**
 * Created by Barel on 18/09/2017.
 */
public class BossEnemy extends GameObject {
    private int bossEnemyW = 96, bossEnemyH = 96;
    private Handler handler;
    private int timer = 100;
    private int timer2 = 50;
    private Random r = new Random();
    private HUD hud;
    private int spwanLevl;

    public BossEnemy(float x, float y, ID id,Handler handler,HUD hud){
        super(x,y,id);
        this.handler =handler;
        this.hud = hud;
        velX = 0;
        velY = 2;
        spwanLevl = hud.getLevel();
    }

    public Rectangle getBounds(){
        return new Rectangle((int) x,(int) y,bossEnemyW,bossEnemyH);
    }

    public void tick(){
        x += velX;
        y += velY;
        if(timer <= 0) {
            velY = 0;
            timer2--;
        }
        else timer--;

        if(timer2 <=0 ){
            if(velX == 0) velX =5;
            int spwan = r.nextInt(10);
            if(spwan == 0) handler.addObject(new BossBullet(x + 48,y + 48,ID.BossBullet,handler));
        }

        if(y <= 0 || y >= Game.HEIGHT-64) velY *= -1;
        if(x <= 0 || x >= Game.WIDTH-32) velX *= -1;

        if(hud.getLevel() >= spwanLevl + 4)
            handler.removeObject(this);
    }

    public void render(Graphics g){
        g.setColor(Color.red);
        g.fillRect((int) x,(int) y,bossEnemyW,bossEnemyH);
    }



}

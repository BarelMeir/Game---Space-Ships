package com.main;

import java.awt.*;

public class Player extends GameObject {
    private int playerW = 64, playerH = 64;
    Handler handler;

    public Player(float x, float y, ID id,Handler handler){
        super(x,y,id);
        this.handler = handler;
    }

    public Player(float x, float y, ID id,Handler handler, int pW,int pH){
        super(x,y,id);
        this.handler = handler;
        this.playerH = pH;
        this.playerW = pW;
    }

    public Rectangle getBounds(){
        return new Rectangle((int) x,(int) y,playerW,playerH);
    }

    public void tick(){
        x += velX;
        y += velY;

        x = Game.clamp( x,0,Game.WIDTH-32);
        y = Game.clamp( y,0,Game.HEIGHT-80);

        collision();
    }

    public void collision(){
        for (int i = 0; i <handler.object.size() ; i++) {
            GameObject tempObject = handler.object.get(i);

            switch (tempObject.getId()) {
                case BasicEnemy:
                    if (getBounds().intersects(tempObject.getBounds()))
                        HUD.HEALTH -= 2;
                    break;
                case FastEnemy:
                    if (getBounds().intersects(tempObject.getBounds()))
                        HUD.HEALTH -= 6;
                    break;
                case SmartEnemy:
                    if (getBounds().intersects(tempObject.getBounds()))
                        HUD.HEALTH -= 8;
                    break;
                case BossEnemy:
                    if (getBounds().intersects(tempObject.getBounds()))
                        HUD.HEALTH -= 12;
                    break;
                case BossBullet:
                    if(getBounds().intersects(tempObject.getBounds()))
                        HUD.HEALTH -= 3;
                    break;
                case HardEnemy:
                    if(getBounds().intersects(tempObject.getBounds()))
                        HUD.HEALTH -= 4;
                    break;
                default:
                    break;
            }
        }
    }

    public void render(Graphics g){
        g.setColor(Color.white);
        g.fillRect((int) x,(int) y,playerW,playerH);
    }


}
     /*
            if(tempObject.getId() == ID.BasicEnemy || tempObject.getId() == ID.FastEnemy ){
                if(getBounds().intersects(tempObject.getBounds())){
                    //collision code
                    HUD.HEALTH -= 2;
                }
            } */
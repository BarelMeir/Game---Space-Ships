package com.main;


import java.util.Random;

public class Spawn {
    private Handler handler;
    private HUD hud;
    private Game game;
    private int scoreKeep = 0;
    private Random r = new Random();
    private int levlVal;

    public Spawn(Handler handler, HUD hud, Game game) {
        this.handler = handler;
        this.hud = hud;
        this.game = game;
    }


    private void summon(int kind) {
        switch (kind) {
            case 0: //basic
                handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), ID.BasicEnemy, handler));
                break;
            case 1: // fast
                handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), ID.FastEnemy, handler));
                break;
            case 2: // smart
                handler.addObject(new SmartEnemy(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), ID.SmartEnemy, handler));
                break;
            case 3: //hard
                handler.addObject(new HardEnemy(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), ID.HardEnemy, handler));
                break;
            case 4: //boss
                handler.addObject(new BossEnemy(Game.WIDTH / 2, 0, ID.BossEnemy, handler, hud));
                break;
            default:
                break;
        }
    }

    public void tick() {
        scoreKeep++;
        if (scoreKeep >= 100) {
            scoreKeep = 0;
            hud.setLevel(hud.getLevel() + 1);


            if (game.dif == 0) {
                levlVal = hud.getLevel() % 10;
                switch (levlVal) {
                    case 0:
                        summon(4);
                        break;
                    case 1:
                        summon(0);
                        break;
                    case 2:
                        summon(0);
                        break;
                    case 3:
                        summon(0);
                        summon(1);
                        break;
                    case 6:
                        summon(2);
                        break;
                    case 8:
                        summon(1);
                        break;
                    case 9:
                        summon(3);
                        break;
                    default:
                        break;
                }
            } else if(game.dif == 1){
                summon(r.nextInt(6));
            }
        }
    }
}


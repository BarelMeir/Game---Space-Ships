package com.main;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.util.Random;

public class Game extends Canvas implements Runnable {
    public static final int WIDTH = 1920, HEIGHT = WIDTH/12*9;
    private Thread thread;
    private  boolean running =false;
    public static boolean paused = false;
    public int dif = 0; //0 = normal ; 1 = hard
    private Handler handler;
    private HUD hud;
    private Spawn spawner;
    private Random r;
    private Menu menu;
    public STATE gameState = STATE.Menu;


    public enum STATE {
      Menu,
        Help,
        End,
        Select,
        GAME
    }


    public Game(){
        handler = new Handler();
        hud = new HUD();
        menu = new Menu(this, handler,hud);
        this.addKeyListener(new KeyInput(handler, this, menu));
        this.addMouseListener(menu);
        AudioPlayer.load();
        AudioPlayer.getMusic("music").loop(); // GAME MUSIC
        new Window(WIDTH,HEIGHT,"Invaderes", this);
        spawner = new Spawn(handler,hud,this);
        r = new Random();

        if(gameState == STATE.GAME) {
            handler.addObject(new Player(WIDTH / 2 - 32, HEIGHT / 2 - 32, ID.Player, handler));
            handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), ID.BasicEnemy, handler));
        }
    }

    public synchronized void start(){
        thread = new Thread(this);
        thread.start();
        running = true;
    }
    public synchronized void stop(){
        try{
            thread.join();
            running = false;
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private  void  tick(){
        if(gameState == STATE.GAME){
            if(!paused) {
                hud.tick();
                spawner.tick();
                handler.tick();

                if (hud.HEALTH <= 0) {
                    HUD.HEALTH = 100;
                    handler.clearAll();
                    for (int i = 0; i < 20; i++) {
                        handler.addObject(new MenuParticals(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), ID.MenuParticals, handler));
                    }
                    gameState = STATE.End;
                }
            }
        }else if(gameState == STATE.Menu || gameState == STATE.End || gameState == STATE.Select){
            menu.tick();
            handler.tick();
        }


    }

    private void  render(){
        BufferStrategy bs = this.getBufferStrategy();
        if(bs == null){
            this.createBufferStrategy(3);
            return;
        }
        Graphics g = bs.getDrawGraphics();
        g.setColor(Color.black);
        g.fillRect(0,0,WIDTH,HEIGHT);
        handler.render(g);

        if (paused){
            Font fnt = new Font("arial", 1, 50);
            g.setFont(fnt);
            g.setColor(Color.yellow);
            g.drawString("PAUSED",900,500);
        }

        if(gameState == STATE.GAME) {
            if(!paused)
                hud.render(g);
        }else if(gameState == STATE.Menu || gameState == STATE.Help || gameState == STATE.End || gameState == STATE.Select){
            menu.render(g);
        }

        g.dispose();
        bs.show();
    }


    public static float clamp(float var, float min, float max){
        if(var >= max)
            return var = max;
        else if(var <= min)
            return var = min;
        else
            return var;
    }


    public void run(){
        this.requestFocus();
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;
        while (running){
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while (delta >= 1){
                tick();
                delta--;
            }
            if (running)
                render();
            frames++;

            if(System.currentTimeMillis() - timer > 1000){
                timer += 1000;
               // System.out.println("FPS: " + frames); //printing FPS rate
                frames = 0;
            }
        }
        stop();
    }

    public static void main(String args[]){
        new Game();
    }
}

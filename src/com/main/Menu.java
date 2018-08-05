package com.main;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * Created by Barel on 25/09/2017.
 */
public class Menu extends MouseAdapter {

    private Game game;
    private Handler handler;
    private Random r;
    private HUD hud;

    public Menu(Game game, Handler handler, HUD hud) {
        this.game = game;
        this.handler = handler;
        r = new Random();
        this.hud = hud;
        waitScreen();
    }


    protected void startNewGame(){
        game.gameState = Game.STATE.GAME;
        hud.HEALTH = 100;
        hud.setLevel(1);
        hud.setScore(0);
        handler.clearAll();
        handler.addObject(new Player(game.WIDTH / 2 - 32, game.HEIGHT / 2 - 32, ID.Player, handler));
        //handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), ID.BasicEnemy, handler));
    }

    protected void waitScreen(){
        for (int i = 0; i < 20 ; i++) {
            handler.addObject(new MenuParticals(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), ID.MenuParticals, handler));
        }
    }

    public void mousePressed(MouseEvent e) {
        int mx = e.getX();
        int my = e.getY();
        AudioPlayer.getSound("menu_sound").play();

        if (game.gameState == Game.STATE.Menu) { //Menu screen

            //Play
            if (mouseOver(mx, my, 810, 150, 300, 100)) {
                game.gameState = Game.STATE.Select;
            }

            //Help
            if (mouseOver(mx, my, 810, 350, 300, 100)) {
                game.gameState = Game.STATE.Help;
                //handler.clearAll();
            }
            //Quit
            if (mouseOver(mx, my, 810, 550, 300, 100)) {
                System.exit(1);
            }
        }else if(game.gameState == Game.STATE.Select){  // Select difficulty screen
            if (mouseOver(mx, my, 810, 150, 300, 100)) { // Normal
                game.dif = 0;
                startNewGame();
            }
            if (mouseOver(mx, my, 810, 350, 300, 100)) { // Hard
                game.dif = 1;
                startNewGame();
            }
        } else if (game.gameState == Game.STATE.Help ) { // Help screen
            //Help - Back to menu
            if ( mouseOver(mx, my, 100, 1250, 300, 100)) {
                game.gameState = Game.STATE.Menu;
            }
        }else if (game.gameState == Game.STATE.End ) { //End screen
            waitScreen();
            //End - ReStart new game
            if ( mouseOver(mx, my, 100, 1250, 300, 100)) {
                startNewGame();
            }
        }

    }

    public void mouseReleased(MouseEvent e) {

    }

    private boolean mouseOver(int mx, int my, int x, int y, int width, int height) {
        if (mx > x && mx < x + width) {
            if (my > y && my < y + height)
                return true;
            else
                return false;
        } else return false;
    }

    public void tick() {

    }

    public void render(Graphics g) {
        Font fnt = new Font("arial", 1, 50);
        Font fnt2 = new Font("arial", 1, 30);

        if (game.gameState == Game.STATE.Menu) {
            g.setFont(fnt);
            g.setColor(Color.white);
            g.drawString("Invaders", 870, 100);

            g.setFont(fnt);
            g.setColor(Color.white);
            g.drawString("Play", 900, 220);
            g.drawRect(810, 150, 300, 100);

            g.drawString("Help", 900, 420);
            g.drawRect(810, 350, 300, 100);

            g.drawString("Quit", 900, 620);
            g.drawRect(810, 550, 300, 100);
        } else if (game.gameState == Game.STATE.Help) {
            g.setFont(fnt);
            g.setColor(Color.white);
            g.drawString("Help", 900, 100);

            g.drawString("Back", 170, 1320);
            g.drawRect(100, 1250, 300, 100);

            g.setFont(fnt2);
            g.drawString("Use wasd / arrows keys to move player and dodge enemeys", 100, 300);
        } else if (game.gameState == Game.STATE.End) {
            g.setFont(fnt);
            g.setColor(Color.white);
            g.drawString("Game Over", 900, 100);

            g.drawString("Try Again", 170, 1320);
            g.drawRect(100, 1250, 300, 100);

            g.setFont(fnt2);
            g.drawString("You lost! \n Your score is: " + hud.getScore() + " , nice!" , 100, 300);
        } else if (game.gameState == Game.STATE.Select) {
            g.setFont(fnt);
            g.setColor(Color.white);
            g.drawString("Select Difficulty", 870, 100);

            g.setFont(fnt);
            g.setColor(Color.white);
            g.drawString("Normal", 900, 220);
            g.drawRect(810, 150, 300, 100);

            g.drawString("Hard", 900, 420);
            g.drawRect(810, 350, 300, 100);

            g.drawString("Back", 900, 620);
            g.drawRect(810, 550, 300, 100);
        }

    }

}

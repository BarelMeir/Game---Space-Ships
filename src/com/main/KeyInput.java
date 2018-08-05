package com.main;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter {
    private Handler handler;
    private boolean[] keyDown = new boolean[4]; //for smooth motion
    private Game game;
    private Menu menu;


    public KeyInput(Handler handler, Game game, Menu menu){
        this.handler = handler;
        for (int i = 0; i <keyDown.length ; i++) { //set keyDown array as false
            keyDown[i] = false;
        }
        this.game = game;
        this.menu = menu;
    }

    public void keyPressed(KeyEvent e){
        int key = e.getKeyCode();
        for (int i = 0; i < handler.object.size() ; i++) {
            GameObject tempObject = handler.object.get(i);

            if(tempObject.getId() == ID.Player){
                //KeyEvents of Player
                if(key == KeyEvent.VK_W|| key == KeyEvent.VK_UP){ tempObject.setVelY(-5); keyDown[0] = true;}
                if(key == KeyEvent.VK_S || key == KeyEvent.VK_DOWN){ tempObject.setVelY(5);keyDown[1] = true;}
                if(key == KeyEvent.VK_D || key == KeyEvent.VK_RIGHT){ tempObject.setVelX(5);keyDown[2] = true;}
                if(key == KeyEvent.VK_A || key == KeyEvent.VK_LEFT){ tempObject.setVelX(-5);keyDown[3] = true;}
            }

            if(key == KeyEvent.VK_Z) handler.stopMovement(); //stop everything
        }

        if(key == KeyEvent.VK_B) handler.clearAll();
        if(key == KeyEvent.VK_N) handler.clearEnemys();
        if(key == KeyEvent.VK_M) AudioPlayer.getMusic("music").stop();
        if(key == KeyEvent.VK_P && game.gameState == Game.STATE.GAME) {
            Game.paused = !Game.paused;
        }
        if(key == KeyEvent.VK_SPACE && (game.gameState == Game.STATE.End || game.gameState == Game.STATE.Select))
            menu.startNewGame();
        if(key == KeyEvent.VK_SPACE && game.gameState == Game.STATE.Menu)
            game.gameState = Game.STATE.Select;
        if(key == KeyEvent.VK_BACK_SPACE) {
            handler.clearAll();
            game.gameState = Game.STATE.Menu;
            menu.waitScreen();
        }
        if(key == KeyEvent.VK_ESCAPE) System.exit(1);
    }

    public void keyReleased(KeyEvent e){
        int key = e.getKeyCode();
        for (int i = 0; i < handler.object.size() ; i++) {
            GameObject tempObject = handler.object.get(i);

            if(tempObject.getId() == ID.Player){
                //KeyEvents of Player
                if(key == KeyEvent.VK_W|| key == KeyEvent.VK_UP)  keyDown[0] = false;
                if(key == KeyEvent.VK_S || key == KeyEvent.VK_DOWN) keyDown[1] = false;
                if(key == KeyEvent.VK_D || key == KeyEvent.VK_RIGHT) keyDown[2] = false;
                if(key == KeyEvent.VK_A || key == KeyEvent.VK_LEFT) keyDown[3] = false;

                //vertical movment
                if (!keyDown[0] && !keyDown[1]) tempObject.setVelY(0);
                //horozantil movment
                if (!keyDown[2] && !keyDown[3]) tempObject.setVelX(0);
            }
        }
    }
}


/* old keyReleased :
        if(key == KeyEvent.VK_W|| key == KeyEvent.VK_UP) tempObject.setVelY(0);

 */
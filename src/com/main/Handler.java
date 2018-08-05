package com.main;

import java.awt.*;
import java.util.LinkedList;
import java.util.ListIterator;

public class Handler {
    LinkedList<GameObject> object = new LinkedList<GameObject>();

    public void tick() {
        for (int i = 0; i < object.size(); i++) {
            GameObject tempObject = object.get(i);
            tempObject.tick();
        }
    }

    public void render(Graphics g) {
        for (int i = 0; i < object.size(); i++) {
            try {
                GameObject tempObject = object.get(i);
                tempObject.render(g);
            }
            catch (NullPointerException e){
                System.out.println("Null pointer catch");
                render(g);
            }
        }
    }

    public void addObject(GameObject object) {
        this.object.add(object);
    }

    public void removeObject(GameObject object) {
        this.object.remove(object);
    }


    public void stopMovement(){
        for (int i = 0; i <object.size() ; i++) {
            GameObject tempObject = object.get(i);
            tempObject.setVelY(0);
            tempObject.setVelX(0);
        }
    }

    public void clearEnemys() {
        while (object.size() > 1) {
            //  System.out.println("object.size = " + object.size());
            for (int i = 0; i < object.size(); i++) {
                //  System.out.println("i: " + i + ";  id: " + object.get(i).getId() );
                if (object.get(i).getId() != ID.Player) {
                    removeObject(object.get(i));
                }
            }
        }
        System.out.println("delete enemy done");
    }

    public void clearAll() {
        object.clear();
        System.out.println("clear all done");
    }


}

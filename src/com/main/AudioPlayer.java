package com.main;

import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;

import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;

/**
 * Created by Barel on 25/09/2017.
 */
public class AudioPlayer {

    public static Map<String, Sound> soundMap = new HashMap<String, Sound>();
    public static Map<String, Music> musicMap = new HashMap<String, Music>();

    public static void load(){

        try{
            soundMap.put("menu_sound", new Sound("res/poker_chip_click.ogg"));
            musicMap.put("music",new Music("res/Neutrino.ogg"));
        }catch (SlickException e){
            e.printStackTrace();
        }
    }

    public static Music getMusic(String key){
        return musicMap.get(key);
    }

    public static Sound getSound(String key){
        return soundMap.get(key);
    }

}

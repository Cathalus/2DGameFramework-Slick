package com.cathalus.slick.framework.core.resources;

import org.newdawn.slick.*;
import org.newdawn.slick.tiled.TiledMap;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * static class ResourceManager handles resources
 */
public class ResourceManager {

    public static enum ResourceType{ IMAGE, SPRITESHEET, FONT, ANIMATION, MUSIC, SOUND, TILEDMAP };

    private static final Map<String, Image>         images      = new HashMap<String, Image>();
    private static final Map<String, SpriteSheet>   spriteSheets= new HashMap<String, SpriteSheet>();
    private static final Map<String, Font>          fonts       = new HashMap<String, Font>();
    private static final Map<String, Animation>     animations  = new HashMap<String, Animation>();
    private static final Map<String, Music>         music       = new HashMap<String,Music>();
    private static final Map<String, Sound>         sounds      = new HashMap<String, Sound>();
    private static final Map<String, TiledMap>      tiledMaps   = new HashMap<String, TiledMap>();

    public static void loadResources(InputStream in) throws IOException
    {
        ResourceLoader loader = new ResourceLoader();
        loader.load(in);
    }

    public static void addImage(String key, Image value)
    {
        if(!hasResource(key, images))
        {
            images.put(key,value);
        }
    }

    public static void addSpriteSheet(String key, SpriteSheet value)
    {
        if(!hasResource(key, spriteSheets))
        {
            spriteSheets.put(key,value);
        }
    }

    public static void addFont(String key, Font value)
    {
        if(!hasResource(key, fonts))
        {
            fonts.put(key,value);
        }
    }

    public static void addAnimation(String key, Animation value)
    {
        if(!hasResource(key, animations))
        {
            animations.put(key,value);
        }
    }

    public static void addMusic(String key, Music value)
    {
        if(!hasResource(key, music))
        {
            music.put(key,value);
        }
    }

    public static void addSound(String key, Sound value)
    {
        if(!hasResource(key, sounds))
        {
            sounds.put(key,value);
        }
    }

    public static void addTiledMap(String key, TiledMap value)
    {
        if(!hasResource(key, tiledMaps))
        {
            tiledMaps.put(key,value);
        }
    }


    private static boolean hasResource(String key, Map map)
    {
        return map.containsKey(key);
    }

    public static boolean hasResource(String key, ResourceType type)
    {
        switch (type)
        {
            case IMAGE:
                return hasResource(key, images);
            case SPRITESHEET:
                return hasResource(key, spriteSheets);
            case FONT:
                return hasResource(key, fonts);
            case ANIMATION:
                return hasResource(key, animations);
            case MUSIC:
                return hasResource(key, music);
            case SOUND:
                return hasResource(key, sounds);
            case TILEDMAP:
                return hasResource(key, tiledMaps);
            default:
                return false;
        }
    }

    public static Image getImage(String key) {
        Image image = images.get(key);
        if (image == null)
            throw new IllegalArgumentException("There was no image found for key " + key + " " + images.keySet());
        return image;
    }

    public static SpriteSheet getSpriteSheet(String key)
    {
        SpriteSheet spriteSheet = spriteSheets.get(key);
        if(spriteSheet == null)
            throw new IllegalArgumentException("There was no SpriteSheet found for key " + key + " " + spriteSheets.keySet());
        return spriteSheet;
    }

    public static Font getFont(String key)
    {
        Font font = fonts.get(key);
        if(font == null)
            throw new IllegalArgumentException("There was no Font found for key " + key + " " + fonts.keySet());
        return font;
    }

    public static Animation getAnimation(String key)
    {
        Animation animation = animations.get(key);
        if(animation == null)
            throw new IllegalArgumentException("There was no Animation found for key " + key + " " + animations.keySet());
        return animation;
    }

    public static Music getMusic(String key)
    {
        Music mus = music.get(key);
        if(mus == null)
            throw new IllegalArgumentException("There was no Music found for key " + key + " " + music.keySet());
        return mus;
    }

    public static Sound getSound(String key)
    {
        Sound sound = sounds.get(key);
        if(sound == null)
            throw new IllegalArgumentException("There was no Sound found for key " + key + " " + sounds.keySet());
        return sound;
    }

    public static TiledMap getTiledMap(String key)
    {
        TiledMap map = tiledMaps.get(key);
        if(map == null)
            throw new IllegalArgumentException("There was no TiledMap found for key " + key + " " + tiledMaps.keySet());
        return map;
    }


}

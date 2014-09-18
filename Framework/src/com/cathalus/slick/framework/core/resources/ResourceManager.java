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
        if(!hasObject(key,images))
        {
            images.put(key,value);
        }
    }

    public static void addSpriteSheet(String key, SpriteSheet value)
    {
        if(!hasObject(key,spriteSheets))
        {
            spriteSheets.put(key,value);
        }
    }

    public static void addFont(String key, Font value)
    {
        if(!hasObject(key,fonts))
        {
            fonts.put(key,value);
        }
    }

    public static void addAnimation(String key, Animation value)
    {
        if(!hasObject(key,animations))
        {
            animations.put(key,value);
        }
    }

    public static void addMusic(String key, Music value)
    {
        if(!hasObject(key,music))
        {
            music.put(key,value);
        }
    }

    public static void addSound(String key, Sound value)
    {
        if(!hasObject(key,sounds))
        {
            sounds.put(key,value);
        }
    }

    public static void addTiledMap(String key, TiledMap value)
    {
        if(!hasObject(key,tiledMaps))
        {
            tiledMaps.put(key,value);
        }
    }


    public static boolean hasObject(String key, Map map)
    {
        return map.containsKey(key);
    }

    public static Image getImage(String key) {
        Image image = images.get(key);
        if (image == null)
            throw new IllegalArgumentException("There was no image found for key " + key + " " + images.keySet());
        return image;
    }
}

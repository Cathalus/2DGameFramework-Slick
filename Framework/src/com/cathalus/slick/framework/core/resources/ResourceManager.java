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
    private static final Map<String, String>        parameters  = new HashMap<String, String>();
    private static final Map<String, TiledMap>      tiledMaps   = new HashMap<String, TiledMap>();

    public static void loadResources(InputStream in) throws IOException
    {
        ResourceLoader loader = new ResourceLoader();
        loader.load(in);
    }

}

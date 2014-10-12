package com.cathalus.slick.framework.core.states;

import com.cathalus.slick.framework.core.Scene;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.GameState;

import java.util.HashMap;

/**
 * Created by cathalus on 17.09.14.
 */
public abstract class SceneBasedState extends BasicGameState {

    public HashMap<String,Scene> scenes = new HashMap<String, Scene>();
    protected Scene current;

    public void transitionTo(String target)
    {
        transitionTo(target,true);
    }

    public void transitionTo(String target, boolean unloadResources)
    {
        if(unloadResources && current != null)
            current.unloadResources();
        current = scenes.getOrDefault(target,null);
        if(current == null)
        {
            System.err.println("Couldn't find Scene "+target);
            System.exit(1);
        }
        current.init();
    }

}

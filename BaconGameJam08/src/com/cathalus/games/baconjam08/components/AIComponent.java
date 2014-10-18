package com.cathalus.games.baconjam08.components;

import com.cathalus.slick.framework.core.entities.EntityComponent;
import org.newdawn.slick.GameContainer;

/**
 * Created by Cathalus on 18.10.2014.
 */
public class AIComponent extends EntityComponent {

    public static final String NAME = "AIComponent";

    public AIComponent()
    {
        this.identifier = NAME;
    }

    @Override
    public void update(GameContainer container, float delta) {

    }

    @Override
    public void onAdd() {

    }
}

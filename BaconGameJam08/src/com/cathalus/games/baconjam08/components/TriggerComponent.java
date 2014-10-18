package com.cathalus.games.baconjam08.components;

import com.cathalus.games.baconjam08.util.Globals;
import com.cathalus.slick.framework.core.entities.EntityComponent;
import org.newdawn.slick.GameContainer;

/**
 * Created by Cathalus on 18.10.2014.
 */
public class TriggerComponent extends EntityComponent {

    public static final String NAME = "TriggerComponent";

    private boolean triggered = false;
    private Globals.Triggers triggerType;

    public TriggerComponent(Globals.Triggers triggerType)
    {
        this.triggerType = triggerType;
        this.identifier = NAME;
    }

    @Override
    public void update(GameContainer container, float delta) {

    }

    @Override
    public void onAdd() {

    }

    public Globals.Triggers getTriggerType() {
        return triggerType;
    }

    public boolean isTriggered() {
        return triggered;
    }

    public void setTriggered(boolean triggered) {
        this.triggered = triggered;
    }
}

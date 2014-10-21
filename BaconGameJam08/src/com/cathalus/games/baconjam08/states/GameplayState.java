package com.cathalus.games.baconjam08.states;

import com.cathalus.games.baconjam08.components.AABBRenderComponent;
import com.cathalus.games.baconjam08.components.InputComponent;
import com.cathalus.games.baconjam08.components.SpawnComponent;
import com.cathalus.games.baconjam08.components.TriggerComponent;
import com.cathalus.games.baconjam08.entities.Player;
import com.cathalus.games.baconjam08.scenes.GameplayScene;
import com.cathalus.games.baconjam08.util.Globals;
import com.cathalus.games.baconjam08.util.State;
import com.cathalus.slick.framework.core.entities.Entity;
import com.cathalus.slick.framework.core.math.BoundingBox;
import com.cathalus.slick.framework.core.math.QuadTree;
import com.cathalus.slick.framework.core.resources.ResourceManager;
import com.cathalus.slick.framework.core.states.SceneBasedState;

import org.newdawn.slick.*;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Cathalus on 18.10.2014.
 */
public class GameplayState extends SceneBasedState {

    private QuadTree quadTree;
    private Player player;
    private ArrayList<Entity> playerSpawnPoints = new ArrayList<Entity>();
    private ArrayList<Entity> enemySpawnPoints = new ArrayList<Entity>();

    @Override
    public int getID() {
        return State.GAMEPLAY.getID();
    }

    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
        quadTree = new QuadTree(new BoundingBox(0,-gameContainer.getHeight(),gameContainer.getWidth(),0),25);

        ArrayList<Entity> mapObjects = Globals.CURRENT_LEVEL.getObjects();


        if(Globals.CURRENT_LEVEL != null)
        {
            for(Entity e : mapObjects)
            {
                if(e.hasComponent(TriggerComponent.NAME))
                {
                    TriggerComponent trigger = (TriggerComponent) e.getComponent(TriggerComponent.NAME);
                    switch(trigger.getTriggerType())
                    {
                        case PLAYER_SPAWN:
                            playerSpawnPoints.add(e);
                            break;
                        case ENEMY_SPAWN:
                            enemySpawnPoints.add(e);
                            break;
                    }
                }
                quadTree.add(e);
            }
        }

        spawnEntities();

        gameContainer.setMouseCursor(ResourceManager.getImage("crosshair"),1,1);

        scenes.put("Gameplay", new GameplayScene(this,quadTree,gameContainer.getWidth(),gameContainer.getHeight()));
        transitionTo("Gameplay");
    }

    private void spawnEntities() {
        // Random player spawn point selection
        if(playerSpawnPoints.size() > 0) {
            Random rand = new Random();
            int point = rand.nextInt(playerSpawnPoints.size());
            player = new Player(new Vector2f(playerSpawnPoints.get(point).getX(),-playerSpawnPoints.get(point).getY()),
                    10,25,
                    new InputComponent(InputComponent.InputMethod.MOUSE_KEYBOARD));
        }else{
            player = new Player(new Vector2f(0,0),50,50,new InputComponent(InputComponent.InputMethod.MOUSE_KEYBOARD));
        }
        Globals.CURRENT_PLAYER = player;
        quadTree.add(player);

        // Random enemy spawn point
        // TODO: currently rudimentary spawnpoint selection
        if(enemySpawnPoints.size() > 0) {
            int numberOfSpawns = (int) (Globals.CURRENT_DIFFICULTY.getPercentage()*enemySpawnPoints.size());
            Random rand = new Random();
            if(Globals.CURRENT_DIFFICULTY != Globals.Difficulty.HARD) {
                while (numberOfSpawns > 0) {
                    // Randomly allocate enemy spawners
                    int point = rand.nextInt(enemySpawnPoints.size());
                    Entity spawnPoint = enemySpawnPoints.get(point);
                    TriggerComponent component = (TriggerComponent) spawnPoint.getComponent(TriggerComponent.NAME);
                    if (!component.isTriggered()) {
                        component.setTriggered(true);
                        spawnPoint.addComponent(new AABBRenderComponent());
                        spawnPoint.addComponent(new SpawnComponent(100,2+(rand.nextFloat()*1.5f),1));
                        quadTree.add(spawnPoint);
                        numberOfSpawns--;
                    }
                }
            }else{
                // Allocate every enemy spawner
                for(Entity spawnPoint : enemySpawnPoints)
                {
                    spawnPoint.addComponent(new AABBRenderComponent());
                    spawnPoint.addComponent(new SpawnComponent(100,1+(rand.nextFloat()*2.5f),1));
                    quadTree.add(spawnPoint);
                }
            }
        }
    }

    @Override
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics) throws SlickException {
        if(current != null) {
            current.render(graphics);
        }
    }

    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int i) throws SlickException {
        if(current != null) {
            float delta = i;
            current.update(gameContainer, delta/1000.0f);
        }
    }
}

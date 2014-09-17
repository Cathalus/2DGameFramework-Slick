package com.cathalus.slick.framework.core.entities.components;

import com.cathalus.slick.framework.core.entities.EntityComponent;
import com.cathalus.slick.framework.core.math.MathUtil;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;
import org.newdawn.slick.geom.Vector2f;

/**
 * Created by cathalus on 15.09.14.
 */
public class MovementComponent extends EntityComponent {

    private int speed;

    private boolean jumping = false;
    private float jumpCounter = 0.0f;
    private float jumpHeight = 150;
    private float jumpSpeed = 3f;
    private float jumpStartY = 0.0f;

    public MovementComponent(int movementSpeed)
    {
        this.speed = speed;
    }

    @Override
    public void update(float delta, GameContainer container) {
        Input in = container.getInput();
        Vector2f deltaMovement = new Vector2f(in.isKeyDown(Input.KEY_A) ? -1 : in.isKeyDown(Input.KEY_D) ? 1 : 0, in.isKeyDown(Input.KEY_W) && !jumping ? 1 : in.isKeyDown(Input.KEY_S) && !jumping ? -1 : 0);
        deltaMovement.normalise();
        deltaMovement.scale(speed * (delta));
        entity.setDeltaX(deltaMovement.getX());
        entity.setDeltaY(deltaMovement.getY());

        if(!jumping) {
            if(in.isKeyDown(Input.KEY_SPACE))
            {
                jumping = true;
                jumpStartY = entity.getY();
                return;
            }
        }

        if(jumping)
        {
            jumpCounter += MathUtil.clamp(delta*jumpSpeed,0,jumpHeight);
            float currentJumpValue = (float) Math.sin(jumpCounter)*jumpHeight*delta;
            if(jumpCounter > Math.PI/2) {
                entity.setDeltaY(-currentJumpValue);
            }else{
                entity.setDeltaY(currentJumpValue);
            }
//            Vector2f newPos = new Vector2f(entity.getX(),jumpStartY-currentJumpValue);
//            entity.setPosition(newPos);
            if(jumpCounter >= Math.PI)
            {
                jumpCounter = 0.0f;
                jumping = false;
            }
        }
    }

    @Override
    public void onAdd() {

    }
}

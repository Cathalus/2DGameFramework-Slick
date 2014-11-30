package com.cathalus.games.mathgame.states;

import com.cathalus.games.mathgame.Keyboard;
import com.cathalus.games.mathgame.gui.ProgressBar;
import com.cathalus.games.mathgame.levels.BasicCalculation;
import com.cathalus.games.mathgame.math.Calculation;
import com.cathalus.games.mathgame.util.States;
import com.cathalus.slick.framework.core.resources.ResourceManager;
import org.newdawn.slick.*;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class AdditionSubtractionState extends BasicGameState {

    private Color color = Color.white;
    private Calculation calc;
    private String a, b, c;
    private ProgressBar progress;

    @Override
    public int getID() {
        return States.AdditionSubtraction.getID();
    }

    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
        progress = new ProgressBar(0,20,1280,new Vector2f(0f,0f));
        a = "";
        b = "";
        c = "";
        generateCalculation();
    }

    @Override
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics) throws SlickException {
        graphics.drawImage(ResourceManager.getImage("board"),0,0);
        progress.draw(graphics);
        graphics.setColor(color);
        if(calc != null)
        {
            graphics.setFont(ResourceManager.getFont("eraser"));
            String display = a+" "+calc.getOP().toString()+" "+b+" = "+c;
            int posX = (gameContainer.getWidth()-ResourceManager.getFont("eraser").getWidth(display))/2;
            int posY = (gameContainer.getHeight()-ResourceManager.getFont("eraser").getHeight(display))/2;
            graphics.drawString(display,posX,posY+progress.getHeight());
        }
    }

    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int i) throws SlickException {
        Input in = gameContainer.getInput();
        // TODO: Remove this debug mode
        if(in.isKeyDown(Input.KEY_SPACE))
        {
            generateCalculation();
        }
        int num = Keyboard.getNumber(in);

        if(num != -1 && num != 42)
        {
            switch(calc.getType())
            {
                case FIRST_BLANK:
                    if(a.equals("_"))
                        a = "";
                    a += num;
                    break;
                case SECOND_BLANK:
                    if(b.equals("_"))
                        b = "";
                    b += num;
                    break;
                case THIRD_BLANK:
                    if(c.equals("_"))
                        c = "";
                    c += num;
                    break;
            }
        }else if(num == -1)
        {
            switch(calc.getType())
            {
                case FIRST_BLANK:
                    if(a.length()>0)
                        a = a.substring(0,a.length()-1);
                    break;
                case SECOND_BLANK:
                    if(b.length()>0)
                        b = b.substring(0,b.length()-1);
                    break;
                case THIRD_BLANK:
                    if(c.length()>0)
                        c = c.substring(0,c.length()-1);
                    break;
            }
        }

        if(in.isKeyPressed(Input.KEY_ENTER) || in.isKeyPressed(Input.KEY_NUMPADENTER))
        {
            // check calculation
            if(a == "_" || b == "_" || c == "_") {
                color = Color.orange;
            }else{
                checkCalculation();
            }
        }

        // Check for win conditions
        if(progress.getCurrent() < 0)
        {
            // game lost

        }else if(progress.getCurrent() == progress.getMax())
        {
            // game won
        }
    }

    private void checkCalculation() {
        try{
            switch(calc.getType())
            {
                case FIRST_BLANK:
                    if(Integer.parseInt(a) == calc.getA())
                    {
                        right();
                    }else{
                        wrong();
                    }
                    break;
                case SECOND_BLANK:
                    if(Integer.parseInt(b) == calc.getB())
                    {
                        right();
                    }else{
                        wrong();
                    }
                    break;
                case THIRD_BLANK:
                    if(Integer.parseInt(c) == calc.getResult())
                    {
                        right();
                    }else{
                        wrong();
                    }
                    break;
            }
        }catch(Exception e)
        {
            color = Color.orange;
        }
    }

    public void right()
    {
        color = Color.green;
        progress.increaseCurrent();
        ResourceManager.getSound("cheer").play();
        //generateCalculation();
    }

    public void wrong()
    {
        color = Color.red;
        progress.decreaseCurrent();
        ResourceManager.getSound("buzzer").play();
        //generateCalculation();
    }

    public void generateCalculation()
    {
        color = Color.white;
        BasicCalculation calculation = new BasicCalculation();
        calc = calculation.getCalculation();
        a = calc.aToString();
        b = calc.bToString();
        c = calc.resultToString();
    }
}
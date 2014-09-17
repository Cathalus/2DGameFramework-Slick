import com.cathalus.slick.framework.core.entities.Entity;
import com.cathalus.slick.framework.core.entities.components.MovementComponent;
import com.cathalus.slick.framework.core.entities.components.RenderComponent;
import com.cathalus.slick.framework.core.math.AABB;
import com.cathalus.slick.framework.core.math.QuadTree;
import org.newdawn.slick.*;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

/**
 * Created by cathalus on 10.09.14.
 */
public class SimpleSlickGame extends BasicGame {

    public SimpleSlickGame(String title) {
        super(title);
    }

    Image wizard, box;
    float ppS = 250;
    Entity player;
    Entity enemy;
    Entity temp;

    private QuadTree scene = new QuadTree(new AABB(0,-600,800,0),2);

    @Override
    public void init(GameContainer gameContainer) throws SlickException {
        try {
            String fileName = "black-mage.png";
            String[] splitArray = fileName.split("\\.");
            String extension = splitArray[splitArray.length-1];
            Texture tex = TextureLoader.getTexture(extension, new FileInputStream(new File("./SimpleGame/res/sprites/" + fileName)));
            wizard = new Image(tex).getScaledCopy(0.2f);

            fileName = "box.jpg";
            splitArray = fileName.split("\\.");
            extension = splitArray[splitArray.length-1];
            tex = TextureLoader.getTexture(extension, new FileInputStream(new File("./SimpleGame/res/sprites/" + fileName)));
            box = new Image(tex).getScaledCopy(0.75f);
        } catch (IOException e) {
            e.printStackTrace();
        }

        player = new Entity(new AABB(0,-50,50,0));
        player.addComponent(new RenderComponent(wizard));
        player.addComponent(new MovementComponent(100));

        Entity temp;
        for(int i = 0; i < 5; i++)
        {
            for(int j = 0; j < 5; j++) {
                temp = new Entity(new Vector2f(100 + i * 150, 75 + j * 160),1,1);
                temp.addComponent(new RenderComponent(box));
                scene.add(temp);
            }
        }

        scene.add(player);
    }

    @Override
    public void update(GameContainer gameContainer, int i) throws SlickException {
        Set<Entity> entities = scene.queryRange(new AABB(0,-600, 800, 0), new HashSet<Entity>());

        Iterator<Entity> it = entities.iterator();
        while (it.hasNext()) {
            Entity current = (Entity) it.next();

            current.update((float) i/1000,gameContainer);

            if (current.hasMoved()) {
                scene.remove(current);
                current.updateAABB();
                scene.add(current);
            }
        }
    }

    @Override
    public void render(GameContainer gameContainer, Graphics graphics) throws SlickException {
        Set<Entity> entities = scene.queryRange(new AABB(0,-600, 800, 0), new HashSet<Entity>());

        Iterator<Entity> it = entities.iterator();
        while (it.hasNext()) {
            Entity current = (Entity) it.next();
            current.render(graphics);
        }

    }

    public static void main(String[] args)
    {
        try{
            AppGameContainer container = new AppGameContainer(new SimpleSlickGame("Simple Slick Game"));
            container.setDisplayMode(800,600,false);
            container.setAlwaysRender(true);
            container.setShowFPS(true);
            container.setTargetFrameRate(60);
            container.setMinimumLogicUpdateInterval(1000 / 60);
            container.start();
        }catch(SlickException ex)
        {

        }
    }
}

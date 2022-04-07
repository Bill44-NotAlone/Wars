package game.wars;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public class Stone {
    private final TextureRegion textureRegion;
    private Vector2 location;
    private int XP;
    private Texture texture;

    public Stone(int x, int y){
        location = new Vector2();
        location.set(x, y);
        int XP = 100;
        texture = new Texture("Stone.png");
        textureRegion = new TextureRegion(texture);
    }

    public void render(Batch batch){
        batch.draw(textureRegion, location.x, location.y);
    }
}

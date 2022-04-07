package game.wars;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import game.wars.emitter.Emitter;

public class Tank {
    private final float size = 64;
    private final float halfsize = size / 2;
    private  Vector2 location = new Vector2();
    private final Texture texture;
    private final TextureRegion textureRegion;
    private final Vector2 origin = new Vector2();

    public Vector2 GetOrigin() {
        return origin;
    }

    public Vector2 GetAngle() {
        return angle;
    }

    private final Vector2 angle = new Vector2();
    public final Emitter emitter = new Emitter();

    public Tank(int x, int y) {
        texture = new Texture("me_tank.png");
        textureRegion = new TextureRegion(texture);
        location.set(x, y);
        origin.set(location).add(halfsize, halfsize);
    }

    public Tank(int x, int y, String textureName) {
        texture = new Texture(textureName);
        textureRegion = new TextureRegion(texture);
        location.set(x, y);
    }

    public void render(Batch batch){
        batch.draw(
                textureRegion,
                location.x,
                location.y,
                halfsize,
                halfsize,
                size,
                size,
                1,
                1,
                angle.angleDeg() - 90
        );
    }

    public void dispose (){
        texture.dispose();
    }

    public void MoveTo(Vector2 direction) {
        location.add(direction);
    }

    public void RotateTo(Vector2 mouselocation) {
        angle.set(mouselocation).sub(location.x + halfsize, location.y + halfsize);
    }

    public Vector2 getLocation() {
        return location;
    }
}

package game.wars.emitter;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Pool;

public class Projectile implements Pool.Poolable{
    private float size;
    private float speed;
    private float distance;
    private float distance2;

    private final Vector2 position = new Vector2();
    private final Vector2 startpoint = new Vector2();
    private final Vector2 nextpoint = new Vector2(1, 1);

    public void Act(float delta) {
        float steplenth = speed * delta;
        position.add(nextpoint.setLength(steplenth));
    }

    public Vector2 getPosition() {
        return position;
    }

    public void Fill(Vector2 position, float angle, float size, float speed, float distance) {
        this.position.set(position);
        this.startpoint.set(position);
        this.nextpoint.setAngleDeg(angle);
        this.size = size;
        this.speed = speed;
        this.distance = distance;
        this.distance2 = distance * distance;
    }

    @Override
    public void reset() {
        this.position.set(0, 0);
        this.startpoint.set(0, 0);
        this.nextpoint.set(0, 0);
        this.size = 0;
        this.speed = 0;
        this.distance = 0;
    }

    public boolean IsFinished() {
       return position.dst2(startpoint) >= distance2;
    }
}

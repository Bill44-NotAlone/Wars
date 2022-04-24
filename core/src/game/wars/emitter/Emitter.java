package game.wars.emitter;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.DelayedRemovalArray;
import com.badlogic.gdx.utils.Pool;

public class Emitter {
    private Vector2 position = new Vector2();
    private float speed = 500;
    private float angle = 0;
    private float rate = 25;
    private float distence = 1500;
    private float size = 10;

    private final DelayedRemovalArray<Projectile> projectiles = new DelayedRemovalArray<>();
    private final Pool<Projectile> projectilePool = new Pool<Projectile>() {
        @Override
        protected Projectile newObject() {
            return new Projectile();
        }
    };

    public void Start(){
        Projectile projectile = projectilePool.obtain();
        projectile.Fill(position, angle, size, speed, distence);
        projectiles.add(projectile);
    }

    public void Act(float delta){
        projectiles.begin();

        for(Projectile projectile : projectiles){
            projectile.Act(delta);
            if(projectile.IsFinished()){
                destroyProjectile(projectile);
            }
        }

        projectiles.end();
    }

    public Vector2 GetPosition() {
        return position;
    }

    public void SetAngle(float angle) {
        this.angle = angle;
    }

    public DelayedRemovalArray<Projectile> getProjectiles() {
        return projectiles;
    }

    public void setPosition(Vector2 position) {
        this.position = position;
    }

    public void destroyProjectile(Projectile projectile){
        projectiles.removeValue(projectile, true);
        projectilePool.free(projectile);
    }
}

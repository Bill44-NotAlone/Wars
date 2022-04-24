package game.wars;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ScreenUtils;
import game.wars.emitter.Emitter;
import game.wars.emitter.Projectile;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Starter extends ApplicationAdapter {
	private SpriteBatch batch;
	private Tank me_tansk;
	private KeyBoardAdapter inputProcessor = new KeyBoardAdapter();
	private List<Stone> stones;

	@Override
	public void create () {
		Gdx.input.setInputProcessor(inputProcessor);
		batch = new SpriteBatch();
		me_tansk = new Tank(100, 100);

		stones = IntStream.range(0, 15).mapToObj(i ->{
			int x = MathUtils.random(Gdx.graphics.getWidth());
			int y = MathUtils.random(Gdx.graphics.getHeight());
			return new Stone(x, y);
		}).collect(Collectors.toList());
	}

	@Override
	public void render () {
		ScreenUtils.clear(1, 1, 1, 1);
		batch.begin();


		Emitter emitter = me_tansk.emitter;
		emitter.SetAngle(me_tansk.GetAngle().angleDeg());
		emitter.GetPosition().set(me_tansk.GetOrigin());
		emitter.setPosition(me_tansk.GetOrigin());
		if(inputProcessor.pressWhitespace){
			emitter.Start();
		}
		emitter.Act(Gdx.graphics.getDeltaTime());
		for(Projectile projectile : emitter.getProjectiles()){
			Vector2 position = projectile.getPosition();
			batch.draw(projectile.pilet, position.x - 5, position.y - 5);
			stones.forEach(stone -> {
				if(stone.rectangle.contains(projectile.rectangle)){
					emitter.destroyProjectile(projectile);
					stone.takesDamage();
				}
				if(stone.IsDestruction) stones.remove(stone);
			});
		}

		me_tansk.render(batch);

		stones.forEach(stone -> {
			stone.render(batch);
		});

		me_tansk.MoveTo(inputProcessor.GetDirection());
		me_tansk.RotateTo(inputProcessor.GetMouseLocation());

		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		me_tansk.dispose();
	}
}

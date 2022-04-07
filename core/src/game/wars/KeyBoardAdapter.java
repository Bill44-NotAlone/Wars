package game.wars;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.math.Vector2;

public class KeyBoardAdapter extends InputAdapter {

    private boolean pressA;
    private boolean pressD;
    private boolean pressS;
    private boolean pressW;

    public boolean pressWhitespace;

    private final Vector2 direction = new Vector2();
    private final Vector2 mouselocation = new Vector2();

    @Override
    public boolean keyDown(int keycode) {
        if(keycode == Input.Keys.A) pressA = true;
        if(keycode == Input.Keys.S) pressS = true;
        if(keycode == Input.Keys.W) pressW = true;
        if(keycode == Input.Keys.D) pressD = true;
        if(keycode == Input.Keys.SPACE) pressWhitespace = true;

        return false;
    }

    @Override
    public boolean keyUp(int keycode) {

        if(keycode == Input.Keys.A) pressA = false;
        if(keycode == Input.Keys.S) pressS = false;
        if(keycode == Input.Keys.W) pressW = false;
        if(keycode == Input.Keys.D) pressD = false;
        if(keycode == Input.Keys.SPACE) pressWhitespace = false;

        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        mouselocation.set(screenX, Gdx.graphics.getHeight() - screenY);

        return false;
    }

    public Vector2 GetDirection(){
        direction.set(0, 0);

        if(pressA) direction.add(-5, 0);
        if(pressW) direction.add(0, 5);
        if(pressS) direction.add(0, -5);
        if(pressD) direction.add(5, 0);

        return direction;
    }

    public Vector2 GetMouseLocation(){
        return mouselocation;
    }
}

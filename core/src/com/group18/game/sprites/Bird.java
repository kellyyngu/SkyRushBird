package com.group18.game.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;


public class Bird
{
    private static final int GRAVITY = -15;
    private static final int MOVEMENT = 100;
    private final Vector3 position;
    private final Vector3 velocity;
    private final Rectangle bounds;

    private final Texture bird;
    private final Sound flap;

    public Bird(int x, int y)
    {
        position = new Vector3(x, y, 0);
        velocity = new Vector3(0, 0, 0);
        bird = new Texture("bird.png");
        flap = Gdx.audio.newSound(Gdx.files.internal("flap.mp3"));
        flap.setVolume(1, 5f);
        bounds = new Rectangle(x, y, bird.getWidth(), bird.getHeight());
    }

    public void update(float dt)
    {
        if(position.y > 0)
            velocity.add(0, GRAVITY, 0);
        velocity.scl(dt);
        position.add(MOVEMENT * dt, velocity.y, 0);
        if(position.y < 0)
            position.y = 0;

        velocity.scl(1/dt);
        bounds.setPosition(position.x, position.y);
    }

    public Vector3 getPosition() {
        return position;
    }

    public Texture getTexture() {
        return bird;
    }
    public void jump(){
        velocity.y = 300;
        flap.play();
    }
    public Rectangle getBounds() {
        return bounds;
    }
    public void dispose() {
        bird.dispose();
        flap.dispose();
    }
}

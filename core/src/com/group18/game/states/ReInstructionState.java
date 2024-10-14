package com.group18.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.group18.game.SkyRushBird;

public class ReInstructionState extends State
{
    private final Texture background;
    private final Texture instructionImage;

    private float passValue;

    public ReInstructionState(GameStateManager gsm, float passValue)
    {
        super(gsm);
        this.passValue = passValue;

        background = new Texture("bg.jpg");
        instructionImage = new Texture("instruction.jpg");
    }

    @Override
    public void handleInput()
    {
        if (Gdx.input.justTouched())
        {
            // If the user clicks on the instruction screen, transition back to the menu state
            gsm.set(new ResumeState(gsm, passValue));
            dispose();
        }
    }

    @Override
    public void update(float dt)
    {
        handleInput();
    }

    @Override
    public void render(SpriteBatch sb)
    {
        sb.begin();
        sb.draw(background, 100*passValue-65-SkyRushBird.WIDTH/5, 0, SkyRushBird.WIDTH, SkyRushBird.HEIGHT);
        // Draw the instruction image in the center of the screen
        sb.draw(instructionImage, 100*passValue-65-SkyRushBird.WIDTH/5+(float) (SkyRushBird.WIDTH - instructionImage.getWidth()) / 2, (float) (SkyRushBird.HEIGHT - instructionImage.getHeight()) / 2);
        sb.end();
    }

    @Override
    public void dispose()
    {
        background.dispose();
        instructionImage.dispose();
    }
}

package com.group18.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.group18.game.SkyRushBird;


public class MenuState extends State
{
    private final Texture background;
    private final Texture playBtn;
    private final Texture exitBtn;
    private final Texture tutBtn;
    private final Texture logo;

    public MenuState(GameStateManager gsm)
    {
        super(gsm);
        background = new Texture("bg.jpg");
        playBtn = new Texture("playBtn.png");
        exitBtn = new Texture("exitBtn.png");
        tutBtn = new Texture("tutBtn.png");
        logo = new Texture("skyrushBird.png");
    }

    @Override
    public void handleInput() {
        if (Gdx.input.justTouched()) {
            float touchX = Gdx.input.getX();
            float touchY = Gdx.input.getY();

            // Convert touch coordinates to viewport coordinates
            touchY = SkyRushBird.HEIGHT - touchY;

            // Check if the touch is within the bounds of the tutorial button
            float buttonSpacing = 85;
            if (touchX >= ((float) SkyRushBird.WIDTH / 2) - ((float) tutBtn.getWidth() / 2) &&
                    touchX <= ((float) SkyRushBird.WIDTH / 2) + ((float) tutBtn.getWidth() / 2) &&
                    touchY >= (float) SkyRushBird.HEIGHT / 3 + buttonSpacing * 2 &&
                    touchY <= (float) SkyRushBird.HEIGHT / 3 + buttonSpacing * 2 + tutBtn.getHeight())
            {

                // If tutorial button is touched, transition to the instruction state
                gsm.set(new InstructionState(gsm));
                dispose();
            }
            else if (touchX >= ((float) SkyRushBird.WIDTH / 2) - ((float) exitBtn.getWidth() / 2) &&
                    touchX <= ((float) SkyRushBird.WIDTH / 2) + ((float) exitBtn.getWidth() / 2) &&
                    touchY >= (float) SkyRushBird.HEIGHT / 3 &&
                    touchY <= (float) SkyRushBird.HEIGHT / 3 + exitBtn.getHeight())
            {
                // If exit button is touched, exit the application
                dispose();
                Gdx.app.exit();
            } else
            {
                // If not touching any button, proceed with game start
                gsm.set(new PlayState(gsm));
                dispose();
            }
        }
    }


    @Override
    public void update(float dt) {
        handleInput();
    }

    @Override
    public void render(SpriteBatch sb)
    {
        sb.begin();
        sb.draw(background, 0, 0, SkyRushBird.WIDTH, SkyRushBird.HEIGHT);

        float buttonSpacing = 100;

        float tutBtnY = (float) SkyRushBird.HEIGHT / 3 + buttonSpacing * 2;
        float playBtnY = (float) SkyRushBird.HEIGHT / 3 + buttonSpacing;
        float exitBtnY = (float) SkyRushBird.HEIGHT / 3;

        sb.draw(logo,((float) SkyRushBird.WIDTH /2) - ((float) logo.getWidth() / 2),SkyRushBird.HEIGHT-logo.getHeight()-50);
        sb.draw(tutBtn, ((float) SkyRushBird.WIDTH /2) - ((float) tutBtn.getWidth() / 2), tutBtnY);
        sb.draw(playBtn, ((float) SkyRushBird.WIDTH /2) - ((float) playBtn.getWidth() / 2), playBtnY);
        sb.draw(exitBtn, ((float) SkyRushBird.WIDTH /2) - ((float) exitBtn.getWidth() / 2), exitBtnY);

        sb.end();
    }

    @Override
    public void dispose()
    {
        background.dispose();
        playBtn.dispose();
        exitBtn.dispose();
    }
}

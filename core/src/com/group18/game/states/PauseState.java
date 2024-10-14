package com.group18.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.ScalingViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.group18.game.SkyRushBird;

public class PauseState extends State {  //version 7
    private final Texture background;
    private Texture resumeBtn;
    private Texture menuBtn;
    private Texture quitBtn;
    private Texture pauseTitle;
    private float passValue;


    public PauseState(GameStateManager gsm, float passValue) {
        super(gsm);
        this.passValue = passValue;
        cam.setToOrtho(false, (float)SkyRushBird.WIDTH, (float)SkyRushBird.HEIGHT);

        background = new Texture("bg.jpg");
        resumeBtn = new Texture("resumeBtn.png");
        menuBtn = new Texture("menuBtn.png");
        quitBtn = new Texture("quitBtn.png");
        pauseTitle = new Texture("pauseMenu.png");
    }

    @Override
    protected void handleInput() {
        if(Gdx.input.justTouched()){
            float touchX = Gdx.input.getX();
            float touchY = Gdx.input.getY();

            touchY = SkyRushBird.HEIGHT - touchY;

            if( (touchX >= SkyRushBird.WIDTH/2-resumeBtn.getWidth()/2) &&
                    (touchX <= SkyRushBird.WIDTH/2+resumeBtn.getWidth()/2) &&
                    (touchY >= 390) && (touchY <= 390+resumeBtn.getHeight())){
                gsm.pop();

            }
            else if((touchX >= SkyRushBird.WIDTH/2-menuBtn.getWidth()/2) &&
                    (touchX <= SkyRushBird.WIDTH/2+menuBtn.getWidth()/2) &&
                    (touchY >= 300) && (touchY <= 300+menuBtn.getHeight())){
                gsm.set(new ResumeState(gsm, passValue));
            }
            else if ((touchX >= SkyRushBird.WIDTH/2-quitBtn.getWidth()/2) &&
                    (touchX <= SkyRushBird.WIDTH/2+quitBtn.getWidth()/2) &&
                    (touchY >= 150) && (touchY <= 150+quitBtn.getHeight())) {
                Gdx.app.exit();
            }


        }


        if (Gdx.input.isKeyJustPressed(Input.Keys.P))
        {
            gsm.pop(); //have some problem 3.19.2024
        } else if (Gdx.input.isKeyJustPressed(Input.Keys.M))
        {
            gsm.set(new ResumeState(gsm, passValue)); //this one is good but wasting the memory 3.18.2024
        } else if (Gdx.input.isKeyJustPressed(Input.Keys.Q))
        {
            Gdx.app.exit(); //good, cause ez to achieve 3.18.2024
        }
    }

    @Override
    public void update(float dt) {
        handleInput();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.begin();
        sb.draw(background, 100*passValue-65-SkyRushBird.WIDTH / 5, 0);
        sb.draw(pauseTitle, 100*passValue-65-SkyRushBird.WIDTH / 5+SkyRushBird.WIDTH/2-pauseTitle.getWidth()/2, SkyRushBird.HEIGHT-pauseTitle.getHeight());
        sb.draw(resumeBtn, 100*passValue-65-SkyRushBird.WIDTH / 5+SkyRushBird.WIDTH/2-resumeBtn.getWidth()/2, 390);
        sb.draw(menuBtn, 100*passValue-65-SkyRushBird.WIDTH / 5+SkyRushBird.WIDTH/2-menuBtn.getWidth()/2,300);
        sb.draw(quitBtn, 100*passValue-65-SkyRushBird.WIDTH / 5+SkyRushBird.WIDTH/2-quitBtn.getWidth()/2, 150);
        sb.end();
    }

    @Override
    public void dispose() {
        background.dispose();
        resumeBtn.dispose();
        menuBtn.dispose();
        quitBtn.dispose();
    }

    private float calculateXPositionForButton(Texture button) {
        // This method calculates the X position for the button based on your original logic
        return 100 * passValue - 65 - SkyRushBird.WIDTH / 5 + SkyRushBird.WIDTH / 2 - button.getWidth() / 2;
    }
}

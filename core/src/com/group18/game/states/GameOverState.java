package com.group18.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.ScalingViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.group18.game.SkyRushBird;

public class GameOverState extends State {  //version 7
    private final Texture background;
    private Texture newGameBtn;
    private Texture menuBtn;
    private Texture quitBtn;
    private Texture gameover;
    private float passValue;
    private BitmapFont font;
    private BitmapFont gameoverFont;

    public GameOverState(GameStateManager gsm, float passValue) {
        super(gsm);
        this.passValue = passValue;
        cam.setToOrtho(false, (float)SkyRushBird.WIDTH, (float)SkyRushBird.HEIGHT);

        background = new Texture("bg.jpg");
        newGameBtn = new Texture("restartBtn.png");
        menuBtn = new Texture("menuBtn.png");
        quitBtn = new Texture("quitBtn.png");
        gameover = new Texture("gameOver.png");
        font = new BitmapFont();
        gameoverFont = new BitmapFont();
    }

    @Override
    public void handleInput() {
        if(Gdx.input.justTouched()){
            float touchX = Gdx.input.getX();
            float touchY = Gdx.input.getY();

            touchY = SkyRushBird.HEIGHT - touchY;

            if( (touchX >= SkyRushBird.WIDTH/2-newGameBtn.getWidth()/2) &&
                    (touchX <= SkyRushBird.WIDTH/2+newGameBtn.getWidth()/2) &&
                    (touchY >= 320) && (touchY <= 320+newGameBtn.getHeight())){
                gsm.set(new PlayState(gsm));

            }
            else if((touchX >= SkyRushBird.WIDTH/2-menuBtn.getWidth()/2) &&
                    (touchX <= SkyRushBird.WIDTH/2+menuBtn.getWidth()/2) &&
                    (touchY >= 250) && (touchY <= 250+menuBtn.getHeight())){
                gsm.set(new ResumeState(gsm, passValue));
            }
            else if ((touchX >= SkyRushBird.WIDTH/2-quitBtn.getWidth()/2) &&
                    (touchX <= SkyRushBird.WIDTH/2+quitBtn.getWidth()/2) &&
                    (touchY >= 100) && (touchY <= 100+quitBtn.getHeight())) {
                Gdx.app.exit();
            }


        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.P)) {
            gsm.pop(); //have some problem 3.19.2024
        } else if (Gdx.input.isKeyJustPressed(Input.Keys.M)) {
            gsm.set(new ResumeState(gsm, passValue)); //this one is good but wasting the memory 3.18.2024
        } else if (Gdx.input.isKeyJustPressed(Input.Keys.Q)) {
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

        font.getData().setScale(2f);
        font.setColor(Color.RED);
        font.draw(sb, "Score: " + (int)passValue, 100*passValue-65-SkyRushBird.WIDTH / 5+SkyRushBird.WIDTH/2-50, SkyRushBird.HEIGHT - 250);

        sb.draw(gameover, 100*passValue-65-SkyRushBird.WIDTH / 5+SkyRushBird.WIDTH/2-gameover.getWidth()/2, SkyRushBird.HEIGHT - gameover.getHeight());

        sb.draw(newGameBtn, 100*passValue-65-SkyRushBird.WIDTH / 5+SkyRushBird.WIDTH/2-newGameBtn.getWidth()/2, 20+300);
        sb.draw(menuBtn, 100*passValue-65-SkyRushBird.WIDTH / 5+SkyRushBird.WIDTH/2-menuBtn.getWidth()/2,100+150);
        sb.draw(quitBtn, 100*passValue-65-SkyRushBird.WIDTH / 5+SkyRushBird.WIDTH/2-quitBtn.getWidth()/2, 100);
        sb.end();
    }

    @Override
    public void dispose() {
        background.dispose();
        newGameBtn.dispose();
        menuBtn.dispose();
        quitBtn.dispose();
    }

    private float calculateXPositionForButton(Texture button) {
        // This method calculates the X position for the button based on your original logic
        return 100 * passValue - 65 - SkyRushBird.WIDTH / 5 + SkyRushBird.WIDTH / 2 - button.getWidth() / 2;
    }
}

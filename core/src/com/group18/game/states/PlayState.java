package com.group18.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.group18.game.SkyRushBird;
import com.group18.game.sprites.Bird;
import com.group18.game.sprites.Tree;

public class PlayState extends State {
    private float timer; //time to score
    private BitmapFont font;
    private static final int TREE_SPACING = 500;
    private static final int TREE_COUNT = 4;
    private final Bird bird;
    private final Texture pauseBtn;
    private final Texture background;

    private final Array<Tree> trees;
    public PlayState(GameStateManager gsm) {
        super(gsm);

        timer = 0;
        font = new BitmapFont();

        bird = new Bird(SkyRushBird.WIDTH / 5, SkyRushBird.HEIGHT / 2);
        background = new Texture("bg.jpg");
        pauseBtn = new Texture("pauseBtn.png");
        cam.setToOrtho(false, (float) SkyRushBird.WIDTH , (float) SkyRushBird.HEIGHT );

        trees = new Array<Tree>();

        for (int i = 1; i <= TREE_COUNT; i++)
            trees.add(new Tree(i * (TREE_SPACING + Tree.TREE_WIDTH)));
    }

    @Override
    protected void handleInput() {
        if(Gdx.input.justTouched())
        {
            float touchX = Gdx.input.getX(); //to get the x coordinate
            float touchY = Gdx.input.getY(); //to get the y coordinate
            touchY = SkyRushBird.HEIGHT - touchY;

            if( (touchX >= SkyRushBird.WIDTH/2-400) &&
                    (touchX >= SkyRushBird.WIDTH/2-400+80) &&
                    (touchY >= SkyRushBird.HEIGHT-100) &&
                    (touchY <= SkyRushBird.HEIGHT-100+80)){
                gsm.push(new PauseState(gsm, timer));
            }
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.P)) {
            gsm.push(new PauseState(gsm, timer)); // Pushes PauseState onto the stack
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
            bird.jump();
        }
    }

    @Override
    public void update(float dt) {
        timer += dt;

        bird.update(dt);
        cam.position.x = bird.getPosition().x +80;

        for (Tree tree : trees) {
            if (cam.position.x - (cam.viewportWidth / 2) > tree.getPosTopTree().x + tree.getTopTree().getWidth()) {
                tree.reposition(tree.getPosTopTree().x + ((Tree.TREE_WIDTH + TREE_SPACING) * TREE_COUNT));
            }

            if ( (tree.collides(bird.getBounds())) || (bird.getBounds().y == 0) )
                gsm.set(new GameOverState(gsm, timer));

        }
        cam.update();
    }

    @Override
    public void render(SpriteBatch sb) {
        handleInput();

        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        sb.draw(background, cam.position.x - (cam.viewportWidth / 2), 0);
        sb.draw(bird.getTexture(), bird.getPosition().x, bird.getPosition().y);
        for (Tree tree : trees) {
            sb.draw(tree.getTopTree(), tree.getPosTopTree().x, tree.getPosTopTree().y);
            sb.draw(tree.getBottomTree(), tree.getPosBotTree().x, tree.getPosBotTree().y);

            //to show the score on the screen
            font.getData().setScale(2f);
            font.setColor(Color.RED);
            font.draw(sb, "Score: " + (int)timer, cam.position.x - 700, SkyRushBird.HEIGHT - 50);

        }

        sb.draw(pauseBtn, SkyRushBird.WIDTH/2-400+100*timer, SkyRushBird.HEIGHT-100, 80, 80);
        sb.end();
    }

    @Override
    public void dispose() {
    }
}

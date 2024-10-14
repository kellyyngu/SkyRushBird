package com.group18.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.util.Random;

public class Tree {

    public static final int TREE_WIDTH = 30;
    private static final int FLUCTUATION = 130;
    private static final int TREE_GAP = 300;
    private static final int LOWEST_OPENING = 120;
    private Texture topTree, bottomTree;
    private Vector2 posTopTree, posBotTree;
    private Rectangle boundsTop, boundsBot;
    private Random rand;


    public Tree(float x) {
        topTree = new Texture("treee.jpeg");
        bottomTree = new Texture("treee.jpeg");
        rand = new Random();

        posTopTree = new Vector2(x, rand.nextInt(FLUCTUATION) + TREE_GAP + LOWEST_OPENING);
        posBotTree = new Vector2(x, posTopTree.y - TREE_GAP - bottomTree.getHeight());

        boundsTop = new Rectangle(posTopTree.x, posTopTree.y, topTree.getWidth(), topTree.getHeight());
        boundsBot = new Rectangle(posBotTree.x, posBotTree.y, bottomTree.getWidth(), bottomTree.getHeight());
    }

    public Texture getTopTree() {
        return topTree;
    }

    public Texture getBottomTree() {
        return bottomTree;
    }

    public Vector2 getPosTopTree() {
        return posTopTree;
    }

    public Vector2 getPosBotTree() {
        return posBotTree;
    }

    public void reposition(float x) {
        posTopTree.set(x, rand.nextInt(FLUCTUATION) + TREE_GAP + LOWEST_OPENING);
        posBotTree.set(x, posTopTree.y - TREE_GAP - bottomTree.getHeight());
        boundsTop.setPosition(posTopTree.x, posTopTree.y);
        boundsBot.setPosition(posBotTree.x, posBotTree.y);
    }

    public boolean collides(Rectangle player) {
        return player.overlaps(boundsTop) || player.overlaps(boundsBot);
    }
}
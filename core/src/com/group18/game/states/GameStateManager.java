package com.group18.game.states;

import java.util.Stack;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;



public class GameStateManager {

    private final Stack<com.group18.game.states.State> states;

    public GameStateManager() {
        states = new Stack<com.group18.game.states.State>();
    }

    public void push(com.group18.game.states.State state) {
        states.push(state);
    }


    public void pop(){
        states.pop();
    }

    public void set(State state){
        states.pop();
        states.push(state);
    }

    public void update(float dt){
        states.peek().update(dt);
    }

    public void render(SpriteBatch sb){
        states.peek().render(sb);
    }
}

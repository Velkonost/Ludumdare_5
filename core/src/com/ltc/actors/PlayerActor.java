package com.ltc.actors;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * @author Velkonost
 */
public class PlayerActor extends Actor {

    /** The player texture. */
    private Texture player;

    /** Is the player alive or not. */
    private boolean alive;

    public PlayerActor(Texture player) {
        this.player = player;
        this.alive = true;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(player, getX(), getY(), getWidth(), getHeight());
    }

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }
}

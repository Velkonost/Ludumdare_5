package com.ltc.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.ltc.screens.GameScreen;

import static com.ltc.Constants.PIXELS_IN_METER;

/**
 * @author Velkonost
 */
public class EnemyEntity extends Actor {

    /** The player texture. */
    private Texture texture;

    /** The world instance this player is in. */
    private World world;

    /** The body for this player. */
    private Body body;

    /** The fixture for this player. */
    private Fixture fixture;

    private Vector2 prevPosition;

    private boolean ableMove;

    /**
     * Is the player alive? If he touches a spike, is not alive. The player will only move and
     * jump if it's alive. Otherwise it is said that the user has lost and the game is over.
     */
    private boolean alive = true;

    private String fixt;

    private Vector2 playerPosition;

    private float speedUp = 2f;

    public EnemyEntity(GameScreen game, World world, Texture texture, Vector2 position, int index) {
        this.world = world;
        this.texture = texture;
        ableMove = true;

        fixt = "enemy" + index;

        // Create the player body.
        BodyDef def = new BodyDef();                // (1) Create the body definition.
        def.position.set(position);                 // (2) Put the body in the initial position.
        def.type = BodyDef.BodyType.DynamicBody;    // (3) Remember to make it dynamic.
        body = world.createBody(def);               // (4) Now create the body.

        // Give it some shape.
        PolygonShape box = new PolygonShape();      // (1) Create the shape.
        box.setAsBox(0.5f, 0.5f);                   // (2) 1x1 meter box.
        fixture = body.createFixture(box, 0);       // (3) Create the fixture.
        fixture.setUserData("enemy" + index);              // (4) Set the user data.
        box.dispose();                              // (5) Destroy the shape.

        // Set the size to a value that is big enough to be rendered on the screen.
        setSize(PIXELS_IN_METER, PIXELS_IN_METER);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        // Always update the position of the actor when you are going to draw it, so that the
        // position of the actor on the screen is as accurate as possible to the current position
        // of the Box2D body.
        setPosition((body.getPosition().x - 0.5f) * PIXELS_IN_METER,
                (body.getPosition().y - 0.5f) * PIXELS_IN_METER);
        batch.draw(texture, getX(), getY(), getWidth(), getHeight());
    }

    @Override
    public void act(float delta) {}

    public void detach() {
        body.destroyFixture(fixture);
        world.destroyBody(body);
    }

    public void setPlayerPosition(Vector2 playerPosition) {
        this.playerPosition = playerPosition;
    }

    public void move(Vector2 playerPosition) {
        setPlayerPosition(playerPosition);
        setPrevPosition(new Vector2(body.getPosition()));

        if (ableMove) {
//            System.out.println(ableMove);
            if (body.getPosition().x < this.playerPosition.x) {
                body.setLinearVelocity(speedUp, body.getLinearVelocity().y);
            }

            if (body.getPosition().x > this.playerPosition.x) {
                body.setLinearVelocity(-speedUp, body.getLinearVelocity().y);
            }

            if (body.getPosition().y < this.playerPosition.y) {
                body.setLinearVelocity(body.getLinearVelocity().x, speedUp);
            }

            if (body.getPosition().y > this.playerPosition.y) {
                body.setLinearVelocity(body.getLinearVelocity().x, -speedUp);
            }

        }

    }

    private void setPrevPosition(Vector2 prevPosition) {
        this.prevPosition = prevPosition;
    }

    public Vector2 getPrevPosition() {
        return prevPosition;
    }


    // Getter and setter festival below here.
    public boolean isAlive() {
        return alive;
    }

    public void changeSpeed(float speed) {
        this.speedUp = speed;
    }

    public void blockMove(boolean ableMove) {
        System.out.println(ableMove);
        this.ableMove = !ableMove;
//        body.setLinearVelocity(0, 0);
    }

    public String getFixt() {
        return fixt;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }
}


package com.ltc.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.scenes.scene2d.Actor;

import java.util.HashMap;
import java.util.Map;

import static com.ltc.Constants.PIXELS_IN_METER;

/**
 * @author Velkonost
 */
public class PlayerEntity extends Actor implements InputProcessor {

    /** The player texture. */
    private Texture texture;

    /** The world instance this player is in. */
    private World world;

    /** The body for this player. */
    private Body body;

    /** The fixture for this player. */
    private Fixture fixture;

    /**
     * Is the player alive? If he touches a spike, is not alive. The player will only move and
     * jump if it's alive. Otherwise it is said that the user has lost and the game is over.
     */
    private boolean alive = true;

    /**
     * Is the player jumping? If the player is jumping, then it is not possible to jump again
     * because the user cannot double jump. The flag has to be set when starting a jump and be
     * unset when touching the floor again.
     */
    private boolean jumping = false;

    /**
     * Does the player have to jump? This flag is used when the player touches the floor and the
     * user is still touching the screen, to make a double jump. Remember that we cannot add
     * a force inside a ContactListener. We have to use this flag to remember that the player
     * had to jump after the collision.
     */
    private boolean mustJump = false;

    private float speedUp = 4f;

    public enum KeysMove {
        LEFT, RIGHT, UP, DOWN
    }

    private Map<KeysMove, Boolean> keys = new HashMap<KeysMove, Boolean>();

    {
        keys.put(KeysMove.LEFT, false);
        keys.put(KeysMove.RIGHT, false);
        keys.put(KeysMove.UP, false);
        keys.put(KeysMove.DOWN, false);

    }


    public PlayerEntity(World world, Texture texture, Vector2 position) {
        this.world = world;
        this.texture = texture;

        // Create the player body.
        BodyDef def = new BodyDef();                // (1) Create the body definition.
        def.position.set(position);                 // (2) Put the body in the initial position.
        def.type = BodyDef.BodyType.DynamicBody;    // (3) Remember to make it dynamic.
        body = world.createBody(def);               // (4) Now create the body.

        // Give it some shape.
        PolygonShape box = new PolygonShape();      // (1) Create the shape.
        box.setAsBox(0.5f, 0.5f);                   // (2) 1x1 meter box.
        fixture = body.createFixture(box, 0);       // (3) Create the fixture.
        fixture.setUserData("player");              // (4) Set the user data.
        box.dispose();                              // (5) Destroy the shape.

        // Set the size to a value that is big enough to be rendered on the screen.
        setSize(PIXELS_IN_METER, PIXELS_IN_METER);

        Gdx.input.setInputProcessor(this);
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
    public void act(float delta) {
        // Jump when you touch the screen.
//        if (Gdx.input.justTouched()) {
//            jump();
//        }
//
//        // Jump if we were required to jump during a collision.
//        if (mustJump) {
//            mustJump = false;
//            jump();
//        }

//        // If the player is alive, change the speed so that it moves.
//        if (alive) {
//            // Only change X speed. Do not change Y speed because if the player is jumping,
//            // this speed has to be managed by the forces applied to the player. If we modify
//            // Y speed, jumps can get very very weir.d
//            float speedY = body.getLinearVelocity().y;
//            body.setLinearVelocity(8f, 0);
//        }
//
//        // If the player is jumping, apply some opposite force so that the player falls faster.
//        if (jumping) {
//            body.applyForceToCenter(0, -es.danirod.jddprototype.game.Constants.IMPULSE_JUMP * 1.15f, true);
//        }
    }

//    public void jump() {
//        // The player must not be already jumping and be alive to jump.
//        if (!jumping && alive) {
//            jumping = true;
//
//            // Apply an impulse to the player. This will make change the velocity almost
//            // at the moment unlike using forces, which gradually changes the force used
//            // during the jump. We get the position becase we have to apply the impulse
//            // at the center of mass of the body.
//            Vector2 position = body.getPosition();
//            body.applyLinearImpulse(0, es.danirod.jddprototype.game.Constants.IMPULSE_JUMP, position.x, position.y, true);
//        }
//    }

    public void detach() {
        body.destroyFixture(fixture);
        world.destroyBody(body);
    }

    // Getter and setter festival below here.

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public void setJumping(boolean jumping) {
        this.jumping = jumping;
    }

    public void setMustJump(boolean mustJump) {
        this.mustJump = mustJump;
    }

    @Override
    public boolean keyDown(int keycode) {
        if (keycode == Input.Keys.D || keycode == Input.Keys.RIGHT) {
            rightPressed();
        } else if (keycode == Input.Keys.A || keycode == Input.Keys.LEFT) {
            leftPressed();
        } else if (keycode == Input.Keys.W || keycode == Input.Keys.UP) {
            upPressed();
        } else if (keycode == Input.Keys.S || keycode == Input.Keys.DOWN) {
            downPressed();
        }
        return true;
    }

    @Override
    public boolean keyUp(int keycode) {
        if (keycode == Input.Keys.D || keycode == Input.Keys.RIGHT) {
            rightReleased();
        } else if (keycode == Input.Keys.A || keycode == Input.Keys.LEFT) {
            leftReleased();
        } else if (keycode == Input.Keys.W || keycode == Input.Keys.UP) {
            upReleased();
        } else if (keycode == Input.Keys.S || keycode == Input.Keys.DOWN) {
            downReleased();
        }
        return true;
    }

    //флаг устанавливаем, что движемся влево
    private void leftPressed() {
        keys.get(keys.put(KeysMove.LEFT, true));
    }

    //флаг устанавливаем, что движемся вправо
    private void rightPressed() {
        keys.get(keys.put(KeysMove.RIGHT, true));
    }

    //флаг устанавливаем, что движемся вверх
    private void upPressed() {
        keys.get(keys.put(KeysMove.UP, true));
    }

    //флаг устанавливаем, что движемся вниз
    private void downPressed() {
        keys.get(keys.put(KeysMove.DOWN, true));
    }


    //освобождаем флаги
    private void leftReleased() {
        keys.get(keys.put(KeysMove.LEFT, false));
    }

    private void rightReleased() {
        keys.get(keys.put(KeysMove.RIGHT, false));
    }

    private void upReleased() {
        keys.get(keys.put(KeysMove.UP, false));
    }

    private void downReleased() {
        keys.get(keys.put(KeysMove.DOWN, false));
    }

    public void resetWay(){
        rightReleased();
        leftReleased();
        downReleased();
        upReleased();
    }

    //в зависимости от выбранного направления движения выставляем новое направление движения для персонажа
    public void processInput() {


        if (keys.get(KeysMove.LEFT)) {
            System.out.println(111);
            body.setLinearVelocity(-speedUp, body.getLinearVelocity().y);
//            finishAngle = 90;
//            isMove = true;
        }
        if (keys.get(KeysMove.RIGHT)) {
            body.setLinearVelocity(speedUp, body.getLinearVelocity().y);
//            finishAngle = -90;
//            isMove = true;
        }
        if (keys.get(KeysMove.UP)) {
//            if (game.amountResources >= 9) {
//                body.setLinearVelocity(body.getLinearVelocity().x, -speedUp);
//            } else {
                body.setLinearVelocity(body.getLinearVelocity().x, speedUp);
//            }

//            finishAngle = 0;
//            isMove = true;
        }

        if (keys.get(KeysMove.DOWN)) {
            body.setLinearVelocity(body.getLinearVelocity().x, -speedUp);
        }

        //если не выбрано направление, то стоим на месте
        if ((keys.get(KeysMove.LEFT) && keys.get(KeysMove.RIGHT)) || (!keys.get(KeysMove.LEFT) && (!keys.get(KeysMove.RIGHT)))) {
            body.setLinearVelocity(0, body.getLinearVelocity().y);

        }

        //если не выбрано направление, то стоим на месте
        if ((keys.get(KeysMove.UP) && keys.get(KeysMove.DOWN)) || (!keys.get(KeysMove.UP) && (!keys.get(KeysMove.DOWN)))) {
            body.setLinearVelocity(body.getLinearVelocity().x, 0);

        }

        if (!keys.get(KeysMove.LEFT) && (!keys.get(KeysMove.RIGHT)))  {
//            isMove = false;
        }
    }

    public Vector2 getPosition() {
        return getPosition();
    }


    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}


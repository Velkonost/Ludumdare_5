package com.ltc;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;

/**
 * @author Velkonost
 */
public class GameScreen extends BaseScreen {

    private Stage stage;

    private World world;

    private PlayerEntity player;

    private PinchoEntity pincho;

    private Box2DDebugRenderer renderer;
    private OrthographicCamera camera;

    public GameScreen(MainGame game) {
        super(game);

        stage = new Stage(new FitViewport(640, 360));
        world = new World(new Vector2(0, 0), true);
    }

    @Override
    public void show() {
        renderer = new Box2DDebugRenderer();
        camera = new OrthographicCamera(16, 9);
        camera.translate(0, 1);

        Texture playerTexture = game.getManager().get("hero.png");
        Texture pinchoTexture = game.getManager().get("hero.png");
        Texture floorTexture = game.getManager().get("badlogic.jpg");

        player = new PlayerEntity(playerTexture, world, new Vector2(1, 2));
        pincho = new PinchoEntity(pinchoTexture, world, new Vector2(6, 1.5f));

//        floor = new FloorEntity(floorTexture, world, new Vector2(0, -1));

        stage.addActor(player);
        stage.addActor(pincho);
//        stage.addActor(floor);


//        world.setContactListener(new ContactListener() {
//            @Override
//            public void beginContact(Contact contact) {
//                Fixture fixtureA = contact.getFixtureA();
//                Fixture fixtureB = contact.getFixtureB();
//
//                // проверка на контакт с полом
//                if ((fixtureA.getUserData().equals("player") && fixtureB.getUserData().equals("floor"))
//                        || (fixtureA.getUserData().equals("floor") && fixtureB.getUserData().equals("player"))) {
//                    hasCollision = true;
//                }
//
//                // со штыком
//                if ((fixtureA.getUserData().equals("player") && fixtureB.getUserData().equals("pincho"))
//                        || (fixtureA.getUserData().equals("pincho") && fixtureB.getUserData().equals("pincho"))) {
//                    joeVivo = false;
//                }
//            }
//
//            @Override
//            public void endContact(Contact contact) {
//
//            }
//
//            @Override
//            public void preSolve(Contact contact, Manifold oldManifold) {
//
//            }
//
//            @Override
//            public void postSolve(Contact contact, ContactImpulse impulse) {
//
//            }
//        });
    }

    @Override
    public void hide() {
        player.detach();
        pincho.detach();

        player.remove();
        pincho.remove();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.4f, 0.5f, 0.8f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act();

        //можно прыгать только если есть коллизия с полом
//        if (Gdx.input.justTouched() && hasCollision) {
//            hasCollision = false;
//            saltar();
//        }

        // движение по горизонтали
//        if (joeVivo) {
            float velocityY = player.getBody().getLinearVelocity().y;
            player.getBody().setLinearVelocity(4, velocityY);
            camera.position.x = player.getBody().getPosition().x;

            camera.position.y = player.getBody().getPosition().y;
//
//        }

        world.step(delta, 6, 2);
        camera.update();
        renderer.render(world, camera.combined);
        stage.draw();
    }

    @Override
    public void dispose() {
        stage.dispose();
        world.dispose();
        renderer.dispose();
    }
}

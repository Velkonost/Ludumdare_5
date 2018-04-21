package com.ltc.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.ltc.MainGame;
import com.ltc.entities.EnemyEntity;
import com.ltc.entities.EntityFactory;
import com.ltc.entities.PlayerEntity;
import com.ltc.entities.WallEntity;

import java.util.ArrayList;
import java.util.TimerTask;

/**
 * @author Velkonost
 */
public class GameScreen extends BaseScreen {

    /** Stage instance for Scene2D rendering. */
    private Stage stage;

    /** World instance for Box2D engine. */
    private World world;

    /** Player entity. */
    private PlayerEntity player;

    private ArrayList<WallEntity> walls;

    private WallEntity wallExample;

    private ArrayList<EnemyEntity> enemies;


    /** List of floors attached to this level. */
//    private List<FloorEntity> floorList = new ArrayList<FloorEntity>();

    /** List of spikes attached to this level. */
//    private List<SpikeEntity> spikeList = new ArrayList<SpikeEntity>();

    /** Jump sound that has to play when the player jumps. */
    private Sound jumpSound;

    /** Die sound that has to play when the player collides with a spike. */
    private Sound dieSound;

    /** Background music that has to play on the background all the time. */
    private Music backgroundMusic;

    /** Initial position of the camera. Required for reseting the viewport. */
    private Vector3 position;

    private OrthographicCamera camera;

    private Box2DDebugRenderer renderer;



    /**
     * Create the screen. Since this constructor cannot be invoked before libGDX is fully started,
     * it is safe to do critical code here such as loading assets and setting up the stage.
     * @param game
     */
    public GameScreen(MainGame game) {
        super(game);

        // Create a new Scene2D stage for displaying things.
        stage = new Stage(new FitViewport(6400, 3600));
        position = new Vector3(stage.getCamera().position);

        // Create a new Box2D world for managing things.
        world = new World(new Vector2(0, 0), true);
        world.setContactListener(new GameContactListener());

        enemies = new ArrayList<EnemyEntity>();

        // Get the sound effect references that will play during the game.
//        jumpSound = game.getManager().get("audio/jump.ogg");
//        dieSound = game.getManager().get("audio/die.ogg");
//        backgroundMusic = game.getManager().get("audio/song.ogg");
    }

    /**
     * This method will be executed when this screen is about to be rendered.
     * Here, I use this method to set up the initial position for the stage.
     */
    @Override
    public void show() {
        EntityFactory factory = new EntityFactory(game.getManager());

        // Create the player. It has an initial position.
        player = factory.createPlayer(world, new Vector2(8f, 1f));

        // This is the main floor. That is why is so long.
//        floorList.add(factory.createFloor(world, 0, 1000, 1));

        // Now generate some floors over the main floor. Needless to say, that on a real game
        // this should be better engineered. For instance, have all the information for floors
        // and spikes in a data structure or even some level file and generate them without
        // writing lines of code.
//        floorList.add(factory.createFloor(world, 15, 10, 2));
//        floorList.add(factory.createFloor(world, 30, 8, 2));

        // Generate some spikes too.
//        spikeList.add(factory.createSpikes(world, 8, 1));
//        spikeList.add(factory.createSpikes(world, 23, 2));
//        spikeList.add(factory.createSpikes(world, 35, 2));
//        spikeList.add(factory.createSpikes(world, 50, 1));

        // All add the floors and spikes to the stage.
//        for (FloorEntity floor : floorList)
//            stage.addActor(floor);
//        for (SpikeEntity spike : spikeList)
//            stage.addActor(spike);
      //Vertical
        stage.addActor(factory.createWall(world, new Vector2(9.4f, 6.5f), 10f, 400f, -10f, -200f, "wall1", 0.1f, 2f));
        stage.addActor(factory.createWall(world, new Vector2(9.4f, 8.5f), 10f, 400f, -10f, -200f, "wall1", 0.1f, 2f));
        stage.addActor(factory.createWall(world, new Vector2(9.4f, 16.5f), 10f, 400f, -10f, -200f, "wall1", 0.1f, 2f));

        enemies.add(factory.createEnemy(this, world, new Vector2(5f, 5f), 0));

      //Horizontal
        stage.addActor(factory.createWall(world, new Vector2(7.2f, 2.2f), 400f, 10f, -205f, 0f, "wall2", 2.2f, 0.1f));
        stage.addActor(factory.createWall(world, new Vector2(7.2f, 12.5f), 400f, 10f, -205f, 0f, "wall2", 2.2f, 0.1f));
        stage.addActor(factory.createWall(world, new Vector2(9.4f, 12.5f), 400f, 10f, -205f, 0f, "wall2", 2.2f, 0.1f));
        stage.addActor(factory.createWall(world, new Vector2(11.6f, 12.5f), 400f, 10f, -205f, 0f, "wall2", 2.2f, 0.1f));
        stage.addActor(factory.createWall(world, new Vector2(13.8f, 12.5f), 400f, 10f, -205f, 0f, "wall2", 2.2f, 0.1f));
        stage.addActor(factory.createWall(world, new Vector2(16f, 12.5f), 400f, 10f, -205f, 0f, "wall2", 2.2f, 0.1f));
        stage.addActor(factory.createWall(world, new Vector2(7.2f, 18.6f), 400f, 10f, -205f, 0f, "wall2", 2.2f, 0.1f));
        stage.addActor(factory.createWall(world, new Vector2(9.2f, 14.5f), 400f, 10f, -205f, 0f, "wall2", 2.2f, 0.1f));






        //Pamki

        stage.addActor(factory.createWall(world, new Vector2(5f, 1f), 10f, 400f, -10f, -200f, "wall1", 0.1f, 2f));
        stage.addActor(factory.createWall(world, new Vector2(5f, 3f), 10f, 400f, -10f, -200f, "wall1", 0.1f, 2f));
        stage.addActor(factory.createWall(world, new Vector2(5f, 5f), 10f, 400f, -10f, -200f, "wall1", 0.1f, 2f));
        stage.addActor(factory.createWall(world, new Vector2(5f, 7f), 10f, 400f, -10f, -200f, "wall1", 0.1f, 2f));
        stage.addActor(factory.createWall(world, new Vector2(5f, 9f), 10f, 400f, -10f, -200f, "wall1", 0.1f, 2f));
        stage.addActor(factory.createWall(world, new Vector2(5f, 11f), 10f, 400f, -10f, -200f, "wall1", 0.1f, 2f));
        stage.addActor(factory.createWall(world, new Vector2(5f, 13f), 10f, 400f, -10f, -200f, "wall1", 0.1f, 2f));
        stage.addActor(factory.createWall(world, new Vector2(5f, 15f), 10f, 400f, -10f, -200f, "wall1", 0.1f, 2f));
        stage.addActor(factory.createWall(world, new Vector2(5f, 17f), 10f, 400f, -10f, -200f, "wall1", 0.1f, 2f));

        stage.addActor(factory.createWall(world, new Vector2(7.2f, -1.3f), 400f, 10f, -205f, 0f, "wall2", 2.2f, 0.1f));
        stage.addActor(factory.createWall(world, new Vector2(9.4f, -1.3f), 400f, 10f, -205f, 0f, "wall2", 2.2f, 0.1f));
        stage.addActor(factory.createWall(world, new Vector2(11.6f, -1.3f), 400f, 10f, -205f, 0f, "wall2", 2.2f, 0.1f));
        stage.addActor(factory.createWall(world, new Vector2(13.8f, -1.3f), 400f, 10f, -205f, 0f, "wall2", 2.2f, 0.1f));
        stage.addActor(factory.createWall(world, new Vector2(16.0f, -1.3f), 400f, 10f, -205f, 0f, "wall2", 2.2f, 0.1f));
        stage.addActor(factory.createWall(world, new Vector2(18.2f, -1.3f), 400f, 10f, -205f, 0f, "wall2", 2.2f, 0.1f));
        stage.addActor(factory.createWall(world, new Vector2(20.4f, -1.3f), 400f, 10f, -205f, 0f, "wall2", 2.2f, 0.1f));
        stage.addActor(factory.createWall(world, new Vector2(22.6f, -1.3f), 400f, 10f, -205f, 0f, "wall2", 2.2f, 0.1f));
        stage.addActor(factory.createWall(world, new Vector2(24.8f, -1.3f), 400f, 10f, -205f, 0f, "wall2", 2.2f, 0.1f));
        stage.addActor(factory.createWall(world, new Vector2(27.0f, -1.3f), 400f, 10f, -205f, 0f, "wall2", 2.2f, 0.1f));
        stage.addActor(factory.createWall(world, new Vector2(29.2f, -1.3f), 400f, 10f, -205f, 0f, "wall2", 2.2f, 0.1f));

        stage.addActor(factory.createWall(world, new Vector2(31.4f, 1f), 10f, 400f, -10f, -200f, "wall1", 0.1f, 2f));
        stage.addActor(factory.createWall(world, new Vector2(31.4f, 3f), 10f, 400f, -10f, -200f, "wall1", 0.1f, 2f));
        stage.addActor(factory.createWall(world, new Vector2(31.4f, 5f), 10f, 400f, -10f, -200f, "wall1", 0.1f, 2f));
        stage.addActor(factory.createWall(world, new Vector2(31.4f, 7f), 10f, 400f, -10f, -200f, "wall1", 0.1f, 2f));
        stage.addActor(factory.createWall(world, new Vector2(31.4f, 9f), 10f, 400f, -10f, -200f, "wall1", 0.1f, 2f));
        stage.addActor(factory.createWall(world, new Vector2(31.4f, 13f), 10f, 400f, -10f, -200f, "wall1", 0.1f, 2f));
        stage.addActor(factory.createWall(world, new Vector2(31.4f, 15f), 10f, 400f, -10f, -200f, "wall1", 0.1f, 2f));
        stage.addActor(factory.createWall(world, new Vector2(31.4f, 17f), 10f, 400f, -10f, -200f, "wall1", 0.1f, 2f));

        stage.addActor(factory.createWall(world, new Vector2(29.2f, 19.2f), 400f, 10f, -205f, 0f, "wall2", 2.2f, 0.1f));
        stage.addActor(factory.createWall(world, new Vector2(27.0f, 19.2f), 400f, 10f, -205f, 0f, "wall2", 2.2f, 0.1f));
        stage.addActor(factory.createWall(world, new Vector2(24.8f, 19.2f), 400f, 10f, -205f, 0f, "wall2", 2.2f, 0.1f));
        stage.addActor(factory.createWall(world, new Vector2(22.6f, 19.2f), 400f, 10f, -205f, 0f, "wall2", 2.2f, 0.1f));
        stage.addActor(factory.createWall(world, new Vector2(20.4f, 19.2f), 400f, 10f, -205f, 0f, "wall2", 2.2f, 0.1f));
        stage.addActor(factory.createWall(world, new Vector2(18.2f, 19.2f), 400f, 10f, -205f, 0f, "wall2", 2.2f, 0.1f));
        stage.addActor(factory.createWall(world, new Vector2(16.0f, 19.2f), 400f, 10f, -205f, 0f, "wall2", 2.2f, 0.1f));
        stage.addActor(factory.createWall(world, new Vector2(13.8f, 19.2f), 400f, 10f, -205f, 0f, "wall2", 2.2f, 0.1f));
        stage.addActor(factory.createWall(world, new Vector2(11.6f, 19.2f), 400f, 10f, -205f, 0f, "wall2", 2.2f, 0.1f));
        stage.addActor(factory.createWall(world, new Vector2(9.4f, 19.2f), 400f, 10f, -205f, 0f, "wall2", 2.2f, 0.1f));
        stage.addActor(factory.createWall(world, new Vector2(7.2f, 19.2f), 400f, 10f, -205f, 0f, "wall2", 2.2f, 0.1f));














        // Add the player to the stage too
        stage.addActor(player);


        for (EnemyEntity enemy : enemies) {
            stage.addActor(enemy);
        }
        // during the game. We need to put the camera on the initial position so that you can
        // use it again if you replay the game.
        stage.getCamera().position.set(position);
        stage.getCamera().update();


        // Everything is ready, turn the volume up.
//        backgroundMusic.setVolume(0.75f);
//        backgroundMusic.play();

        renderer = new Box2DDebugRenderer();
        camera = new OrthographicCamera(32, 18);
        camera.translate(0, 1);
    }

    /**
     * This method will be executed when this screen is no more the active screen.
     * I use this method to destroy all the things that have been used in the stage.
     */
    @Override
    public void hide() {
        // Clear the stage. This will remove ALL actors from the stage and it is faster than
        // removing every single actor one by one. This is not shown in the video but it is
        // an improvement.
        stage.clear();

        // Detach every entity from the world they have been living in.
        player.detach();

    }

    public Vector2 getPlayerPosition() {
        return player.getPosition();
    }

    /**
     * This method is executed whenever the game requires this screen to be rendered. This will
     * display things on the screen. This method is also used to update the game.
     */
    @Override
    public void render(float delta) {
        // Do not forget to clean the screen.
        Gdx.gl.glClearColor(0.4f, 0.5f, 0.8f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // Update the stage. This will update the player speed.
        stage.act();
        player.processInput();

        for (EnemyEntity enemy : enemies) {
            enemy.move(getPlayerPosition());
        }

        // Step the world. This will update the physics and update entity positions.
        world.step(delta, 6, 2);

        stage.getCamera().position.set(new Vector2(player.getX(), player.getY()), 0);
        // Render the screen. Remember, this is the last step!

        camera.update();
        renderer.render(world, camera.combined);

        stage.draw();

    }
    /**
     * This method is executed when the screen can be safely disposed.
     * I use this method to dispose things that have to be manually disposed.
     */
    @Override
    public void dispose() {
        // Dispose the stage to remove the Batch references in the graphics card.
        stage.dispose();

        // Dispose the world to remove the Box2D native data (C++ backend, invoked by Java).
        world.dispose();
    }

    /**
     * This is the contact listener that checks the world for collisions and contacts.
     * I use this method to evaluate when things collide, such as player colliding with floor.
     */
    private class GameContactListener implements ContactListener {

        private boolean areCollided(Contact contact, Object userA, Object userB) {
            Object userDataA = contact.getFixtureA().getUserData();
            Object userDataB = contact.getFixtureB().getUserData();

            // This is not in the video! It is a good idea to check that user data is not null.
            // Sometimes you forget to put user data or you get collisions by entities you didn't
            // expect. Not preventing this will probably result in a NullPointerException.
            if (userDataA == null || userDataB == null) {
                return false;
            }

            // Because you never know what is A and what is B, you have to do both checks.
            return (userDataA.equals(userA) && userDataB.equals(userB)) ||
                    (userDataA.equals(userB) && userDataB.equals(userA));
        }

        /**
         * This method is executed when a contact has started: when two fixtures just collided.
         */
        @Override
        public void beginContact(Contact contact) {

            for (EnemyEntity enemy : enemies) {
                String enemyNick = enemy.getFixt();

                if (areCollided(contact, enemyNick, "wall")) {
                    enemy.blockMove(true);

                    if (contact.getFixtureB().getUserData().toString().contains("enemy")) {
                        contact.getFixtureB().getBody().setLinearVelocity(
                                -contact.getFixtureB().getBody().getLinearVelocity().x,
                                -contact.getFixtureB().getBody().getLinearVelocity().y
                        );
                    } else {
                        contact.getFixtureA().getBody().setLinearVelocity(
                                -contact.getFixtureA().getBody().getLinearVelocity().x,
                                -contact.getFixtureA().getBody().getLinearVelocity().y
                        );
                    }

                }

            }

        }

        /**
         * This method is executed when a contact has finished: two fixtures are no more colliding.
         */
        @Override
        public void endContact(final Contact contact) {

            for (final EnemyEntity enemy : enemies) {
                String enemyNick = enemy.getFixt();

                if (areCollided(contact, enemyNick, "wall")) {

                    TimerTask enemyTimer = new TimerTask() {
                        @Override
                        public void run() {
                            enemy.blockMove(false);
                        }
                    };

                    new java.util.Timer().schedule(enemyTimer, 500);

                }

            }

        }

        // Here two lonely methods that I don't use but have to override anyway.
        @Override public void preSolve(Contact contact, Manifold oldManifold) { }
        @Override public void postSolve(Contact contact, ContactImpulse impulse) { }
    }
}

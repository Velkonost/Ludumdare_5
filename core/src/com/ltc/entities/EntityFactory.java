package com.ltc.entities;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.ltc.screens.GameScreen;

/**
 * @author Velkonost
 */
public class EntityFactory {

    private AssetManager manager;

    /**
     * Create a new entity factory using the provided asset manager.
     * @param manager   the asset manager used to generate things.
     */
    public EntityFactory(AssetManager manager) {
        this.manager = manager;
    }

    /**
     * Create a player using the default texture.
     * @param world     world where the player will have to live in.
     * @param position  initial position ofr the player in the world (meters,meters).
     * @return          a player.
     */
    public PlayerEntity createPlayer(GameScreen game, World world, Vector2 position) {
        Texture playerTexture = manager.get("player.png");
        return new PlayerEntity(game, world, playerTexture, position);
    }

    public EnemyEntity createEnemy(GameScreen game, World world, Vector2 position, int index) {
        Texture playerTexture = manager.get("enemy.png");
        return new EnemyEntity(game, world, playerTexture, position, index);
    }

    public WallEntity createWall(World world, Vector2 position, Float size_x, Float size_y, Float plus_x, Float plus_y, String name, Float box_sizex, Float boxsize_y) {
        Texture playerTexture = manager.get("wallTexture.png");
        return new WallEntity(world, playerTexture, position, size_x, size_y, plus_x, plus_y, name, box_sizex, boxsize_y);
    }

    public BulletEntity createBullet(World world, Vector2 position) {
        Texture playerTexture = manager.get("bullet.png");
        return new BulletEntity(world, playerTexture, position);

    }

    /**
     * Create floor using the default texture set.
     * @param world     world where the floor will live in.
     * @param x         horizontal position for the spikes in the world (meters).
     * @param width     width for the floor (meters).
     * @param y         vertical position for the top of this floor (meters).
     * @return          a floor.
     */
//    public FloorEntity createFloor(World world, float x, float width, float y) {
//        Texture floorTexture = manager.get("floor.png");
//        Texture overfloorTexture = manager.get("overfloor.png");
//        return new FloorEntity(world, floorTexture, overfloorTexture, x, width, y);
//    }

    /**
     * Create spikes using the default texture.
     * @param world     world where the spikes will live in.
     * @param x         horizontal position for the spikes in the world (meters).
     * @param y         vertical position for the base of the spikes in the world (meters).
     * @return          some spikes.
     */
//    public SpikeEntity createSpikes(World world, float x, float y) {
//        Texture spikeTexture = manager.get("spike.png");
//        return new SpikeEntity(world, spikeTexture, x, y);
//    }

}


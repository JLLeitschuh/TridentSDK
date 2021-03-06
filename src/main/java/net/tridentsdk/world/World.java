/*
 * Trident - A Multithreaded Server Alternative
 * Copyright 2017 The TridentSDK Team
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package net.tridentsdk.world;

import net.tridentsdk.base.Block;
import net.tridentsdk.base.Position;
import net.tridentsdk.entity.Entity;
import net.tridentsdk.entity.living.Player;
import net.tridentsdk.world.opt.*;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.ThreadSafe;
import java.nio.file.Path;
import java.util.Collection;
import java.util.Set;
import java.util.stream.Stream;

/**
 * This class is a representation of a world
 *
 * <p>A world is defined as a folder containing compressed
 * chunk data and may be accessed using this class both
 * whilst loaded and unloaded.</p>
 *
 * @author TridentSDK
 * @since 0.3-alpha-DP
 */
@ThreadSafe
public interface World {
    /**
     * Obtains the name of the world that is represented
     * with the name of the file folder containing the
     * region files.
     *
     * @return the name of the world
     */
    String getName();

    /**
     * Obtains the current world time in ticks.
     *
     * <p>Each day-night cycle takes 24000 ticks to complete
     * where 0 is sunrise and 23999 is the end of the day.
     * </p>
     *
     * @return the time between 0 and 23999
     */
    int getTime();

    /**
     * Obtains a collection of players occupying this world.
     *
     * @return the collection of players that inhabit this
     * world
     */
    Set<? extends Player> getPlayers();

    /**
     * Obtains a stream of the entities, including players,
     * that currently inhabit this world.
     *
     * @return the players and entities in this world
     */
    Stream<? extends Entity> getEntities();

    /**
     * Obtains the options that this world has been created
     * to use.
     *
     * <p>The returned collection is immutable; it cannot
     * be modified.</p>
     *
     * @return the world options
     */
    WorldOpts getWorldOptions();

    /**
     * Obtains the weather conditions currently taking place
     * in the world.
     *
     * @return the weather options
     */
    Weather getWeather();

    /**
     * Obtains the options for the generator of this world.
     *
     * @return the generator options
     */
    GenOpts getGeneratorOptions();

    /**
     * Obtains the world border
     *
     * @return the world border options
     */
    WorldBorder getBorder();

    /**
     * Obtains which dimension this world is.
     *
     * @return this world's dimension
     */
    Dimension getDimension();

    /**
     * Obtains the chunk that is located at the given X/Z
     * horizontal plane coordinates.
     *
     * @param x the chunk X coordinate (not block XYZ!)
     * @param z the chunk Z coordinate (not block XYZ!)
     * @return the chunk at the given coordinates
     */
    @Nonnull
    Chunk getChunkAt(int x, int z);

    /**
     * Obtains a chunk at the given coordinates with an
     * option to not generate the chunk.
     *
     * @param x the x coordinate
     * @param z the z coordinate
     * @param gen {@code true} to generate the chunk if it
     * doesn't exist, {@code false} to return null
     * @return the chunk, or {@code null} if {@code gen} is
     * {@code false} and no chunk is found
     */
    @Nullable
    Chunk getChunkAt(int x, int z, boolean gen);

    /**
     * Obtains the collection of chunks that are currently
     * loaded on this world.
     *
     * @return the collection of loaded chunks
     */
    Collection<? extends Chunk> getLoadedChunks();

    /**
     * Obtains the highest non-air block at the given two
     * coordinates.
     *
     * @param x the x coordinate
     * @param z the z coordinate
     * @return the highest Y
     */
    int getHighestY(int x, int z);

    /**
     * Obtains the block that is located at the given XYZ
     * coordinates.
     *
     * <p>This method should be preferred over
     * {@link Position#getBlock()} if you do not already have
     * a Position object.</p>
     *
     * @param x the x coordinate which to find the block
     * @param y the y coordinate which to find the block
     * @param z the z coordinate which to find the block
     * @return the block located at the given position
     */
    Block getBlockAt(int x, int y, int z);

    /**
     * Obtains the block that is located at the given
     * position.
     *
     * @param pos the position which to find the block in
     * this world
     * @return the block located at the given position
     */
    Block getBlockAt(Position pos);

    /**
     * Obtains the enclosing directory which contains the
     * region and data files of this worlds's chunks.
     *
     * @return the world's directory
     */
    Path getDirectory();

    /**
     * Saves this world to the region files in the world
     * directory.
     */
    void save();
}
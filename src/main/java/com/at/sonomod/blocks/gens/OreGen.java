package com.at.sonomod.blocks.gens;

import com.at.sonomod.SonoMod;
import com.at.sonomod.blocks.ModBlocks;
import cpw.mods.fml.common.IWorldGenerator;
import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;

import java.util.Random;

public class OreGen implements IWorldGenerator {
    protected Block blockToGen;
    protected int veinNums = 6;
    protected int veinDense = 15;
    protected int topBound = 50;
    protected int bottomBound = 5;
    protected int chunkXBound = 16;
    protected int chunkZBound = 16;


    public OreGen(Block block, int veinNums, int dense, int topBound, int bottomBound) {
        blockToGen = block;
        this.veinNums = veinNums;
        veinDense = dense;
        this.topBound = topBound;
        this.bottomBound = bottomBound;
    }

    public OreGen(Block block, int veinNums, int dense, int topBound, int bottomBound, int chunkXBound, int chunkZBound) {
        this(block, veinNums, dense, topBound, bottomBound);
        this.chunkXBound = chunkXBound;
        this.chunkZBound = chunkZBound;
    }

    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world,
                         IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {

        switch(world.provider.dimensionId) {
        case -1 :
            generateInNether(world,random, chunkX*16, chunkZ*16);
            break;
        case 0 :
            generateInOverworld(world,random, chunkX*16, chunkZ*16);
            break;
        case 1 :
            generateInEnd(world,random, chunkX*16, chunkZ*16);
            break;
        }
}

    public void generateInEnd(World world, Random random, int x, int y) {

    }

    public void generateInOverworld(World world, Random random, int x, int z) {
        for(int i=0; i<veinNums; i++) { //The number after i (in this case 6) is how many veins spawn per chunk (16*16*16 area)

            int xcoord = x + random.nextInt(chunkXBound); //Sets random coords for the x axis

            int ycoord = random.nextInt(topBound - bottomBound) + bottomBound; //Sets random coords for the y axis

            int zcoord = z + random.nextInt(chunkZBound); //Sets random coords for the z axis

            new WorldGenMinable(blockToGen, veinDense).generate(world, random, xcoord, ycoord, zcoord); //Generates it (the number in the World Gen Minable function (in this case 15) is the max amount of blocks in each vein).
        }
    }

    public void generateInNether(World world, Random random, int x, int y) {

    }
}

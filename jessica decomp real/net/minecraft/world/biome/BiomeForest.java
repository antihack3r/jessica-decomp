/*
 * Decompiled with CFR 0.152.
 */
package net.minecraft.world.biome;

import java.util.Random;
import net.minecraft.block.BlockDoublePlant;
import net.minecraft.block.BlockFlower;
import net.minecraft.entity.passive.EntityRabbit;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenBigMushroom;
import net.minecraft.world.gen.feature.WorldGenBirchTree;
import net.minecraft.world.gen.feature.WorldGenCanopyTree;

public class BiomeForest
extends Biome {
    protected static final WorldGenBirchTree SUPER_BIRCH_TREE = new WorldGenBirchTree(false, true);
    protected static final WorldGenBirchTree BIRCH_TREE = new WorldGenBirchTree(false, false);
    protected static final WorldGenCanopyTree ROOF_TREE = new WorldGenCanopyTree(false);
    private final Type type;

    public BiomeForest(Type typeIn, Biome.BiomeProperties properties) {
        super(properties);
        this.type = typeIn;
        this.theBiomeDecorator.treesPerChunk = 10;
        this.theBiomeDecorator.grassPerChunk = 2;
        if (this.type == Type.FLOWER) {
            this.theBiomeDecorator.treesPerChunk = 6;
            this.theBiomeDecorator.flowersPerChunk = 100;
            this.theBiomeDecorator.grassPerChunk = 1;
            this.spawnableCreatureList.add(new Biome.SpawnListEntry(EntityRabbit.class, 4, 2, 3));
        }
        if (this.type == Type.NORMAL) {
            this.spawnableCreatureList.add(new Biome.SpawnListEntry(EntityWolf.class, 5, 4, 4));
        }
        if (this.type == Type.ROOFED) {
            this.theBiomeDecorator.treesPerChunk = -999;
        }
    }

    @Override
    public WorldGenAbstractTree genBigTreeChance(Random rand) {
        if (this.type == Type.ROOFED && rand.nextInt(3) > 0) {
            return ROOF_TREE;
        }
        if (this.type != Type.BIRCH && rand.nextInt(5) != 0) {
            return rand.nextInt(10) == 0 ? BIG_TREE_FEATURE : TREE_FEATURE;
        }
        return BIRCH_TREE;
    }

    @Override
    public BlockFlower.EnumFlowerType pickRandomFlower(Random rand, BlockPos pos) {
        if (this.type == Type.FLOWER) {
            double d0 = MathHelper.clamp((1.0 + GRASS_COLOR_NOISE.getValue((double)pos.getX() / 48.0, (double)pos.getZ() / 48.0)) / 2.0, 0.0, 0.9999);
            BlockFlower.EnumFlowerType blockflower$enumflowertype = BlockFlower.EnumFlowerType.values()[(int)(d0 * (double)BlockFlower.EnumFlowerType.values().length)];
            return blockflower$enumflowertype == BlockFlower.EnumFlowerType.BLUE_ORCHID ? BlockFlower.EnumFlowerType.POPPY : blockflower$enumflowertype;
        }
        return super.pickRandomFlower(rand, pos);
    }

    @Override
    public void decorate(World worldIn, Random rand, BlockPos pos) {
        if (this.type == Type.ROOFED) {
            this.addMushrooms(worldIn, rand, pos);
        }
        int i = rand.nextInt(5) - 3;
        if (this.type == Type.FLOWER) {
            i += 2;
        }
        this.addDoublePlants(worldIn, rand, pos, i);
        super.decorate(worldIn, rand, pos);
    }

    protected void addMushrooms(World p_185379_1_, Random p_185379_2_, BlockPos p_185379_3_) {
        int i = 0;
        while (i < 4) {
            int j = 0;
            while (j < 4) {
                int k = i * 4 + 1 + 8 + p_185379_2_.nextInt(3);
                int l = j * 4 + 1 + 8 + p_185379_2_.nextInt(3);
                BlockPos blockpos = p_185379_1_.getHeight(p_185379_3_.add(k, 0, l));
                if (p_185379_2_.nextInt(20) == 0) {
                    WorldGenBigMushroom worldgenbigmushroom = new WorldGenBigMushroom();
                    worldgenbigmushroom.generate(p_185379_1_, p_185379_2_, blockpos);
                } else {
                    WorldGenAbstractTree worldgenabstracttree = this.genBigTreeChance(p_185379_2_);
                    worldgenabstracttree.setDecorationDefaults();
                    if (worldgenabstracttree.generate(p_185379_1_, p_185379_2_, blockpos)) {
                        worldgenabstracttree.generateSaplings(p_185379_1_, p_185379_2_, blockpos);
                    }
                }
                ++j;
            }
            ++i;
        }
    }

    protected void addDoublePlants(World p_185378_1_, Random p_185378_2_, BlockPos p_185378_3_, int p_185378_4_) {
        int i = 0;
        while (i < p_185378_4_) {
            int j = p_185378_2_.nextInt(3);
            if (j == 0) {
                DOUBLE_PLANT_GENERATOR.setPlantType(BlockDoublePlant.EnumPlantType.SYRINGA);
            } else if (j == 1) {
                DOUBLE_PLANT_GENERATOR.setPlantType(BlockDoublePlant.EnumPlantType.ROSE);
            } else if (j == 2) {
                DOUBLE_PLANT_GENERATOR.setPlantType(BlockDoublePlant.EnumPlantType.PAEONIA);
            }
            int k = 0;
            while (k < 5) {
                int l = p_185378_2_.nextInt(16) + 8;
                int i1 = p_185378_2_.nextInt(16) + 8;
                int j1 = p_185378_2_.nextInt(p_185378_1_.getHeight(p_185378_3_.add(l, 0, i1)).getY() + 32);
                if (DOUBLE_PLANT_GENERATOR.generate(p_185378_1_, p_185378_2_, new BlockPos(p_185378_3_.getX() + l, j1, p_185378_3_.getZ() + i1))) break;
                ++k;
            }
            ++i;
        }
    }

    @Override
    public Class<? extends Biome> getBiomeClass() {
        return BiomeForest.class;
    }

    @Override
    public int getGrassColorAtPos(BlockPos pos) {
        int i = super.getGrassColorAtPos(pos);
        return this.type == Type.ROOFED ? (i & 0xFEFEFE) + 2634762 >> 1 : i;
    }

    public static enum Type {
        NORMAL,
        FLOWER,
        BIRCH,
        ROOFED;

    }
}


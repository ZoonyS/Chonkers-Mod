package zoony.chonker.cat.common.world;

import zoony.chonker.cat.common.entity.ChonkerEntity;
import zoony.chonker.cat.common.init.ChonkerModEntity;

import net.minecraft.entity.EntityData;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.SpawnRestriction;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.world.GameRules;
import net.minecraft.world.SpawnHelper;
import net.minecraft.world.gen.Spawner;
import net.minecraft.world.gen.feature.StructureFeature;
import net.minecraft.world.poi.PointOfInterestStorage;
import net.minecraft.world.poi.PointOfInterestType;

import java.util.List;
import java.util.Random;

// Code is inspired from https://github.com/Ladysnake/Rats-Mischief/blob/main/src/main/java/ladysnake/ratsmischief/common/world/RatSpawner.java <3

public class ChonkerSpawner implements Spawner {
    public static final int SPAWN_RADIUS = 100;
    private int ticksUntilNextSpawn;
    EntityType<ChonkerEntity> CHONKER = ChonkerModEntity.CHONKER;

    public int spawn(ServerWorld world, boolean spawnMonsters, boolean spawnAnimals) {
        if (spawnAnimals && world.getGameRules().getBoolean(GameRules.DO_MOB_SPAWNING)) {
            --this.ticksUntilNextSpawn;
            if (this.ticksUntilNextSpawn <= 0) {
                this.ticksUntilNextSpawn = 1;
                Random random = world.random;
                world.getPlayers().forEach(serverPlayerEntity -> {
                    int x = (8 + random.nextInt(32)) * (random.nextBoolean() ? -1 : 1);
                    int y = (random.nextInt(4)) * (random.nextBoolean() ? -1 : 1);
                    int z = (8 + random.nextInt(32)) * (random.nextBoolean() ? -1 : 1);
                    BlockPos blockPos = serverPlayerEntity.getBlockPos().add(x, y, z);

                    if (ChonkerEntity.canMobSpawn(CHONKER, world, SpawnReason.NATURAL, blockPos, world.getRandom())) {
                        BlockPos villagePos = world.locateStructure(StructureFeature.VILLAGE, blockPos, 5, false);
                        if (villagePos != null && blockPos.getManhattanDistance(villagePos) <= 300) {
                            if (SpawnHelper.canSpawn(SpawnRestriction.Location.ON_GROUND, world, blockPos, CHONKER)) {
                                for (int i = 0; i <= random.nextInt(5); i++) {
                                    this.spawnInHouse(world, blockPos);
                                }
                            }
                        }
                    }
                });
            }
        }
        return 0;
    }   

    private void spawnInHouse(ServerWorld world, BlockPos pos) {
        long bedCount = world.getPointOfInterestStorage().count(PointOfInterestType.HOME.getCompletionCondition(), pos, 48, PointOfInterestStorage.OccupationStatus.HAS_SPACE);
        List<ChonkerEntity> list = world.getNonSpectatingEntities(ChonkerEntity.class, (new Box(pos)).expand(96.0, 16.0D, 96.0D));

        if (list.size() < bedCount * 3 && list.size() < 20) {
            this.spawn(world, pos);
        }
    }

    private void spawn(ServerWorld world, BlockPos pos) {
        ChonkerEntity entity = CHONKER.create(world);
        if (entity != null) {
            entity.initialize(world, world.getLocalDifficulty(pos), SpawnReason.NATURAL, (EntityData) null, (NbtCompound) null);
            entity.refreshPositionAndAngles(pos, 0.0F, 0.0F);
            world.spawnEntityAndPassengers(entity);
        }
    }
}
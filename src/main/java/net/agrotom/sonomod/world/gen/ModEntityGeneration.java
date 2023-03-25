package net.agrotom.sonomod.world.gen;


import java.util.Arrays;
import java.util.List;

import net.agrotom.sonomod.entity.ModEntityTypes;
import net.minecraft.entity.EntityType;
import net.minecraft.util.RegistryKey;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biomes;
import net.minecraft.world.biome.MobSpawnInfo;
import net.minecraftforge.common.world.MobSpawnInfoBuilder;
import net.minecraftforge.event.world.BiomeLoadingEvent;

public class ModEntityGeneration
{

	public static void onEntitySpawn(final BiomeLoadingEvent event)
	{
		addEntityToSpecificBiomes(event, ModEntityTypes.REPTILE.get(), 40, 2, 4, Biomes.BEACH, Biomes.SAVANNA);
		addEntityToSpecificBiomes(event, ModEntityTypes.MYCEPLIT.get(), 40, 4, 6, Biomes.PLAINS);
		addEntityToAllBiomes(event.getSpawns(), ModEntityTypes.GIMOS.get(), 20, 1, 2);
	}

	@SuppressWarnings("unused")
	@SafeVarargs
	private static void addEntityToAllBiomesExceptThese(BiomeLoadingEvent event, EntityType<?> type,
                                                        int weight, int minCount, int maxCount, RegistryKey<Biome>... biomes) {
        boolean isBiomeSelected = Arrays.stream(biomes).map(RegistryKey::location)
                .map(Object::toString).anyMatch(s -> s.equals(event.getName().toString()));

        if(!isBiomeSelected) {
            addEntityToAllBiomes(event.getSpawns(), type, weight, minCount, maxCount);
        }
    }

    @SafeVarargs
	private static void addEntityToSpecificBiomes(BiomeLoadingEvent event, EntityType<?> type,
                                                  int weight, int minCount, int maxCount, RegistryKey<Biome>... biomes) {
        boolean isBiomeSelected = Arrays.stream(biomes).map(RegistryKey::location)
                .map(Object::toString).anyMatch(s -> s.equals(event.getName().toString()));

        if(isBiomeSelected) {
            addEntityToAllBiomes(event.getSpawns(), type, weight, minCount, maxCount);
        }
    }

    private static void addEntityToAllBiomes(MobSpawnInfoBuilder spawns, EntityType<?> type,
                                             int weight, int minCount, int maxCount) {
        List<MobSpawnInfo.Spawners> base = spawns.getSpawner(type.getCategory());
        base.add(new MobSpawnInfo.Spawners(type,weight, minCount, maxCount));
    }

}

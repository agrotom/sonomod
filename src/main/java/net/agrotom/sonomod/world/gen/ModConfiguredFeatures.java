package net.agrotom.sonomod.world.gen;

import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.IFeatureConfig;

public class ModConfiguredFeatures
{
	/*public static final ConfiguredFeature<?, ?> ONYX_ORE = 
			register("")*/
	
	public static <FC extends IFeatureConfig> ConfiguredFeature<FC, ?> register(final String key,
			ConfiguredFeature<FC, ?> configuredFeature)
	{
		return Registry.register(WorldGenRegistries.CONFIGURED_FEATURE, key, configuredFeature);
	}

}

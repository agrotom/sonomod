package net.agrotom.sonomod.entity;

import net.agrotom.sonomod.SonoMod;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModEntityTypes
{
	public static DeferredRegister<EntityType<?>> ENTITY_TYPES
			= DeferredRegister.create(ForgeRegistries.ENTITIES, SonoMod.MOD_ID);
	
	public static final RegistryObject<EntityType<ReptileEntity>> REPTILE =
			ENTITY_TYPES.register("reptile",
					() -> EntityType.Builder.of(ReptileEntity::new,
								EntityClassification.CREATURE).sized(1.5f, 1.5f).build(
											new ResourceLocation(SonoMod.MOD_ID, "reptile").toString()));
	
	public static final RegistryObject<EntityType<MyceplitEntity>> MYCEPLIT =
			ENTITY_TYPES.register("myceplit",
					() -> EntityType.Builder.of(MyceplitEntity::new,
								EntityClassification.CREATURE).sized(1.5f, 2f).build(
											new ResourceLocation(SonoMod.MOD_ID, "myceplit").toString()));
	
	public static final RegistryObject<EntityType<GimosEntity>> GIMOS =
			ENTITY_TYPES.register("gimos",
					() -> EntityType.Builder.of(GimosEntity::new,
								EntityClassification.MONSTER).sized(1.5f, 2f).build(
											new ResourceLocation(SonoMod.MOD_ID, "gimos").toString()));
	
	public static void register(IEventBus eventBus)
	{
		ENTITY_TYPES.register(eventBus);
	}

}

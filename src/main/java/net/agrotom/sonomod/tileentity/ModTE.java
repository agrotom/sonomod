package net.agrotom.sonomod.tileentity;

import net.agrotom.sonomod.SonoMod;
import net.agrotom.sonomod.blocks.ModBlocks;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModTE {
	public static final DeferredRegister<TileEntityType<?>> TILE_ENTITIES = DeferredRegister.create(ForgeRegistries.TILE_ENTITIES, SonoMod.MOD_ID);

	public static final RegistryObject<TileEntityType<GrillTileEntity>> GRILL_TILEENTITY = TILE_ENTITIES.register("grill_tileentity",
			() -> TileEntityType.Builder.of(GrillTileEntity::new, ModBlocks.GRILL_BLOCK.get()).build(null));
	
	public static void register(IEventBus eventBus)
	{
		TILE_ENTITIES.register(eventBus);
	}
}
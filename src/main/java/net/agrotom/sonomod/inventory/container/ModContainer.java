package net.agrotom.sonomod.inventory.container;

import net.agrotom.sonomod.SonoMod;
import net.minecraft.inventory.container.ContainerType;
import net.minecraftforge.common.extensions.IForgeContainerType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModContainer {
	
	private ModContainer() {}
	
	private static final DeferredRegister<ContainerType<?>> CONTAINER_TYPES =
		      DeferredRegister.create(ForgeRegistries.CONTAINERS, SonoMod.MOD_ID);
	
	public static final RegistryObject<ContainerType<SkillsContainer>> SKILLS_CONTAINER =
			CONTAINER_TYPES.register("skills_container",
		          () -> IForgeContainerType.create(SkillsContainer::new));
	
	public static void register(IEventBus eventBus) {
		CONTAINER_TYPES.register(eventBus);
    }
}

package net.agrotom.sonomod;

import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.agrotom.sonomod.blocks.ModBlocks;
import net.agrotom.sonomod.client.ModKeybinds;
import net.agrotom.sonomod.client.render.GimosRenderer;
import net.agrotom.sonomod.client.render.GrillTileEntityRenderer;
import net.agrotom.sonomod.client.render.MyceplitRenderer;
import net.agrotom.sonomod.client.render.ReptileRenderer;
import net.agrotom.sonomod.client.render.WoodenBarrelTileEntityRenderer;
import net.agrotom.sonomod.client.render.color.ModColorBlocks;
import net.agrotom.sonomod.data.recipes.ModRecipeTypes;
import net.agrotom.sonomod.entity.GimosEntity;
import net.agrotom.sonomod.entity.ModEntityTypes;
import net.agrotom.sonomod.items.ModItems;
import net.agrotom.sonomod.tileentity.ModTE;
import net.minecraft.entity.EntitySpawnPlacementRegistry;
import net.minecraft.entity.EntitySpawnPlacementRegistry.PlacementType;
import net.minecraft.world.gen.Heightmap;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(SonoMod.MOD_ID)
public class SonoMod
{
	public static final String MOD_ID = "sonomod";
	public static final String BLANK = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. "
		+ "Nullam euismod magna ac sapien ultricies consectetur. "
		+ "Fusce mi metus, varius id convallis et, semper in dui. In hac habitasse platea dictumst. "
		+ "Sed tincidunt fermentum massa porttitor auctor.";
	
    // Directly reference a log4j logger.
    private static final Logger LOGGER = LogManager.getLogger();

    public SonoMod() {
        // Register the setup method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        // Register the enqueueIMC method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::enqueueIMC);
        // Register the processIMC method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::processIMC);
        // Register the doClientStuff method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::doClientStuff);
        
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ModItems.register(eventBus);
        ModBlocks.register(eventBus);
        ModTE.register(eventBus);
        ModEntityTypes.register(eventBus);

        ModRecipeTypes.register(eventBus);

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
    }

    private void setup(final FMLCommonSetupEvent event)
    {
    	event.enqueueWork(() ->
    	{
    		EntitySpawnPlacementRegistry.register(ModEntityTypes.GIMOS.get(), PlacementType.NO_RESTRICTIONS, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, GimosEntity::checkMonsterSpawnRules);
    	});
    }

    @SuppressWarnings("resource")
	private void doClientStuff(final FMLClientSetupEvent event) {
    	ClientRegistry.bindTileEntityRenderer(ModTE.GRILL_TILEENTITY.get(), GrillTileEntityRenderer::new);
    	ClientRegistry.bindTileEntityRenderer(ModTE.WOODEN_BARREL_TILEENTITY.get(), WoodenBarrelTileEntityRenderer::new);
    	ModColorBlocks.register();
    	
    	RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.REPTILE.get(), ReptileRenderer::new);
    	RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.MYCEPLIT.get(), MyceplitRenderer::new);
    	RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.GIMOS.get(), GimosRenderer::new);
    	
    	ModKeybinds.register(event);
    	
        // do something that can only be done on the client
        //LOGGER.info("Got game settings {}", event.getMinecraftSupplier().ge);
    }

    private void enqueueIMC(final InterModEnqueueEvent event)
    {
        // some example code to dispatch IMC to another mod
        InterModComms.sendTo("examplemod", "helloworld", () -> { LOGGER.info("Hello world from the MDK"); return "Hello world";});
    }

    private void processIMC(final InterModProcessEvent event)
    {
        // some example code to receive and process InterModComms from other mods
        LOGGER.info("Got IMC {}", event.getIMCStream().
                map(m->m.getMessageSupplier().get()).
                collect(Collectors.toList()));
    }
    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(FMLServerStartingEvent event) {
        // do something when the server starts
        LOGGER.info("HELLO from server starting");
    }

    // You can use EventBusSubscriber to automatically subscribe events on the contained class (this is subscribing to the MOD
    // Event bus for receiving Registry Events)
    @Mod.EventBusSubscriber(bus=Mod.EventBusSubscriber.Bus.MOD)
    public static class RegistryEvents {
    	
    }
}

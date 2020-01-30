package com.github.commoble.exmachina.content.event;

import com.github.commoble.exmachina.ExMachinaMod;
import com.github.commoble.exmachina.api.circuit.ComponentRegistry;
import com.github.commoble.exmachina.content.block.BlockRegistrar;
import com.github.commoble.exmachina.content.block.CubeWireProperties;
import com.github.commoble.exmachina.content.block.ResistorProperties;
import com.github.commoble.exmachina.content.block.VoltageSourceProperties;
import com.github.commoble.exmachina.content.item.ItemRegistrar;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

/**
 * Event handler for registering Blocks, Enchantments, Items, Potions, SoundEvents, and Biomes
 * @author Joseph
 *
 */
@Mod.EventBusSubscriber(modid = ExMachinaMod.MODID, bus=Bus.MOD)
public class CommonModEventHandler
{
	@SubscribeEvent
	public static void registerBlocks(RegistryEvent.Register<Block> event)
	{
		BlockRegistrar.registerBlocks(event);
	}
	
	@SubscribeEvent
	public static void registerItems(RegistryEvent.Register<Item> event)
	{
		ItemRegistrar.registerItems(event);
	}
	
	@SubscribeEvent
	public static void onCommonSetup(FMLCommonSetupEvent event)
	{
		//CubeWireProperties.registerCubeWire(Blocks.GOLD_BLOCK, 1D);
		ComponentRegistry.WIRES.put(Blocks.GOLD_BLOCK, new CubeWireProperties(0.001D));
		ComponentRegistry.ELEMENTS.put(BlockRegistrar.battery, new VoltageSourceProperties(10D));
		ComponentRegistry.ELEMENTS.put(BlockRegistrar.lightbulb, new ResistorProperties(1000D));
	}
}
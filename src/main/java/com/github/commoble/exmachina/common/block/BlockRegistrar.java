package com.github.commoble.exmachina.common.block;

import com.github.commoble.exmachina.common.ExMachinaMod;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.ObjectHolder;

/**
 * Class for registering blocks and storing their references
 */
public class BlockRegistrar
{	
	//public static final String TRANSPORTER_REGISTRY_NAME = "transporter";
	
	@ObjectHolder("exmachina:ash")
	public static final BlockAsh ash = null;
	
	@ObjectHolder("exmachina:battery")
	public static final BlockBattery battery = null;
	
	@ObjectHolder("exmachina:wire")
	public static final BlockWire wire = null;
	
	@ObjectHolder("exmachina:lightbulb")
	public static final BlockLightbulb lightbulb = null;
	
	

	public static void registerBlocks(RegistryEvent.Register<Block> event)
	{
		IForgeRegistry<Block> registry = event.getRegistry();
		//BlockLedger.blockTransporter = (BlockTransporter)registerBlock(event.getRegistry(), new BlockTransporter(), BlockLedger.TRANSPORTER_REGISTRY_NAME);
		
		// override default fire block
		BlockExtendedFire blockExtendedFire = new BlockExtendedFire();
		blockExtendedFire.setRegistryName("minecraft:fire");
		registry.register(blockExtendedFire);
		
		registerBlock(registry, new BlockAsh(Block.Properties.create(Material.SAND, MaterialColor.GRAY).needsRandomTick()), BlockNames.ASH_NAME);
		registerBlock(registry, new BlockBattery(Block.Properties.create(Material.IRON, MaterialColor.BROWN).hardnessAndResistance(2F, 5F)), BlockNames.BATTERY_NAME);
		registerBlock(registry, new BlockWire(Block.Properties.create(Material.CIRCUITS, MaterialColor.ORANGE_TERRACOTTA).hardnessAndResistance(0.2F, 0F).sound(SoundType.STONE)), BlockNames.WIRE_NAME);
		registerBlock(registry, new BlockLightbulb(Block.Properties.create(Material.GLASS, MaterialColor.GOLD).hardnessAndResistance(0.3F, 0F)), BlockNames.LIGHTBULB_NAME);
		
		CategoriesOfBlocks.addWireBlock(BlockRegistrar.wire);
		CategoriesOfBlocks.addActiveComponentBlock(BlockRegistrar.battery);
		CategoriesOfBlocks.addPassiveComponentBlock(BlockRegistrar.lightbulb);
		// register recipes
		
		// redo flammability init
		// must delay until this point or it'll affect the vanilla fire block instead
		BlockExtendedFire.init();
	}
	
	private static <T extends Block> T registerBlock(IForgeRegistry<Block> registry, T newBlock, String name)
	{
		String prefixedName = ExMachinaMod.MODID + ":" + name;
		newBlock.setRegistryName(prefixedName);
		registry.register(newBlock);
		return newBlock;
	}
}
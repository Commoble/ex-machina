package com.github.commoble.exmachina.common.tileentity;

import com.github.commoble.exmachina.common.ExMachinaMod;
import com.github.commoble.exmachina.common.block.BlockNames;
import com.github.commoble.exmachina.common.block.BlockRegistrar;

import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.registries.ObjectHolder;

public class TileEntityRegistrar
{
	@ObjectHolder(ExMachinaMod.MODID + ":" + BlockNames.BATTERY_NAME)
	public static TileEntityType<TileEntityBattery> teBatteryType;
	

	@ObjectHolder(ExMachinaMod.MODID + ":" + BlockNames.LIGHTBULB_NAME)
	public static TileEntityType<TileEntityBattery> teLightbulbType;
	
	public static void registerTileEntities(RegistryEvent.Register<TileEntityType<?>> event)
	{
		TileEntityType.register(BlockRegistrar.battery.getRegistryName().toString(), TileEntityType.Builder.create(TileEntityBattery::new));
		TileEntityType.register(BlockRegistrar.lightbulb.getRegistryName().toString(), TileEntityType.Builder.create(TileEntityLightbulb::new));
	}
}
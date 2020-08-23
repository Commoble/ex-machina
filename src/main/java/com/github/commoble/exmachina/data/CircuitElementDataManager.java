package com.github.commoble.exmachina.data;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

import com.github.commoble.exmachina.CircuitBehaviourRegistry;
import com.github.commoble.exmachina.ExMachina;
import com.github.commoble.exmachina.api.CircuitComponent;
import com.google.gson.Gson;
import com.google.gson.JsonElement;

import net.minecraft.block.Block;
import net.minecraft.client.resources.JsonReloadListener;
import net.minecraft.profiler.IProfiler;
import net.minecraft.resources.IResourceManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.ForgeRegistries;

public class CircuitElementDataManager extends JsonReloadListener implements Supplier<Map<Block, ? extends CircuitComponent>>
{	
	public static final Gson GSON = new Gson();
	
	// use a subfolder so we're less likely to conflict with other mods
	// i.e. this loads jsons at resources/data/modid/exmachina/circuit_elements/name.json
	public static final String FOLDER = "exmachina/circuit_elements";

	public Map<Block, DefinedCircuitComponent> data = new HashMap<>();
	
	public CircuitElementDataManager()
	{
		super(GSON, FOLDER);
	}

	@Override
	// the superclass parses all jsons for all modids in the above data folder
	// and compiles them into the given JsonElement map below
	// no merging is done here, so jsons with the same modid:name identifier are overwritten by datapack priority
	protected void apply(Map<ResourceLocation, JsonElement> jsons, IResourceManager resourceManager, IProfiler profiler)
	{
		Map<Block, DefinedCircuitComponent> newMap = new HashMap<>();
		CircuitBehaviourRegistry circuitBehaviourRegistry = ExMachina.INSTANCE.circuitBehaviourRegistry;
		for (java.util.Map.Entry<ResourceLocation, JsonElement> entry : jsons.entrySet())
		{
			// gson the json element into an intermediary class
			RawCircuitElement raw = GSON.fromJson(entry.getValue(), RawCircuitElement.class);
			Block block = ForgeRegistries.BLOCKS.getValue(entry.getKey());
			if (block != null)
			{
				// and use it to create the finalized element
				newMap.put(block, new DefinedCircuitComponent(raw, block, circuitBehaviourRegistry));
			}
		}
		
		this.data = newMap;
	}

	@Override
	public Map<Block, ? extends CircuitComponent> get()
	{
		return this.data;
	}
	
	
}
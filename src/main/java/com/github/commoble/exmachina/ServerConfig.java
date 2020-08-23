package com.github.commoble.exmachina;

import com.github.commoble.exmachina.util.ConfigHelper;
import com.github.commoble.exmachina.util.ConfigHelper.ConfigValueListener;

import net.minecraftforge.common.ForgeConfigSpec;

public class ServerConfig
{
	public final ConfigValueListener<Double> max_wire_post_connection_range;
	public final ConfigValueListener<Integer> max_circuit_size_in_blocks;
	
	public ServerConfig(ForgeConfigSpec.Builder builder, ConfigHelper.Subscriber subscriber)
	{
		builder.push("General Settings");
		this.max_wire_post_connection_range = subscriber.subscribe(builder
			.comment("Maximum Wire Post Connection Range")
			.translation("exmachina.config.max_wire_plinth_connection_range")
			.defineInRange("max_wire_post_connection_range", 32D, 0D, Double.MAX_VALUE));
		
		this.max_circuit_size_in_blocks = subscriber.subscribe(builder
			.comment("Maximum size of circuits in blocks")
			.translation("exmachina.config.max_circuit_size_in_blocks")
			.defineInRange("max_circuit_size_in_blocks", 2000, 1, Integer.MAX_VALUE));
	}
}
package net.commoble.exmachina.api.content;

import java.util.Collection;
import java.util.List;

import org.jetbrains.annotations.Nullable;

import com.mojang.serialization.MapCodec;

import net.commoble.exmachina.api.Channel;
import net.commoble.exmachina.api.ExMachinaRegistries;
import net.commoble.exmachina.api.Face;
import net.commoble.exmachina.api.Receiver;
import net.commoble.exmachina.api.SignalReceiver;
import net.commoble.exmachina.internal.ExMachina;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.BlockState;

/**
 * Default SignalReceiver used for blocks that have none assigned to them. No-ops.
 * 
<pre>
{
	"type": "exmachina:default"
}
</pre>
 */
public enum DefaultReceiver implements SignalReceiver
{
	/** Singleton instance of DefaultReceiver */
	INSTANCE;

	/** exmachina:signal_receiver_type / exmachina:default */
	public static final ResourceKey<MapCodec<? extends SignalReceiver>> KEY = ResourceKey.create(ExMachinaRegistries.SIGNAL_RECEIVER_TYPE, ExMachina.id("default"));

	/** <pre>{"type": "exmachina:default"}</pre> */
	public static final MapCodec<DefaultReceiver> CODEC = MapCodec.unit(INSTANCE);
	
	@Override
	public MapCodec<? extends SignalReceiver> codec()
	{
		return CODEC;
	}
	@Override
	public @Nullable Receiver getReceiverEndpoint(BlockGetter level, BlockPos receiverPos, BlockState receiverState, Direction receiverSide, Face connectedFace, Channel channel)
	{
		return null;
	}
	@Override
	public Collection<Receiver> getAllReceivers(BlockGetter level, BlockPos receiverPos, BlockState receiverState, Channel channel)
	{
		return List.of();
	}
}
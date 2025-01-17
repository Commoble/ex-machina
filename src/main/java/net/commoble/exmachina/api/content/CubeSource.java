package net.commoble.exmachina.api.content;

import java.util.Map;
import java.util.function.ToIntFunction;

import org.jetbrains.annotations.Nullable;

import com.mojang.serialization.MapCodec;

import net.commoble.exmachina.api.Channel;
import net.commoble.exmachina.api.ExMachinaRegistries;
import net.commoble.exmachina.api.Node;
import net.commoble.exmachina.api.NodeShape;
import net.commoble.exmachina.api.SignalSource;
import net.commoble.exmachina.internal.ExMachina;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.state.BlockState;

/**
 * SignalSource for a Block that can connect to the signal graph on all sides, providing their vanilla redstone signal output to the graph.
<pre>
{
	"type": "exmachina:cube"
}
</pre>
 */
public enum CubeSource implements SignalSource
{
	/** Singleton instance for CubeSource */
	INSTANCE;
	
	/** exmachina:signal_source_type / exmachina:cube */
	public static final ResourceKey<MapCodec<? extends SignalSource>> KEY = ResourceKey.create(ExMachinaRegistries.SIGNAL_SOURCE_TYPE, ExMachina.id("cube"));
	
	/** <pre>{"type": "exmachina:cube"}</pre> */
	public static final MapCodec<CubeSource> CODEC = MapCodec.unit(INSTANCE);

	@Override
	public MapCodec<? extends SignalSource> codec()
	{
		return CODEC;
	}

	@Override
	public Map<Channel, ToIntFunction<LevelReader>> getSupplierEndpoints(ResourceKey<Level> supplierLevelKey, BlockGetter supplierLevel, BlockPos supplierPos, BlockState supplierState, NodeShape preferredSupplierShape, Node connectingNode)
	{
		BlockPos offsetFromNeighbor = supplierPos.subtract(connectingNode.pos());
		@Nullable Direction directionFromNeighbor = Direction.getNearest(offsetFromNeighbor, null); 
		return directionFromNeighbor == null
			? Map.of()
			: Map.of(Channel.redstone(), reader -> reader.getSignal(supplierPos, directionFromNeighbor));
	}
}

package net.kaupenjoe.mccourse.block.custom;

import com.google.common.base.Suppliers;
import com.google.common.collect.BiMap;
import com.google.common.collect.ImmutableBiMap;
import net.kaupenjoe.mccourse.block.ModBlocks;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.ChangeOverTimeBlock;
import net.minecraft.world.level.block.WeatheringCopper;
import net.minecraft.world.level.block.state.BlockState;

import java.util.Optional;
import java.util.function.Supplier;

public interface GemDegradable extends ChangeOverTimeBlock<GemDegradable.GemDegradationLevel> {
    Supplier<BiMap<Block, Block>> NEXT_BY_BLOCK = Suppliers.memoize(() -> {
        return ImmutableBiMap.<Block, Block>builder()
                .put(ModBlocks.RUBY_BLOCK.get(), ModBlocks.RUBY_BLOCK_1.get())
                .put(ModBlocks.RUBY_BLOCK_1.get(), ModBlocks.RUBY_BLOCK_2.get())
                .put(ModBlocks.RUBY_BLOCK_2.get(), ModBlocks.RUBY_BLOCK_3.get()).build();
    });
    Supplier<BiMap<Block, Block>> PREVIOUS_BY_BLOCK = Suppliers.memoize(() -> {
        return NEXT_BY_BLOCK.get().inverse();
    });

    static Optional<Block> getPrevious(Block pBlock) {
        return Optional.ofNullable(PREVIOUS_BY_BLOCK.get().get(pBlock));
    }

    static Block getFirst(Block pBlock) {
        Block block = pBlock;

        for(Block block1 = PREVIOUS_BY_BLOCK.get().get(pBlock); block1 != null; block1 = PREVIOUS_BY_BLOCK.get().get(block1)) {
            block = block1;
        }

        return block;
    }

    static Optional<BlockState> getPrevious(BlockState pState) {
        return getPrevious(pState.getBlock()).map((p_154903_) -> {
            return p_154903_.withPropertiesOf(pState);
        });
    }

    static Optional<Block> getNext(Block pBlock) {
        return Optional.ofNullable(NEXT_BY_BLOCK.get().get(pBlock));
    }

    static BlockState getFirst(BlockState pState) {
        return getFirst(pState.getBlock()).withPropertiesOf(pState);
    }

    default Optional<BlockState> getNext(BlockState pState) {
        return getNext(pState.getBlock()).map((p_154896_) -> {
            return p_154896_.withPropertiesOf(pState);
        });
    }

    default float getChanceModifier() {
        return this.getAge() == GemDegradationLevel.UNAFFECTED ? 0.75F : 1.0F;
    }

    public static enum GemDegradationLevel {
        UNAFFECTED,
        EXPOSED,
        WEATHERED,
        DEGRADED;
    }
}

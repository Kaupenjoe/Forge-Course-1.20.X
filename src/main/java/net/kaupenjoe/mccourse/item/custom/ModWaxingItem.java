package net.kaupenjoe.mccourse.item.custom;

import com.google.common.base.Suppliers;
import com.google.common.collect.BiMap;
import com.google.common.collect.ImmutableBiMap;
import net.kaupenjoe.mccourse.block.ModBlocks;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;

import java.util.Optional;
import java.util.function.Supplier;

public class ModWaxingItem extends Item {
    public static final Supplier<BiMap<Block, Block>> WAXABLES = Suppliers.memoize(() -> {
        return ImmutableBiMap.<Block, Block>builder()
                .put(ModBlocks.RUBY_BLOCK.get(), ModBlocks.WAXED_RUBY_BLOCK.get())
                .put(ModBlocks.RUBY_BLOCK_1.get(), ModBlocks.WAXED_RUBY_BLOCK_1.get())
                .put(ModBlocks.RUBY_BLOCK_2.get(), ModBlocks.WAXED_RUBY_BLOCK_2.get())
                .put(ModBlocks.RUBY_BLOCK_3.get(), ModBlocks.WAXED_RUBY_BLOCK_3.get()).build();
    });
    public static final Supplier<BiMap<Block, Block>> WAX_OFF_BY_BLOCK = Suppliers.memoize(() -> {
        return WAXABLES.get().inverse();
    });

    public ModWaxingItem(Item.Properties pProperties) {
        super(pProperties);
    }

    /**
     * Called when this item is used when targeting a Block
     */
    public InteractionResult useOn(UseOnContext pContext) {
        Level level = pContext.getLevel();
        BlockPos blockpos = pContext.getClickedPos();
        BlockState blockstate = level.getBlockState(blockpos);
        return getWaxed(blockstate).map((p_238251_) -> {
            Player player = pContext.getPlayer();
            ItemStack itemstack = pContext.getItemInHand();
            if (player instanceof ServerPlayer) {
                CriteriaTriggers.ITEM_USED_ON_BLOCK.trigger((ServerPlayer)player, blockpos, itemstack);
            }

            itemstack.shrink(1);
            level.setBlock(blockpos, p_238251_, 11);
            level.gameEvent(GameEvent.BLOCK_CHANGE, blockpos, GameEvent.Context.of(player, p_238251_));
            level.levelEvent(player, 3003, blockpos, 0);
            return InteractionResult.sidedSuccess(level.isClientSide);
        }).orElse(InteractionResult.PASS);
    }

    public static Optional<BlockState> getWaxed(BlockState pState) {
        return Optional.ofNullable(WAXABLES.get().get(pState.getBlock())).map((p_150877_) -> {
            return p_150877_.withPropertiesOf(pState);
        });
    }
}

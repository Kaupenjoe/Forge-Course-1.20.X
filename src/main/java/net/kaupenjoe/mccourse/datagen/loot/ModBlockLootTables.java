package net.kaupenjoe.mccourse.datagen.loot;

import net.kaupenjoe.mccourse.block.ModBlocks;
import net.kaupenjoe.mccourse.block.custom.KohlrabiCropBlock;
import net.kaupenjoe.mccourse.item.ModItems;
import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraftforge.registries.RegistryObject;

import java.util.Set;

public class ModBlockLootTables extends BlockLootSubProvider {
    public ModBlockLootTables() {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags());
    }

    @Override
    protected void generate() {
        this.dropSelf(ModBlocks.ALEXANDRITE_BLOCK.get());
        this.dropSelf(ModBlocks.RAW_ALEXANDRITE_BLOCK.get());
        this.dropSelf(ModBlocks.SOUND_BLOCK.get());

        this.add(ModBlocks.ALEXANDRITE_ORE.get(),
                block -> createOreDrop(ModBlocks.ALEXANDRITE_ORE.get(), ModItems.RAW_ALEXANDRITE.get()));
        this.add(ModBlocks.DEEPSLATE_ALEXANDRITE_ORE.get(),
                block -> createOreDrop(ModBlocks.DEEPSLATE_ALEXANDRITE_ORE.get(), ModItems.RAW_ALEXANDRITE.get()));
        this.add(ModBlocks.END_STONE_ALEXANDRITE_ORE.get(),
                block -> createOreDrop(ModBlocks.END_STONE_ALEXANDRITE_ORE.get(), ModItems.RAW_ALEXANDRITE.get()));
        this.add(ModBlocks.NETHER_ALEXANDRITE_ORE.get(),
                block -> createOreDrop(ModBlocks.NETHER_ALEXANDRITE_ORE.get(), ModItems.RAW_ALEXANDRITE.get()));

        this.dropSelf(ModBlocks.ALEXANDRITE_STAIRS.get());
        this.add(ModBlocks.ALEXANDRITE_SLAB.get(),
                block -> createSlabItemTable(ModBlocks.ALEXANDRITE_SLAB.get()));
        this.dropSelf(ModBlocks.ALEXANDRITE_PRESSURE_PLATE.get());
        this.dropSelf(ModBlocks.ALEXANDRITE_BUTTON.get());
        this.dropSelf(ModBlocks.ALEXANDRITE_FENCE.get());
        this.dropSelf(ModBlocks.ALEXANDRITE_FENCE_GATE.get());
        this.dropSelf(ModBlocks.ALEXANDRITE_WALL.get());
        this.dropSelf(ModBlocks.ALEXANDRITE_TRAPDOOR.get());

        this.add(ModBlocks.ALEXANDRITE_DOOR.get(),
                block -> createDoorTable(ModBlocks.ALEXANDRITE_DOOR.get()));

        this.dropSelf(ModBlocks.ALEXANDRITE_LAMP.get());

        LootItemCondition.Builder lootitemcondition$builder1 = LootItemBlockStatePropertyCondition.hasBlockStateProperties(ModBlocks.KOHLRABI_CROP.get())
                .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(KohlrabiCropBlock.AGE, 6));
        this.add(ModBlocks.KOHLRABI_CROP.get(), this.createCropDrops(ModBlocks.KOHLRABI_CROP.get(),
                ModItems.KOHLRABI.get(), ModItems.KOHLRABI_SEEDS.get(), lootitemcondition$builder1));

        this.dropSelf(ModBlocks.SNAPDRAGON.get());
        this.add(ModBlocks.POTTED_SNAPDRAGON.get(),
                createPotFlowerItemTable(ModBlocks.POTTED_SNAPDRAGON.get()));

        this.dropSelf(ModBlocks.GEM_EMPOWERING_STATION.get());

        this.dropSelf(ModBlocks.WALNUT_LOG.get());
        this.dropSelf(ModBlocks.WALNUT_WOOD.get());
        this.dropSelf(ModBlocks.STRIPPED_WALNUT_LOG.get());
        this.dropSelf(ModBlocks.STRIPPED_WALNUT_WOOD.get());
        this.dropSelf(ModBlocks.WALNUT_PLANKS.get());
        this.dropSelf(ModBlocks.WALNUT_SAPLING.get());

        this.add(ModBlocks.WALNUT_LEAVES.get(), block ->
                createLeavesDrops(block, ModBlocks.WALNUT_SAPLING.get(), NORMAL_LEAVES_SAPLING_CHANCES));

        this.add(ModBlocks.WALNUT_SIGN.get(), block ->
                createSingleItemTable(ModItems.WALNUT_SIGN.get()));
        this.add(ModBlocks.WALNUT_WALL_SIGN.get(), block ->
                createSingleItemTable(ModItems.WALNUT_SIGN.get()));
        this.add(ModBlocks.WALNUT_HANGING_SIGN.get(), block ->
                createSingleItemTable(ModItems.WALNUT_HANGING_SIGN.get()));
        this.add(ModBlocks.WALNUT_WALL_HANGING_SIGN.get(), block ->
                createSingleItemTable(ModItems.WALNUT_HANGING_SIGN.get()));

        this.dropSelf(ModBlocks.COLORED_LEAVES.get());
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return ModBlocks.BLOCKS.getEntries().stream().map(RegistryObject::get)::iterator;
    }
}

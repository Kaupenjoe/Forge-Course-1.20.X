package net.kaupenjoe.mccourse.datagen;

import net.kaupenjoe.mccourse.MCCourseMod;
import net.kaupenjoe.mccourse.block.ModBlocks;
import net.kaupenjoe.mccourse.item.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, MCCourseMod.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        simpleItem(ModItems.ALEXANDRITE);
        simpleItem(ModItems.RAW_ALEXANDRITE);

        simpleItem(ModItems.KOHLRABI);
        simpleItem(ModItems.METAL_DETECTOR);
        simpleItem(ModItems.PEAT_BRICK);

        buttonItem(ModBlocks.ALEXANDRITE_BUTTON, ModBlocks.ALEXANDRITE_BLOCK);
    }

    public void buttonItem(RegistryObject<Block> block, RegistryObject<Block> baseBlock) {
        this.withExistingParent(ForgeRegistries.BLOCKS.getKey(block.get()).getPath(), mcLoc("block/button_inventory"))
                .texture("texture",  new ResourceLocation(MCCourseMod.MOD_ID, "block/" + ForgeRegistries.BLOCKS.getKey(baseBlock.get()).getPath()));
    }

    private ItemModelBuilder simpleItem(RegistryObject<Item> item) {
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(MCCourseMod.MOD_ID,"item/" + item.getId().getPath()));
    }
}

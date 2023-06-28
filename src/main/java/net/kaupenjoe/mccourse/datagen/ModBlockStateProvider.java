package net.kaupenjoe.mccourse.datagen;

import net.kaupenjoe.mccourse.MCCourseMod;
import net.kaupenjoe.mccourse.block.ModBlocks;
import net.kaupenjoe.mccourse.block.custom.AlexandriteLampBlock;
import net.kaupenjoe.mccourse.block.custom.KohlrabiCropBlock;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Function;

public class ModBlockStateProvider extends BlockStateProvider {
    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, MCCourseMod.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        blockWithItem(ModBlocks.ALEXANDRITE_BLOCK);
        blockWithItem(ModBlocks.RAW_ALEXANDRITE_BLOCK);

        blockWithItem(ModBlocks.ALEXANDRITE_ORE);
        blockWithItem(ModBlocks.DEEPSLATE_ALEXANDRITE_ORE);
        blockWithItem(ModBlocks.END_STONE_ALEXANDRITE_ORE);
        blockWithItem(ModBlocks.NETHER_ALEXANDRITE_ORE);

        blockWithItem(ModBlocks.SOUND_BLOCK);

        stairsBlock((StairBlock) ModBlocks.ALEXANDRITE_STAIRS.get(), blockTexture(ModBlocks.ALEXANDRITE_BLOCK.get()));
        slabBlock(((SlabBlock) ModBlocks.ALEXANDRITE_SLAB.get()), blockTexture(ModBlocks.ALEXANDRITE_BLOCK.get()), blockTexture(ModBlocks.ALEXANDRITE_BLOCK.get()));

        buttonBlock((ButtonBlock) ModBlocks.ALEXANDRITE_BUTTON.get(), blockTexture(ModBlocks.ALEXANDRITE_BLOCK.get()));
        pressurePlateBlock((PressurePlateBlock) ModBlocks.ALEXANDRITE_PRESSURE_PLATE.get(), blockTexture(ModBlocks.ALEXANDRITE_BLOCK.get()));

        fenceBlock((FenceBlock) ModBlocks.ALEXANDRITE_FENCE.get(), blockTexture(ModBlocks.ALEXANDRITE_BLOCK.get()));
        fenceGateBlock((FenceGateBlock) ModBlocks.ALEXANDRITE_FENCE_GATE.get(), blockTexture(ModBlocks.ALEXANDRITE_BLOCK.get()));
        wallBlock((WallBlock) ModBlocks.ALEXANDRITE_WALL.get(), blockTexture(ModBlocks.ALEXANDRITE_BLOCK.get()));

        doorBlockWithRenderType((DoorBlock)ModBlocks.ALEXANDRITE_DOOR.get(), modLoc("block/alexandrite_door_bottom"), modLoc("block/alexandrite_door_top"), "cutout");
        trapdoorBlockWithRenderType((TrapDoorBlock) ModBlocks.ALEXANDRITE_TRAPDOOR.get(), modLoc("block/alexandrite_trapdoor"), true, "cutout");

        blockItem(ModBlocks.ALEXANDRITE_STAIRS);
        blockItem(ModBlocks.ALEXANDRITE_SLAB);
        blockItem(ModBlocks.ALEXANDRITE_PRESSURE_PLATE);
        blockItem(ModBlocks.ALEXANDRITE_FENCE_GATE);
        blockItem(ModBlocks.ALEXANDRITE_TRAPDOOR, "_bottom");

        customLamp();

        makeCrop(((KohlrabiCropBlock) ModBlocks.KOHLRABI_CROP.get()), "kohlrabi_stage", "kohlrabi_stage");

        simpleBlock(ModBlocks.SNAPDRAGON.get(),
                models().cross(blockTexture(ModBlocks.SNAPDRAGON.get()).getPath(), blockTexture(ModBlocks.SNAPDRAGON.get())).renderType("cutout"));

        horizontalBlock(ModBlocks.GEM_EMPOWERING_STATION.get(),
                new ModelFile.UncheckedModelFile(modLoc("block/gem_empowering_station")));
        
        logBlock(((RotatedPillarBlock) ModBlocks.WALNUT_LOG.get()));
        axisBlock(((RotatedPillarBlock) ModBlocks.WALNUT_WOOD.get()), blockTexture(ModBlocks.WALNUT_LOG.get()), blockTexture(ModBlocks.WALNUT_LOG.get()));
        axisBlock((RotatedPillarBlock) ModBlocks.STRIPPED_WALNUT_LOG.get(), new ResourceLocation(MCCourseMod.MOD_ID, "block/stripped_walnut_log"),
                new ResourceLocation(MCCourseMod.MOD_ID, "block/stripped_walnut_log_top"));
        axisBlock((RotatedPillarBlock) ModBlocks.STRIPPED_WALNUT_WOOD.get(), new ResourceLocation(MCCourseMod.MOD_ID, "block/stripped_walnut_log"),
                new ResourceLocation(MCCourseMod.MOD_ID, "block/stripped_walnut_log"));

        blockItem(ModBlocks.WALNUT_LOG);
        blockItem(ModBlocks.WALNUT_WOOD);
        blockItem(ModBlocks.STRIPPED_WALNUT_LOG);
        blockItem(ModBlocks.STRIPPED_WALNUT_WOOD);

        blockWithItem(ModBlocks.WALNUT_PLANKS);

        leavesBlock(ModBlocks.WALNUT_LEAVES);
        saplingBlock(ModBlocks.WALNUT_SAPLING);

    }

    private void leavesBlock(RegistryObject<Block> blockRegistryObject) {
        simpleBlockWithItem(blockRegistryObject.get(),
                models().cubeAll(ForgeRegistries.BLOCKS.getKey(blockRegistryObject.get()).getPath(), blockTexture(blockRegistryObject.get())).renderType("cutout"));
    }

    private void saplingBlock(RegistryObject<Block> blockRegistryObject) {
        simpleBlock(blockRegistryObject.get(),
                models().cross(ForgeRegistries.BLOCKS.getKey(blockRegistryObject.get()).getPath(), blockTexture(blockRegistryObject.get())).renderType("cutout"));
    }

    public void makeCrop(CropBlock block, String modelName, String textureName) {
        Function<BlockState, ConfiguredModel[]> function = state -> states(state, block, modelName, textureName);

        getVariantBuilder(block).forAllStates(function);
    }

    private ConfiguredModel[] states(BlockState state, CropBlock block, String modelName, String textureName) {
        ConfiguredModel[] models = new ConfiguredModel[1];
        models[0] = new ConfiguredModel(models().crop(modelName + state.getValue(((KohlrabiCropBlock) block).getAgeProperty()),
                new ResourceLocation(MCCourseMod.MOD_ID, "block/" + textureName + state.getValue(((KohlrabiCropBlock) block).getAgeProperty()))).renderType("cutout"));

        return models;
    }

    private void customLamp() {
        getVariantBuilder(ModBlocks.ALEXANDRITE_LAMP.get()).forAllStates(state -> {
            if(state.getValue(AlexandriteLampBlock.CLICKED)) {
                return new ConfiguredModel[]{new ConfiguredModel(models().cubeAll("alexandrite_lamp_on",
                        new ResourceLocation(MCCourseMod.MOD_ID, "block/" + "alexandrite_lamp_on")))};
            } else {
                return new ConfiguredModel[]{new ConfiguredModel(models().cubeAll("alexandrite_lamp_off",
                        new ResourceLocation(MCCourseMod.MOD_ID, "block/" +"alexandrite_lamp_off")))};
            }
        });
        simpleBlockItem(ModBlocks.ALEXANDRITE_LAMP.get(), models().cubeAll("alexandrite_lamp_on",
                new ResourceLocation(MCCourseMod.MOD_ID, "block/" +"alexandrite_lamp_on")));
    }

    private void blockItem(RegistryObject<Block> blockRegistryObject, String appendix) {
        simpleBlockItem(blockRegistryObject.get(), new ModelFile.UncheckedModelFile("mccourse:block/" + ForgeRegistries.BLOCKS.getKey(blockRegistryObject.get()).getPath() + appendix));
    }

    private void blockItem(RegistryObject<Block> blockRegistryObject) {
        simpleBlockItem(blockRegistryObject.get(), new ModelFile.UncheckedModelFile("mccourse:block/" + ForgeRegistries.BLOCKS.getKey(blockRegistryObject.get()).getPath()));
    }

    private void blockWithItem(RegistryObject<Block> blockRegistryObject) {
        simpleBlockWithItem(blockRegistryObject.get(), cubeAll(blockRegistryObject.get()));
    }
}

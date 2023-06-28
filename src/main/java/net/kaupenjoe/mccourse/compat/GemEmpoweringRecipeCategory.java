package net.kaupenjoe.mccourse.compat;

import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.kaupenjoe.mccourse.MCCourseMod;
import net.kaupenjoe.mccourse.block.ModBlocks;
import net.kaupenjoe.mccourse.recipe.GemEmpoweringRecipe;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;

public class GemEmpoweringRecipeCategory implements IRecipeCategory<GemEmpoweringRecipe> {
    public static final ResourceLocation UID = new ResourceLocation(MCCourseMod.MOD_ID, "gem_empowering");
    public static final ResourceLocation TEXTURE = new ResourceLocation(MCCourseMod.MOD_ID,
            "textures/gui/gem_empowering_station_gui.png");

    public static final RecipeType<GemEmpoweringRecipe> GEM_EMPOWERING_TYPE =
            new RecipeType<>(UID, GemEmpoweringRecipe.class);

    private final IDrawable background;
    private final IDrawable icon;

    public GemEmpoweringRecipeCategory(IGuiHelper helper) {
        this.background = helper.createDrawable(TEXTURE, 0, 0, 176, 85);
        this.icon = helper.createDrawableIngredient(VanillaTypes.ITEM_STACK, new ItemStack(ModBlocks.GEM_EMPOWERING_STATION.get()));
    }


    @Override
    public RecipeType<GemEmpoweringRecipe> getRecipeType() {
        return GEM_EMPOWERING_TYPE;
    }

    @Override
    public Component getTitle() {
        return Component.literal("Gem Infusing Station");
    }

    @Override
    public IDrawable getBackground() {
        return this.background;
    }

    @Override
    public IDrawable getIcon() {
        return this.icon;
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, GemEmpoweringRecipe recipe, IFocusGroup focuses) {
        builder.addSlot(RecipeIngredientRole.INPUT, 80, 11).addIngredients(recipe.getIngredients().get(0));

        builder.addSlot(RecipeIngredientRole.OUTPUT, 80, 59).addItemStack(recipe.getResultItem(null));
    }
}

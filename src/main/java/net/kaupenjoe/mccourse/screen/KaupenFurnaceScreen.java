package net.kaupenjoe.mccourse.screen;

import net.kaupenjoe.mccourse.MCCourseMod;
import net.kaupenjoe.mccourse.recipe.KaupenFurnaceRecipeBookComponent;
import net.minecraft.client.gui.screens.inventory.AbstractFurnaceScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class KaupenFurnaceScreen extends AbstractFurnaceScreen<KaupenFurnaceMenu> {
    private static final ResourceLocation TEXTURE = new ResourceLocation(MCCourseMod.MOD_ID, "textures/gui/kaupen_furnace.png");

    public KaupenFurnaceScreen(KaupenFurnaceMenu pMenu, Inventory pPlayerInventory, Component pTitle) {
        super(pMenu, new KaupenFurnaceRecipeBookComponent(), pPlayerInventory, pTitle, TEXTURE);
    }
}

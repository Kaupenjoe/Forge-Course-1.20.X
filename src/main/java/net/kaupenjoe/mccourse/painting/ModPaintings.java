package net.kaupenjoe.mccourse.painting;

import net.kaupenjoe.mccourse.MCCourseMod;
import net.minecraft.world.entity.decoration.PaintingVariant;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModPaintings {
    public static final DeferredRegister<PaintingVariant> PAINTING_VARIANTS =
            DeferredRegister.create(ForgeRegistries.PAINTING_VARIANTS, MCCourseMod.MOD_ID);

    public static final RegistryObject<PaintingVariant> SAW_THEM = PAINTING_VARIANTS.register("saw_them",
            () -> new PaintingVariant(16, 16));
    public static final RegistryObject<PaintingVariant> SHRIMP = PAINTING_VARIANTS.register("shrimp",
            () -> new PaintingVariant(32, 16));
    public static final RegistryObject<PaintingVariant> WORLD = PAINTING_VARIANTS.register("world",
            () -> new PaintingVariant(32, 32));

    public static void register(IEventBus eventBus) {
        PAINTING_VARIANTS.register(eventBus);
    }
}

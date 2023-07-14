package net.kaupenjoe.mccourse.worldgen.tree;

import net.kaupenjoe.mccourse.MCCourseMod;
import net.kaupenjoe.mccourse.worldgen.tree.custom.WalnutTrunkPlacer;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModTrunkPlacerTypes {
    public static final DeferredRegister<TrunkPlacerType<?>> TRUNK_PLACERS =
            DeferredRegister.create(Registries.TRUNK_PLACER_TYPE, MCCourseMod.MOD_ID);

    public static final RegistryObject<TrunkPlacerType<WalnutTrunkPlacer>> WALNUT_TRUNK_PLACER =
            TRUNK_PLACERS.register("walnut_trunk_placer", () -> new TrunkPlacerType<>(WalnutTrunkPlacer.CODEC));

    public static void register(IEventBus eventBus) {
        TRUNK_PLACERS.register(eventBus);
    }
}

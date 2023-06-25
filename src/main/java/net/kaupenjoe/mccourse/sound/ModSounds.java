package net.kaupenjoe.mccourse.sound;

import net.kaupenjoe.mccourse.MCCourseMod;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.common.util.ForgeSoundType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModSounds {
    public static final DeferredRegister<SoundEvent> SOUND_EVENTS =
            DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, MCCourseMod.MOD_ID);

    public static final RegistryObject<SoundEvent> METAL_DETECTOR_FOUND_ORE = registerSoundEvents("metal_detector_found_ore");

    public static final RegistryObject<SoundEvent> ALEXANDRITE_LAMP_BREAK = registerSoundEvents("alexandrite_lamp_break");
    public static final RegistryObject<SoundEvent> ALEXANDRITE_LAMP_STEP = registerSoundEvents("alexandrite_lamp_step");
    public static final RegistryObject<SoundEvent> ALEXANDRITE_LAMP_FALL = registerSoundEvents("alexandrite_lamp_fall");
    public static final RegistryObject<SoundEvent> ALEXANDRITE_LAMP_PLACE = registerSoundEvents("alexandrite_lamp_place");
    public static final RegistryObject<SoundEvent> ALEXANDRITE_LAMP_HIT = registerSoundEvents("alexandrite_lamp_hit");

    public static final RegistryObject<SoundEvent> BAR_BRAWL = registerSoundEvents("bar_brawl");


    public static final ForgeSoundType ALEXANDRITE_LAMP_SOUNDS = new ForgeSoundType(1f, 1f,
            ModSounds.ALEXANDRITE_LAMP_BREAK, ModSounds.ALEXANDRITE_LAMP_STEP, ModSounds.ALEXANDRITE_LAMP_PLACE,
            ModSounds.ALEXANDRITE_LAMP_HIT, ModSounds.ALEXANDRITE_LAMP_FALL);


    private static RegistryObject<SoundEvent> registerSoundEvents(String name) {
        ResourceLocation id = new ResourceLocation(MCCourseMod.MOD_ID, name);
        return SOUND_EVENTS.register(name, () -> SoundEvent.createVariableRangeEvent(id));
    }

    public static void register(IEventBus eventBus) {
        SOUND_EVENTS.register(eventBus);
    }
}

package net.unicraft.gismi.levelmod.eventhandler;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.unicraft.gismi.levelmod.Levelmod;
import net.unicraft.gismi.levelmod.capabilities.generic.LevelableSkill;
import net.unicraft.gismi.levelmod.capabilities.generic.LevelableSkillProvider;


@Mod.EventBusSubscriber(modid = Levelmod.MODID)
public class LevelableSkillsBasicHandler {

    @SubscribeEvent
    public static void onAttachCapabilitiesPlayer(AttachCapabilitiesEvent<Entity> event) {
        if(event.getObject() instanceof Player) {
            if(!event.getObject().getCapability(LevelableSkillProvider.PLAYER_LEVELABLE_SKILLS).isPresent()) {
                event.addCapability(new ResourceLocation(Levelmod.MODID,"properties"),new LevelableSkillProvider());
            }
        }
    }

    @SubscribeEvent
    public static void onPlayerCloned(PlayerEvent.Clone event) {
        if(event.isWasDeath()) {
            event.getOriginal().getCapability(LevelableSkillProvider.PLAYER_LEVELABLE_SKILLS).ifPresent(oldSkills -> {
                event.getOriginal().getCapability(LevelableSkillProvider.PLAYER_LEVELABLE_SKILLS).ifPresent(newSkills -> {
                    newSkills.putAll(oldSkills);
                });
            });
        }
    }

    @SubscribeEvent
    public static void onRegisterCapabilities(RegisterCapabilitiesEvent event) {
        event.register(LevelableSkill.class);
    }
}

package net.unicraft.gismi.levelmod.eventhandler;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.Tags;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.unicraft.gismi.levelmod.Levelmod;
import net.unicraft.gismi.levelmod.capabilities.generic.LevelableSkill;
import net.unicraft.gismi.levelmod.capabilities.generic.LevelableSkillProvider;

@Mod.EventBusSubscriber(modid = Levelmod.MODID)
public class AttackEventHandler {

    @SubscribeEvent
    public static void onEntityAttack(LivingAttackEvent event) {
        if(event.getSource().getDirectEntity() != null && event.getSource().getDirectEntity() instanceof Player player) {
            player.getCapability(LevelableSkillProvider.PLAYER_LEVELABLE_SKILLS).ifPresent(skills -> {
                if(player.getMainHandItem().is(Tags.Items.TOOLS_SWORDS)) {
                    handleSwordAttackEvent(event,player,skills.get(LevelableSkill.SWORD_SKILL_ID));
                }
            });
            String test = "test";
        }
    }

    private static void handleSwordAttackEvent(LivingAttackEvent event,Player player,LevelableSkill swordSkill) {
        swordSkill.earnXP(event);
        player.sendSystemMessage(Component.literal("Added Swordfighting XP"));
        player.sendSystemMessage(Component.literal("New Swordfighting XP: " + swordSkill.getXP() + "/" + swordSkill.getXpNeeded()));
        player.sendSystemMessage(Component.literal("Level: " + swordSkill.getLevel()));
    }
}

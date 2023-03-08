package net.unicraft.gismi.levelmod.eventhandler;

import net.minecraft.network.chat.Component;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.unicraft.gismi.levelmod.Levelmod;
import net.unicraft.gismi.levelmod.capabilities.MiningSkill;
import net.unicraft.gismi.levelmod.capabilities.generic.LevelableSkillProvider;
import net.unicraft.gismi.levelmod.config.LevelmodCommonConfig;

@Mod.EventBusSubscriber(modid = Levelmod.MODID)
public class MiningEventHandler {

    private static TagKey<Block> mineablePickAxe = BlockTags.MINEABLE_WITH_PICKAXE;
    private static TagKey<Block> stoneType = BlockTags.BASE_STONE_OVERWORLD;

    @SubscribeEvent
    public static void onBlockBreak(BlockEvent.BreakEvent event) {

        if(MiningSkill.isXPEligible(event)) {
            event.getPlayer().getCapability(LevelableSkillProvider.PLAYER_LEVELABLE_SKILLS).ifPresent(skills -> {
                skills.get("mining").earnXP(event);
                if(LevelmodCommonConfig.DEBUG.get()) {
                    event.getPlayer().sendSystemMessage(Component.literal("Added Mining XP"));
                    event.getPlayer().sendSystemMessage(Component.literal("New Mining XP: " + skills.get("mining").getXP() + "/" + skills.get("mining").getXpNeeded()));
                    event.getPlayer().sendSystemMessage(Component.literal("Level: " + skills.get("mining").getLevel()));
                }
            });
        }
    }

}

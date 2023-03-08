package net.unicraft.gismi.levelmod.capabilities;

import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.Tags;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.eventbus.api.Event;
import net.unicraft.gismi.levelmod.capabilities.generic.LevelableSkill;
import net.unicraft.gismi.levelmod.config.LevelmodCommonConfig;

public class MiningSkill extends LevelableSkill {

    public MiningSkill() {
        super(0,0,"mining","Mining","How good you are at breaking stone and ore");
    }

    public static boolean isXPEligible(BlockEvent.BreakEvent event) {
        return event.getPlayer().getItemInHand(event.getPlayer().getUsedItemHand()).is(Tags.Items.TOOLS_PICKAXES) && (event.getState().is(BlockTags.BASE_STONE_OVERWORLD) || event.getState().is(BlockTags.BASE_STONE_NETHER) || event.getState().is(Tags.Blocks.ORES));
    }

    @Override
    public <T extends Event> void earnXP(T event) {
        if(((BlockEvent.BreakEvent)event).getState().is(Tags.Blocks.ORES)) {
            addXP(LevelmodCommonConfig.MINING_SKILL_BASE_XP.get() * LevelmodCommonConfig.MINING_SKILL_ORE_MULTIPLIER.get());
        }
        else {
            addXP(LevelmodCommonConfig.MINING_SKILL_BASE_XP.get());
        }
    }
}

package net.unicraft.gismi.levelmod.capabilities;

import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.Tags;
import net.minecraftforge.event.level.BlockEvent;
import net.unicraft.gismi.levelmod.capabilities.generic.LevelableSkill;

public class MiningSkill extends LevelableSkill {

    public MiningSkill() {
        super(0,0,"mining","Mining","How good you are at breaking stone and ore");
    }

    public static boolean isXPEligible(BlockEvent.BreakEvent event) {
        return event.getPlayer().getItemInHand(event.getPlayer().getUsedItemHand()).is(Tags.Items.TOOLS_PICKAXES) && (event.getState().is(BlockTags.BASE_STONE_OVERWORLD) || event.getState().is(BlockTags.BASE_STONE_NETHER) || event.getState().is(Tags.Blocks.ORES));
    }
}

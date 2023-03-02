package net.unicraft.gismi.levelmod.eventhandler;

import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.unicraft.gismi.levelmod.Levelmod;
import net.unicraft.gismi.levelmod.capabilities.generic.LevelableSkillProvider;

import java.util.List;

@Mod.EventBusSubscriber(modid = Levelmod.MODID)
public class MiningEventHandler {

    private static TagKey<Block> mineablePickAxe = BlockTags.MINEABLE_WITH_PICKAXE;
    private static TagKey<Block> stoneType = BlockTags.BASE_STONE_OVERWORLD;

    @SubscribeEvent
    public static void onBlockBreak(BlockEvent.BreakEvent event) {

        if(event.getState().is(mineablePickAxe) && event.getState().is(stoneType)) {
            event.getPlayer().getCapability(LevelableSkillProvider.PLAYER_LEVELABLE_SKILLS).ifPresent(skills -> {
                skills.get("mining").addXP(10f);
                event.getPlayer().sendSystemMessage(Component.literal("Added Mining XP"));
                event.getPlayer().sendSystemMessage(Component.literal("New Mining XP: " + skills.get("mining").getXP() + "/" + skills.get("mining").getXpNeeded()));
                event.getPlayer().sendSystemMessage(Component.literal("Level: " + skills.get("mining").getLevel()));
            });
        }
        LootContext.Builder builder = new LootContext.Builder(event.getLevel().getServer().getLevel(ServerLevel.OVERWORLD));
        builder.withParameter(LootContextParams.ORIGIN,new Vec3(event.getPos().getX(),event.getPos().getY(),event.getPos().getZ()));
        builder.withParameter(LootContextParams.TOOL,event.getPlayer().getItemInHand(event.getPlayer().getUsedItemHand()));

        List<ItemStack> itemStacks = event.getState().getDrops(builder);
        String test = "";
    }
}

package net.unicraft.gismi.levelmod.provider;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.level.BlockEvent;

import java.util.List;

public class BlockBreakResultProvider {
    public static List<ItemStack> getItemsForBlockBreak(BlockEvent.BreakEvent event) {
        LootContext.Builder builder = new LootContext.Builder(event.getLevel().getServer().getLevel(ServerLevel.OVERWORLD));
        builder.withParameter(LootContextParams.ORIGIN,new Vec3(event.getPos().getX(),event.getPos().getY(),event.getPos().getZ()));
        builder.withParameter(LootContextParams.TOOL,event.getPlayer().getItemInHand(event.getPlayer().getUsedItemHand()));
        return event.getState().getDrops(builder);
    }
}

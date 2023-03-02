package net.unicraft.gismi.levelmod.capabilities;

import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.player.PlayerEvent;

public class LevelUpEvent extends PlayerEvent {

    public LevelUpEvent(Player player) {
        super(player);
    }
}

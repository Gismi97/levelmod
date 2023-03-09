package net.unicraft.gismi.levelmod.eventhandler;

import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.server.command.ConfigCommand;
import net.unicraft.gismi.levelmod.Levelmod;
import net.unicraft.gismi.levelmod.commands.SetLevelCommand;

@Mod.EventBusSubscriber(modid = Levelmod.MODID)
public class CommandsEventHandler {

    @SubscribeEvent
    public static void onCommandRegister(RegisterCommandsEvent event) {
        new SetLevelCommand(event.getDispatcher());


        ConfigCommand.register(event.getDispatcher());
    }
}

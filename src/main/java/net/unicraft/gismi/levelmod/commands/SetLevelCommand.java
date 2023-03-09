package net.unicraft.gismi.levelmod.commands;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.unicraft.gismi.levelmod.capabilities.generic.LevelableSkill;
import net.unicraft.gismi.levelmod.capabilities.generic.LevelableSkillProvider;

public class SetLevelCommand {
    public SetLevelCommand(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(Commands.literal("setLevel").requires((player)-> player.hasPermission(2)).
                then(Commands.argument("target", EntityArgument.player()).
                    then(Commands.argument("skill", StringArgumentType.string()).
                        then(Commands.argument("value", IntegerArgumentType.integer(0,1000)).executes(this::setLevel)
                                )
                        )
                )
        );
    }

    private int setLevel(CommandContext<CommandSourceStack> command) throws CommandSyntaxException {

        ServerPlayer target = EntityArgument.getPlayer(command,"target");
        String skillName = StringArgumentType.getString(command,"skill");
        int value = IntegerArgumentType.getInteger(command,"value");


        target.getCapability(LevelableSkillProvider.PLAYER_LEVELABLE_SKILLS).ifPresent(skills -> {
            LevelableSkill skill = skills.get(skillName);
            if(skill != null) {
                skill.setLevel(value);
                command.getSource().sendSuccess(Component.literal("Successfully set " + skillName + " level of " + target.getDisplayName().getString() + " to " + value),true);
                target.sendSystemMessage(Component.literal("Your " + skillName + " level has been set to " + value));
            }
            else {
                command.getSource().sendFailure(Component.literal("Invalid Skill"));
            }
        });

        return Command.SINGLE_SUCCESS;
    }
}

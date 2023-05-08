package net.agrotom.sonomod.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.exceptions.CommandSyntaxException;

import net.agrotom.sonomod.capabilities.SkillsCapability;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.command.arguments.EntityArgument;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.text.StringTextComponent;

public class GetSkillValueCommand {

    public static void register(CommandDispatcher<CommandSource> dispatcher) {
	dispatcher.register(Commands.literal("sonomod").then(Commands.literal("skills")
		.then(Commands.argument("target", EntityArgument.player()).then(Commands.literal("get")
			.then(Commands.argument("name", StringArgumentType.word()).executes(command -> {
			    return getXP(command.getSource(), EntityArgument.getPlayer(command, "target"),
				    StringArgumentType.getString(command, "name"));
			}))))));
    }
    
    private static int getXP(CommandSource source, ServerPlayerEntity player, String name) throws CommandSyntaxException {
	final ServerPlayerEntity sender = source.getPlayerOrException();
	
	player.getCapability(SkillsCapability.SKILLS_HANDLER).ifPresent(cap -> {
	    cap.getSkillByID(name).ifPresent(skill -> {
		sender.sendMessage(new StringTextComponent(player.getName().getContents() + "'s " + name + " skill value is: " + skill.getXP()), 
			sender.getUUID()); // TODO: Translatable command output.
	    });
	});
	
	return 0;
    }
    
}

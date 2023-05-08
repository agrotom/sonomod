package net.agrotom.sonomod.commands;

import java.util.Optional;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.FloatArgumentType;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.exceptions.CommandSyntaxException;

import net.agrotom.sonomod.capabilities.SkillsCapability;
import net.agrotom.sonomod.skills.BasicSkill;
import net.agrotom.sonomod.skills.ISkill;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.command.arguments.EntityArgument;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.text.StringTextComponent;

public class SetSkillValueCommand {

	public SetSkillValueCommand(CommandDispatcher<CommandSource> dispatcher) {
		dispatcher.register(Commands.literal("sonomod").then(Commands.literal("skills")
			.then(Commands.argument("target", EntityArgument.player())
				.then(Commands.literal("set").then(Commands.argument("name", StringArgumentType.word())
						.then(Commands.argument("value", FloatArgumentType.floatArg(BasicSkill.MIN_XP, BasicSkill.MAX_XP))
								.executes(command -> {
							return setSkillValue(command.getSource(),
								EntityArgument.getPlayer(command, "target"),
								StringArgumentType.getString(command, "name"),
									FloatArgumentType.getFloat(command, "value"));
						})))))));
	}
	
	private int setSkillValue(CommandSource source, ServerPlayerEntity player, String arg, float value) throws CommandSyntaxException {
		ServerPlayerEntity sender = source.getPlayerOrException();
		
		player.getCapability(SkillsCapability.SKILLS_HANDLER).ifPresent(cap -> {
			final Optional<ISkill> skillOptional = cap.getSkillByID(arg);
			
			skillOptional.ifPresent(skill -> {
			    skill.setXP(value);
				
			    sender.sendMessage(new StringTextComponent(player.getName().getContents() + "'s " + arg + " skill value changed to: " + value),
				sender.getUUID());
			});
		});
		
		return 0;
	}

}

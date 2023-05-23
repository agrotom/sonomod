package net.agrotom.sonomod.command.impl;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.exceptions.CommandSyntaxException;

import net.agrotom.sonomod.capabilities.SkillsCapability;
import net.agrotom.sonomod.command.arguments.SkillArgument;
import net.agrotom.sonomod.skills.ISkillInstance;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.command.arguments.EntityArgument;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.text.StringTextComponent;

public class GetSkillValueCommand {

	public static void register(CommandDispatcher<CommandSource> dispatcher) {
		dispatcher
				.register(
						Commands.literal(
								"sonomod").then(
										Commands.literal("skills").requires((source) -> source.hasPermission(2))
												.then(Commands
														.argument("target",
																EntityArgument.player())
														.then(Commands.literal("get")
																.then(Commands
																		.argument("name", SkillArgument.skill())
																		.executes(command -> {
																			return getXP(command.getSource(),
																					EntityArgument.getPlayer(command,
																							"target"),
																					SkillArgument.getSkillInstance(
																							command, "name"));
																		}))))));
	}

	private static int getXP(CommandSource source, ServerPlayerEntity player, ISkillInstance instance)
			throws CommandSyntaxException {
		final ServerPlayerEntity sender = source.getPlayerOrException();

		player.getCapability(SkillsCapability.SKILLS_HANDLER).ifPresent(cap -> {
			cap.getSkill(instance).ifPresent(skill -> {
				sender.sendMessage(
						new StringTextComponent(
								player.getName().getContents() + "'s " + instance.getName() + " skill value is: " + skill.getXP()),
						sender.getUUID()); // TODO: Translatable command output.
			});
		});

		return 0;
	}

}

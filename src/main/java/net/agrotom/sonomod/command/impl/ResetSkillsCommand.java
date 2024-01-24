package net.agrotom.sonomod.command.impl;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.exceptions.CommandSyntaxException;

import net.agrotom.sonomod.capabilities.SkillsCapability;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.command.arguments.EntityArgument;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.text.StringTextComponent;

public class ResetSkillsCommand {
	
	public static void register(CommandDispatcher<CommandSource> dispatcher) {
		dispatcher
				.register(
						Commands.literal(
								"sonomod").then(
										Commands.literal("skills").requires((source) -> source.hasPermission(2))
												.then(Commands
														.argument("target",
																EntityArgument.player())
														.then(Commands.literal("reset")
																		.executes(command -> {
																			return reset(command.getSource(),
																					EntityArgument.getPlayer(command,
																							"target"));
																		})))));
	}

	private static int reset(CommandSource source, ServerPlayerEntity player)
			throws CommandSyntaxException {
		final ServerPlayerEntity sender = source.getPlayerOrException();

		player.getCapability(SkillsCapability.SKILLS_HANDLER).ifPresent(cap -> {
			cap.forEach((key, skill) -> {
				skill.setXP(0.0f);
				sender.sendMessage(
						new StringTextComponent(
								player.getName().getContents() + "'s skills was resetted!"),
						sender.getUUID());
			});
		});

		return 0;
	}
	
}

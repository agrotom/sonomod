package net.agrotom.sonomod.command.impl;

import java.util.Optional;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.FloatArgumentType;
import com.mojang.brigadier.exceptions.CommandSyntaxException;

import net.agrotom.sonomod.capabilities.SkillsCapability;
import net.agrotom.sonomod.command.arguments.SkillArgument;
import net.agrotom.sonomod.skills.ISkillHandler;
import net.agrotom.sonomod.skills.ISkillInstance;
import net.agrotom.sonomod.skills.impl.BasicSkillHandler;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.command.arguments.EntityArgument;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.text.StringTextComponent;

public class SetSkillValueCommand {

	public SetSkillValueCommand(CommandDispatcher<CommandSource> dispatcher) {
		dispatcher.register(Commands.literal("sonomod")
				.then(Commands.literal("skills").requires((source) -> source.hasPermission(2))
						.then(Commands.argument("target", EntityArgument.player())
						.then(Commands.literal("set").then(Commands.argument("name", SkillArgument.skill())
								.then(Commands.argument("value",
										FloatArgumentType.floatArg(BasicSkillHandler.MIN_XP, BasicSkillHandler.MAX_XP))
										.executes(command -> {
											return setSkillValue(command.getSource(),
													EntityArgument.getPlayer(command, "target"),
													SkillArgument.getSkillInstance(command, "name"),
													FloatArgumentType.getFloat(command, "value"));
										})))))));
	}

	private int setSkillValue(CommandSource source, ServerPlayerEntity player, ISkillInstance instance, float value)
			throws CommandSyntaxException {
		ServerPlayerEntity sender = source.getPlayerOrException();

		player.getCapability(SkillsCapability.SKILLS_HANDLER).ifPresent(cap -> {
			final Optional<ISkillHandler> skillOptional = cap.getSkill(instance);

			skillOptional.ifPresent(skill -> {
				skill.setXP(value);

				sender.sendMessage(
						new StringTextComponent(
								player.getName().getContents() + "'s " + instance.getName() + " skill value changed to: " + value),
						sender.getUUID());
			});
		});

		return 0;
	}

}

package net.agrotom.sonomod.command.arguments;

import java.util.concurrent.CompletableFuture;

import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.DynamicCommandExceptionType;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;

import net.agrotom.sonomod.skills.ISkillInstance;
import net.agrotom.sonomod.skills.ModSkills;
import net.minecraft.command.CommandSource;
import net.minecraft.command.ISuggestionProvider;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TranslationTextComponent;

public class SkillArgument implements ArgumentType<ISkillInstance> {

	public static final DynamicCommandExceptionType ERROR_UNKNOWN_SKILL = new DynamicCommandExceptionType(
			(p_208663_0_) -> {
				return new TranslationTextComponent("skills.skillNotFound", p_208663_0_);
			});

	public static SkillArgument skill() {
		return new SkillArgument();
	}

	public static ISkillInstance getSkillInstance(CommandContext<CommandSource> p_197125_0_, String p_197125_1_)
			throws CommandSyntaxException {
		return p_197125_0_.getArgument(p_197125_1_, ISkillInstance.class);
	}

	@Override
	public ISkillInstance parse(StringReader reader) throws CommandSyntaxException {
		ResourceLocation resourcelocation = ResourceLocation.read(reader);

		final ISkillInstance skillInstance = ModSkills.getInstancesUnmodifiable().get(resourcelocation);

		if (skillInstance == null) {
			ERROR_UNKNOWN_SKILL.create(resourcelocation);
		}

		return skillInstance;
	}

	@Override
	public <S> CompletableFuture<Suggestions> listSuggestions(CommandContext<S> context, SuggestionsBuilder builder) {
		return ISuggestionProvider.suggestResource(ModSkills.getInstancesUnmodifiable().keySet(), builder);
	}

}

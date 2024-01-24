package net.agrotom.sonomod.skills.impl;

import javax.annotation.Nonnull;

import net.agrotom.sonomod.skills.ISkillInstance;

public class BasicSkillInstance implements ISkillInstance {

	private final String name;
	private final String unlocalizedName;

	public BasicSkillInstance(@Nonnull String name, @Nonnull String modid) {
		this.name = name;
		this.unlocalizedName = modid + ":skills." + name;
	}
	
	public static ISkillInstance create(@Nonnull String name, @Nonnull String modid) {
		return new BasicSkillInstance(name, modid);
	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public String getUnlocalizedName() {
		return this.unlocalizedName;
	}

	@Override
	public boolean equalsByID(ISkillInstance skill) {
		return this.unlocalizedName == skill.getUnlocalizedName();
	}

}

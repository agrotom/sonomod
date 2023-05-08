package net.agrotom.sonomod.util;

import javax.annotation.Nonnull;

import net.agrotom.sonomod.skills.ISkill;

public class SkillWrapper implements ICopyableWrapper<ISkill> {

    private final ISkill toWrap;
    
    private SkillWrapper(ISkill skill) {
	toWrap = skill;
    }
    
    public static ICopyableWrapper<ISkill> create(@Nonnull ISkill skill) {
	return new SkillWrapper(skill);
    }
    
    @Override
    public ISkill copyObject() {
	return toWrap.copy();
    }

}

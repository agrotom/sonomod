package net.agrotom.sonomod.skills;

import net.agrotom.sonomod.util.ICopyable;

public interface IImmutableSkillHandler extends ICopyable<ISkillHandler> {
	float getXP();
	ISkillInstance getInstance();
}

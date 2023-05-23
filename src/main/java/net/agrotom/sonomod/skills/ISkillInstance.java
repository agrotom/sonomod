package net.agrotom.sonomod.skills;

import javax.annotation.Nonnull;

public interface ISkillInstance {
	
	String getName();
	
	String getUnlocalizedName();
	
	boolean equalsByID(@Nonnull ISkillInstance skill);
}

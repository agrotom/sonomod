package net.agrotom.sonomod.network.packets;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.agrotom.sonomod.skills.ISkillsCompound;
import net.minecraft.network.PacketBuffer;

public class SkillsGUIAnswerPacket {

	private ISkillsCompound skillsCompound = null;
	private boolean valid = false;
	
	public SkillsGUIAnswerPacket(@Nonnull ISkillsCompound skillsCompound) {
		this.skillsCompound = skillsCompound;
		valid = true;
	}
	
	public SkillsGUIAnswerPacket() {}
	
	@Nullable
	public ISkillsCompound getSkillsCompound() {
		return skillsCompound;
	}
	
	public boolean isValid() {
		return valid;
	}
	
	public void encode(@Nonnull PacketBuffer buffer) {
		if (valid) {
			buffer.writeNbt(skillsCompound.serializeNBT());
		}
		else {
			LOGGER.error("Can't write a \"" + SkillsGUIAnswerPacket.class.getName() + "\" packet to buffer, because it's not valid!");
		}
	}
	
	public static SkillsGUIAnswerPacket decode(@Nonnull PacketBuffer buffer) {
		final SkillsGUIAnswerPacket packet = new SkillsGUIAnswerPacket();
		
		packet.skillsCompound.deserializeNBT(buffer.readNbt());
		
		return packet; 
	}
	
	private static final Logger LOGGER = LogManager.getLogger();
	
}

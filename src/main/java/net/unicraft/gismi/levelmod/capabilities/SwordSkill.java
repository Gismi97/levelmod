package net.unicraft.gismi.levelmod.capabilities;

import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.eventbus.api.Event;
import net.unicraft.gismi.levelmod.capabilities.generic.LevelableSkill;
import net.unicraft.gismi.levelmod.config.LevelmodCommonConfig;

public class SwordSkill extends LevelableSkill {

    public SwordSkill() {
        super(0,0,SWORD_SKILL_ID,"Swordfighting","The ability to fight using swords");
    }

    @Override
    public <T extends Event> void earnXP(T event) {
        if(!((LivingAttackEvent)event).getEntity().getType().getCategory().isFriendly() || ((LivingAttackEvent)event).getEntity().getType().getCategory().equals(MobCategory.MONSTER)) {
            addXP(LevelmodCommonConfig.SWORD_SKILL_BASE_XP.get() * LevelmodCommonConfig.SWORD_SKILL_HOSTILE_MULTIPLIER.get());
        }
        else {
            addXP(LevelmodCommonConfig.SWORD_SKILL_BASE_XP.get());
        }
    }
}

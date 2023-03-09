package net.unicraft.gismi.levelmod.capabilities.generic;

import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;
import net.unicraft.gismi.levelmod.capabilities.MiningSkill;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;

public class LevelableSkillProvider implements ICapabilityProvider, INBTSerializable<CompoundTag> {
    public static Capability<HashMap<String,LevelableSkill>> PLAYER_LEVELABLE_SKILLS = CapabilityManager.get(new CapabilityToken<>() {});
    
    private HashMap<String,LevelableSkill> skills = null;
    private final LazyOptional<HashMap<String,LevelableSkill>> optionalLevelableSkills= LazyOptional.of(this::createLevelableSkills);

    private @NotNull HashMap<String,LevelableSkill> createLevelableSkills() {
        if(this.skills == null) {
            this.skills = new HashMap<>();
            MiningSkill mining = new MiningSkill();
            skills.put(mining.getIdName(),mining);
        }

        return this.skills;
    }


    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        LazyOptional<T> optional = LazyOptional.empty();
        if(cap == PLAYER_LEVELABLE_SKILLS) {
            optional =  optionalLevelableSkills.cast();
        }
        return optional;
    }

    @Override
    public CompoundTag serializeNBT() {
        CompoundTag nbt = new CompoundTag();
        createLevelableSkills().values().forEach(skill -> skill.saveNBTData(nbt));
        return nbt;
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        createLevelableSkills().values().forEach(skill -> skill.loadNBTData(nbt));
    }
}

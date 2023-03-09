package net.unicraft.gismi.levelmod.capabilities.generic;

import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.eventbus.api.Event;

public abstract class LevelableSkill {
    private int level;
    private float xp;
    private String idName;
    private String name;
    private String description;
    private String NBT_LEVEL;
    private String NBT_XP;
    private float xpNeeded;

    public LevelableSkill(int level,float xp,String idName, String name, String description) {
        this.level = level;
        this.xp = xp;
        this.idName = idName;
        this.NBT_LEVEL = idName + "_level";
        this.NBT_XP = idName + "_xp";
        this.name = name;
        this.description = description;
        calcXpNeeded();
    }

    public void addXP(float xpToAdd) {
        this.xp += xpToAdd;
        if(this.xp >= this.xpNeeded) {
            levelUp();
        }
        this.xp = (Math.round(xp * 100F)) / 100F;
    }

    public abstract <T extends Event> void earnXP(T event);

    private void levelUp() {
        this.xp -= this.xpNeeded;
        this.level++;
        calcXpNeeded();
    }

    private void calcXpNeeded() {
        this.xpNeeded = Math.round((Math.pow(this.level * 100F,1.15F)*100F))/100F;
    }

    public float getXpNeeded() {
        return this.xpNeeded;
    }

    public float getXP() {
        return this.xp;
    }

    public String getIdName() {
        return this.idName;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
        calcXpNeeded();
    }

    public void saveNBTData(CompoundTag nbt) {
        nbt.putInt(this.NBT_LEVEL,this.level);
        nbt.putFloat(this.NBT_XP,this.xp);
    }

    public void loadNBTData(CompoundTag nbt) {
        this.level = nbt.getInt(NBT_LEVEL);
        this.xp = nbt.getFloat(NBT_XP);
        calcXpNeeded();
    }
}

package net.unicraft.gismi.levelmod.config;

import net.minecraftforge.common.ForgeConfigSpec;

public class LevelmodCommonConfig {

    public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec SPEC;

    public static final ForgeConfigSpec.ConfigValue<Boolean> DEBUG;

    public static final ForgeConfigSpec.ConfigValue<Float> MINING_SKILL_BASE_XP;
    public static final ForgeConfigSpec.ConfigValue<Integer> MINING_SKILL_ORE_MULTIPLIER;

    static {
        BUILDER.push("Levelmod configs");

        DEBUG = BUILDER.comment("Enable Debug Mode").define("debug",false);

        MINING_SKILL_BASE_XP = BUILDER.comment("How much XP is earned for breaking Block that give Mining XP").
                define("Mining XP",0.5F);

        MINING_SKILL_ORE_MULTIPLIER = BUILDER.comment("Base XP is multiplied with this value as bonus xp for ores. \nSet to 1 for no bonus XP.").
                defineInRange("Mining Ore Bonus",3,1,99);

        BUILDER.pop();
        SPEC = BUILDER.build();
    }
}

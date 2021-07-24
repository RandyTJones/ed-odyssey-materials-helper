package nl.jixxed.eliteodysseymaterials.enums;

public enum RecipeName {

    ENGINEER_A1,
    ENGINEER_A2,
    ENGINEER_A3,
    ENGINEER_A4,
    ENGINEER_A5,
    ENGINEER_B1,
    ENGINEER_B2,
    ENGINEER_B3,
    ENGINEER_B4,
    ENGINEER_B5,
    ENGINEER_C1,
    ENGINEER_C2,
    ENGINEER_C3,
    ENGINEER_C4,
    ENGINEER_C5,
    MAVERICK_SUIT_GRADE_1_2,
    MAVERICK_SUIT_GRADE_2_3,
    MAVERICK_SUIT_GRADE_3_4,
    MAVERICK_SUIT_GRADE_4_5,
    DOMINATOR_SUIT_GRADE_1_2,
    DOMINATOR_SUIT_GRADE_2_3,
    DOMINATOR_SUIT_GRADE_3_4,
    DOMINATOR_SUIT_GRADE_4_5,
    ARTEMIS_SUIT_GRADE_1_2,
    ARTEMIS_SUIT_GRADE_2_3,
    ARTEMIS_SUIT_GRADE_3_4,
    ARTEMIS_SUIT_GRADE_4_5,
    ADDED_MELEE_DAMAGE,
    COMBAT_MOVEMENT_SPEED,
    DAMAGE_RESISTANCE,
    ENHANCED_TRACKING,
    EXTRA_AMMO_CAPACITY,
    EXTRA_BACKPACK_CAPACITY,
    FASTER_SHIELD_REGEN,
    IMPROVED_BATTERY_CAPACITY,
    IMPROVED_JUMP_ASSIST,
    INCREASED_AIR_RESERVES,
    INCREASED_SPRINT_DURATION,
    NIGHT_VISION,
    QUIETER_FOOTSTEPS,
    REDUCED_TOOL_BATTERY_CONSUMPTION,
    KARMA_1_2,
    KARMA_2_3,
    KARMA_3_4,
    KARMA_4_5,
    TK_1_2,
    TK_2_3,
    TK_3_4,
    TK_4_5,
    MANTICORE_1_2,
    MANTICORE_2_3,
    MANTICORE_3_4,
    MANTICORE_4_5,
    AUDIO_MASKING,
    FASTER_HANDLING,
    GREATER_RANGE_KINETIC,
    GREATER_RANGE_LASER,
    GREATER_RANGE_PLASMA,
    HEADSHOT_DAMAGE_KINETIC,
    HEADSHOT_DAMAGE_LASER,
    HEADSHOT_DAMAGE_PLASMA,
    HIGHER_ACCURACY_KINETIC,
    HIGHER_ACCURACY_LASER,
    HIGHER_ACCURACY_PLASMA,
    MAGAZINE_SIZE,
    NOISE_SUPPRESSOR,
    RELOAD_SPEED,
    SCOPE,
    STABILITY,
    STOWED_RELOADING;

    public static RecipeName forName(final String name) {
        try {
            return RecipeName.valueOf(name.toUpperCase());
        } catch (final IllegalArgumentException ex) {
            return null;
        }
    }

    public String getLocalizationKey() {
        return "recipe.name." + this.name().toLowerCase();
    }

    public String getDescriptionLocalizationKey() {
        return "recipe.description." + this.name().toLowerCase();
    }

    public static RecipeName forEngineer(final Engineer engineer) {
        return switch (engineer) {
            case DOMINO_GREEN -> ENGINEER_A1;
            case HERO_FERRARI -> ENGINEER_B1;
            case JUDE_NAVARRO -> ENGINEER_C1;
            case KIT_FOWLER -> ENGINEER_A3;
            case ODEN_GEIGER -> ENGINEER_C5;
            case TERRA_VELASQUEZ -> ENGINEER_C3;
            case UMA_LASZLO -> ENGINEER_B5;
            case WELLINGTON_BECK -> ENGINEER_B3;
            case YARDEN_BOND -> ENGINEER_A5;
            case UNKNOWN -> throw new IllegalArgumentException("unknown engineer");
        };
    }
}

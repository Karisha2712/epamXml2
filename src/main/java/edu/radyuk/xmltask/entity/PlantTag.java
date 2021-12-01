package edu.radyuk.xmltask.entity;

public enum PlantTag {
    FLOWERS,
    TREE,
    BUSH,
    GRASS,
    ID,
    PICTURE,
    VISUAL_PARAMETERS,
    GROWING_TIPS,
    NAME,
    PLANTING_DATE,
    SOIL,
    COUNTRY,
    STEM_COLOR,
    LEAF_COLOR,
    MEDIUM_SIZE,
    TEMPERATURE,
    HUMIDITY,
    WATERING,
    MULTIPLYING,
    FRUIT_PRESENCE,
    NEED_FOR_PRUNING,
    FLOWER_PRESENCE;

    private static final String UNDERSCORE = "_";
    private static final String HYPHEN = "-";

    @Override
    public String toString() {
        String result = this.name();
        result = result.toLowerCase();
        result = result.replace(UNDERSCORE, HYPHEN);
        return result;
    }
}

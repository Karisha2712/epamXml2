package edu.radyuk.xmltask.entity.tag;

public enum PlantTag {
    TREE,
    BUSH,
    GRASS,
    NAME,
    PLANTING_DATE,
    SOIL,
    COUNTRY,
    VISUAL_PARAMETERS,
    STEM_COLOR,
    LEAF_COLOR,
    MEDIUM_SIZE,
    GROWING_TIPS,
    TEMPERATURE,
    HUMIDITY,
    WATERING,
    MULTIPLYING,
    FRUIT_PRESENCE,
    NEED_FOR_PRUNING,
    FLOWER_PRESENCE,
    ID,
    PICTURE;

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

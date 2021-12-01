package edu.radyuk.xmltask.entity.type;

public enum PlantType {
    TREE,
    BUSH,
    GRASS;

    @Override
    public String toString() {
        String result = this.name();
        result = result.toLowerCase();
        return result;
    }
}

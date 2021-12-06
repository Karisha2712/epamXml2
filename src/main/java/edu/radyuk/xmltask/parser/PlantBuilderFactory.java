package edu.radyuk.xmltask.parser;

import edu.radyuk.xmltask.exception.PlantException;

public class PlantBuilderFactory {

    private static PlantBuilderFactory instance = new PlantBuilderFactory();

    private PlantBuilderFactory() {
    }

    public PlantBuilder getPlantBuilder(BuilderType builderType) throws PlantException {
        switch (builderType) {
            case DOM -> {
                return new PlantDomBuilder();
            }
            case SAX -> {
                return new PlantSaxBuilder();
            }
            case STAX -> {
                return new PlantStaxBuilder();
            }
            default -> throw new PlantException("Invalid builder type");
        }
    }

    public static PlantBuilderFactory getInstance() {
        return instance;
    }
}

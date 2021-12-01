package edu.radyuk.xmltask.parser;

import edu.radyuk.xmltask.entity.Plant;
import edu.radyuk.xmltask.exception.PlantException;

import java.util.List;

public interface PlantBuilder {
    void buildPlants(String filePath) throws PlantException;

    List<Plant> getPlants();
}

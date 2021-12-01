package edu.radyuk.xmltask.parser;

import edu.radyuk.xmltask.entity.Plant;
import edu.radyuk.xmltask.exception.PlantException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.net.URL;
import java.util.List;

public class PlantSaxBuilderTest {

    @Test
    void ifPlantSaxBuilderReturnsCorrectPlants() throws PlantException {
        URL fileUrl = PlantDomBuilderTest.class.getClassLoader().getResource("files/greenhouse.xml");
        File file = new File(fileUrl.getFile());
        String filePath = file.getAbsolutePath();
        PlantSaxBuilder plantSaxBuilder = new PlantSaxBuilder();
        plantSaxBuilder.buildPlants(filePath);
        List<Plant> plants = plantSaxBuilder.getPlants();
        int expectedSize = 16;
        System.out.println(plants);
        Assertions.assertEquals(expectedSize, plants.size());
    }
}

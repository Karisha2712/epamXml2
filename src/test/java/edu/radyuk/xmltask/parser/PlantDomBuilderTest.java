package edu.radyuk.xmltask.parser;

import edu.radyuk.xmltask.entity.Plant;
import edu.radyuk.xmltask.exception.PlantException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.net.URL;
import java.util.List;

public class PlantDomBuilderTest {

    @Test
    void ifGetPlantsMethodReturnsCorrectList() throws PlantException {
        URL fileUrl = PlantDomBuilderTest.class.getClassLoader().getResource("files/greenhouse.xml");
        File file = new File(fileUrl.getFile());
        String filePath = file.getAbsolutePath();
        PlantDomBuilder plantDomBuilder = new PlantDomBuilder();
        plantDomBuilder.buildPlants(filePath);
        List<Plant> plants = plantDomBuilder.getPlants();
        int expectedSize = 16;
        System.out.println(plants);
        Assertions.assertEquals(expectedSize, plants.size());
    }
}

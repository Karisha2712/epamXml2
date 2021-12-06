package edu.radyuk.xmltask.parser;

import edu.radyuk.xmltask.entity.Plant;
import edu.radyuk.xmltask.exception.PlantException;
import edu.radyuk.xmltask.validator.XmlValidator;

import java.util.List;

public class BuilderManager {
    public List<Plant> parseXml(String xmlFilePath, String xsdFilePath, PlantBuilder builder) throws PlantException {
        XmlValidator xmlValidator = new XmlValidator();
        if (!xmlValidator.isXmlFileValid(xmlFilePath, xsdFilePath)) {
            throw new PlantException("Invalid xml");
        }
        builder.buildPlants(xmlFilePath);
        return builder.getPlants();
    }
}

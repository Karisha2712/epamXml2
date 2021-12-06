package edu.radyuk.xmltask._main;

import edu.radyuk.xmltask.entity.Plant;
import edu.radyuk.xmltask.exception.PlantException;
import edu.radyuk.xmltask.parser.BuilderType;
import edu.radyuk.xmltask.parser.PlantBuilder;
import edu.radyuk.xmltask.parser.PlantBuilderFactory;
import edu.radyuk.xmltask.validator.XmlValidator;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.net.URL;
import java.util.List;

public class Main {
    public static final Logger logger = LogManager.getLogger();
    private static final String XML_FILE_PATH = "files/greenhouse.xml";
    private static final String XSD_FILE_PATH = "files/greenhouse.xsd";

    public static void main(String[] args) {
        URL xmlFileUrl = Main.class.getClassLoader().getResource(XML_FILE_PATH);
        URL xsdFileUrl = Main.class.getClassLoader().getResource(XSD_FILE_PATH);
        File xmlFile = new File(xmlFileUrl.getFile());
        File xsdFile = new File(xsdFileUrl.getFile());
        String xmlFilepath = xmlFile.getAbsolutePath();
        String xsdFilepath = xsdFile.getAbsolutePath();
        XmlValidator validator = new XmlValidator();
        if (validator.isXmlFileValid(xmlFilepath, xsdFilepath)) {
            try {
                PlantBuilderFactory plantBuilderFactory = PlantBuilderFactory.getInstance();
                PlantBuilder builder;

                builder = plantBuilderFactory.getPlantBuilder(BuilderType.DOM);
                builder.buildPlants(xmlFilepath);
                List<Plant> domPlants = builder.getPlants();

                builder = plantBuilderFactory.getPlantBuilder(BuilderType.SAX);
                builder.buildPlants(xmlFilepath);
                List<Plant> saxPlants = builder.getPlants();

                builder = plantBuilderFactory.getPlantBuilder(BuilderType.STAX);
                builder.buildPlants(xmlFilepath);
                List<Plant> staxPlants = builder.getPlants();

                System.out.println(domPlants);
                System.out.println(saxPlants);
                System.out.println(staxPlants);

            } catch (PlantException e) {
                logger.log(Level.ERROR, e);
            }
        }
    }
}

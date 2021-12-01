package edu.radyuk.xmltask.parser;

import edu.radyuk.xmltask.entity.Plant;
import edu.radyuk.xmltask.exception.PlantException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.util.List;

public class PlantSaxBuilder implements PlantBuilder {
    private static final Logger logger = LogManager.getLogger();
    private List<Plant> plants;

    public PlantSaxBuilder() {
    }

    @Override
    public void buildPlants(String filePath) throws PlantException {
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser parser = factory.newSAXParser();
            XMLReader reader = parser.getXMLReader();
            var handler = new PlantHandler();
            reader.setContentHandler(handler);
            reader.parse(filePath);
            plants = handler.getPlants();
        } catch (EnumConstantNotPresentException | SAXException e) {
            throw new PlantException("File %s can't be parsed".formatted(filePath), e);
        } catch (IOException e) {
            throw new PlantException("File %s can't be read".formatted(filePath), e);
        } catch (ParserConfigurationException e) {
            throw new PlantException(e);
        }
        logger.log(Level.INFO, "Plants were created successfully");
    }

    @Override
    public List<Plant> getPlants() {
        return plants;
    }
}

package edu.radyuk.xmltask.parser;

import edu.radyuk.xmltask.entity.BushPlant;
import edu.radyuk.xmltask.entity.GrassPlant;
import edu.radyuk.xmltask.entity.Plant;
import edu.radyuk.xmltask.entity.TreePlant;
import edu.radyuk.xmltask.entity.tag.PlantTag;
import edu.radyuk.xmltask.entity.type.*;
import edu.radyuk.xmltask.exception.PlantException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class PlantStaxBuilder implements PlantBuilder {
    private static final Logger logger = LogManager.getLogger();
    private static final String UNDERSCORE = "_";
    private static final String HYPHEN = "-";
    private final List<Plant> plants;
    private final XMLInputFactory inputFactory;

    public PlantStaxBuilder() {
        inputFactory = XMLInputFactory.newFactory();
        plants = new ArrayList<>();
    }

    @Override
    public void buildPlants(String filePath) throws PlantException {
        XMLStreamReader reader;
        String name;
        try (FileInputStream inputStream = new FileInputStream(filePath)) {
            reader = inputFactory.createXMLStreamReader(inputStream);
            while (reader.hasNext()) {
                int type = reader.next();
                if (type == XMLStreamConstants.START_ELEMENT) {
                    name = reader.getLocalName();
                    if (name.equals(PlantType.TREE.toString())
                            || name.equals(PlantType.BUSH.toString())
                            || name.equals(PlantType.GRASS.toString())) {
                        Plant plant = buildPlant(reader);
                        plants.add(plant);
                    }
                }
            }
        } catch (XMLStreamException e) {
            throw new PlantException("File %s can't be parsed".formatted(filePath), e);
        } catch (FileNotFoundException e) {
            throw new PlantException("File %s not found".formatted(filePath), e);
        } catch (IOException e) {
            throw new PlantException("File %s can't be read".formatted(filePath), e);
        }
        logger.log(Level.INFO, "Plants were created successfully");
    }

    @Override
    public List<Plant> getPlants() {
        return plants;
    }

    private Plant buildPlant(XMLStreamReader reader) throws XMLStreamException {
        Plant plant;
        PlantType plantType = PlantType.valueOf(reader.getLocalName().toUpperCase(Locale.ROOT));
        plant = switch (plantType) {
            case TREE -> new TreePlant();

            case BUSH -> new BushPlant();

            case GRASS -> new GrassPlant();
        };
        String id = reader.getAttributeValue(null, PlantTag.ID.toString());
        plant.setPlantId(id);
        String picture = reader.getAttributeValue(null, PlantTag.PICTURE.toString());
        if (picture == null) {
            picture = Plant.DEFAULT_PICTURE;
        }
        plant.setPicture(picture);
        String name;
        while (reader.hasNext()) {
            int type = reader.next();
            switch (type) {
                case XMLStreamConstants.START_ELEMENT -> {
                    name = reader.getLocalName().toUpperCase(Locale.ROOT).replace(HYPHEN, UNDERSCORE);
                    setPlantProperty(reader, name, plant);
                }
                case XMLStreamConstants.END_ELEMENT -> {
                    name = reader.getLocalName().toUpperCase(Locale.ROOT).replace(HYPHEN, UNDERSCORE);
                    if (PlantTag.valueOf(name) == PlantTag.TREE
                            || PlantTag.valueOf(name) == PlantTag.BUSH
                            || PlantTag.valueOf(name) == PlantTag.GRASS) {
                        return plant;
                    }
                }

            }
        }
        throw new XMLStreamException("Unknown element in tag <tree|bush|grass>");
    }

    private void setPlantProperty(XMLStreamReader reader, String name, Plant plant) throws XMLStreamException {
        switch (PlantTag.valueOf(name)) {
            case NAME -> {
                String plantName = getXmlText(reader);
                plant.setName(plantName);
            }
            case SOIL -> {
                String soil = getXmlText(reader);
                plant.setSoil(Soil.valueOf(soil.toUpperCase(Locale.ROOT)));
            }
            case COUNTRY -> {
                String country = getXmlText(reader);
                plant.setCountry(Country.valueOf(country.toUpperCase(Locale.ROOT)));
            }

            case MULTIPLYING -> {
                String multiplying = getXmlText(reader);
                plant.setMultiplying(Multiplying.valueOf(multiplying.toUpperCase(Locale.ROOT)));
            }

            case VISUAL_PARAMETERS -> setVisualParameters(reader, plant);

            case GROWING_TIPS -> setGrowingTips(reader, plant);

            case PLANTING_DATE -> {
                String plantingDate = getXmlText(reader);
                plant.setPlantingDate(LocalDateTime.parse(plantingDate));
            }

            case FRUIT_PRESENCE -> {
                assert plant instanceof TreePlant;
                String fruitPresence = getXmlText(reader);
                ((TreePlant) plant).setFruitPresent(Boolean.parseBoolean(fruitPresence));
            }

            case NEED_FOR_PRUNING -> {
                assert plant instanceof BushPlant;
                String subjectedToPruning = getXmlText(reader);
                ((BushPlant) plant).setSubjectedToPruning(Boolean.parseBoolean(subjectedToPruning));
            }

            case FLOWER_PRESENCE -> {
                assert plant instanceof GrassPlant;
                String flowerPresence = getXmlText(reader);
                ((GrassPlant) plant).setFlowerPresent(Boolean.parseBoolean(flowerPresence));
            }
        }

    }

    private void setVisualParameters(XMLStreamReader reader, Plant plant) throws XMLStreamException {
        String name;
        while (reader.hasNext()) {
            int type = reader.next();
            switch (type) {
                case XMLStreamConstants.START_ELEMENT -> {
                    name = reader.getLocalName().toUpperCase(Locale.ROOT).replace(HYPHEN, UNDERSCORE);
                    switch (PlantTag.valueOf(name)) {
                        case STEM_COLOR -> {
                            String stemColor = getXmlText(reader);
                            plant.setStemColor(Color.valueOf(stemColor.toUpperCase(Locale.ROOT)));
                        }
                        case LEAF_COLOR -> {
                            String leafColor = getXmlText(reader);
                            plant.setLeafColor(Color.valueOf(leafColor.toUpperCase(Locale.ROOT)));
                        }
                        case MEDIUM_SIZE -> {
                            String mediumSize = getXmlText(reader);
                            plant.setMediumSize(Integer.parseInt(mediumSize));
                        }
                    }
                }
                case XMLStreamConstants.END_ELEMENT -> {
                    name = reader.getLocalName().toUpperCase(Locale.ROOT).replace(HYPHEN, UNDERSCORE);
                    if (PlantTag.valueOf(name) == PlantTag.VISUAL_PARAMETERS) {
                        return;
                    }
                }
            }
        }
        throw new XMLStreamException("Unknown element in tag <visual-parameters>");
    }

    private void setGrowingTips(XMLStreamReader reader, Plant plant) throws XMLStreamException {
        String name;
        while (reader.hasNext()) {
            int type = reader.next();
            switch (type) {
                case XMLStreamConstants.START_ELEMENT -> {
                    name = reader.getLocalName().toUpperCase(Locale.ROOT);
                    switch (PlantTag.valueOf(name)) {
                        case TEMPERATURE -> {
                            String temperature = getXmlText(reader);
                            plant.setTemperature(Integer.parseInt(temperature));
                        }
                        case HUMIDITY -> {
                            String humidity = getXmlText(reader);
                            plant.setHumidity(Integer.parseInt(humidity));
                        }
                        case WATERING -> {
                            String watering = getXmlText(reader);
                            plant.setWatering(Integer.parseInt(watering));
                        }
                    }
                }
                case XMLStreamConstants.END_ELEMENT -> {
                    name = reader.getLocalName().toUpperCase(Locale.ROOT).replace(HYPHEN, UNDERSCORE);
                    if (PlantTag.valueOf(name) == PlantTag.GROWING_TIPS) {
                        return;
                    }
                }
            }
        }
        throw new XMLStreamException("Unknown element in tag <growing-tips>");
    }

    private String getXmlText(XMLStreamReader reader) throws XMLStreamException {
        String text = null;
        if (reader.hasNext()) {
            reader.next();
            text = reader.getText();
        }
        return text;
    }
}

package edu.radyuk.xmltask.parser;

import edu.radyuk.xmltask.entity.*;
import edu.radyuk.xmltask.entity.type.*;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.Locale;

public class PlantHandler extends DefaultHandler {
    private static final char HYPHEN = '-';
    private static final char UNDERSCORE = '_';
    private final List<Plant> plants;
    private Plant currentPlant;
    private PlantTag currentTag;
    private EnumSet<PlantTag> withText;

    public PlantHandler() {
        plants = new ArrayList<>();
        withText = EnumSet.range(PlantTag.NAME, PlantTag.FLOWER_PRESENCE);
    }

    public List<Plant> getPlants() {
        return plants;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        String name = qName.toUpperCase(Locale.ROOT).replace(HYPHEN, UNDERSCORE);
        if (qName.equals(PlantType.TREE.toString())
                || qName.equals(PlantType.BUSH.toString())
                || qName.equals(PlantType.GRASS.toString())) {
            PlantType plantType = PlantType.valueOf(name);
            currentPlant = switch (plantType) {
                case TREE -> new TreePlant();

                case BUSH -> new BushPlant();

                case GRASS -> new GrassPlant();
            };
            if (attributes.getLength() == 1) {
                currentPlant.setPlantId(attributes.getValue(0));
                currentPlant.setPicture(Plant.DEFAULT_PICTURE);
            } else {
                int idAttributeIndex = attributes.getLocalName(0).equals(PlantTag.ID.toString()) ? 0 : 1;
                int pictureAttributeIndex = 1 - idAttributeIndex;
                currentPlant.setPlantId(attributes.getValue(idAttributeIndex));
                currentPlant.setPicture(attributes.getValue(pictureAttributeIndex));
            }
        } else {
            PlantTag temp = PlantTag.valueOf(name);
            if (withText.contains(temp)) {
                currentTag = temp;
            }
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) {
        if (qName.equals(PlantType.TREE.toString())
                || qName.equals(PlantType.BUSH.toString())
                || qName.equals(PlantType.GRASS.toString())) {
            plants.add(currentPlant);
            currentPlant = null;
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) {
        String data = new String(ch, start, length).strip();
        if (currentTag != null) {
            switch (currentTag) {
                case NAME -> currentPlant.setName(data);

                case SOIL -> {
                    String soil = data.toUpperCase(Locale.ROOT).replace(HYPHEN, UNDERSCORE);
                    currentPlant.setSoil(Soil.valueOf(soil));
                }

                case COUNTRY -> {
                    String country = data.toUpperCase(Locale.ROOT).replace(HYPHEN, UNDERSCORE);
                    currentPlant.setCountry(Country.valueOf(country));
                }

                case MULTIPLYING -> {
                    String multiplying = data.toUpperCase(Locale.ROOT).replace(HYPHEN, UNDERSCORE);
                    currentPlant.setMultiplying(Multiplying.valueOf(multiplying));
                }

                case PLANTING_DATE -> {
                    String plantingDate = data.toUpperCase(Locale.ROOT);
                    currentPlant.setPlantingDate(LocalDateTime.parse(plantingDate));
                }

                case FRUIT_PRESENCE -> {
                    TreePlant tempPlant = (TreePlant) currentPlant;
                    String fruitPresence = data.toUpperCase(Locale.ROOT);
                    tempPlant.setFruitPresent(Boolean.parseBoolean(fruitPresence));
                }

                case NEED_FOR_PRUNING -> {
                    BushPlant tempPlant = (BushPlant) currentPlant;
                    String subjectedToPruning = data.toUpperCase(Locale.ROOT);
                    tempPlant.setSubjectedToPruning(Boolean.parseBoolean(subjectedToPruning));
                }

                case FLOWER_PRESENCE -> {
                    GrassPlant tempPlant = (GrassPlant) currentPlant;
                    String flowerPresence = data.toUpperCase(Locale.ROOT);
                    tempPlant.setFlowerPresent(Boolean.parseBoolean(flowerPresence));
                }
                case STEM_COLOR -> {
                    String stemColor = data.toUpperCase(Locale.ROOT);
                    currentPlant.setStemColor(Color.valueOf(stemColor));
                }

                case LEAF_COLOR -> {
                    String leafColor = data.toUpperCase(Locale.ROOT);
                    currentPlant.setLeafColor(Color.valueOf(leafColor));
                }

                case MEDIUM_SIZE -> {
                    String mediumSize = data.toUpperCase(Locale.ROOT);
                    currentPlant.setMediumSize(Integer.parseInt(mediumSize));
                }

                case TEMPERATURE -> {
                    String temperature = data.toUpperCase(Locale.ROOT);
                    currentPlant.setTemperature(Integer.parseInt(temperature));
                }

                case HUMIDITY -> {
                    String humidity = data.toUpperCase(Locale.ROOT);
                    currentPlant.setHumidity(Integer.parseInt(humidity));
                }

                case WATERING -> {
                    String watering = data.toUpperCase(Locale.ROOT);
                    currentPlant.setWatering(Integer.parseInt(watering));
                }

                default -> throw new EnumConstantNotPresentException(
                        currentTag.getDeclaringClass(), currentTag.name());
            }

            currentTag = null;
        }
    }
}

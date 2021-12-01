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
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class PlantDomBuilder implements PlantBuilder {
    private static final Logger logger = LogManager.getLogger();
    private final List<Plant> plants;
    private final DocumentBuilder documentBuilder;

    public PlantDomBuilder() throws PlantException {
        plants = new ArrayList<>();
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        try {
            documentBuilder = documentBuilderFactory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            throw new PlantException(e);
        }
    }

    @Override
    public void buildPlants(String filePath) throws PlantException {
        Document document;
        try {
            document = documentBuilder.parse(filePath);
            Element root = document.getDocumentElement();
            createSpecificTypeFlowers(root, PlantType.TREE);
            createSpecificTypeFlowers(root, PlantType.BUSH);
            createSpecificTypeFlowers(root, PlantType.GRASS);
        } catch (SAXException e) {
            throw new PlantException("File %s can't be parsed".formatted(filePath), e);
        } catch (IOException e) {
            throw new PlantException("File %s can't be read".formatted(filePath), e);
        }
    }

    @Override
    public List<Plant> getPlants() {
        return plants;
    }

    private void createSpecificTypeFlowers(Element root, PlantType plantType) throws PlantException {
        NodeList plantNodeList = root.getElementsByTagName(plantType.toString());
        for (int i = 0; i < plantNodeList.getLength(); i++) {
            Element plantElement = (Element) plantNodeList.item(i);
            Plant plant = buildPlant(plantElement, plantType);
            plants.add(plant);
        }
        logger.log(Level.INFO, "Plants were created successfully");
    }

    private static Plant buildPlant(Element plantElement, PlantType plantType) throws PlantException {
        Plant plant;
        switch (plantType) {
            case TREE -> {
                plant = new TreePlant();
                ((TreePlant) plant).setFruitPresent(Boolean
                        .parseBoolean(getElementTextContent(plantElement, PlantTag.FRUIT_PRESENCE.toString())));
            }
            case BUSH -> {
                plant = new BushPlant();
                ((BushPlant) plant).setSubjectedToPruning(Boolean
                        .parseBoolean(getElementTextContent(plantElement, PlantTag.NEED_FOR_PRUNING.toString())));
            }
            case GRASS -> {
                plant = new GrassPlant();
                ((GrassPlant) plant).setFlowerPresent(Boolean
                        .parseBoolean(getElementTextContent(plantElement, PlantTag.FLOWER_PRESENCE.toString())));
            }
            default -> throw new PlantException("Invalid plant type");

        }
        String picture = plantElement.getAttribute(PlantTag.PICTURE.toString());
        if (picture.isBlank()) {
            picture = Plant.DEFAULT_PICTURE;
        }
        plant.setPicture(picture);
        plant.setPlantId(plantElement.getAttribute(PlantTag.ID.toString()));
        String name = getElementTextContent(plantElement, PlantTag.NAME.toString());
        plant.setName(name);
        String date = getElementTextContent(plantElement, PlantTag.PLANTING_DATE.toString());
        plant.setPlantingDate(LocalDateTime.parse(date));
        String soil = getElementTextContent(plantElement, PlantTag.SOIL.toString());
        plant.setSoil(Soil.valueOf(soil.toUpperCase(Locale.ROOT)));
        String country = getElementTextContent(plantElement, PlantTag.COUNTRY.toString());
        plant.setCountry(Country.valueOf(country.toUpperCase(Locale.ROOT)));
        String multiplying = getElementTextContent(plantElement, PlantTag.MULTIPLYING.toString());
        plant.setMultiplying(Multiplying.valueOf(multiplying.toUpperCase(Locale.ROOT)));
        NodeList visualParamsList = plantElement.getElementsByTagName(PlantTag.VISUAL_PARAMETERS.toString());
        Element visualParameters = (Element) visualParamsList.item(0);
        String stemColor = getElementTextContent(visualParameters, PlantTag.STEM_COLOR.toString());
        plant.setStemColor(Color.valueOf(stemColor.toUpperCase(Locale.ROOT)));
        String leafColor = getElementTextContent(visualParameters, PlantTag.LEAF_COLOR.toString());
        plant.setLeafColor(Color.valueOf(leafColor.toUpperCase(Locale.ROOT)));
        String mediumSize = getElementTextContent(visualParameters, PlantTag.MEDIUM_SIZE.toString());
        plant.setMediumSize(Integer.parseInt(mediumSize));
        NodeList growingTipsNodeList = plantElement.getElementsByTagName(PlantTag.GROWING_TIPS.toString());
        Element growingTips = (Element) growingTipsNodeList.item(0);
        String temperature = getElementTextContent(growingTips, PlantTag.TEMPERATURE.toString());
        plant.setTemperature(Integer.parseInt(temperature));
        String humidity = getElementTextContent(growingTips, PlantTag.HUMIDITY.toString());
        plant.setTemperature(Integer.parseInt(humidity));
        String watering = getElementTextContent(growingTips, PlantTag.WATERING.toString());
        plant.setWatering(Integer.parseInt(watering));
        return plant;
    }

    private static String getElementTextContent(Element element, String tagName) {
        NodeList nList = element.getElementsByTagName(tagName);
        Node node = nList.item(0);
        return node.getTextContent();
    }
}

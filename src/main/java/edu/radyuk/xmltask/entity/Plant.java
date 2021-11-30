package edu.radyuk.xmltask.entity;

import edu.radyuk.xmltask.entity.type.Color;
import edu.radyuk.xmltask.entity.type.Country;
import edu.radyuk.xmltask.entity.type.Multiplying;
import edu.radyuk.xmltask.entity.type.Soil;

import java.time.LocalDateTime;

public abstract class Plant {
    public static final String DEFAULT_PICTURE = "1.png";
    private String plantId;
    private String picture;
    private String name;
    private LocalDateTime plantingDate;
    private Soil soil;
    private Country country;
    private Color leafColor;
    private Color stemColor;
    private int mediumSize;
    private int temperature;
    private int humidity;
    private int watering;
    private Multiplying multiplying;

    public Plant() {
    }

    public Plant(String plantId, String name, LocalDateTime plantingDate,
                 Soil soil, Country country, Color leafColor,
                 Color stemColor, int mediumSize, int temperature,
                 int humidity, int watering, Multiplying multiplying) {
        this.plantId = plantId;
        this.name = name;
        this.plantingDate = plantingDate;
        this.soil = soil;
        this.country = country;
        this.leafColor = leafColor;
        this.stemColor = stemColor;
        this.mediumSize = mediumSize;
        this.temperature = temperature;
        this.humidity = humidity;
        this.watering = watering;
        this.multiplying = multiplying;
    }

    public String getPlantId() {
        return plantId;
    }

    public void setPlantId(String plantId) {
        this.plantId = plantId;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getPlantingDate() {
        return plantingDate;
    }

    public void setPlantingDate(LocalDateTime plantingDate) {
        this.plantingDate = plantingDate;
    }

    public Soil getSoil() {
        return soil;
    }

    public void setSoil(Soil soil) {
        this.soil = soil;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public Color getLeafColor() {
        return leafColor;
    }

    public void setLeafColor(Color leafColor) {
        this.leafColor = leafColor;
    }

    public Color getStemColor() {
        return stemColor;
    }

    public void setStemColor(Color stemColor) {
        this.stemColor = stemColor;
    }

    public int getMediumSize() {
        return mediumSize;
    }

    public void setMediumSize(int mediumSize) {
        this.mediumSize = mediumSize;
    }

    public int getTemperature() {
        return temperature;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }

    public int getHumidity() {
        return humidity;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    public int getWatering() {
        return watering;
    }

    public void setWatering(int watering) {
        this.watering = watering;
    }

    public Multiplying getMultiplying() {
        return multiplying;
    }

    public void setMultiplying(Multiplying multiplying) {
        this.multiplying = multiplying;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Plant plant = (Plant) o;
        return picture.equals(plant.picture)
                && name.equals(plant.name)
                && mediumSize == plant.mediumSize
                && temperature == plant.temperature
                && humidity == plant.humidity
                && watering == plant.watering
                && soil == plant.soil
                && country == plant.country
                && leafColor == plant.leafColor
                && stemColor == plant.stemColor
                && multiplying == plant.multiplying;
    }

    @Override
    public int hashCode() {
        int result = 1;
        result += 31 * result + picture.hashCode();
        result += 31 * result + soil.hashCode();
        result += 31 * result + country.hashCode();
        result += 31 * result + leafColor.hashCode();
        result += 31 * result + stemColor.hashCode();
        result += 31 * result + multiplying.hashCode();
        result += 31 * result + mediumSize;
        result += 31 * result + temperature;
        result += 31 * result + humidity;
        result += 31 * result + watering;
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Plant{");
        sb.append("plantId='").append(plantId).append('\'');
        sb.append(", picture='").append(picture).append('\'');
        sb.append(", name='").append(name).append('\'');
        sb.append(", plantingDate=").append(plantingDate);
        sb.append(", soil=").append(soil);
        sb.append(", country=").append(country);
        sb.append(", leafColor=").append(leafColor);
        sb.append(", stemColor=").append(stemColor);
        sb.append(", mediumSize=").append(mediumSize);
        sb.append(", temperature=").append(temperature);
        sb.append(", humidity=").append(humidity);
        sb.append(", watering=").append(watering);
        sb.append(", multiplying=").append(multiplying);
        sb.append('}');
        return sb.toString();
    }
}

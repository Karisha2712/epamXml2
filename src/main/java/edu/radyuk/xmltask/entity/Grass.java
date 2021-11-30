package edu.radyuk.xmltask.entity;

import edu.radyuk.xmltask.entity.type.Color;
import edu.radyuk.xmltask.entity.type.Country;
import edu.radyuk.xmltask.entity.type.Multiplying;
import edu.radyuk.xmltask.entity.type.Soil;

import java.time.LocalDateTime;

public class Grass extends Plant {
    private boolean isFlowerPresent;

    public Grass() {
    }

    public Grass(String plantId, String name, LocalDateTime plantingDate,
                 Soil soil, Country country, Color leafColor,
                 Color stemColor, int mediumSize, int temperature,
                 int humidity, int watering, Multiplying multiplying, boolean isFruitPresent) {

        super(plantId, name, plantingDate, soil, country, leafColor, stemColor,
                mediumSize, temperature, humidity, watering, multiplying);
        this.isFlowerPresent = isFruitPresent;
    }

    public boolean isFlowerPresent() {
        return isFlowerPresent;
    }

    public void setFlowerPresent(boolean flowerPresent) {
        isFlowerPresent = flowerPresent;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Grass grass = (Grass) o;
        return isFlowerPresent == grass.isFlowerPresent;
    }

    @Override
    public int hashCode() {
        return 31 * super.hashCode() + Boolean.hashCode(isFlowerPresent);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Grass{");
        sb.append(super.toString());
        sb.append("isFlowerPresent=").append(isFlowerPresent);
        sb.append('}');
        return sb.toString();
    }
}

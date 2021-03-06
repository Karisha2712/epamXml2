package edu.radyuk.xmltask.entity;

import edu.radyuk.xmltask.entity.type.Color;
import edu.radyuk.xmltask.entity.type.Country;
import edu.radyuk.xmltask.entity.type.Multiplying;
import edu.radyuk.xmltask.entity.type.Soil;

import java.time.LocalDateTime;

public class TreePlant extends Plant {
    private boolean isFruitPresent;

    public TreePlant() {
    }

    public TreePlant(String plantId, String name, LocalDateTime plantingDate,
                     Soil soil, Country country, Color leafColor,
                     Color stemColor, int mediumSize, int temperature,
                     int humidity, int watering, Multiplying multiplying, boolean fruitPresent) {

        super(plantId, name, plantingDate, soil, country, leafColor, stemColor,
                mediumSize, temperature, humidity, watering, multiplying);
        isFruitPresent = fruitPresent;
    }

    public boolean isFruitPresent() {
        return isFruitPresent;
    }

    public void setFruitPresent(boolean fruitPresent) {
        isFruitPresent = fruitPresent;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        TreePlant tree = (TreePlant) o;
        return isFruitPresent == tree.isFruitPresent;
    }

    @Override
    public int hashCode() {
        return 31 * super.hashCode() + Boolean.hashCode(isFruitPresent);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Tree{");
        sb.append(super.toString());
        sb.append("isFruitPresent=").append(isFruitPresent);
        sb.append('}');
        return sb.toString();
    }
}

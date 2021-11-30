package edu.radyuk.xmltask.entity;

import edu.radyuk.xmltask.entity.type.Color;
import edu.radyuk.xmltask.entity.type.Country;
import edu.radyuk.xmltask.entity.type.Multiplying;
import edu.radyuk.xmltask.entity.type.Soil;

import java.time.LocalDateTime;

public class Bush extends Plant {
    private boolean isSubjectedToPruning;

    public Bush() {
    }

    public Bush(String plantId, String name, LocalDateTime plantingDate,
                Soil soil, Country country, Color leafColor,
                Color stemColor, int mediumSize, int temperature,
                int humidity, int watering, Multiplying multiplying, boolean isFruitPresent) {

        super(plantId, name, plantingDate, soil, country, leafColor, stemColor,
                mediumSize, temperature, humidity, watering, multiplying);
        this.isSubjectedToPruning = isFruitPresent;
    }

    public boolean isSubjectedToPruning() {
        return isSubjectedToPruning;
    }

    public void setSubjectedToPruning(boolean subjectedToPruning) {
        isSubjectedToPruning = subjectedToPruning;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Bush bush = (Bush) o;
        return isSubjectedToPruning == bush.isSubjectedToPruning;
    }

    @Override
    public int hashCode() {
        return 31 * super.hashCode() + Boolean.hashCode(isSubjectedToPruning);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Tree{");
        sb.append(super.toString());
        sb.append("isFruitPresent=").append(isSubjectedToPruning);
        sb.append('}');
        return sb.toString();
    }
}

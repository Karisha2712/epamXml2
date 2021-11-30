package edu.radyuk.xmltask.entity;

import edu.radyuk.xmltask.entity.type.Color;
import edu.radyuk.xmltask.entity.type.Country;
import edu.radyuk.xmltask.entity.type.Multiplying;
import edu.radyuk.xmltask.entity.type.Soil;

import java.time.LocalDateTime;

public class BushPlant extends Plant {
    private boolean isSubjectedToPruning;

    public BushPlant() {
    }

    public BushPlant(String plantId, String name, LocalDateTime plantingDate,
                     Soil soil, Country country, Color leafColor,
                     Color stemColor, int mediumSize, int temperature,
                     int humidity, int watering, Multiplying multiplying, boolean subjectedToPruning) {

        super(plantId, name, plantingDate, soil, country, leafColor, stemColor,
                mediumSize, temperature, humidity, watering, multiplying);
        isSubjectedToPruning = subjectedToPruning;
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
        BushPlant bush = (BushPlant) o;
        return isSubjectedToPruning == bush.isSubjectedToPruning;
    }

    @Override
    public int hashCode() {
        return 31 * super.hashCode() + Boolean.hashCode(isSubjectedToPruning);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Bush{");
        sb.append(super.toString());
        sb.append("isSubjectedToPruning=").append(isSubjectedToPruning);
        sb.append('}');
        return sb.toString();
    }
}

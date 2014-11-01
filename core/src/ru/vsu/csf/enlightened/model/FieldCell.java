package ru.vsu.csf.enlightened.model;

public class FieldCell {
    FieldCellType type;

    int lightsAround;

    public FieldCell(FieldCellType type, int lightsAround) {
        this.type = type;
        this.lightsAround = lightsAround;
    }

    public FieldCellType getType() {
        return type;
    }

    public int getLightsAround() {
        return lightsAround;
    }

    public void setType(FieldCellType type) {
        this.type = type;
    }
}

package cz.fi.muni.pa165.api.dto;

/**
 * This file is part of PA165 school project.
 */
public class CarLogPossibleStateDTO {

    private String typeName;

    public CarLogPossibleStateDTO(String typeName) {
        this.typeName = typeName;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    @Override
    public String toString() {
        return typeName;
    }
}

package cz.fi.muni.pa165.api.dto;

import java.util.List;

/**
 * This file is part of PA165 school project.
 */
public class CarLogStateDTO {

    private String typeName;

    private List<CarLogPossibleStateDTO> possibleStates;

    public CarLogStateDTO(String typeName, List<CarLogPossibleStateDTO> possibleStates) {
        this.typeName = typeName;
        this.possibleStates = possibleStates;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public List<CarLogPossibleStateDTO> getPossibleStates() {
        return possibleStates;
    }

    public void setPossibleStates(List<CarLogPossibleStateDTO> possibleStates) {
        this.possibleStates = possibleStates;
    }
}

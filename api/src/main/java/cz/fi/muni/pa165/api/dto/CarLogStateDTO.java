package cz.fi.muni.pa165.api.dto;

import java.util.List;
import java.util.UUID;

/**
 * This file is part of PA165 school project.
 */
public class CarLogStateDTO {

    private String typeName;
    private UUID recordId;

    private List<CarLogPossibleStateDTO> possibleStates;

    public CarLogStateDTO(String typeName, List<CarLogPossibleStateDTO> possibleStates, UUID recordId) {
        this.typeName = typeName;
        this.possibleStates = possibleStates;
        this.recordId = recordId;
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

    public UUID getRecordId() {
        return recordId;
    }

    public void setRecordId(UUID recordId) {
        this.recordId = recordId;
    }
}

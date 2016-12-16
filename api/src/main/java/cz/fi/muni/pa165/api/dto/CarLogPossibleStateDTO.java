package cz.fi.muni.pa165.api.dto;

/**
 * This file is part of PA165 school project.
 */
public class CarLogPossibleStateDTO {

    private String typeName;

    private String id;

    private String url;

    public CarLogPossibleStateDTO(String typeName, String id) {
        this.typeName = typeName;
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}

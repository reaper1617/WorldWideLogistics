package com.gerasimchuk.dto;

public class CityDTO {
    private String id;
    private String name;
    private String hasAgency;

    public CityDTO() {
    }

    public CityDTO(String id, String name, String hasAgency) {
        this.id = id;
        this.name = name;
        this.hasAgency = hasAgency;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHasAgency() {
        return hasAgency;
    }

    public void setHasAgency(String hasAgency) {
        this.hasAgency = hasAgency;
    }


}

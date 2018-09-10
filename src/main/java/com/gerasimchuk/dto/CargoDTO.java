package com.gerasimchuk.dto;

/** Cargo Data Transfer Object
 * @author Reaper
 * @version 1.0
 */

public class CargoDTO {
    private String id;
    private String personalNumber;
    private String name;
    private String weight;
    private String cityFrom;
    private String cityTo;
    private String status;

    public CargoDTO() {
    }

    public CargoDTO(String id, String personalNumber, String name, String weight, String cityFrom, String cityTo, String status) {
        this.id = id;
        this.personalNumber = personalNumber;
        this.name = name;
        this.weight = weight;
        this.cityFrom = cityFrom;
        this.cityTo = cityTo;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPersonalNumber() {
        return personalNumber;
    }

    public void setPersonalNumber(String personalNumber) {
        this.personalNumber = personalNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getCityFrom() {
        return cityFrom;
    }

    public void setCityFrom(String cityFrom) {
        this.cityFrom = cityFrom;
    }

    public String getCityTo() {
        return cityTo;
    }

    public void setCityTo(String cityTo) {
        this.cityTo = cityTo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CargoDTO)) return false;

        CargoDTO cargoDTO = (CargoDTO) o;

        if (personalNumber != null ? !personalNumber.equals(cargoDTO.personalNumber) : cargoDTO.personalNumber != null)
            return false;
        if (!name.equals(cargoDTO.name)) return false;
        if (!weight.equals(cargoDTO.weight)) return false;
        if (!cityFrom.equals(cargoDTO.cityFrom)) return false;
        if (!cityTo.equals(cargoDTO.cityTo)) return false;
        return status != null ? status.equals(cargoDTO.status) : cargoDTO.status == null;
    }

    @Override
    public int hashCode() {
        int result = personalNumber != null ? personalNumber.hashCode() : 0;
        result = 31 * result + name.hashCode();
        result = 31 * result + weight.hashCode();
        result = 31 * result + cityFrom.hashCode();
        result = 31 * result + cityTo.hashCode();
        result = 31 * result + (status != null ? status.hashCode() : 0);
        return result;
    }
}

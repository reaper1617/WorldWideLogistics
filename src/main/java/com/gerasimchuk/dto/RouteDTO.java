package com.gerasimchuk.dto;


/** Route Data Transfer Object
 * @author Reaper
 * @version 1.0
 */

public class RouteDTO {
    private String id;
    private String cityFrom;
    private String cityTo;
    private String distance;

    public RouteDTO() {
    }

    public RouteDTO(String id, String cityFrom, String cityTo, String distance) {
        this.id = id;
        this.cityFrom = cityFrom;
        this.cityTo = cityTo;
        this.distance = distance;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }
}

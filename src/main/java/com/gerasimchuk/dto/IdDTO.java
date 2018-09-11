package com.gerasimchuk.dto;

/** Id Data Transfer Object
 * @author Reaper
 * @version 1.0
 */

public class IdDTO {
    private String id;

    public IdDTO() {
    }

    public IdDTO(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof IdDTO)) return false;

        IdDTO idDTO = (IdDTO) o;

        return id != null ? id.equals(idDTO.id) : idDTO.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}

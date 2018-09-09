package com.gerasimchuk.dto;

/** Admin Data Transfer Object
 * @author Reaper
 * @version 1.0
 */

public class AdminDTO {
    private String id;
    private String firstName;
    private String middleName;
    private String lastName;
    private String password;
    private String personalNumber;

    public AdminDTO() {
    }

    public AdminDTO(String id, String firstName, String middleName, String lastName, String password, String personalNumber) {
        this.id = id;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.password = password;
        this.personalNumber = personalNumber;
    }

    public String getPersonalNumber() {
        return personalNumber;
    }

    public void setPersonalNumber(String personalNumber) {
        this.personalNumber = personalNumber;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AdminDTO)) return false;

        AdminDTO adminDTO = (AdminDTO) o;

        if (!firstName.equals(adminDTO.firstName)) return false;
        if (!middleName.equals(adminDTO.middleName)) return false;
        return lastName.equals(adminDTO.lastName);
    }

    @Override
    public int hashCode() {
        int result = firstName.hashCode();
        result = 31 * result + middleName.hashCode();
        result = 31 * result + lastName.hashCode();
        return result;
    }
}

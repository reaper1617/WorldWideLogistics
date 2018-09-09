package com.gerasimchuk.dto;

/** Manager Data Transfer Object
 * @author Reaper
 * @version 1.0
 */

public class ManagerDTO {
    private String id;
    private String firstName;
    private String middleName;
    private String lastName;
    private String personalNumber;
    private String password;

    public ManagerDTO() {
    }

    public ManagerDTO(String id, String firstName, String middleName, String lastName, String personalNumber, String password) {
        this.id = id;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.personalNumber = personalNumber;
        this.password = password;
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
        if (!(o instanceof ManagerDTO)) return false;

        ManagerDTO that = (ManagerDTO) o;

        if (!firstName.equals(that.firstName)) return false;
        if (!middleName.equals(that.middleName)) return false;
        return lastName.equals(that.lastName);
    }

    @Override
    public int hashCode() {
        int result = firstName.hashCode();
        result = 31 * result + middleName.hashCode();
        result = 31 * result + lastName.hashCode();
        return result;
    }
}

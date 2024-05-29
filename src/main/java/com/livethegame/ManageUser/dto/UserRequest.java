package com.livethegame.ManageUser.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.time.LocalDate;
import java.util.Date;

@ApiModel()
public class UserRequest {
    @ApiModelProperty(name = "id", required = true,example = "", value = "")
    private Long id;
    @ApiModelProperty(name = "name", required = true,example = "Juan", value = "")
    private String name;
    @ApiModelProperty(name = "lastname", required = true,example = "Perez", value = "")
    private String lastname;
    @ApiModelProperty(name = "birthdate", required = true,example = "", value = "")
    private LocalDate birthdate;
    @ApiModelProperty(name = "phone", required = false,example = "", value = "")
    private int phone;
    @ApiModelProperty(name = "identification_number", required = false,example = "", value = "")
    private int identification_number;
    @ApiModelProperty(name = "identification_type", required = false,example = "", value = "")
    private String identification_type;
    @ApiModelProperty(name = "country", required = true,example = "Colombia", value = "Colombia")
    private String country;
    @ApiModelProperty(name = "currency", required = true,example = "COP", value = "COP")
    private String currency;


    public UserRequest(){
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public int getIdentification_number() {
        return identification_number;
    }

    public void setIdentification_number(int identification_number) {
        this.identification_number = identification_number;
    }

    public String getIdentification_type() {
        return identification_type;
    }

    public void setIdentification_type(String identification_type) {
        this.identification_type = identification_type;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

}

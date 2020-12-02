package com.example.afpoo.dto;

import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

public class ClientDTO {
    @NotBlank(message = "Name is required!")
    @Length(min=2, max=60, message="Name must have at least 3 characters and 60 maximum!")
    private String name;
    @NotBlank(message = "Address is required!")
    @Length(min=5,max=180, message = "Address must have at least 5 characters and 180 maximum!")
    private String address;
    @NotBlank(message = "CPF is required!")
    @Length(min=14,max=14, message = "CPF must have 14 characters (XXX.XXX.XXX-XX)")
    private String CPF;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    
    public String getCPF() {
        return CPF;
    }

    public void setCpf(String cpf) {
        this.CPF = cpf;
    }

}

package com.example.afpoo.dto;

public class ClientDTO {
    private String name;
    private String address;
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

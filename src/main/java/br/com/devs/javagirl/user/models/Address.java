package br.com.devs.javagirl.user.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Data
@Builder
@Embeddable
@AllArgsConstructor
@NoArgsConstructor
public class Address {

    @JsonProperty(value = "logradouro")
    @Column(name = "logradouro")
    private String street;

    @Column(name = "numero")
    private String number;

    @JsonProperty(value = "bairro")
    @Column(name = "bairro")
    private String district;

    @JsonProperty(value = "localidade")
    @Column(name = "localidade")
    private String city;

}
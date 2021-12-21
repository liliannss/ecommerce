package br.com.ecommerce.user.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Getter
@Setter
@Builder
@ToString
@Embeddable
@AllArgsConstructor
@NoArgsConstructor
public class Address {

    @JsonProperty(value = "logradouro")
    @Column(name = "logradouro")
    private String street;

    @JsonProperty(value = "numero")
    @Column(name = "numero")
    private String number;

    @JsonProperty(value = "bairro")
    @Column(name = "bairro")
    private String district;

    @JsonProperty(value = "localidade")
    @Column(name = "localidade")
    private String city;
}
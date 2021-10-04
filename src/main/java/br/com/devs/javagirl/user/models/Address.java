package br.com.devs.javagirl.user.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@Data
@Builder
@Embeddable
@AllArgsConstructor
@NoArgsConstructor
public class Address {

    private String street;
    private String number;
    private String district;
    private String city;
}

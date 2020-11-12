package com.radek.customerservice.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.istack.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Data
/*@Table(name = "customers")*/
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotNull
    @JsonProperty("name")
    private String name;
    @NotNull
    @JsonProperty("last name")
    private String lastName;
    @NotNull
    @JsonProperty("email")
    private String email;
    @NotNull
    @JsonProperty("city")
    private String city;
    @NotNull
    @JsonProperty("address")
    private String address;
}

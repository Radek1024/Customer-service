package com.radek.customerservice.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Data
/*@Table(name = "customers")*/
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull(message = "please fill in name")
    @JsonProperty("name")
    private String name;

    @NotNull(message = "please fill in last name")
    @JsonProperty("last name")
    private String lastName;

    @NotNull(message = "please fill in email address")
    @JsonProperty("email")
    private String email;

    @NotNull(message = "please fill in city")
    @JsonProperty("city")
    private String city;

    @NotNull(message = "please fill in address")
    @JsonProperty("address")
    private String address;
}
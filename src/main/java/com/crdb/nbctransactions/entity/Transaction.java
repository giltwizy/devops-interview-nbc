package com.crdb.nbctransactions.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@Entity
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    public String service;
    public String name;

    @NotNull(message = "Amount can not be NULL")
    @NotEmpty(message = "Amount can not be Empty")
    public String amount;

    @NotNull(message = "Account can not be NULL")
    @NotEmpty(message = "Account can not be Empty")
    public String account;

    @NotNull(message = "reference can not be NULL")
    @NotEmpty(message = "reference can not be Empty")
    public String reference;
}

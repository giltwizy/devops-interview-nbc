package com.nbc.nbctransactions.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class TransactionRequest {
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

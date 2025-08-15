package com.crdb.nbctransactions.dto;

import lombok.Data;

@Data
public class TransactionRequest {
    public String service;
    public String name;
    public String amount;
    public String account;
    public String reference;
}

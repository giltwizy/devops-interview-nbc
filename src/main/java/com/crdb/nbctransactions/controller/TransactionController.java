package com.crdb.nbctransactions.controller;

import com.crdb.nbctransactions.dto.TransactionRequest;
import com.crdb.nbctransactions.dto.TransactionResponse;
import com.crdb.nbctransactions.service.TransactionService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("nbc-bank/devops/v1")
public class TransactionController {

    private final TransactionService transactionService;


    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping("transactions")
    public TransactionResponse createTransaction(@RequestBody TransactionRequest transactionRequest) {
        return transactionService.createTransaction(transactionRequest);
    }
}

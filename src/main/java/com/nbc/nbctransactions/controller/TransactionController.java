package com.nbc.nbctransactions.controller;

import com.nbc.nbctransactions.dto.TransactionRequest;
import com.nbc.nbctransactions.dto.TransactionResponse;
import com.nbc.nbctransactions.service.TransactionService;
import jakarta.validation.Valid;
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
    public TransactionResponse createTransaction(@Valid @RequestBody TransactionRequest transactionRequest) {
        return transactionService.createTransaction(transactionRequest);
    }
}

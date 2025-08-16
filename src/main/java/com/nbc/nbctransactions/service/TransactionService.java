package com.nbc.nbctransactions.service;

import com.nbc.nbctransactions.dto.TransactionRequest;
import com.nbc.nbctransactions.dto.TransactionResponse;
import com.nbc.nbctransactions.entity.Transaction;
import com.nbc.nbctransactions.repository.TransactionRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;

    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public TransactionResponse createTransaction(TransactionRequest transactionRequest) {
        Optional<Transaction> transactionOptional = transactionRepository.findByReference(transactionRequest.reference);

        if (transactionOptional.isPresent()) {
            TransactionResponse transactionResponse = new TransactionResponse();
            transactionResponse.statusCode = "601";
            transactionResponse.message = "Duplicate reference received, try with one";
            return transactionResponse;
        }

        Transaction transaction = new Transaction();
        transaction.service = transactionRequest.service;
        transaction.name = transactionRequest.name;
        transaction.amount = transactionRequest.amount;
        transaction.account = transactionRequest.account;
        transaction.reference = transactionRequest.reference;

        transactionRepository.save(transaction);

        TransactionResponse transactionResponse = new TransactionResponse();
        transactionResponse.statusCode = "600";
        transactionResponse.message = "Transaction has been processed";
        return transactionResponse;
    }
}

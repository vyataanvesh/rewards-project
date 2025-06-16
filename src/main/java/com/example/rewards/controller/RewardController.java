package com.example.rewards.controller;

import com.example.rewards.model.RewardResponse;
import com.example.rewards.model.Transaction;
import com.example.rewards.repository.TransactionRepository;
import com.example.rewards.service.RewardService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class RewardController {
    private final RewardService service;
    private final TransactionRepository transactionRepository;

    public RewardController(RewardService service, TransactionRepository transactionRepository) {
        this.service = service;
        this.transactionRepository = transactionRepository;
    }

    @Operation(summary = "Get rewards for all customers")
    @GetMapping("/rewards")
    public List<RewardResponse> getAllRewards() {
        return service.calculateRewardsForAllCustomers();
    }

    @Operation(summary = "Get rewards for a customer")
    @GetMapping("/rewards/{customerId}")
    public RewardResponse getRewardsForCustomer(@PathVariable Long customerId) {
        var tx = transactionRepository.findByCustomerId(customerId);
        return service.calculateForCustomer(customerId, tx);
    }

    @Operation(summary = "Get all transactions")
    @GetMapping("/transactions")
    public List<Transaction> getTransactions() {
        return transactionRepository.findAll();
    }

    @Operation(summary = "Add a transaction")
    @PostMapping("/transactions")
    public Transaction addTransaction(@RequestBody Transaction t) {
        return transactionRepository.save(t);
    }
}

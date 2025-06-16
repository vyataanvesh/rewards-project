package com.example.rewards.service;

import com.example.rewards.model.RewardResponse;
import com.example.rewards.model.Transaction;
import com.example.rewards.repository.TransactionRepository;
import com.example.rewards.util.RewardCalculator;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class RewardService {
    private final TransactionRepository repository;

    public RewardService(TransactionRepository repository) {
        this.repository = repository;
    }

    public List<RewardResponse> calculateRewardsForAllCustomers() {
        List<Transaction> all = repository.findAll();
        Map<Long, List<Transaction>> byCustomer = all.stream().collect(Collectors.groupingBy(Transaction::getCustomerId));
        List<RewardResponse> responses = new ArrayList<>();
        for (var entry : byCustomer.entrySet()) {
            responses.add(calculateForCustomer(entry.getKey(), entry.getValue()));
        }
        return responses;
    }

    public RewardResponse calculateForCustomer(Long customerId, List<Transaction> transactions) {
        Map<String, Integer> monthly = new HashMap<>();
        int total = 0;
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM");
        for (var t : transactions) {
            String month = t.getTransactionDate().format(fmt);
            int pts = RewardCalculator.calculatePoints(t.getAmount());
            monthly.put(month, monthly.getOrDefault(month, 0) + pts);
            total += pts;
        }
        return new RewardResponse(customerId, monthly, total);
    }
}

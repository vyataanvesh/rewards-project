package com.example.rewards.service;

import com.example.rewards.model.Transaction;
import com.example.rewards.repository.TransactionRepository;
import com.example.rewards.util.RewardCalculator;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RewardServiceTest {

    private final TransactionRepository repo = Mockito.mock(TransactionRepository.class);
    private final RewardService service = new RewardService(repo);

    @Test
    void testCalculatePoints() {
        assertEquals(90, RewardCalculator.calculatePoints(120.0));
        assertEquals(50, RewardCalculator.calculatePoints(100.0));
        assertEquals(0, RewardCalculator.calculatePoints(50.0));
    }

    @Test
    void testCalculateRewardsForCustomer() {
        var transactions = List.of(
          new Transaction(1L, 1L, 120.0, LocalDate.of(2025,1,10)),
          new Transaction(2L, 1L, 75.0, LocalDate.of(2025,1,15))
        );
        Mockito.when(repo.findByCustomerId(1L)).thenReturn(transactions);
        var response = service.calculateForCustomer(1L, transactions);
        assertEquals(140, response.getTotalPoints());
        assertEquals(140, response.getMonthlyPoints().get("2025-01"));
    }
}

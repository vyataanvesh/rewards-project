package com.example.rewards.controller;

import com.example.rewards.model.RewardResponse;
import com.example.rewards.model.Transaction;
import com.example.rewards.service.RewardService;
import com.example.rewards.repository.TransactionRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.beans.factory.annotation.Autowired;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(RewardController.class)
class RewardControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private RewardService service;

    @MockBean
    private TransactionRepository repo;

    @Test
    void testGetAllRewards() throws Exception {
        Mockito.when(service.calculateRewardsForAllCustomers())
            .thenReturn(List.of(new RewardResponse(1L, Map.of("2025-01", 90), 90)));
        mvc.perform(get("/api/rewards"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$[0].customerId").value(1));
    }

    @Test
    void testAddTransaction() throws Exception {
        var tx = new Transaction(1L,1L,120.0,LocalDate.of(2025,1,10));
        Mockito.when(repo.save(Mockito.any(Transaction.class))).thenReturn(tx);
        mvc.perform(post("/api/transactions")
            .contentType("application/json")
            .content("{"customerId":1,"amount":120.0,"transactionDate":"2025-01-10"}"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.amount").value(120.0));
    }
}

package com.example.rewards.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Map;

@Data
@AllArgsConstructor
public class RewardResponse {
    private Long customerId;
    private Map<String, Integer> monthlyPoints;
    private Integer totalPoints;
}

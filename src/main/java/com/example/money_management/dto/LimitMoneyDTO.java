package com.example.money_management.dto;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class LimitMoneyDTO {
    private int year;
    private int month;
    private String id;
    private int limit_money;
}

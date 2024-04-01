package com.example.money_management.entity;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@ToString
@Getter
@Setter
public class LimitMoney {
    @EmbeddedId
    private LimitMoneyId id;
    private int limit_money;
}

package com.example.money_management.entity;

import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;

@Embeddable
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class LimitMoneyId implements Serializable {
    private int year;
    private int month;
    private String id;
}

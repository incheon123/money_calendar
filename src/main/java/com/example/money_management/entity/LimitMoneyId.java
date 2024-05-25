package com.example.money_management.entity;

import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;

import java.io.Serializable;

import com.example.money_management.enumType.RoomType;

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

    private Long rid;
}

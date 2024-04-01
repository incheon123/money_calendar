package com.example.money_management.entity;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import lombok.*;

import java.io.Serializable;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@ToString
@Getter
@Setter
public class History extends TimeLog implements Serializable {

    @EmbeddedId
    private HistoryId historyId;

    private int money;
    private String content;

    private String type;
}

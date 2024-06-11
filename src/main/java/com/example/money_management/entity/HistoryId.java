package com.example.money_management.entity;

import jakarta.persistence.*;
import lombok.*;
import java.io.Serializable;


@Embeddable
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class HistoryId implements Serializable {
    private String id;
    private int year;
    private int month;
    private int date;
    private String rid;
    private Long content_no;
}

package com.example.money_management.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.LastModifiedDate;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;


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

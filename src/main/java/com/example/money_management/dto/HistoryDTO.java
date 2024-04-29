package com.example.money_management.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class HistoryDTO {

    private String id;
    private int year;
    private int month;
    private int date;
    private int content_no;
    private String limit_money;
    private int money;
    private String content;
    private String type;
    private long room_id;
    private String roomType;
}

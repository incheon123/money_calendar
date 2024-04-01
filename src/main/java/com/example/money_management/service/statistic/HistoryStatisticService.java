package com.example.money_management.service.statistic;

import com.example.money_management.entity.History;

import java.util.HashMap;

public interface HistoryStatisticService {
    /*
        주차별, 월별, 년별 함수
     */

    Long getTotalMonthIncome(int year, int month);
    Long getTotalMonthOutcome(int year, int month);
    void getTotalWeekIncome();
    void getTotalWeekOutcome();
    HashMap<Long, Long> getTotalYearIncome(int year);
    HashMap<Long, Long> getTotalYearOutcome(int year);
}

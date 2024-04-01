package com.example.money_management.service.statistic;

import com.example.money_management.entity.History;
import com.example.money_management.repository.HistoryStatisticRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class HistoryStatisticServiceImpl implements HistoryStatisticService{

    @Autowired
    HistoryStatisticRepository repository;

    @Override
    public Long getTotalMonthIncome(int year, int month) {
        Object[] obj = repository.calculateSpecifiedMonthTotalIncome(year, month);

        for(Object o : obj){
            System.out.println(o);
            return (Long)o;
        }

        return null;
    }

    @Override
    public Long getTotalMonthOutcome(int year, int month) {
        Object[] obj = repository.calculateSpecifiedMonthTotalOutcome(year, month);

        for(Object o : obj){
            System.out.println(o);
            return (Long)o;
        }

        return null;
    }

    @Override
    public void getTotalWeekIncome() {

    }

    @Override
    public void getTotalWeekOutcome() {

    }

    @Override
    public HashMap<Long, Long> getTotalYearIncome(int year) {
        List<Long[]> result = repository.calculateSpecifiedYearTotalIncome(year);

        HashMap<Long, Long> map = new HashMap<>();

        for(Long[] l : result){
            map.put(l[0], l[1]);
        }
        System.out.println(map.keySet());


        return map;
    }

    @Override
    public HashMap<Long, Long> getTotalYearOutcome(int year) {
        List<Long[]> result = repository.calculateSpecifiedYearTotalOutcome(year);

        HashMap<Long, Long> map = new HashMap<>();

        for(Long[] l : result){
            map.put(l[0], l[1]);
        }
        System.out.println(map.keySet());


        return map;
    }
}

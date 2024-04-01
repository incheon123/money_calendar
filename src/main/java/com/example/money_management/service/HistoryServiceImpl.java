package com.example.money_management.service;

import com.example.money_management.dto.HistoryDTO;
import com.example.money_management.dto.LimitMoneyDTO;
import com.example.money_management.entity.History;
import com.example.money_management.entity.HistoryId;
import com.example.money_management.entity.LimitMoney;
import com.example.money_management.repository.HistoryRepository;
import com.example.money_management.repository.LimitMoneyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class HistoryServiceImpl implements HistoryService{

    @Autowired
    private HistoryRepository historyRepository;

    @Autowired
    private LimitMoneyRepository limitMoneyRepository;

    @Override
    public Integer getLimitMoney(int y, int m, String id) {
        Integer result = historyRepository.getLimitMoney(y,m,id);
        return result;
    }

    @Override
    public void saveHistory(HistoryDTO h) {
        History history = dtoToEntity(h);
        historyRepository.save(history);
    }

    @Override
    public void deleteHistory(HistoryDTO history) {

        HistoryId id = HistoryId.builder()
                .id(history.getId())
                .year(history.getYear())
                .month(history.getMonth())
                .date(history.getDate())
                .content_no(history.getContent_no())
                .build();

        historyRepository.deleteById(id);
    }

    @Override
    public Boolean saveLimitMoney(LimitMoneyDTO limitMoneyDTO) {
        LimitMoney lm = limitMoneyDtoToEntity(limitMoneyDTO);

        try {
            limitMoneyRepository.save(lm);
            return true;
        }catch (Exception e){
            e.printStackTrace();
        }

        return false;
    }


    @Override
    public List<HistoryDTO> getHistory(String id, int year, int month) {
        History[] result = historyRepository.getHistory(id, year, month);

        List<HistoryDTO> list = new ArrayList<>();
        for(int i = 0; i  < result.length; i++){
            list.add(entityToDTO(result[i]));
        }

        return list;
    }

    @Override
    public Integer getTotalCount(String id, int y, int m, int d) {

        return historyRepository.getCount(id, y, m, d);
    }

}

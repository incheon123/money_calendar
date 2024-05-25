package com.example.money_management.service;

import com.example.money_management.dto.HistoryDTO;
import com.example.money_management.dto.LimitMoneyDTO;
import com.example.money_management.entity.History;
import com.example.money_management.entity.HistoryId;
import com.example.money_management.entity.LimitMoney;
import com.example.money_management.repository.HistoryRepository;
import com.example.money_management.repository.LimitMoneyRepository;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Log4j2
@Service
public class HistoryServiceImpl implements HistoryService{

    @Autowired
    private HistoryRepository historyRepository;

    @Autowired
    private LimitMoneyRepository limitMoneyRepository;

    @Override
    public Integer getLimitMoney(int y, int m, Long rid) {
        Integer result = historyRepository.getLimitMoney(y,m,rid);
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
        log.warn("saveLimitMoney.........");
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
    public List<HistoryDTO> getHistory(Long rid, int year, int month) {
        History[] result = historyRepository.getHistory(rid, year, month);

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

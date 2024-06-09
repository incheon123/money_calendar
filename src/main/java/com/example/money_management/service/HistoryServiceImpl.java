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
    public HistoryDTO saveHistory(HistoryDTO h) {

        History history = dtoToEntity(h, getContentNum(h.getRid(), h.getYear(), h.getMonth(), h.getDate()) + 1);
        historyRepository.save(history);


        return entityToDTO(history);
    }

    public HistoryDTO updateHistory(HistoryDTO h){
        History history = dtoToEntity(h, h.getContent_no());
        log.info("updateHistory -> [{}]",history);
        History resulut_history = historyRepository.save(history);

        return entityToDTO(resulut_history);
    }

    @Override
    public void deleteHistory(HistoryDTO history) {

        HistoryId id = getHistoryId(history, history.getContent_no());

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
            log.error("can't save");
            e.printStackTrace();
        }

        return false;
    }


    @Override
    public List<HistoryDTO> getHistory(String rid, int year, int month) {
        History[] result = historyRepository.getHistory(rid, year, month);

        List<HistoryDTO> list = new ArrayList<>();
        for(int i = 0; i  < result.length; i++){
            list.add(entityToDTO(result[i]));
        }

        return list;
    }

   @Override
   public Long getContentNum(String rid, int y, int m, int d) {
        
        if(historyRepository.checkHistoryExists(rid, y, m, d) == 0){
            return 1L;
        }

        return historyRepository.getCount(rid, y, m, d);
   }

}

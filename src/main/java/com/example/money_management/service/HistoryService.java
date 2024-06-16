package com.example.money_management.service;

import com.example.money_management.dto.HistoryDTO;
import com.example.money_management.dto.LimitMoneyDTO;
import com.example.money_management.entity.History;
import com.example.money_management.entity.HistoryId;
import com.example.money_management.entity.LimitMoney;
import com.example.money_management.entity.LimitMoneyId;

import java.util.List;

public interface HistoryService {

    Integer getLimitMoney(int y, int m, Long rid);
    HistoryDTO saveHistory(HistoryDTO history);
    HistoryDTO updateHistory(HistoryDTO history);
    void deleteHistory(HistoryDTO history);
    Boolean saveLimitMoney(LimitMoneyDTO limitMoneyDTO);
    List<HistoryDTO> getHistory(String rid, int year, int month);

//    Integer getTotalCount(String id, int y, int m, int d);

    default History dtoToEntity(HistoryDTO dto, Long content_no){

        History history = History.builder()
                .historyId(getHistoryId(dto, content_no))
                .type(dto.getType())
                .money(dto.getMoney())
                .content(dto.getContent())
                .build();

        return history;
    }
    
    default HistoryId getHistoryId(HistoryDTO dto, Long content_no)
    {
        HistoryId hid = HistoryId.builder()
                .id(dto.getId())
                .year(dto.getYear())
                .month(dto.getMonth())
                .date(dto.getDate())
                .rid(dto.getRid())
                .content_no(content_no)
                .build();
        
        return hid;
    }

    default HistoryDTO entityToDTO(History history){

        HistoryDTO dto = HistoryDTO.builder()
                .id(history.getHistoryId().getId())
                .year(history.getHistoryId().getYear())
                .month(history.getHistoryId().getMonth())
                .date(history.getHistoryId().getDate())
                .type(history.getType())
                .rid(history.getHistoryId().getRid())
                .money(history.getMoney())
                .content_no(history.getHistoryId().getContent_no())
                .content(history.getContent())
                .build();

        return dto;
    }
    default LimitMoney limitMoneyDtoToEntity(LimitMoneyDTO dto){
        LimitMoneyId id = LimitMoneyId.builder()
                .year(dto.getYear())
                .month(dto.getMonth())
                .rid(dto.getRid())
                .member(dto.getMember())
                .build();
        LimitMoney lm = LimitMoney.builder()
                .id(id)
                .limit_money(dto.getLimit_money())
                .build();

        return lm;
    }

    public Long getContentNum(String rid, int y, int m, int d);
}

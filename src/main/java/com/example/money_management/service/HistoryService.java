package com.example.money_management.service;

import com.example.money_management.dto.HistoryDTO;
import com.example.money_management.dto.LimitMoneyDTO;
import com.example.money_management.entity.History;
import com.example.money_management.entity.HistoryId;
import com.example.money_management.entity.LimitMoney;
import com.example.money_management.entity.LimitMoneyId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HistoryService {

    Integer getLimitMoney(int y, int m, Long rid);
    void saveHistory(HistoryDTO history);
    void deleteHistory(HistoryDTO history);
    Boolean saveLimitMoney(LimitMoneyDTO limitMoneyDTO);
    List<HistoryDTO> getHistory(Long rid, int year, int month);

    Integer getTotalCount(String id, int y, int m, int d);

    default History dtoToEntity(HistoryDTO dto){

        HistoryId hid = HistoryId.builder()
                .id(dto.getId())
                .year(dto.getYear())
                .month(dto.getMonth())
                .date(dto.getDate())
                .content_no(dto.getContent_no())
                .build();

        History history = History.builder()
                .historyId(hid)
                .type(dto.getType())
                .money(dto.getMoney())
                .content(dto.getContent())
                .build();

        return history;
    }

    default HistoryDTO entityToDTO(History history){

        HistoryDTO dto = HistoryDTO.builder()
                .id(history.getHistoryId().getId())
                .year(history.getHistoryId().getYear())
                .month(history.getHistoryId().getMonth())
                .date(history.getHistoryId().getDate())
                .content_no(history.getHistoryId().getContent_no())
                .type(history.getType())
                .money(history.getMoney())
                .content(history.getContent())
                .build();

        return dto;
    }
    default LimitMoney limitMoneyDtoToEntity(LimitMoneyDTO dto){
        LimitMoneyId id = LimitMoneyId.builder()
                .year(dto.getYear())
                .month(dto.getMonth())
                .rid(dto.getRid())
                .build();
        LimitMoney lm = LimitMoney.builder()
                .id(id)
                .limit_money(dto.getLimit_money())
                .build();

        return lm;
    }
}

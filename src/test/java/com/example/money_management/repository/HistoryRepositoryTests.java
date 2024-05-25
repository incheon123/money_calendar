package com.example.money_management.repository;

import com.example.money_management.entity.History;
import com.example.money_management.entity.HistoryId;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

@SpringBootTest
public class HistoryRepositoryTests {

    @Autowired
    private HistoryRepository repository;

    @Autowired
    private HistoryStatisticRepository historyStatisticRepository;

    @Test
    public void insertTests(){
        HistoryId id = HistoryId.builder()
                .id("opportuntiy13")
                .year(2024)
                .month(2)
                .date(6)
                .build();

        History history = History.builder()
                .historyId(id)
                .content("20000원")
                .type("outcome")
                .build();

        repository.save(history);

    }

    @Test
    public void getHistoryTest(){
        Calendar c = Calendar.getInstance();
        History[] result = repository.getHistory(123L, 2024, 2);
        System.out.println("============================");
        System.out.println(result[0].getContent());
    }

    @Test
    public void testDate(){
        Calendar c = Calendar.getInstance();
        System.out.println(c.get(Calendar.MONTH)+1 + " " + c.get(Calendar.DATE));
    }

    @Test
    public void insertVirtualHistorys(){
        for(int i = 0; i < 300; i++){

            HistoryId id = HistoryId.builder()
                    .year(2024)
                    .month( ((int)(Math.random() * 12)) + 1 )
                    .date( ((int)(Math.random() * 28)) + 1)
                    .id("opportunity13")
                    .build();

            String type = ((int) (Math.random() * 2)) + 1  == 2 ? "income" : "outcome";

            History h = History.builder()
                    .historyId(id)
                    .content(i+"")
                    .type(type)
                    .money(((int) (Math.random() * 10000)) + 100)
                    .build();
            repository.save(h);
        }
    }

    @Test
    public void testYear(){
        List<Long[]> result = historyStatisticRepository.calculateSpecifiedYearTotalIncome(2024);

        HashMap<Long, Long> map = new HashMap<>();

        for(Long[] l : result){
            map.put(l[0], l[1]);
        }

        Set set = map.keySet();
        Iterator iterator = set.iterator();

        while(iterator.hasNext()){
            Long key = (Long)iterator.next();
            System.out.println("key : " + key);
        }
    }

    @Test
    public void testMoney(){
        System.out.println(repository.getLimitMoney(2024, 3, 123L));
    }
}

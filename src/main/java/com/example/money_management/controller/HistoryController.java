package com.example.money_management.controller;

import com.example.money_management.dto.HistoryDTO;
import com.example.money_management.service.HistoryService;
import com.example.money_management.service.statistic.HistoryStatisticService;
import jakarta.servlet.http.HttpSession;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/money_management")
@Log4j2
public class HistoryController {


    @Autowired
    private HistoryService historyService;

    @Autowired
    private HistoryStatisticService historyStatisticService;

    @Autowired
    private HttpSession httpSession;


    @CrossOrigin(origins="*")
    @PostMapping("/get/historys")
    public List<HistoryDTO> getHistorys(@RequestBody HistoryDTO dto){
        System.out.println("=========================");
        System.out.println("getHistorys.....................");

        String id = (String) httpSession.getAttribute("member");
        System.out.println(id);

        List<HistoryDTO> result = historyService.getHistory(id, dto.getYear(), dto.getMonth());
//        List<HistoryDTO> result = historyService.getHistory(id, c.get(Calendar.YEAR), c.get(Calendar.MONTH) + 1);

        return result;
    }

    @PostMapping("/update/historys")
    public String[] modify(@RequestBody HistoryDTO dto){
        System.out.println("modify......................");

        String id = (String) httpSession.getAttribute("member");
        dto.setId(id);
        System.out.println(dto);

        historyService.saveHistory(dto);

        return new String[]{"success"};
    }

    @ResponseBody
    @PostMapping("/get/limit_money")
    public Integer[] getLimitMoney(@RequestBody HistoryDTO dto){

        System.out.println(dto);

        Integer result = historyService.getLimitMoney(dto.getYear(), dto.getMonth(), (String)httpSession.getAttribute("member"));

        return new Integer[]{result};
    }

    @ResponseBody
    @PostMapping("/delete/history")
    public String[] deleteHistroyById(@RequestBody HistoryDTO dto){
        dto.setId( (String) httpSession.getAttribute("member"));

        log.info("deleteHistoryById....................");
        log.info(dto);

        historyService.deleteHistory(dto);

        return new String[]{"success"};
    }


    /*
    * 통계 부분
    * */

    @ResponseBody
    @PostMapping("/get/monthTotalIncome")
    public String[] getMonthTotalIncome(@RequestBody HashMap<String, Integer> date){
        Integer y = date.get("year");
        Integer m = date.get("month");

        Long result = historyStatisticService.getTotalMonthIncome(y, m);
        System.out.println("result : " + result);
        return new String[]{result + ""};
    }

    @ResponseBody
    @PostMapping("/get/monthTotalIncomeAndOutcome")
    public String[] getMonthTotalIncomeAndOutcome(@RequestBody HashMap<String, Integer> date){
        Integer y = date.get("year");
        Integer m = date.get("month");

        Long in = historyStatisticService.getTotalMonthIncome(y, m);
        Long out = historyStatisticService.getTotalMonthOutcome(y, m);

        return new String[]{(in + ""), (out + "")};
    }

    @ResponseBody
    @PostMapping("/get/yearTotalIncomeAndOutcome")
    public HashMap<String, HashMap<Long, Long>> getYearTotalIncomeAndOutcome(@RequestBody HashMap<String, Integer> date){
        Integer y = date.get("year");
        System.out.println(y);
        HashMap<Long, Long> income_map = historyStatisticService.getTotalYearIncome(y);
        HashMap<Long, Long> outcome_map = historyStatisticService.getTotalYearOutcome(y);

        HashMap<String, HashMap<Long, Long>> answer = new HashMap<>();
        answer.put("income", income_map);
        answer.put("outcome", outcome_map);

        return answer;
    }
}

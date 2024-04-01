package com.example.money_management.repository;

import com.example.money_management.entity.History;
import com.example.money_management.entity.HistoryId;
import jakarta.persistence.ColumnResult;
import jakarta.persistence.ConstructorResult;
import jakarta.persistence.SqlResultSetMapping;
import jdk.dynalink.beans.StaticClass;
import org.hibernate.annotations.NamedNativeQuery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HistoryStatisticRepository extends JpaRepository<History, HistoryId> {

    @Query("select sum(h.money) " +
            "from History h " +
            "where h.type = 'income' and h.historyId.month = :month and h.historyId.year = :year " +
            "group by h.historyId.year, h.historyId.month ")
    Object[] calculateSpecifiedMonthTotalIncome(@Param("year") int year, @Param("month") int month);


    @Query("select sum(h.money) " +
            "from History h " +
            "where h.type = 'outcome' and h.historyId.month = :month and h.historyId.year = :year " +
            "group by h.historyId.year, h.historyId.month ")
    Object[] calculateSpecifiedMonthTotalOutcome(@Param("year") int year, @Param("month") int month);

    @Query("select h.historyId.month, sum(h.money) " +
            "from History h " +
            "where h.historyId.year = :year AND  h.type = 'income' " +
            "group by h.historyId.year, h.historyId.month")
    List<Long[]> calculateSpecifiedYearTotalIncome(@Param("year") int year);

    @Query("select h.historyId.month, sum(h.money) " +
            "from History h " +
            "where h.historyId.year = :year AND  h.type = 'outcome' " +
            "group by h.historyId.year, h.historyId.month")
    List<Long[]> calculateSpecifiedYearTotalOutcome(@Param("year") int year);
}

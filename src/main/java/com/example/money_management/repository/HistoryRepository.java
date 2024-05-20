package com.example.money_management.repository;

import com.example.money_management.entity.History;
import com.example.money_management.entity.HistoryId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface HistoryRepository extends JpaRepository<History, HistoryId> {
    @Query("select max(h.historyId.content_no) " +
            "from History h " +
            "where " +
            "h.historyId.id = :id and " +
            "h.historyId.year = :year and " +
            "h.historyId.month = :month and " +
            "h.historyId.date = :date"
    )
    Integer getCount(@Param("id") String id
            , @Param("year") int year
            , @Param("month") int month
            , @Param("date") int date);

    @Query("SELECT h " +
            "FROM History h " +
            "WHERE h.historyId.rid = :rid     AND " +
            "h.historyId.year     = :year     AND " +
            "h.historyId.month    = :month "
    )
    History[] getHistory(@Param("rid") Long rid, @Param("year") int year, @Param("month") int month);

    @Query("SELECT lm.limit_money " +
            "FROM LimitMoney lm " +
            "WHERE lm.id.id = :id  AND " +
            "lm.id.year      = :year AND " +
            "lm.id.month         = :month ")
    Integer getLimitMoney(@Param("year") int year, @Param("month") int month, @Param("id") String id);
}

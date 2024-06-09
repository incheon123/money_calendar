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
           "h.historyId.rid = :rid and " +
           "h.historyId.year = :year and " +
           "h.historyId.month = :month and " +
           "h.historyId.date = :date"
   )
   Long getCount(@Param("rid") String rid
           , @Param("year") int year
           , @Param("month") int month
           , @Param("date") int date);

    @Query("SELECT h " +
            "FROM History h " +
            "WHERE h.historyId.rid = :rid     AND " +
            "h.historyId.year     = :year     AND " +
            "h.historyId.month    = :month "
    )
    History[] getHistory(@Param("rid") String rid, @Param("year") int year, @Param("month") int month);

    /**
     * 매개변수의 값을 갖고 있는 모든 행을 검색해서 개수를 리턴
     * @param rid
     * @param year
     * @param month
     * @param date
     * @return
     */
    @Query("select count(h)" +
           "from History h " +
           "where " +
           "h.historyId.rid = :rid and " +
           "h.historyId.year = :year and " +
           "h.historyId.month = :month and " +
           "h.historyId.date = :date"
    )
    int checkHistoryExists(
        @Param("rid") String rid
        , @Param("year") int year
        , @Param("month") int month
        , @Param("date") int date
    );

    @Query("SELECT lm.limit_money " +
            "FROM LimitMoney lm " +
            "WHERE lm.id.rid = :rid  AND " +
            "lm.id.year      = :year AND " +
            "lm.id.month         = :month ")
    Integer getLimitMoney(@Param("year") int year, @Param("month") int month, @Param("rid") Long rid);
}

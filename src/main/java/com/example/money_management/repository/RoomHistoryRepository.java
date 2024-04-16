package com.example.money_management.repository;

import com.example.money_management.entity.RoomHistory;
import com.example.money_management.entity.RoomHistoryId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomHistoryRepository extends JpaRepository<RoomHistory, RoomHistoryId> {
}

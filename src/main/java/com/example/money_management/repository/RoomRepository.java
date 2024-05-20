package com.example.money_management.repository;

import com.example.money_management.entity.Room;
import com.example.money_management.enumType.RoomType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RoomRepository extends JpaRepository<Room, Long> {
    @Query("select r from Room r where r.roomType = :type")
    List<Room> getRoomsByType(@Param("type") RoomType type);

}

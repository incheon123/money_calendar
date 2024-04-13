package com.example.money_management.entity;

import java.io.Serializable;
import java.util.Objects;

public class RoomHistoryId implements Serializable {
    private String room;
    private String member;

    public RoomHistoryId(){}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RoomHistoryId that = (RoomHistoryId) o;
        return Objects.equals(room, that.room) && Objects.equals(member, that.member);
    }

    @Override
    public int hashCode() {
        return Objects.hash(room, member);
    }
}

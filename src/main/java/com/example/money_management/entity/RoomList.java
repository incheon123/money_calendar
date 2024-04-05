package com.example.money_management.entity;

import com.example.money_management.dto.Room;

import java.util.LinkedList;

public class RoomList {
    private static final LinkedList<com.example.money_management.dto.Room> roomList = new LinkedList<>();

    public void deleteRoom(){
    }
    public void search(String keywordType, String keyword){
        // roomlist에서 파라미터 값에 부합하는 방을 찾아 반환한다
    }

    public void add(com.example.money_management.dto.Room room){
        roomList.add(room);
    }

    public int getRoomListCount(){
        return roomList.size();
    }

    public LinkedList<com.example.money_management.dto.Room> getRoomList(){
        return roomList;
    }

    //hashMap으로 변경할 것
    public com.example.money_management.dto.Room getRoom(long rid){
        for(Room room : roomList){
            if(room.getId() == rid){
                return room;
            }
        }

        return null;
    }
}

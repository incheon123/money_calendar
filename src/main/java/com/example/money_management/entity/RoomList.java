package com.example.money_management.entity;

import java.util.LinkedList;

public class RoomList {
    private static final LinkedList<Room> roomList = new LinkedList<>();

    public void deleteRoom(){
    }
    public void search(String keywordType, String keyword){
        // roomlist에서 파라미터 값에 부합하는 방을 찾아 반환한다
    }

    public void add(Room room){
        roomList.add(room);
    }

    public int getRoomListCount(){
        return roomList.size();
    }

    public LinkedList<Room> getRoomList(){
        return roomList;
    }

    //hashMap으로 변경할 것
    public Room getRoom(long rid){
        for(Room room : roomList){
            if(room.getRid() == rid){
                return room;
            }
        }

        return null;
    }
}

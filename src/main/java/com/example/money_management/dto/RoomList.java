package com.example.money_management.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;
import java.util.LinkedList;

@Component
@Data
@NoArgsConstructor
@Log4j2
public class RoomList {
    private static final LinkedList<Room> roomList = new LinkedList<>();
    //디비에서 가져와야 됨
    /**
     * < 함수 >
     * 1. 똑같은 사용자는 2개이상 방을 못만든다
     * 2. 검색은 방장이름, 방제목으로 검색한다(enum)
     * 3. 방장은 강퇴, 방 설정 권한이 있고 타유저에게 권한을 위임할 수 있다(enum).
     * 4. 방 인원이 0명이 되면 그 방은 자동으로 삭제
     *
     * 제일 중요한 것은 채팅방은 비휘발성이기 때문에 db에 저장해야한다
     * 이때 일반적인 디비 쓰면 I/O 느리니까 고속 캐시 디비 쓰던가
     */

    public void deleteRoom(){
        //방을 삭제하는 메소드 0명이 되면 자동으로 삭제
        //잠수하는 새끼 방은 삭제
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
            if(room.getId() == rid){
                return room;
            }
        }

        return null;
    }
}

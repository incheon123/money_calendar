package com.example.money_management.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class RoomHistoryId implements Serializable {

    @EqualsAndHashCode.Include
    private Room room;
    @EqualsAndHashCode.Include
    private Member member;

}

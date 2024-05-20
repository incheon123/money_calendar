package com.example.money_management.entity;

import java.time.LocalDateTime;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SourceType;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@IdClass(RoomHistoryId.class)
@ToString(exclude = {"room", "member"})
@Getter
public class RoomHistory{

    @Id
    @ManyToOne
    @JoinColumn(name = "room_id")
    @JsonBackReference
    private Room room;

    @Id
    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    private boolean isCreater;

    @CreationTimestamp(source = SourceType.VM)
    private LocalDateTime joinDate;

    @UpdateTimestamp(source = SourceType.VM)
    private LocalDateTime quitDate;
}

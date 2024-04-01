package com.example.money_management.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;
import lombok.extern.log4j.Log4j2;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Log4j2
@ToString
public class Member extends TimeLog{

    @Id
    private String id;

    private String pw;

    private String email;

    private String gender;
}

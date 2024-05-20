package com.example.money_management.annotation;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@DtoToEntity(entity = SomeEntity.class)
class SomeDto{
    private Long id;
    private String name;
}
package com.example.money_management.annotation;

class SomeEntity{
    private Long id;
    private String name;

    public SomeEntity(){}
    public SomeEntity(Long id, String name) {
        this.id = id;
        this.name = name;
    }
    @Override
    public String toString() {
        return "SomeEntity [id=" + id + ", name=" + name + "]";
    }

    
}
package com.example.money_management.money_interface;

import java.util.concurrent.ThreadLocalRandom;

public interface Generable {

    default public Long generateId(){
        return ThreadLocalRandom.current().nextLong(4, Long.MAX_VALUE);
    }

}

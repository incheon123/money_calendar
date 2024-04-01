package com.example.money_management;

import com.example.money_management.config.filter.SessionFilter;
import com.example.money_management.config.listener.SessionListener;
import jakarta.servlet.http.HttpSessionListener;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class MoneyManagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(MoneyManagementApplication.class, args);
    }

    @Bean
    public HttpSessionListener httpSessionListener(){
        return new SessionListener();
    }

    @Bean
    public SessionFilter sessionFilter(){
        return new SessionFilter();
    }


}

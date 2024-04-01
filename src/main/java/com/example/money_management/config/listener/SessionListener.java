package com.example.money_management.config.listener;

import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;
import lombok.*;
import org.springframework.beans.factory.annotation.Value;

public class SessionListener implements HttpSessionListener {

    @Value("#{new Integer('${server.servlet.session.timeout}')}")
    private Integer sessionTime;

    public void sessionCreated(HttpSessionEvent se) {
        System.out.println("session timeout : " + sessionTime);
        se.getSession().setMaxInactiveInterval(sessionTime);
    }
    public void sessionDestroyed(HttpSessionEvent se) {
        System.out.println("세션 파괴");
    }

}

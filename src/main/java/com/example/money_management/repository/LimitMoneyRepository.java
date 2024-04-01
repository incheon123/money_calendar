package com.example.money_management.repository;

import com.example.money_management.entity.LimitMoney;
import com.example.money_management.entity.LimitMoneyId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LimitMoneyRepository extends JpaRepository<LimitMoney, LimitMoneyId> {

}

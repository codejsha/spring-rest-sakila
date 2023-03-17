package com.example.app.repository;

import com.example.app.model.entity.PaymentEntity;
import com.example.app.repository.custom.CustomPaymentRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends JpaRepository<PaymentEntity, Integer>, CustomPaymentRepository {
}

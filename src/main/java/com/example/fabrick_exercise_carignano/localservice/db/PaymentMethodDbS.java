package com.example.fabrick_exercise_carignano.localservice.db;

import com.example.fabrick_exercise_carignano.repositories.PaymentMethodRepository;
import com.example.fabrick_exercise_carignano.repositories.dao.PaymentMethod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentMethodDbS {

    private static final Logger log = LoggerFactory.getLogger(PaymentMethodDbS.class);
    @Autowired
    PaymentMethodRepository paymentMethodRepository;

    public PaymentMethod getPaymentMethodByCircuit(String circuitType){
        if(!paymentMethodRepository.findByCircuitType(circuitType).isPresent()){
            log.warn("WARNING: Payment method {} not found!", circuitType);
            return null;
        }

        return paymentMethodRepository.findByCircuitType(circuitType).get();
    }
}

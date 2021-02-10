package com.jumillano.jumi.core.model.dao;

import com.jumillano.jumi.core.model.entity.PaymentSchedule;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface IPaymentScheduleDao extends MongoRepository<PaymentSchedule, String> {

    List<PaymentSchedule> findByObservationContaining(String observation);
}

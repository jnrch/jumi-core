package com.jumillano.jumi.core.service;

import com.jumillano.jumi.core.model.dao.IPaymentScheduleDao;
import com.jumillano.jumi.core.model.entity.PaymentSchedule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PaymentScheduleService {

    private IPaymentScheduleDao paymentScheduleDao;

    @Autowired
    public PaymentScheduleService(IPaymentScheduleDao paymentScheduleDao) {
        this.paymentScheduleDao = paymentScheduleDao;
    }

    public List<PaymentSchedule> findAll(String observation) {
        return (observation == null) ? paymentScheduleDao.findAll() : paymentScheduleDao.findByObservationContaining(observation);
    }

    public Optional<PaymentSchedule> findById(String id) {
       return paymentScheduleDao.findById(id);
    }

    public PaymentSchedule savePaymentSchedule(PaymentSchedule paymentSchedule) {
        return paymentScheduleDao.save(paymentSchedule);
    }

    public PaymentSchedule updatePaymentSchedule(String id, PaymentSchedule paymentSchedule) {
        Optional<PaymentSchedule> currentPaymentSchedule = paymentScheduleDao.findById(id);

        paymentSchedule.setId(currentPaymentSchedule.get().getId());
        paymentScheduleDao.save(paymentSchedule);

        return paymentSchedule;
    }

    public void deletePaymentSchedule(String id) {
        paymentScheduleDao.deleteById(id);
    }
}

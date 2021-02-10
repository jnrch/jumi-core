package com.jumillano.jumi.core.controller;

import com.jumillano.jumi.core.model.entity.PaymentSchedule;
import com.jumillano.jumi.core.service.PaymentScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api/paymentSchedules")
@RestController
public class PaymentScheduleController {

    private PaymentScheduleService paymentScheduleService;

    @Autowired
    public PaymentScheduleController(PaymentScheduleService paymentScheduleService) {
        this.paymentScheduleService = paymentScheduleService;
    }

    @GetMapping
    public List<PaymentSchedule> findAll(@RequestParam(required = false) String observation) {
        return paymentScheduleService.findAll(observation);
    }

    @GetMapping("/{id}")
    public Optional<PaymentSchedule> findById(@PathVariable String id) {
        return paymentScheduleService.findById(id);
    }

    @PostMapping
    public PaymentSchedule savePaymentSchedule(@RequestBody PaymentSchedule paymentSchedule) {
        return paymentScheduleService.savePaymentSchedule(paymentSchedule);
    }

    @PutMapping("/{id}")
    public PaymentSchedule updatePaymentSchedule(@PathVariable String id, @RequestBody PaymentSchedule paymentSchedule) {
        return paymentScheduleService.updatePaymentSchedule(id, paymentSchedule);
    }

    @DeleteMapping("/{id}")
    public void deletePaymentSchedule(@PathVariable String id) {
        paymentScheduleService.deletePaymentSchedule(id);
    }
}

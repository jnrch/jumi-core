package com.jumillano.jumi.core.controller;

import com.jumillano.jumi.core.model.entity.Event;
import com.jumillano.jumi.core.model.entity.EventResponse;
import com.jumillano.jumi.core.model.entity.PaymentMethodResponse;
import com.jumillano.jumi.core.model.enums.PaymentMethod;
import com.jumillano.jumi.core.model.enums.Status;
import com.jumillano.jumi.core.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.text.ParseException;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api/events")
@RestController
public class EventsController {

    private EventService eventService;

    @Autowired
    public EventsController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping
    public List<Event> findAll() {
        return eventService.findEventByUserAndRole();
    }

    @GetMapping("/{id}")
    public Optional<Event> findById(@PathVariable String id) {
        return eventService.findById(id);
    }

    @PostMapping
    public ResponseEntity<EventResponse> saveEvent(@RequestParam(value = "file", required = false) MultipartFile[] file,
                                                   @RequestParam("provider") String provider,
                                                   @RequestParam("amount") Double amount,
                                                   @RequestParam(value   = "status", required = false) Status status,
                                                   @RequestParam(value = "observation", required = false) String observation,
                                                   @RequestParam("start") @DateTimeFormat(pattern="yyyy-MM-dd'T'HH:mm:ss.SSS'Z'") Date start,
                                                   @RequestParam("end") @DateTimeFormat(pattern="yyyy-MM-dd'T'HH:mm:ss.SSS'Z'") Date end,
                                                   @RequestParam("payment_method") PaymentMethod paymentMethod,
                                                   @RequestParam(value = "processed", required = false) Boolean processed) {

        return eventService.saveEvent(file, provider, amount, status, observation, start, end, paymentMethod, processed);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EventResponse> updateEvent(@PathVariable String id, @RequestParam(value = "file", required = false) MultipartFile[] file,
                             @RequestParam("provider") String provider,
                             @RequestParam("amount") Double amount,
                             @RequestParam(value = "status", required = false) Status status,
                             @RequestParam(value = "observation", required = false) String observation,
                             @RequestParam("start") @DateTimeFormat(pattern="yyyy-MM-dd'T'HH:mm:ss.SSS'Z'") Date start,
                             @RequestParam("end") @DateTimeFormat(pattern="yyyy-MM-dd'T'HH:mm:ss.SSS'Z'") Date end,
                             @RequestParam(value = "payment_method", required = false) PaymentMethod paymentMethod) {
        return eventService.updateEvent(id, file, provider, amount, status, observation, start, end, paymentMethod);
    }

    @DeleteMapping("/{id}")
    public void deleteEvent(@PathVariable String id) {
        eventService.deleteEvent(id);
    }

    @GetMapping("/totalPerPayment")
    public Collection<PaymentMethodResponse> totalPerPayment() throws ParseException {
        return eventService.groupPaymentByDate();
    }

    @GetMapping("/role")
    public List<Event> findEventByUserAndRole() {
        return eventService.findEventByUserAndRole();
    }

    @GetMapping("/file/{fileName:.+}")
    public ResponseEntity<Resource> getFile(@PathVariable String fileName) {
        return eventService.getFile(fileName);
    }

    @DeleteMapping("/file/{fileName:.+}/event/{id}")
    public Optional<Event> deleteFile(@PathVariable String fileName, @PathVariable String id) {
        return eventService.deleteFile(fileName, id);
    }
}

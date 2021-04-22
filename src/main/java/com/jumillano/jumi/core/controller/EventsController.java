package com.jumillano.jumi.core.controller;

import com.jumillano.jumi.core.model.entity.Event;
import com.jumillano.jumi.core.model.entity.EventResponse;
import com.jumillano.jumi.core.model.enums.PaymentMethod;
import com.jumillano.jumi.core.model.enums.Status;
import com.jumillano.jumi.core.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
        return eventService.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Event> findById(@PathVariable String id) {
        return eventService.findById(id);
    }

    @PostMapping
    public ResponseEntity<EventResponse> saveEvent(@RequestParam("file") MultipartFile[] file,
                                                   @RequestParam("provider") String provider,
                                                   @RequestParam("amount") Double amount,
                                                   @RequestParam("status") Status status,
                                                   @RequestParam("observation") String observation,
                                                   @RequestParam("start") @DateTimeFormat(pattern="yyyy-MM-dd'T'HH:mm:ss.SSS'Z'") Date start,
                                                   @RequestParam("end") @DateTimeFormat(pattern="yyyy-MM-dd'T'HH:mm:ss.SSS'Z'") Date end,
                                                   @RequestParam("payment_method") PaymentMethod paymentMethod) {

        return eventService.saveEvent(file, provider, amount, status, observation, start, end, paymentMethod);
    }

    @PutMapping("/{id}")
    public Event updateEvent(@PathVariable String id, @RequestBody Event event) {
        return eventService.updateEvent(id, event);
    }

    @DeleteMapping("/{id}")
    public void deleteEvent(@PathVariable String id) {
        eventService.deleteEvent(id);
    }
}

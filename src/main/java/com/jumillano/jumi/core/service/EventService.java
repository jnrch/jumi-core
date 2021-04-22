package com.jumillano.jumi.core.service;

import com.jumillano.jumi.core.model.dao.IEventDao;
import com.jumillano.jumi.core.model.entity.Event;
import com.jumillano.jumi.core.model.entity.EventResponse;
import com.jumillano.jumi.core.model.entity.User;
import com.jumillano.jumi.core.model.enums.PaymentMethod;
import com.jumillano.jumi.core.model.enums.Status;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

@Service
public class EventService {

    private final static Logger logger = LoggerFactory.getLogger(EventService.class);

    private IEventDao eventDao;
    private IUploadFileService uploadFileService;

    @Autowired
    public EventService(IEventDao eventDao, IUploadFileService uploadFileService) {
        this.eventDao = eventDao;
        this.uploadFileService = uploadFileService;
    }

    public List<Event> findAll() {
        return eventDao.findAll();
    }

    public Optional<Event> findById(String id) {
       return eventDao.findById(id);
    }

    public ResponseEntity<EventResponse> saveEvent(MultipartFile[] files, String provider, Double amount, Status status, String observation, Date start, Date end, PaymentMethod paymentMethod) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        String userId = userDetails.getId();
        String fileName;
        ArrayList<String> filesName = new ArrayList<>();

        User user = new User(userId);
        Event event = new Event();

        event.setUser(user);
        event.setProvider(provider);
        event.setAmount(amount);
        event.setStatus(status);
        event.setObservation(observation);
        event.setStart(start);
        event.setEnd(end);
        event.setPaymentMethod(paymentMethod);

        try {
            for (MultipartFile file: files) {
               fileName = uploadFileService.copy(file);
               filesName.add(fileName);
            }
        } catch (IOException e) {
            logger.error("Could not upload file: " + files, e);
            throw new RuntimeException(e);
        }
        event.setFile(filesName);

        EventResponse eventResponse = new EventResponse();
        eventResponse.setEvent(event);
        eventResponse.setOk(true);

        eventDao.save(event);

        return new ResponseEntity<>(eventResponse, HttpStatus.CREATED);
    }

    public ResponseEntity<EventResponse> updateEvent(String id, MultipartFile[] files, String provider, Double amount, Status status, String observation, Date start, Date end, PaymentMethod paymentMethod) {
        Optional<Event> currentEvent = eventDao.findById(id);
        String fileName;
        ArrayList<String> filesName = new ArrayList<>();
        Event event = new Event();

        event.setId(currentEvent.get().getId());
        event.setProvider(provider);
        event.setAmount(amount);
        event.setStatus(status);
        event.setObservation(observation);
        event.setStart(start);
        event.setEnd(end);
        event.setPaymentMethod(paymentMethod);
        event.setUser(currentEvent.get().getUser());
        try {
            for (MultipartFile file: files) {
                fileName = uploadFileService.copy(file);
                filesName.add(fileName);
            }
        } catch (IOException e) {
            logger.error("Could not upload file: " + files, e);
            throw new RuntimeException(e);
        }
        event.setFile(filesName);

        EventResponse eventResponse = new EventResponse();
        eventResponse.setEvent(event);
        eventResponse.setOk(true);

        eventDao.save(event);

        return new ResponseEntity<>(eventResponse, HttpStatus.ACCEPTED);
    }

    public void deleteEvent(String id) {
        eventDao.deleteById(id);
    }
}

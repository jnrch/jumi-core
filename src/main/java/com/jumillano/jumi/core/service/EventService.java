package com.jumillano.jumi.core.service;

import com.jumillano.jumi.core.model.dao.IEventDao;
import com.jumillano.jumi.core.model.entity.Event;
import com.jumillano.jumi.core.model.entity.User;
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

    public List<Event> findAll(String observation) {
        return (observation == null) ? eventDao.findAll() : eventDao.findByObservationContaining(observation);
    }

    public Optional<Event> findById(String id) {
       return eventDao.findById(id);
    }

    public ResponseEntity<Event> saveEvent(MultipartFile[] files, String provider, Double amount, Status status, String observation, Date start, Date end) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        String userId = userDetails.getId();
        String fileName;
        ArrayList<String> filesName = new ArrayList<>();

        User user = new User();
        user.setId(userId);

        Event event = new Event();
        event.setUser(user);

        event.setProvider(provider);
        event.setAmount(amount);
        event.setStatus(status);
        event.setObservation(observation);
        event.setStart(start);
        event.setEnd(end);

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
        eventDao.save(event);

        return new ResponseEntity<>(event, HttpStatus.CREATED);
    }

    public Event updateEvent(String id, Event event) {
        Optional<Event> currentEvent = eventDao.findById(id);

        event.setId(currentEvent.get().getId());
        eventDao.save(event);

        return event;
    }

    public void deleteEvent(String id) {
        eventDao.deleteById(id);
    }
}

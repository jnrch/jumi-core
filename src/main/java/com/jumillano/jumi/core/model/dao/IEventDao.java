package com.jumillano.jumi.core.model.dao;

import com.jumillano.jumi.core.model.entity.Event;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface IEventDao extends MongoRepository<Event, String> {

    List<Event> findByObservationContaining(String observation);
}

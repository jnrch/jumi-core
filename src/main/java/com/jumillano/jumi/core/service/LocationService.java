package com.jumillano.jumi.core.service;

import com.jumillano.jumi.core.model.dao.ILocationDao;
import com.jumillano.jumi.core.model.entity.Location;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LocationService {

    private Logger log = LoggerFactory.getLogger(this.getClass());
    private ILocationDao locationDao;

    @Autowired
    public LocationService(ILocationDao locationDao) {
        this.locationDao = locationDao;
    }

    public List<Location> findAll() {
        return locationDao.findAll();
    }

    public Optional<Location> findById(String id) {
        return locationDao.findById(id);
    }

    public Location saveLocation(Location location) {
        return locationDao.save(location);
    }

    public Location updateLocation(String id, Location location) {
        Optional<Location> currentLocation = locationDao.findById(id);

        location.setId(new ObjectId(String.valueOf(currentLocation.get().getId())));

        locationDao.save(location);

        return location;
    }

    public void deleteLocation(String id) {
        locationDao.deleteById(id);
    }
}

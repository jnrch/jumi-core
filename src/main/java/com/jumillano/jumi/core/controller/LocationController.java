package com.jumillano.jumi.core.controller;

import com.jumillano.jumi.core.model.entity.Location;
import com.jumillano.jumi.core.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping("/locations")
@RestController
public class LocationController {

    private LocationService locationService;

    @Autowired
    public LocationController(LocationService locationService) {
        this.locationService = locationService;
    }

    @GetMapping
    public List<Location> findAll() {
        return locationService.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Location> findById(@PathVariable String id) {
        return locationService.findById(id);
    }

    @PostMapping
    public Location saveLocation(@RequestBody Location location) {
        return locationService.saveLocation(location);
    }

    @PutMapping("/{id}")
    public Location updateLocation(@PathVariable String id, @RequestBody Location location) {
        return locationService.updateLocation(id, location);
    }

    @DeleteMapping("/{id}")
    public void deleteLocation(@PathVariable String id) {
        locationService.deleteLocation(id);
    }
}

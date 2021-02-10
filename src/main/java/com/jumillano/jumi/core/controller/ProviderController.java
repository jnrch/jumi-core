package com.jumillano.jumi.core.controller;

import com.jumillano.jumi.core.model.entity.Provider;
import com.jumillano.jumi.core.service.ProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api/providers")
@RestController
public class ProviderController {

    private ProviderService providerService;

    @Autowired
    public ProviderController(ProviderService providerService) {
        this.providerService = providerService;
    }

    @GetMapping
    @PreAuthorize("hasRole('USER') or hasRole('LEADER') or hasRole('ADMIN')")
    public List<Provider> findAll(@RequestParam(required = false) String name) {
        return providerService.findAll(name);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('LEADER') or hasRole('ADMIN')")
    public Optional<Provider> findById(@PathVariable String id) {
        return providerService.findById(id);
    }

    @PostMapping
    @PreAuthorize("hasRole('LEADER') or hasRole('ADMIN')")
    public Provider saveProvider(@RequestBody Provider provider) {
        providerService.saveProvider(provider);
        return provider;
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('LEADER') or hasRole('ADMIN')")
    public Provider updateProvider(@PathVariable String id, @RequestBody Provider provider) {
        return providerService.updateProvider(id, provider);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteProvider(@PathVariable String id) {
        providerService.deleteProvider(id);
    }
}

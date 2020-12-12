package com.jumillano.jumi.core.controller;

import com.jumillano.jumi.core.model.entity.Provider;
import com.jumillano.jumi.core.service.ProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping("/providers")
@RestController
public class ProviderController {

    private ProviderService providerService;

    @Autowired
    public ProviderController(ProviderService providerService) {
        this.providerService = providerService;
    }

    @GetMapping
    public List<Provider> findAll() {
        return providerService.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Provider> findById(@PathVariable Long id) {
        return providerService.findById(id);
    }

    @PostMapping
    public Provider saveProvider(@RequestBody Provider provider) {
        providerService.saveProvider(provider);
        return provider;
    }

    @PutMapping("/{id}")
    public Provider updateProvider(@PathVariable Long id, @RequestBody Provider provider) {
        return providerService.updateProvider(id, provider);
    }

    @DeleteMapping("/{id}")
    public void deleteProvider(@PathVariable Long id) {
        providerService.deleteProvider(id);
    }
}

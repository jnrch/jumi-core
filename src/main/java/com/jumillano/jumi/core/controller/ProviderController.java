package com.jumillano.jumi.core.controller;

import com.jumillano.jumi.core.model.entity.Provider;
import com.jumillano.jumi.core.service.ProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
    public Provider saveProvider(Provider provider, @RequestParam(value = "file", required = false) MultipartFile[] files) {
        providerService.saveProvider(provider, files);
        return provider;
    }

    @PutMapping("/{id}")
    public Provider updateProvider(Provider provider, @PathVariable String id, @RequestParam(value = "file", required = false) MultipartFile[] files) {
        return providerService.updateProvider(id, provider, files);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteProvider(@PathVariable String id) {
        providerService.deleteProvider(id);
    }

    @DeleteMapping("/file/{fileName:.+}/provider/{id}")
    public Optional<Provider> deleteFile(@PathVariable String fileName, @PathVariable String id) {
        return providerService.deleteFile(fileName, id);
    }
}

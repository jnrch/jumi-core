package com.jumillano.jumi.core.service;

import com.jumillano.jumi.core.model.dao.IProviderRepository;
import com.jumillano.jumi.core.model.entity.Provider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProviderService {

    private Logger log = LoggerFactory.getLogger(this.getClass());
    private IProviderRepository iProviderRepository;

    public ProviderService(IProviderRepository iProviderRepository) {
        this.iProviderRepository = iProviderRepository;
    }

    public List<Provider> findAll() {
        return iProviderRepository.findAll();
    }

    public Optional<Provider> findById(Long id) {
        return iProviderRepository.findById(id);
    }

    public Provider saveProvider(Provider provider) {
        return iProviderRepository.save(provider);
    }

    public Provider updateProvider(Long id, Provider provider) {
        return iProviderRepository.save(provider);
    }

    public void deleteProvider(Long id) {
        iProviderRepository.deleteById(id);
    }

}

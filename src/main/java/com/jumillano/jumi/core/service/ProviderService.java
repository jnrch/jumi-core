package com.jumillano.jumi.core.service;

import com.jumillano.jumi.core.model.dao.IProviderDao;
import com.jumillano.jumi.core.model.entity.Provider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProviderService {

    private Logger log = LoggerFactory.getLogger(this.getClass());
    private IProviderDao providerDao;

    public ProviderService(IProviderDao iProviderDao) {
        this.providerDao = iProviderDao;
    }

    public List<Provider> findAll(String name) {

        List<Provider> providers;

        if (name == null) {
            providers = providerDao.findAll();
        } else {
            providers = providerDao.findByNameContaining(name);
        }
        return providers;
    }

    public Optional<Provider> findById(String id) {
        return providerDao.findById(id);
    }

    public Provider saveProvider(Provider provider) {
        return providerDao.save(provider);
    }

    public Provider updateProvider(String id, Provider provider) {

        Optional<Provider> currentProvider = findById(id);

        //provider.setId(new ObjectId(String.valueOf(currentProvider.get().getId())));
        provider.setId(currentProvider.get().getId());
        providerDao.save(provider);

        return provider;
    }

    public void deleteProvider(String id) {
        providerDao.deleteById(id);
    }

}

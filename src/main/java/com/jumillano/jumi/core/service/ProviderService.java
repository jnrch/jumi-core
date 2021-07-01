package com.jumillano.jumi.core.service;

import com.jumillano.jumi.core.exceptions.ResourceNotFoundException;
import com.jumillano.jumi.core.model.dao.IProviderDao;
import com.jumillano.jumi.core.model.entity.AdministrativeContact;
import com.jumillano.jumi.core.model.entity.Provider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProviderService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private IProviderDao providerDao;
    private IUploadFileService uploadFileService;

    public ProviderService(IProviderDao iProviderDao, IUploadFileService uploadFileService) {
        this.providerDao = iProviderDao;
        this.uploadFileService = uploadFileService;
    }

    public List<Provider> findAll(String name) {

        List<Provider> providers;

        if (name == null) {
            providers = providerDao.findAll();
        } else {
            providers = providerDao.findByNameContaining(name);
        }

        for (Provider provider: providers) {
            if (provider.getAdministrativeContact() == null) {
                AdministrativeContact administrativeContact = new AdministrativeContact(null,null,null);
                provider.setAdministrativeContact(administrativeContact);
            }
        }
        return providers;
    }

    public Optional<Provider> findById(String id) {
        return providerDao.findById(id);
    }

    public Provider saveProvider(Provider provider, MultipartFile[] files) {

        String fileName;
        ArrayList<String> filesName = new ArrayList<>();

        if (files != null) {
            try {
                for (MultipartFile file: files) {
                    fileName = uploadFileService.copy(file);
                    filesName.add(fileName);
                }
            } catch (IOException e) {
                logger.error("Could not upload file: " + files, e);
                throw new RuntimeException(e);
            }
        }

        provider.setFiles(filesName);
        providerDao.save(provider);

        return provider;
    }

    public Provider updateProvider(String id, Provider provider, MultipartFile[] files) {

        Optional<Provider> currentProvider = findById(id);
        if(!currentProvider.isPresent()) throw new ResourceNotFoundException();

        String fileName;
        List<String> filesName = currentProvider.get().getFiles();

        if (files != null) {
            try {
                for (MultipartFile file: files) {
                    fileName = uploadFileService.copy(file);
                    filesName.add(fileName);
                }
            } catch (IOException e) {
                logger.error("Could not upload file: " + files, e);
                throw new RuntimeException(e);
            }
        }

        provider.setId(currentProvider.get().getId());
        provider.setFiles(filesName);
        providerDao.save(provider);

        return provider;
    }

    public void deleteProvider(String id) {
        providerDao.deleteById(id);
    }

    public Optional<Provider> deleteFile(String fileName, String id) {

        Optional<Provider> currentProvider = providerDao.findById(id);

        if(!currentProvider.isPresent()) throw new ResourceNotFoundException();
        uploadFileService.delete(fileName);
        List<String> files = currentProvider.get().getFiles();
        files.remove(fileName);

        currentProvider.get().setFiles(files);
        providerDao.save(currentProvider.get());

        return currentProvider;
    }
}

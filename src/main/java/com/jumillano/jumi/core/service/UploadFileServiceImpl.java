package com.jumillano.jumi.core.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class UploadFileServiceImpl implements IUploadFileService{

    private final Logger log = LoggerFactory.getLogger(UploadFileServiceImpl.class);
    private final static String UPLOAD_DIRECTORY = "uploads";

    @Override
    public String copy(MultipartFile file) throws IOException {
        String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename().replace(" ", "");

        Path filePath = getPath(fileName);
        log.info(filePath.toString());

        Files.copy(file.getInputStream(), filePath);

        return fileName;
    }

    @Override
    public Path getPath(String fileName) {
        return Paths.get(UPLOAD_DIRECTORY).resolve(fileName).toAbsolutePath();
    }
}

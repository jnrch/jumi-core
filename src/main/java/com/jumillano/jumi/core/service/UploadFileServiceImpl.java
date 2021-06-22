package com.jumillano.jumi.core.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class UploadFileServiceImpl implements IUploadFileService{

    private final Logger log = LoggerFactory.getLogger(UploadFileServiceImpl.class);
    private final static String UPLOAD_DIRECTORY = "uploads";


    @Override
    public Resource load(String fileName) throws MalformedURLException {
        Path pathFile = getPath(fileName);
        log.info(pathFile.toString());

        Resource resource = new UrlResource((pathFile.toUri()));

        if (!resource.exists() && !resource.isReadable()) {
            pathFile = Paths.get("uploads").resolve("").toAbsolutePath();
            resource = new UrlResource(pathFile.toUri());
            log.error("File can not be load: " + fileName);
        }

        return resource;

    }

    @Override
    public String copy(MultipartFile file) throws IOException {
        String fileName = UUID.randomUUID().getMostSignificantBits() + "_" + file.getOriginalFilename().replace(" ", "");

        Path filePath = getPath(fileName);
        log.info(filePath.toString());

        Files.copy(file.getInputStream(), filePath);

        return fileName;
    }

    @Override
    public Path getPath(String fileName) {
        return Paths.get(UPLOAD_DIRECTORY).resolve(fileName).toAbsolutePath();
    }

    @Override
    public boolean delete(String fileName) {
        if (fileName != null && fileName.length() > 0) {
            Path pathFileBefore = Paths.get("uploads").resolve(fileName).toAbsolutePath();
            File fileBefore = pathFileBefore.toFile();
            if (fileBefore.exists() && fileBefore.canRead()) {
                fileBefore.delete();
                return true;
            }
        }
        return false;
    }
}

package com.jumillano.jumi.core.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Path;

public interface IUploadFileService {

    public Resource load(String fileName) throws MalformedURLException;
    public String copy(MultipartFile file) throws IOException;
    public Path getPath(String fileName);
    public boolean delete(String fileName);
}

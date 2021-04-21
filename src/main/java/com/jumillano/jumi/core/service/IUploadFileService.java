package com.jumillano.jumi.core.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;

public interface IUploadFileService {

    public String copy(MultipartFile file) throws IOException;
    public Path getPath(String fileName);
}

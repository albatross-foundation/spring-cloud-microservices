package com.instagram.media.service.impl;

import com.instagram.media.domain.ImageResponse;
import com.instagram.media.exception.InvalidFileException;
import com.instagram.media.exception.InvalidFileNameException;
import com.instagram.media.exception.StorageException;
import com.instagram.media.service.FileStorageService;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Objects;
import java.util.UUID;

@Service
public class FileStorageServiceImpl implements FileStorageService {

    @Value("${file.upload-dir}")
    private String uploadDirectory;
    @Value("${file.path.prefix}")
    private String filePAthPrefix;
    private final Environment environment;

    public FileStorageServiceImpl(Environment environment) {
        this.environment = environment;
    }

    @Override
    public ImageResponse store(MultipartFile file, String userName) {
        String fileName;
        if (Objects.nonNull(file)) {
            fileName = StringUtils.cleanPath(file.getOriginalFilename());
        } else {
            throw new InvalidFileException("File is invalid");
        }
        try {
            if (file.isEmpty()) {
                throw new InvalidFileException("Failed to store empty file " + fileName);
            }
            if (fileName.contains("..")) {
                throw new InvalidFileNameException("Cannot store file with relative path outside current directory");
            }

            String extension = FilenameUtils.getExtension(fileName);
            String newFileName = UUID.randomUUID() + "." + extension;

            try (InputStream inputStream = file.getInputStream()) {
                Path userDir = Paths.get(uploadDirectory + userName);
                if (Files.notExists(userDir)) {
                    Files.createDirectory(userDir);
                }
                Files.copy(inputStream, userDir.resolve(newFileName), StandardCopyOption.REPLACE_EXISTING);
            }
            String port = environment.getProperty("local.server.port");
            String hostName = InetAddress.getLocalHost().getHostName();
            String fileUrl = String.format("http://%s:%s%s/%s/%s", hostName, port, filePAthPrefix, userName, newFileName);
            return new ImageResponse(newFileName, fileUrl, file.getContentType());
        } catch (IOException e) {
            throw new StorageException("Failed to store file " + fileName, e);
        }
    }
}

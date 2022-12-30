package com.kamilabiyev.ecommerce.service.utils.file;

import com.kamilabiyev.ecommerce.domain.exception.InvalidFileException;
import com.kamilabiyev.ecommerce.domain.property.FileProperties;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.util.UUID;

@Slf4j
@Component
@RequiredArgsConstructor
public class FileUtil {

    private final FileProperties fileProperties;

    @SneakyThrows
    public String upload(MultipartFile multipartFile) {
        if (multipartFile.isEmpty())
            throw new InvalidFileException("File is required.");
        try {
            File f = new File(fileProperties.getUploadFolder());
            if (!f.exists() || !f.isDirectory()) f.mkdirs();

            String fileName = UUID.randomUUID() + multipartFile.getOriginalFilename();
            String pathName = fileProperties.getUploadFolder() + fileName;
            File file = new File(pathName);
            try (FileOutputStream fos = new FileOutputStream(file)) {
                fos.write(multipartFile.getBytes());
            }
            return fileName;
        } catch (Exception ex) {
            log.error(ex.getMessage());
            throw new InvalidFileException(ex.getMessage());
        }
    }

    public void delete(String filePath) {
        var file = new File(fileProperties.getUploadFolder() + filePath);
        if (file.exists()) file.delete();
    }
}

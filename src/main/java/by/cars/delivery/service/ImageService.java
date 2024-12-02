package by.cars.delivery.service;

import by.cars.delivery.errors.FileExtensionException;
import by.cars.delivery.errors.FileUploadException;
import by.cars.delivery.model.ImageEntity;
import by.cars.delivery.repository.ImageRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ImageService {

    private final ImageRepository imageRepository;
    private final String  UPLOAD_DIR = "/Users/mihail/Downloads/delivery/uploads";

    private final String[] IMAGE_EXTENSIONS = new String[]{"jpg", "jpeg", "png"};

    public List<ImageEntity> saveImage(List<MultipartFile> images) {
        List<ImageEntity> imageEntities = new ArrayList<>();

        // Создание директории, если она не существует
        File uploadDir = new File(UPLOAD_DIR);
        if (!uploadDir.exists()) {
            if (uploadDir.mkdirs()) {
                log.info("Директория для загрузки создана: {}", UPLOAD_DIR);
            } else {
                log.error("Не удалось создать директорию: {}", UPLOAD_DIR);
                throw new FileUploadException("Ошибка при создании директории для загрузки");
            }
        }

        for (MultipartFile file : images) {
            log.info("start saving image");
            String extension = FilenameUtils.getExtension(file.getOriginalFilename());
            String baseName = FilenameUtils.getBaseName(file.getOriginalFilename()).replaceAll(" ", "_");

            if (!Arrays.stream(IMAGE_EXTENSIONS).anyMatch(extension::equalsIgnoreCase)) {
                throw new FileExtensionException("неверный формат файла, должен быть только: jpg, jpeg или png");
            }

            String uniqueFilename = createUniqueFilename(baseName, extension);
            File destinationFile = new File(UPLOAD_DIR, uniqueFilename);

            try {
                file.transferTo(destinationFile);
            } catch (IOException e) {
                log.error("smth went wrong while file saving");
                throw new FileUploadException("ошибка загрузки файла");
            }
            ImageEntity entity = new ImageEntity();
            entity.setPathToFile("/uploads/" + uniqueFilename);
            imageEntities.add(entity);
        }
        return imageRepository.saveAll(imageEntities);
    }


    private  String createUniqueFilename(String baseName, String extension) {

        String timestamp = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
        return baseName + "_" + timestamp + "." + extension;
    }

}

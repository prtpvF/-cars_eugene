package by.cars.delivery.service;

import by.cars.delivery.model.CarEntity;
import by.cars.delivery.model.ImageEntity;
import by.cars.delivery.repository.CarRepository;
import by.cars.delivery.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class AdminService {

    private final UserService userService;
    private final UserRepository userRepository;
    private final ImageService imageService;
    private final CarRepository carRepository;

    public void createCar(CarEntity car, List<MultipartFile> images) {
        List<ImageEntity> carImages = imageService.saveImage(images);
        car.setImages(carImages);
        carRepository.save(car);
    }
}

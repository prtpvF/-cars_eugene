package by.cars.delivery.service;

import by.cars.delivery.dto.CarFormDto;
import by.cars.delivery.model.CarEntity;
import by.cars.delivery.model.ImageEntity;
import by.cars.delivery.repository.CarRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class CarService {

        private final CarRepository carRepository;
        private final ImageService imageService;
        private final ModelMapper modelMapper;

        @Transactional
        public void createCar(CarFormDto dto) {
                CarEntity carEntity = modelMapper.map(dto, CarEntity.class);
                carEntity.setImages( imageService.saveImage(dto.getFiles()));
                carRepository.save(carEntity);
        }

        public Page<CarFormDto> getAllCars(Pageable pageable) {
                Page<CarEntity> allCars = carRepository.findAll(pageable);
                return allCars.map(this::convertCarToDto);
        }

        private CarFormDto convertCarToDto(CarEntity carEntity) {
                CarFormDto dto = modelMapper.map(carEntity, CarFormDto.class);

                List<String> paths = carEntity.getImages().stream()
                        .map(ImageEntity::getPathToFile)
                        .collect(Collectors.toList());

                dto.setPathToFiles(paths);
                return dto;
        }
}

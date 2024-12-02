package by.cars.delivery.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter
@Setter
public class CarFormDto {
    @Min(value = 0, message = "неверный объем")
    private Double volume;
    @Min(value = 0, message = "пробег не может быть меньше 0")
    private double mileage;
    @Min(value = 1940, message = "год выпуска не может быть меньше 1940")
    @Max(value = 2025, message = "год не может быть больше чем 2025")
    private int year;
    @NotBlank
    private String condition;
    @NotBlank
    private String color;
    @NotBlank
    private String brand;
    @NotBlank
    private String model;
    @Min(value = 0, message = "цена должна быть положительной")
    private double price;
    @NotBlank
    private String transmissionType;

    private String description;

    @NotNull(message = "загрузите как минимум 1 фото")
    private List<MultipartFile> files;

    private List<String> pathToFiles;
}
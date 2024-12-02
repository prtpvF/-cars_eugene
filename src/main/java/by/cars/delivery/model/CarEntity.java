package by.cars.delivery.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "car")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CarEntity {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private int id;
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
        @ManyToMany
        @JoinTable(
                name="car_image",
                joinColumns = @JoinColumn(name = "car_id"),
                inverseJoinColumns = @JoinColumn(name = "image_id")
        )
        private List<ImageEntity> images = new ArrayList<>();
}

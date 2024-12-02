package by.cars.delivery.controller;

import by.cars.delivery.dto.CarFormDto;
import by.cars.delivery.errors.FileExtensionException;
import by.cars.delivery.model.CarEntity;
import by.cars.delivery.service.CarService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/car")
@RequiredArgsConstructor
public class CarController {

        private final CarService carService;

        @GetMapping("/")
        public String carCreatePage(Model model) {
                model.addAttribute("car", new CarFormDto());
                return "car/createPage";
        }

        @PostMapping("/create")
        public String createCar( @ModelAttribute("car") @Valid CarFormDto carFormDto,
                                 BindingResult result,
                                 Model model) {
                if (result.hasErrors()) {
                        result.getAllErrors().forEach(error -> System.out.println(error.getDefaultMessage() ));
                        return "/car/createPage";
                }
               try {
                       carService.createCar(carFormDto);
               } catch (FileExtensionException ex) {
                   model.addAttribute("fileUploadError", ex.getMessage());
                   return "/car/createPage";
               }
                return "redirect:/success";
        }

        @GetMapping("/all")
        public String getAllCars(Pageable pageable, Model model) {
            Page<CarFormDto> carPage = carService.getAllCars(pageable);
            model.addAttribute("carPage", carPage);
            return "/car/all";
        }
}

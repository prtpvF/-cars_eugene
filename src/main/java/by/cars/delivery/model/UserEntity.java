package by.cars.delivery.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "user_entity")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private int id;

        @Email(message = "Email должен быть корректным")
        @NotBlank(message = "Email не может быть пустым")
        private String email;

        @NotBlank(message = "Имя не может быть пустым")
        @Size(min = 2, max = 30, message = "Имя должно содержать от 2 до 30 символов")
        private String firstName;

        @NotBlank(message = "Фамилия не может быть пустой")
        @Size(min = 2, max = 30, message = "Фамилия должна содержать от 2 до 30 символов")
        private String lastName;

        private String role;

        @NotBlank(message = "Номер телефона не может быть пустым")
        @Pattern(regexp = "^\\+?[0-9]{10,15}$", message = "Номер телефона должен содержать только цифры и может начинаться с '+'")
        private String username;

        @NotBlank(message = "Пароль не может быть пустым")
        @Size(min = 8, message = "Пароль должен содержать не менее 8 символов")
        private String password;
}

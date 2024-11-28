package by.cars.delivery.service;

import by.cars.delivery.dto.UserDto;
import by.cars.delivery.errors.CredentialsAlreadyTakenException;
import by.cars.delivery.model.UserEntity;
import by.cars.delivery.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class AuthService {

    private final UserRepository userRepository;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    public void registration(UserEntity user) {
        isPersonCredentialsTaken(user);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole("ROLE_USER");
        userRepository.save(user);
    }

//    public UserEntity login(String phone, String password) {
//        Optional<UserEntity> user = userRepository.findByPhone(phone);
//
//        if (!user.isEmpty() || passwordEncoder.matches(password, user.get().getPassword())) {
//           return user.get();
//        }
//        throw new IllegalArgumentException("неверные логин или пароль");
//    }

    private void isPersonCredentialsTaken(UserEntity user) {
        Optional<UserEntity> foundedUser = userRepository.findByUsernameOrEmail(user.getUsername(), user.getEmail());
        if (foundedUser.isPresent()) {
            throw new CredentialsAlreadyTakenException("почта или телефон уже заняты.");

        }

    }
}

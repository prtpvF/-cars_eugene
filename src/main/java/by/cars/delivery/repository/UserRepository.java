package by.cars.delivery.repository;

import by.cars.delivery.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {

        Optional<UserEntity> findByUsername(String username);
}

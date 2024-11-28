package by.cars.delivery.repository;

import by.cars.delivery.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {

        Optional<UserEntity> findByUsername(String username);

        @Query("SELECT u FROM UserEntity u WHERE " +
                "(u.username = :username AND :username IS NOT NULL AND LENGTH(:username) > 0) OR " +
                "(u.email = :email AND :email IS NOT NULL AND LENGTH(:email) > 0)")
        Optional<UserEntity> findByUsernameOrEmail(@Param("username") String username,
                                                @Param("email") String email);
}

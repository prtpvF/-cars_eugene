package by.cars.delivery.repository;

import by.cars.delivery.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {

        Optional<UserEntity> findByPhone(String phone);

        @Query("SELECT u FROM UserEntity u WHERE " +
                "(u.phone = :phone AND :phone IS NOT NULL AND LENGTH(:phone) > 0) OR " +
                "(u.email = :email AND :email IS NOT NULL AND LENGTH(:email) > 0)")
        Optional<UserEntity> findByEmailOrPhone(@Param("email") String email,
                                                @Param("phone") String phone);
}

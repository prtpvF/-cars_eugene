package by.cars.delivery.repository;

import by.cars.delivery.model.BlackListTokenEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlackListTokenRepository extends JpaRepository<BlackListTokenEntity, Integer> {
}

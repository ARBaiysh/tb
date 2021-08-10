package kg.baiysh.personneltesting.repository;

import kg.baiysh.personneltesting.entity.Personnel;
import kg.baiysh.personneltesting.entity.Position;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PositionRepository extends JpaRepository<Position, String> {
    Boolean existsByPositionName(String name);
    Optional<Position> findUserByPositionName(String positionName);
}

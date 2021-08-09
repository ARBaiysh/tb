package kg.baiysh.personneltesting.repository;

import kg.baiysh.personneltesting.entity.Position;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PositionRepository extends JpaRepository<Position, String> {
}

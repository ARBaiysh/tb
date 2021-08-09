package kg.baiysh.personneltesting.repository;

import kg.baiysh.personneltesting.entity.Personnel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonnelRepository extends JpaRepository<Personnel, String> {
    Boolean existsByPersonnelNumber(int personnelNumber);
}

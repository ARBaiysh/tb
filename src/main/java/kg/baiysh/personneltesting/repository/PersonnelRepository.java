package kg.baiysh.personneltesting.repository;

import kg.baiysh.personneltesting.entity.Personnel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PersonnelRepository extends JpaRepository<Personnel, String> {

    Boolean existsByPersonnelNumber(int personnelNumber);

    Optional<Personnel> findUserByPersonnelName(String personnelName);
}

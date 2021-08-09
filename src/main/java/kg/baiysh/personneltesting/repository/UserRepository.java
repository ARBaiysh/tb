package kg.baiysh.personneltesting.repository;


import kg.baiysh.personneltesting.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    Optional<User> findUserByUsername(String username);

    Optional<User> findUserByEmail(String email);

    Optional<User> findById (String email);

    boolean existsUserByEmailOrUsername(String email, String username);

}

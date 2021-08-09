package kg.baiysh.personneltesting.repository;


import kg.baiysh.personneltesting.entity.ImageModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ImageRepository extends JpaRepository<ImageModel, String> {

}

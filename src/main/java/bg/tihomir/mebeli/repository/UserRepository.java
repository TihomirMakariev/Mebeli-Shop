package bg.tihomir.mebeli.repository;

import bg.tihomir.mebeli.model.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findByEmail(String username);

    Optional<Object> findAllByEmail(String username);
}

package bg.tihomir.mebeli.repository;

import bg.tihomir.mebeli.model.entity.UserRoleEntity;
import bg.tihomir.mebeli.model.enums.UserRoleEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRoleEntity, Long> {

    UserRoleEntity findByUserRole(UserRoleEnum roleEnum);

}

package bg.tihomir.mebeli.repository;

import bg.tihomir.mebeli.model.entity.LivingRoomEntity;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface LivingRoomRepository extends
        JpaRepository<LivingRoomEntity, Long>,
        JpaSpecificationExecutor<LivingRoomEntity> {

}

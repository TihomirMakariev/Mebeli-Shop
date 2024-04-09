package bg.tihomir.mebeli.repository;

import bg.tihomir.mebeli.model.entity.KitchenEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface KitchenRepository extends
        JpaRepository<KitchenEntity, Long>,
        JpaSpecificationExecutor<KitchenEntity> {
}
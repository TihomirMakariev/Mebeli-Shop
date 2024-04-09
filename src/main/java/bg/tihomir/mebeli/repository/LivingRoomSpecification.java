package bg.tihomir.mebeli.repository;

import bg.tihomir.mebeli.model.dto.binding.SearchLivingRoomDTO;
import bg.tihomir.mebeli.model.entity.LivingRoomEntity;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class LivingRoomSpecification implements Specification<LivingRoomEntity> {


    private final SearchLivingRoomDTO searchLivingRoomDTO;

    public LivingRoomSpecification(SearchLivingRoomDTO searchLivingRoomDTO) {
        this.searchLivingRoomDTO = searchLivingRoomDTO;
    }


    @Override
    public Predicate toPredicate(Root root,
                                 CriteriaQuery query,
                                 CriteriaBuilder cb) {
        Predicate p = cb.conjunction();

        if (searchLivingRoomDTO.getColor() != null) {
            p.getExpressions().add(
                    cb.and(cb.equal(root.get("color"), searchLivingRoomDTO.getColor()))
            );
        }

        if (searchLivingRoomDTO.getMinPrice() != null) {
            p.getExpressions().add(
                    cb.and(cb.greaterThanOrEqualTo(root.get("price"), searchLivingRoomDTO.getMinPrice()))
            );
        }

        if (searchLivingRoomDTO.getMaxPrice() != null) {
            p.getExpressions().add(
                    cb.and(cb.lessThanOrEqualTo(root.get("price"), searchLivingRoomDTO.getMaxPrice()))
            );
        }

        return p;
    }


}

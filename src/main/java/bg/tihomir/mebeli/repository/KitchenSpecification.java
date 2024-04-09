package bg.tihomir.mebeli.repository;

import bg.tihomir.mebeli.model.dto.binding.SearchKitchenDTO;
import bg.tihomir.mebeli.model.entity.KitchenEntity;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class KitchenSpecification implements Specification<KitchenEntity> {


    private final SearchKitchenDTO searchKitchenDTO;

    public KitchenSpecification(SearchKitchenDTO searchKitchenDTO) {
        this.searchKitchenDTO = searchKitchenDTO;
    }

    @Override
    public Predicate toPredicate(Root<KitchenEntity> root,
                                 CriteriaQuery<?> query,
                                 CriteriaBuilder cb) {


        Predicate p = cb.conjunction();

        if (searchKitchenDTO.getColor() != null) {
            p.getExpressions().add(
                    cb.and(cb.equal(root.get("color"), searchKitchenDTO.getColor()))
            );
        }

        if (searchKitchenDTO.getMinPrice() != null){
            p.getExpressions().add(
                    cb.and(cb.greaterThanOrEqualTo(root.get("price"), searchKitchenDTO.getMinPrice()))
            );
        }

        if (searchKitchenDTO.getMaxPrice() !=null) {
            p.getExpressions().add(
                    cb.and(cb.greaterThanOrEqualTo(root.get("price"), searchKitchenDTO.getMaxPrice()))
            );
        }



        return p;
    }


}

package org.example.quanlytuyendung.specification;

import jakarta.persistence.criteria.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
public class BaseSpecification<T> implements Specification<T> {
    private final Map<String, Object> filters;

    @Override
    public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = new ArrayList<>();

        if (filters != null) {
            for (Map.Entry<String, Object> entry : filters.entrySet()) {
                String field = entry.getKey();
                Object value = entry.getValue();

                if (value instanceof String) {
                    predicates.add(criteriaBuilder.like(root.get(field), "%" + value + "%"));
                } else {
                    predicates.add(criteriaBuilder.equal(root.get(field), value));
                }
            }
        }

        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }
}

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

        if (filters != null && !filters.isEmpty()) {
            for (Map.Entry<String, Object> entry : filters.entrySet()) {
                String field = entry.getKey();
                Object value = entry.getValue();

                if (value instanceof String stringValue && !stringValue.isEmpty()) {
                    String likeValue = "%" + stringValue.toLowerCase() + "%";
                    predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get(field)), likeValue));
                } else if (value != null) {
                    predicates.add(criteriaBuilder.equal(root.get(field), value));
                }
            }
        }


        return predicates.isEmpty() ? criteriaBuilder.conjunction() : criteriaBuilder.or(predicates.toArray(new Predicate[0]));
    }

}

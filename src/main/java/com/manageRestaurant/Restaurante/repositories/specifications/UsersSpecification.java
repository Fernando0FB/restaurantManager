package com.manageRestaurant.Restaurante.repositories.specifications;

import com.manageRestaurant.Restaurante.models.UsersModel;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class UsersSpecification {
    public static Specification<UsersModel> columnEqual(Map<String, String> filters) {
        return (Root<UsersModel> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            filters.forEach((key, value) -> {
                predicates.add(criteriaBuilder.equal(root.get(key), value));
            });

            predicates.add(criteriaBuilder.equal(root.get("deleted"), Boolean.FALSE));
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}

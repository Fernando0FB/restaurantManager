package com.manageRestaurant.Restaurante.repositories.specifications;

import com.manageRestaurant.Restaurante.models.CustomersModel;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CustomerSpecification {
    public static Specification<CustomersModel> columnEqual(Map<String, String> filters) {
        return (Root<CustomersModel> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            filters.forEach((key, value) -> {
                switch (key) {
                    case "name" -> predicates.add(criteriaBuilder.like(root.get(key), value + "%"));
                    case "cpf" -> predicates.add(criteriaBuilder.equal(root.get(key), value + "%"));
                    default -> predicates.add(criteriaBuilder.equal(root.get(key), value));
                }
            });

            predicates.add(criteriaBuilder.equal(root.get("deleted"), Boolean.FALSE));
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}

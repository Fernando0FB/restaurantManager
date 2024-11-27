package com.manageRestaurant.Restaurante.repositories.specifications;

import com.manageRestaurant.Restaurante.models.ReservationModel;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ReservationSpecification {

    public static Specification<ReservationModel> columnEqual(Map<String, String> filters) {
        return (Root<ReservationModel> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            filters.forEach((key, value) -> {
                switch (key) {
                    case "finalDate" -> predicates.add(getDatePredicate(root, criteriaBuilder, "date", value, false));
                    case "initialDate" -> predicates.add(getDatePredicate(root, criteriaBuilder, "date", value, true));
                    case "initialTime" -> predicates.add(getTimeOverlapPredicate(root, criteriaBuilder, value, true));
                    case "finalTime" -> predicates.add(getTimeOverlapPredicate(root, criteriaBuilder, value, false));
                    case "date" -> predicates.add(getExactDatePredicate(root, criteriaBuilder, "date", value));
                    case "time" -> predicates.add(getExactTimePredicate(root, criteriaBuilder, value));
                    case "customerId" -> predicates.add(criteriaBuilder.equal(root.get("customer").get("id"), Long.valueOf(value)));
                    default -> predicates.add(criteriaBuilder.equal(root.get(key), value));
                }
            });
            predicates.add(criteriaBuilder.equal(root.get("deleted"), Boolean.FALSE));
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }

    private static Predicate getDatePredicate(Root<ReservationModel> root, CriteriaBuilder criteriaBuilder, String column, String value, boolean isStart) {
        LocalDate date = LocalDate.parse(value);
        if (isStart) {
            return criteriaBuilder.greaterThan(root.get(column), date);
        } else {
            return criteriaBuilder.lessThan(root.get(column), date);
        }
    }

    private static Predicate getTimeOverlapPredicate(Root<ReservationModel> root, CriteriaBuilder criteriaBuilder, String value, boolean isInitialTime) {
        LocalTime time = LocalTime.parse(value);

        if (isInitialTime) {
            return criteriaBuilder.or(
                    criteriaBuilder.and(
                            criteriaBuilder.lessThan(root.get("initialTime"), time),
                            criteriaBuilder.greaterThan(root.get("finalTime"), time)
                    ),
                    criteriaBuilder.greaterThanOrEqualTo(root.get("initialTime"), time)
            );
        } else {
            return criteriaBuilder.or(
                    criteriaBuilder.and(
                            criteriaBuilder.greaterThan(root.get("finalTime"), time),
                            criteriaBuilder.lessThan(root.get("initialTime"), time)
                    ),
                    criteriaBuilder.lessThan(root.get("initialTime"), time)
            );
        }
    }

    private static Predicate getExactDatePredicate(Root<ReservationModel> root, CriteriaBuilder criteriaBuilder, String column, String value) {
        LocalDate date = LocalDate.parse(value);
        return criteriaBuilder.equal(root.get(column), date);
    }

    private static Predicate getExactTimePredicate(Root<ReservationModel> root, CriteriaBuilder criteriaBuilder, String value) {
        LocalTime time = LocalTime.parse(value);
        return criteriaBuilder.and(
                criteriaBuilder.lessThanOrEqualTo(root.get("initialTime"), time),
                criteriaBuilder.greaterThan(root.get("finalTime"), time)
        );
    }
}

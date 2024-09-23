package com.student.Api.repository;
// In StudentSpecifications.java
import org.springframework.data.jpa.domain.Specification;
import com.student.Api.entity.Student;

public class StudentSpecifications {
    public static Specification<Student> hasFirstName(String firstName) {
        return (root, query, criteriaBuilder) ->
                firstName == null ? null : criteriaBuilder.like(root.get("first_Name"), "%" + firstName + "%");
    }

    public static Specification<Student> hasLastName(String lastName) {
        return (root, query, criteriaBuilder) ->
                lastName == null ? null : criteriaBuilder.like(root.get("last_Name"), "%" + lastName + "%");
    }

    public static Specification<Student> hasGender(String gender) {
        return (root, query, criteriaBuilder) ->
                gender == null ? null : criteriaBuilder.equal(root.get("gender"), gender);
    }

    public static Specification<Student> hasAgeBetween(Integer minAge, Integer maxAge) {
        return (root, query, criteriaBuilder) ->
                (minAge == null || maxAge == null) ? null : criteriaBuilder.between(root.get("age"), minAge, maxAge);
    }
}
